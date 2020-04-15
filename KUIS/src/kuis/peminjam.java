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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class peminjam extends JFrame{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/prak_pbo";
    static final String USER = "root";
    static final String PASS = "";
    
    Connection koneksi;
    Statement statement;
    
    JLabel judul = new JLabel("DATA PEMINJAM");
    JLabel lNo = new JLabel("No Pinjaman ");
    JTextField tfNo = new JTextField();
    JLabel lNIS= new JLabel("NIS");
    JTextField tfNIS = new JTextField();
    JLabel lNamaS = new JLabel("Nama Siswa");
    JLabel tfNamaS = new JLabel("-");
    JLabel lKelas = new JLabel("Kelas");
    JLabel tfKelas = new JLabel("-");
    JLabel lKode= new JLabel("Kode Buku");
    JTextField tfKode = new JTextField();
    JLabel lNamaB = new JLabel("Nama Buku");
    JLabel tfNamaB = new JLabel("-");
    JLabel lPenerbit = new JLabel("Penerbit");
    JLabel tfPenerbit = new JLabel("-");
    JLabel lPinjam = new JLabel("Tgl Pinjam");
    JTextField tfPinjam = new JTextField();
    JLabel lKembali = new JLabel("Tgl Kembali");
    JTextField tfKembali = new JTextField();
    JLabel lLama = new JLabel("Lama Pinjam");
    JLabel tfLama = new JLabel("-");
    JLabel lDenda = new JLabel("Denda");
    JLabel tfDenda = new JLabel("-");
    JLabel lNIK= new JLabel("NIK");
    JTextField tfNIK = new JTextField();
    JLabel lKaryawan = new JLabel("Karyawan");
    JLabel tfKaryawan = new JLabel("-");

    JButton btnPrint = new JButton("Print");
    JButton btnSearch = new JButton("Search");
    JButton btnRefershPanel = new JButton("Refesh");
    JButton btnCreatePanel = new JButton("Create");
    JButton btnDeletePanel = new JButton("Delete");
    JButton btnExitPanel = new JButton("Exit");

    JTable tabel;
    DefaultTableModel tabelModel;
    JScrollPane scrollPane;
    Object namaKolom[] = {"No Peminjam","NIS","Nama Siswa","Kelas","Kode Buku","Nama Buku","Penerbit","Tgl Pinjam","Tgl Kembali","Lama","Denda","Petugas","NIK"};
   
   public peminjam(){
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
        add(lNo);
        lNo.setBounds(10,260,90,20);
        add(tfNo);
        tfNo.setBounds(120,260,120,20);
        add(lNIS);
        lNIS.setBounds(10,280,90,20);
        add(tfNIS);
        tfNIS.setBounds(120,280,120,20);
        add(lNamaS);
        lNamaS.setBounds(10,300,120,20);
        add(tfNamaS);
	tfNamaS.setBounds(120,300,100,20);
        add(lKelas);
        lKelas.setBounds(10,320,150,20);
        add(tfKelas);
	tfKelas.setBounds(120,320,150,20);
        add(lKode);
        lKode.setBounds(10,340,90,20);
        add(tfKode);
        tfKode.setBounds(120,340,120,20);
        add(lNamaB);
        lNamaB.setBounds(10,360,90,20);
        add(tfNamaB);
        tfNamaB.setBounds(120,360,120,20);
        add(lPenerbit);
        lPenerbit.setBounds(10,380,90,20);
        add(tfPenerbit);
        tfPenerbit.setBounds(120,380,120,20);
        add(lPinjam);
        lPinjam.setBounds(10,400,90,20);
        add(tfPinjam);
        tfPinjam.setBounds(120,400,120,20);
        add(lKembali);
        lKembali.setBounds(10,420,90,20);
        add(tfKembali);
        tfKembali.setBounds(120,420,120,20);
        add(lLama);
        lLama.setBounds(10,440,90,20);
        add(tfLama);
        tfLama.setBounds(120,440,120,20);
        add(lDenda);
        lDenda.setBounds(10,460,90,20);
        add(tfDenda);
        tfDenda.setBounds(120,460,120,20);
        add(lNIK);
        lNIK.setBounds(10,480,90,20);
        add(tfNIK);
        tfNIK.setBounds(120,480,120,20);
        add(lKaryawan);
        lKaryawan.setBounds(10,500,90,20);
        add(tfKaryawan);
        tfKaryawan.setBounds(120,500,120,20);

        add(btnPrint);
        btnPrint.setBounds(600,450,100,50);
        add(btnSearch);
        btnSearch.setBounds(400,450,100,50);
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
   
       
if (this.getBanyakData() != 0) { 
            String dataPinjam[][] = this.readPinjam(); 
            tabel.setModel((new JTable(dataPinjam, namaKolom)).getModel());
          
        } else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }
btnCreatePanel.addActionListener((ActionEvent e) -> {
            if (tfKode.getText().equals("") ) {
                JOptionPane.showMessageDialog(null, "Field tidak boleh kosong");
            } else {
                String no = tfNo.getText();
                String nis = tfNIS.getText();
                String kode = tfKode.getText();
                String pinjam = tfPinjam.getText();
                String kembali = tfKembali.getText();
                Date tglAwal = (Date) Date.valueOf(pinjam);
                Date tglAkhir = (Date) Date.valueOf(kembali);
                long lama = Math.abs(tglAkhir.getTime() - tglAwal.getTime());
                long denda = (lama - 7)*10000; 
                String nik = tfNIK.getText();
                this.insertBuku(no, nis, kode, pinjam, kembali,lama,denda, nik);
  
                String dataPinjam[][] = this.readPinjam();
                tabel.setModel(new JTable(dataPinjam,namaKolom).getModel());
            }
        });

tabel.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e){ 
               int baris = tabel.getSelectedRow();
               int kolom = tabel.getSelectedColumn(); 
               String dataterpilih = tabel.getValueAt(baris, 0).toString();
               btnDeletePanel.addActionListener((ActionEvent f) -> {
                  deletePinjam(dataterpilih);
                  String dataPinjam[][] = readPinjam();
                tabel.setModel(new JTable(dataPinjam,namaKolom).getModel());
                });
               
           }
        });


