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


public class buku extends JFrame{
        
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/prak_pbo";
    static final String USER = "root";
    static final String PASS = "";
    
    Connection koneksi;
    Statement statement;
    
    JLabel judul = new JLabel("DATA BUKU"); 
    JLabel lKode= new JLabel("Kode Buku");
    JTextField tfKode = new JTextField();
    JLabel lNama = new JLabel("Nama Buku");
    JTextField tfNama = new JTextField();
    JLabel lPengarang = new JLabel("Nama Pengarang");
    JTextField tfPengarang = new JTextField();
    JLabel lPenerbit = new JLabel("Penerbit");
    JTextField tfPenerbit = new JTextField();
    JLabel lTahun = new JLabel("Tahun Terbit");
    JTextField tfTahun = new JTextField();

    JButton btnSearch = new JButton("Search");
    JButton btnRefershPanel = new JButton("Refesh");
    JButton btnCreatePanel = new JButton("Create");
    JButton btnDeletePanel = new JButton("Delete");
    JButton btnExitPanel = new JButton("Exit");

    JTable tabel;
    DefaultTableModel tabelModel;
    JScrollPane scrollPane;
    Object namaKolom[] = {"Kode Buku","Nama Buku","Nama Pengarang","Nama Penerbit","Tahun terbit"};
   
   public buku(){
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
        add(lKode);
        lKode.setBounds(10,300,90,20);
        add(tfKode);
        tfKode.setBounds(120,300,120,20);
        add(lNama);
        lNama.setBounds(10,320,90,20);
        add(tfNama);
        tfNama.setBounds(120,320,120,20);
        add(lPengarang);
        lPengarang.setBounds(10,340,90,20);
        add(tfPengarang);
	tfPengarang.setBounds(120,340,120,20);
        add(lPenerbit);
        lPenerbit.setBounds(10,360,90,20);
        add(tfPenerbit);
	tfPenerbit.setBounds(120,360,120,20);
        add(lTahun);
        lTahun.setBounds(10,380,90,20);
        add(tfTahun);
        tfTahun.setBounds(120,380,120,20);

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
   
       
        if (this.getBanyakData() != 0) { 
            String dataBuku[][] = this.readBuku(); 
            tabel.setModel((new JTable(dataBuku, namaKolom)).getModel());
        } else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }
btnCreatePanel.addActionListener((ActionEvent e) -> {
            if (tfKode.getText().equals("") ) {
                JOptionPane.showMessageDialog(null, "Field tidak boleh kosong");
            } else {
                String kode = tfKode.getText();
                String judul = tfNama.getText();
                String pengarang = tfPengarang.getText();
                String penerbit = tfPenerbit.getText();
                String tahun = tfTahun.getText();
    
                this.insertBuku(kode, judul, pengarang, penerbit, tahun);
  
                String dataBuku[][] = this.readBuku();
                tabel.setModel(new JTable(dataBuku,namaKolom).getModel());
            }
        });

tabel.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e){ 
               int baris = tabel.getSelectedRow();
               int kolom = tabel.getSelectedColumn(); 
               String dataterpilih = tabel.getValueAt(baris, 0).toString();
               btnDeletePanel.addActionListener((ActionEvent f) -> {
                  deleteBuku(dataterpilih);
                  String dataBuku[][] = readBuku();
                tabel.setModel(new JTable(dataBuku,namaKolom).getModel());
                });
               
           }
        });


btnRefershPanel.addActionListener((ActionEvent e) -> {
          tfKode.setText("");
          tfNama.setText("");
          tfPengarang.setText("");
          tfPenerbit.setText("");
          tfTahun.setText("");
         
          
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
            String query = "SELECT * from `buku`";
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

String[][] readBuku() {
        try{
            int jmlData = 0;
            String data[][]=new String[getBanyakData()][5];
            String query = "Select * from `buku`";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                data[jmlData][0] = resultSet.getString("Kode");
                data[jmlData][1] = resultSet.getString("Nama");
                data[jmlData][2] = resultSet.getString("Pengarang");
                data[jmlData][3] = resultSet.getString("Penerbit");
                data[jmlData][4] = resultSet.getString("Tahun");
                
                jmlData++;
            }
            return data;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL error");
            return null;
        }
    }

public void insertBuku(String kode, String judul, String pengarang, String penerbit, String tahun) {
        try{
            String query = "INSERT INTO `buku`(`Kode`,`Nama`,`Pengarang`,`Penerbit`,`Tahun`) VALUES ('"+kode+"','"+judul+"','"+pengarang+"', '"+penerbit+"','"+tahun+"')";
        statement = (Statement) koneksi.createStatement();
        statement.executeUpdate(query);
        JOptionPane.showMessageDialog(null,"data berhasil ditambahkan");
        }catch(Exception sql){
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
        
    }

void deleteBuku(String kode) {
        try{
            String query = "DELETE FROM `buku` WHERE `Kode` = '"+kode+"'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "berhasil dihapus" );
        }catch(SQLException sql){
            System.out.println(sql.getMessage());
        }
    }

}

