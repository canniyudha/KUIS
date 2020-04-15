/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kuis;

/**
 *
 * @author ASUS
 */
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;


public class anggota extends JFrame{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/prak_pbo";
    static final String USER = "root";
    static final String PASS = "";
    
    Connection koneksi;
    Statement statement;
    
    JLabel judul = new JLabel("DATA ANGGOTA"); 
    JLabel lNIS= new JLabel("NIS");
    JTextField tfNIS = new JTextField();
    JLabel lNama = new JLabel("Nama Siswa");
    JTextField tfNama = new JTextField();
    JLabel lTanggal = new JLabel("TTL");
    JTextField tfTanggal = new JTextField();
    JLabel lJk = new JLabel("Jenis Kelamin");
    JRadioButton rbPria = new JRadioButton(" Laki-Laki ");
    JRadioButton rbWanita = new JRadioButton(" Perempuan ");
    JLabel lAgama = new JLabel("Agama ");
    String[] namaAgama =
            {"Islam","Kristen","Katolik","Hindu","Budha"};
    JComboBox cmbAgama = new JComboBox(namaAgama);
    JLabel lKelas = new JLabel("Kelas");
    JTextField tfKelas = new JTextField();
    JLabel lDaftar = new JLabel("Tanggal Daftar");
    JTextField tfDaftar = new JTextField();
    JLabel lExp = new JLabel("Berlaku Hingga");
    JTextField tfExp = new JTextField();

    JButton btnRefershPanel = new JButton("Refesh");
    JButton btnCreatePanel = new JButton("Create");
    JButton btnDeletePanel = new JButton("Delete");
    JButton btnExitPanel = new JButton("Exit");

    JTable tabel;
    DefaultTableModel tabelModel;
    JScrollPane scrollPane;
    Object namaKolom[] = {"NIS","Nama Siswa","TTL","Jenis Kelamin","Agama","Tgl Daftar","Berlaku S/D","Kelas"};
   
   public anggota(){
        try{
            Class.forName(JDBC_DRIVER);
            koneksi = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Koneksi Berhasil");
        }catch(ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("Koneksi Gagal");
        }   
        tabelModel = new DefaultTableModel (namaKolom,0);
        tabel = new JTable(tabelModel);
        scrollPane = new JScrollPane(tabel);
       
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        setSize(850,580);
        setLocation(225,75);
        
        add(judul);
        judul.setBounds(350, 10, 200, 20);
        judul.setFont(new Font("",Font.CENTER_BASELINE, 15));
        add(lNIS);
        lNIS.setBounds(10,300,90,20);
        add(tfNIS);
        tfNIS.setBounds(120,300,120,20);
        add(lNama);
        lNama.setBounds(10,320,90,20);
        add(tfNama);
        tfNama.setBounds(120,320,120,20);
        add(lTanggal);
        lTanggal.setBounds(10,340,90,20);
        add(tfTanggal);
        tfTanggal.setBounds(120,340,120,20);
        add(lJk);
        lJk.setBounds(10,360,120,20);
        add(rbPria);
	rbPria.setBounds(130,360,100,20);
        add(rbWanita);
	rbWanita.setBounds(230,360,100,20);
        add(lAgama);
        lAgama.setBounds(10,380,150,20);
        add(cmbAgama);
	cmbAgama.setBounds(120,380,150,20);
        add(lKelas);
        lKelas.setBounds(10,400,90,20);
        add(tfKelas);
        tfKelas.setBounds(120,400,120,20);
        add(lDaftar);
        lDaftar.setBounds(10,420,90,20);
        add(tfDaftar);
        tfDaftar.setBounds(120,420,120,20);
        add(lExp);
        lExp.setBounds(10,440,90,20);
        add(tfExp);
        tfExp.setBounds(120,440,120,20);

        
        add(btnRefershPanel);
        btnRefershPanel.setBounds(400,300,100,50);
        add(btnCreatePanel);
        btnCreatePanel.setBounds(400,400,100,50);
        add(btnDeletePanel);
        btnDeletePanel.setBounds(600,300,100,50);
        add(btnExitPanel);
        btnExitPanel.setBounds(600,400,100,50);

        add(scrollPane);
        scrollPane.setBounds(110,50,600,200);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
   
       
        if (this.getBanyakData() != 0) {
            String dataAnggota[][] = this.readAnggota(); 
            tabel.setModel((new JTable(dataAnggota, namaKolom)).getModel());
        } else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }
btnCreatePanel.addActionListener((ActionEvent e) -> {
            if (tfNIS.getText().equals("") ) {
                JOptionPane.showMessageDialog(null, "Field tidak boleh kosong");
            } else {
                String nis = tfNIS.getText();
                String nama = tfNama.getText();
                String tanggal = tfTanggal.getText();
                String jk = null;
                if (rbWanita.isSelected()) {
                    jk = "perempuan";
                } else if (rbPria.isSelected() ) {
                    jk = "Laki laki";
                }
                String agama = (String) cmbAgama.getSelectedItem();
                String kelas = tfKelas.getText();
                String daftar = tfDaftar.getText();
                String exp = tfExp.getText();             
                this.insertAnggota(nis, nama, tanggal, jk, agama, daftar, exp, kelas);
                String dataAnggota[][] = this.readAnggota();
                tabel.setModel(new JTable(dataAnggota,namaKolom).getModel());
            }
        });

    tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){ 
                int baris = tabel.getSelectedRow();
                int kolom = tabel.getSelectedColumn();
                String dataterpilih = tabel.getValueAt(baris, 0).toString();
                btnDeletePanel.addActionListener((ActionEvent f) -> {
                    deleteAnggota(dataterpilih);
                    String dataAnggota[][] = readAnggota();
                 tabel.setModel(new JTable(dataAnggota,namaKolom).getModel());
                 });   
            }
        });


    btnRefershPanel.addActionListener((ActionEvent e) -> {
        tfNama.setText("");
        tfNIS.setText("");
        tfTanggal.setText("");
        tfDaftar.setText("");
        tfExp.setText("");
        tfKelas.setText("");
    });

    btnExitPanel.addActionListener((ActionEvent e) -> {
        Menu x = new Menu();
        dispose();
    });
}

int getBanyakData() {
    int jmlData = 0;
    try{
        statement = koneksi.createStatement();
        String query = "SELECT * from `anggota`";
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            jmlData++;
    }
    return jmlData;
    }catch(SQLException e){
        System.out.println(e.getMessage());
        System.out.println("SQL error");
        return 0;
    }
}

String[][] readAnggota() {
    try{
        int jmlData = 0;
            String data[][]=new String[getBanyakData()][8];
            String query = "Select * from `anggota`";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                data[jmlData][0] = resultSet.getString("NIS");
                data[jmlData][1] = resultSet.getString("Nama");
                data[jmlData][2] = resultSet.getString("Tanggal");
                data[jmlData][3] = resultSet.getString("Jk");
                data[jmlData][4] = resultSet.getString("Agama");
                data[jmlData][5] = resultSet.getString("Daftar");
                data[jmlData][6] = resultSet.getString("Exp");
                data[jmlData][7] = resultSet.getString("Kelas");
                jmlData++;
            }
            return data;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL error");
            return null;
        }
    }

public void insertAnggota(String nis, String nama, String tanggal, String jk, String agama, String daftar, String exp, String kelas) {
        try{
            String query = "INSERT INTO `anggota`(`NIS`,`Nama`,`Tanggal`,`Jk`,`Agama`,`Daftar`,`Exp`,`Kelas`) VALUES ('"+nis+"','"+nama+"','"+tanggal+"', '"+jk+"','"+agama+"','"+daftar+"','"+exp+"','"+kelas+"')";
            statement = (Statement) koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Data berhasil ditambahkan");
        }catch(Exception sql){
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
}

void deleteAnggota(String nis) {
        try{
            String query = "DELETE FROM `anggota` WHERE `NIS` = '"+nis+"'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil dihapus"+nis );
        }catch(SQLException sql){
            System.out.println(sql.getMessage());
        }
    }
}