btnRefershPanel.addActionListener((ActionEvent e) -> {
          tfNo.setText("");
          tfNIS.setText("");
          tfKode.setText("");
          tfPinjam.setText("");
          tfKembali.setText("");
          tfNIK.setText("");
          
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
            String query = "SELECT * from `peminjam`";
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

String[][] readPinjam() {
        try{
            int jmlData = 0;
            String data[][]=new String[getBanyakData()][13];
            String query = "Select `No.p`,`NIS.p`,`Nama.a`,`Kelas.a`,`Kode.b`,`Nama.b`,`Penerbit.b`,`Pinjam.p`,`Kembali.p`,`Lama.p`,`Denda.p`,`Nama.k`,`NIK.k` from `peminjam p` JOIN `buku b` ON `Kode.b`=`Kode.p` JOIN `karyawan k` ON `NIK.k`=`NIK.k` JOIN `anggota a` ON `NIS.p`=`NIS.a`";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                data[jmlData][0] = resultSet.getString("No");
                data[jmlData][1] = resultSet.getString("NIS");
                data[jmlData][2] = resultSet.getString("Nama");
                data[jmlData][3] = resultSet.getString("Kelas");
                data[jmlData][4] = resultSet.getString("Kode");
                data[jmlData][5] = resultSet.getString("Nama");
                data[jmlData][6] = resultSet.getString("Penerbit");
                data[jmlData][7] = resultSet.getString("Pinjam");
                data[jmlData][8] = resultSet.getString("Kembali");
                data[jmlData][9] = resultSet.getString("Lama");
                data[jmlData][10] = resultSet.getString("Denda");
                data[jmlData][11] = resultSet.getString("Nama");
                data[jmlData][12] = resultSet.getString("NIK");
                jmlData++;
            }
            return data;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL error");
            return null;
        }
    }

public void insertBuku(String no, String nis, String kode, String pinjam, String kembali, long lama, long denda, String nik) {
        try{
            String query = "INSERT INTO `peminjam`(`No`,`NIS`,`Kode`,`Pinjam`,`Kembali`,`Lama`,`Denda`,`NIK`) VALUES ('"+no+"','"+nis+"','"+kode+"', '"+pinjam+"','"+kembali+"','"+lama+"','"+denda+"','"+nik+"')";
        statement = (Statement) koneksi.createStatement();
        statement.executeUpdate(query);
        System.out.println("Berhasil Ditambahkan");
        JOptionPane.showMessageDialog(null,"data berhasil ditambahkan");
        }catch(Exception sql){
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
        
    }

void deletePinjam(String no) {
        try{
            String query = "DELETE FROM `peminjam` WHERE `No` = '"+no+"'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "berhasil dihapus" );
        }catch(SQLException sql){
            System.out.println(sql.getMessage());
        }
    }

}

