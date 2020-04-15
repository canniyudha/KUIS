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

public class karyawan extends JFrame{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/prak_pbo";
    static final String USER = "root";
    static final String PASS = "";
    
    Connection koneksi;
    Statement statement;
    
    JLabel judul = new JLabel("DATA KARYAWAN"); 
    JLabel lNIK= new JLabel("NIK");
    JTextField tfNIK = new JTextField();
    JLabel lNama = new JLabel("Nama Karyawan");
    JTextField tfNama = new JTextField();
    JLabel lJk = new JLabel("Jenis Kelamin");
    JRadioButton rbPria = new JRadioButton(" Laki-Laki ");
    JRadioButton rbWanita = new JRadioButton(" Perempuan ");
    JLabel lGolongan = new JLabel("Gologan");
    JTextField tfGolongan = new JTextField();
    JLabel lJabatan = new JLabel("Jabatan");
    JLabel tfJabatan = new JLabel("-");
    JLabel lTunjangan = new JLabel("Tunjangan");
    JLabel tfTunjangan = new JLabel("-");
    JLabel lGaji = new JLabel("Gaji");
    JLabel tfGaji = new JLabel("-");

    JButton btnSearch = new JButton("Search");
    JButton btnRefershPanel = new JButton("Refesh");
    JButton btnCreatePanel = new JButton("Create");
    JButton btnDeletePanel = new JButton("Delete");
    JButton btnExitPanel = new JButton("Exit");

    JTable tabel;
    DefaultTableModel tabelModel;
    JScrollPane scrollPane;
    Object namaKolom[] = {"NIK","Nama Karyawan","Jenis Kelamin","Golongan","Jabatan","Tunjangan","Total Gaji"};
   
   public karyawan(){
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
        add(lNIK);
        lNIK.setBounds(10,300,90,20);
        add(tfNIK);
        tfNIK.setBounds(120,300,120,20);
        add(lNama);
        lNama.setBounds(10,320,90,20);
        add(tfNama);
        tfNama.setBounds(120,320,120,20);
        add(lJk);
        lJk.setBounds(10,340,120,20);
        add(rbPria);
	rbPria.setBounds(130,340,100,20);
        add(rbWanita);
	rbWanita.setBounds(230,340,100,20);
        add(lGolongan);
        lGolongan.setBounds(10,360,150,20);
        add(tfGolongan);
	tfGolongan.setBounds(120,360,150,20);
        add(lJabatan);
        lJabatan.setBounds(10,380,90,20);
        add(tfJabatan);
        tfJabatan.setBounds(120,380,120,20);
        add(lTunjangan);
        lTunjangan.setBounds(10,400,90,20);
        add(tfTunjangan);
        tfTunjangan.setBounds(120,400,120,20);
        add(lGaji);
        lGaji.setBounds(10,420,90,20);
        add(tfGaji);
        tfGaji.setBounds(120,420,120,20);

        add(btnSearch);
        btnSearch.setBounds(500,450,100,50);
        add(btnRefershPanel);
        btnRefershPanel.setBounds(400,300,100,50);
        add(btnCreatePanel);
        btnCreatePanel.setBounds(400,370,100,50);
        add(btnDeletePanel);
        btnDeletePanel.setBounds(600,300,100,50);
        add(btnExitPanel);
        btnExitPanel.setBounds(600,370,100,50);

        add(scrollPane);
        scrollPane.setBounds(110,50,600,200);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
   
       
        String Golongan = tfGolongan.getText();
int Upah=0;
int Tunjangan=0;
String Jabatan = null;
                if (Golongan == "1") {
                    Jabatan = "Manager";
                    Tunjangan = 700000;
                    Upah = 1000000;
                } else  if (Golongan == "2") {
                     Jabatan = "Supervisor";
                     Tunjangan = 50000;
                     Upah = 900000;
                } else if (Golongan == "3"){
                     Jabatan = "Staff";
                     Tunjangan = 300000;
                     Upah = 800000;
                } else if(Golongan == "4"){
                    Jabatan = "ass Staff";
                    Tunjangan = 250000;
                    Upah = 750000;
                } else if (Golongan == "5"){
                    Jabatan = "Honorer";
                    Tunjangan = 200000;
                    Upah = 650000;
                }
    
                int Gaji = Upah+Tunjangan;
                
                tfJabatan.setText(Jabatan);
                tfTunjangan.setText(Integer.toString(Tunjangan));
                tfGaji.setText(Integer.toString(Gaji));

if (this.getBanyakData() != 0) {
            String dataKaryawan[][] = this.readKaryawan();
            tabel.setModel((new JTable(dataKaryawan, namaKolom)).getModel());
        } else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }
btnCreatePanel.addActionListener((ActionEvent e) -> {
            if (tfNIK.getText().equals("") ) {
                JOptionPane.showMessageDialog(null, "Field tidak boleh kosong");
            } else {
                String nik = tfNIK.getText();
                String nama = tfNama.getText();
                String jk = null;
                if (rbWanita.isSelected()) {
                    jk = "perempuan";
                } else if (rbPria.isSelected() ) {
                    jk = "Laki laki";
                }
                String golongan = tfGolongan.getText();
                String jabatan = tfJabatan.getText();
                String tunjangan = tfTunjangan.getText();
                String gaji = tfGaji.getText();
                
                
                this.insertKaryawan(nik, nama, jk, golongan, jabatan, tunjangan, gaji);
                String dataKaryawan[][] = readKaryawan();
                tabel.setModel(new JTable(dataKaryawan,namaKolom).getModel());
            }
        });


tabel.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e){ 
               int baris = tabel.getSelectedRow();
               int kolom = tabel.getSelectedColumn(); 
               String dataterpilih = tabel.getValueAt(baris, 0).toString();
               btnDeletePanel.addActionListener((ActionEvent f) -> {
                  deleteKaryawan(dataterpilih);
                  String dataKaryawan[][] = readKaryawan();
                tabel.setModel(new JTable(dataKaryawan,namaKolom).getModel());
                });
               
           }
        });


