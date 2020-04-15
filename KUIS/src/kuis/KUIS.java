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
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

class KUIS {
    public static void main(String[] args) {
        Menu g = new Menu();
    } 
}

class Menu extends JFrame {
    JLabel menu = new JLabel("MENU UTAMA");
    JButton tombolAnggota = new JButton("Anggota");
    JButton tombolKaryawan = new JButton("Karyawan");
    JButton tombolBuku = new JButton("Buku");
    JButton tombolPeminjam = new JButton("Peminjam");
    
    public Menu() {
    
        setTitle("BERANDA");
        setDefaultCloseOperation(3);
        setSize(350,250);
        setLocation(500,275);
        setLayout(null);
        
        add(menu);
        add(tombolAnggota);
        add(tombolKaryawan);
        add(tombolBuku);
        add(tombolPeminjam);

        menu.setBounds(110,10,120,20);
        menu.setFont(new Font("",Font.CENTER_BASELINE, 15));
        tombolAnggota.setBounds(20,50,120,40);
        tombolKaryawan.setBounds(20,100,120,40);
        tombolBuku.setBounds(180,50,120,40);
        tombolPeminjam.setBounds(180,100,120,40);
    
        tombolAnggota.addActionListener((ActionEvent e) -> {
            anggota a = new anggota();
            dispose();
        });
        tombolKaryawan.addActionListener((ActionEvent e) -> {
            karyawan b = new karyawan();
            dispose();
        });
        tombolBuku.addActionListener((ActionEvent e) -> {
            buku c = new buku();
            dispose();
        });
        tombolPeminjam.addActionListener((ActionEvent e) -> {
            peminjam d = new peminjam();
            dispose();
        });
        
        setVisible(true);
    }
    
}