btnRefershPanel.addActionListener((ActionEvent e) -> {
          tfNIK.setText("");
          tfNama.setText("");
          tfGolongan.setText("");
          tfJabatan.setText("");
          tfTunjangan.setText("");
          tfGaji.setText("");
          
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
            String query = "SELECT * from `karyawan`";
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

String[][] readKaryawan() {
        try{
            int jmlData = 0;
            String data[][]=new String[getBanyakData()][7];
            String query = "Select * from `karyawan`";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                data[jmlData][0] = resultSet.getString("NIK");
                data[jmlData][1] = resultSet.getString("Nama");
                data[jmlData][2] = resultSet.getString("Jk");
                data[jmlData][3] = resultSet.getString("Golongan");
                data[jmlData][4] = resultSet.getString("Jabatan");
                data[jmlData][5] = resultSet.getString("Tunjangan");
                data[jmlData][6] = resultSet.getString("Gaji");
                
                jmlData++;
            }
            return data;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL error");
            return null;
        }
    }

public void insertKaryawan(String nik, String nama, String jk, String golongan, String jabatan, String tunjangan, String gaji) {
        try{
            String query = "INSERT INTO `karyawan`(`NIK`,`Nama`,`Jk`,`Golongan`,`Jabatan`,`Tunjangan`,`Gaji`) VALUES ('"+nik+"','"+nama+"','"+jk+"', '"+golongan+"','"+jabatan+"','"+tunjangan+"','"+gaji+"')";
        statement = (Statement) koneksi.createStatement();
        statement.executeUpdate(query);
        JOptionPane.showMessageDialog(null,"Data berhasil ditambahkan");
        }catch(Exception sql){
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
        
    }

void deleteKaryawan(String nik) {
        try{
            String query = "DELETE FROM `karyawan` WHERE `NIK` = '"+nik+"'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "berhasil dihapus" );
        }catch(SQLException sql){
            System.out.println(sql.getMessage());
        }
    }

}