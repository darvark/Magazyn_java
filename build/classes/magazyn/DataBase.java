/**
 * Klasa odpowiedzialna na podstawowe połączenie z bazą danych, tutaj jest
 * konfiguracja połączenia, połączenie wstępne, tworzenie bazy danych.
 */
package magazyn;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.BatchUpdateException;

/**
 *
 * @author Marcin Iwaniuk
 */
public class DataBase {

    static String url;// = "jdbc:mysql://localhost:3306";
    static String baza;// = "projekt";
    static String login;// = "root";
    static String password;// = "root";

    /**
     * Zmienne przechowujące konfigurację połączenia z bazą
     *
     * @param adres adres URL w postaci jdbc:mysql://localhost:3306
     * @param baza_d nazwa uzywanej bazy danych
     * @param user użytkownik
     * @param haslo hasło
     */
    public void DB_conf(String adres, String baza_d, String user, String haslo) {
        url = adres;
        baza = baza_d;
        login = user;
        password = haslo;
    }

    /**
     * Wykonuje połączenie z bazą danych
     *
     * @return Stringbędący wynikiem próby połączenia
     */
    public String DB_connect() {
        String wyn;
        try {
            Connection conn = DriverManager.getConnection(url, login, password);
            Statement st = conn.createStatement();
            wyn = "Połączenie ustanowione";
        } catch (SQLException e) {
            wyn = "Uwaga! Mamy problemy z połączeniem! " + e;
        }
        return wyn;
    }

    /**
     * Tworzy bazę danych.
     *
     * @return String będący wynikiem zapytania SQL
     */
    public String DB_utworz_baze() {
        String wynik;
        try {
            Connection conn = DriverManager.getConnection(url, login, password);
            try {
                Statement st = conn.createStatement();
                st.execute("CREATE DATABASE " + baza + ";");
                wynik = "Utworzono bazę";
                return wynik;
            } catch (SQLException e) {
                wynik = "Uwaga! Baza już istnieje " + e;
                return wynik;
            }
        } catch (SQLException f) {
            wynik = "Uwaga! Mamy problemy z połączeniem " + f;
            return wynik;
        }
    }

    /**
     * @deprecated @param baza
     */
    public void DB_usun_baze(String baza) {
        try {
            Connection conn = DriverManager.getConnection(url, login, password);
            Statement st = conn.createStatement();
            try {
                st.execute("DROP DATABASE " + baza);
                System.out.println("Baza usunięta");
            } catch (SQLException e) {
                System.out.println("Uwaga! Baza nie istnieje " + e);
            }
        } catch (SQLException f) {
            System.out.println("Uwaga! Mamy problemy z połączeniem " + f);
        }
    }

    /**
     * Tworzy tabele w bazie danych
     *
     * @return String z informacją czy tabele zostały poprawnie utworzone
     */
    public String DB_create_table() {
        String wynik;
        try {
            Connection conn = DriverManager.getConnection(url, login, password);
            Statement st = conn.createStatement();
            try {
                st.execute("USE " + baza + ";");
                st.addBatch("CREATE TABLE  artykul (id_a INT (5) NOT NULL AUTO_INCREMENT,PRIMARY KEY (id_a),ean_a INT (9) UNIQUE,polka_a INT (3),regal_a INT (3),ilosc_a INT (4),nazwa_a VARCHAR (40),jednostka_a VARCHAR (20));");

                st.addBatch("CREATE TABLE  kontrachent (id_k INT(4) NOT NULL AUTO_INCREMENT,PRIMARY KEY(id_k),nip_k BIGINT(12) UNIQUE,regon_k INT(12) UNIQUE,nazwa_k VARCHAR(100),miasto_k VARCHAR (20),kod_k VARCHAR (6),ulica_k VARCHAR (20),nr_domu_k INT(3),nr_lok_k INT(3));");

//                st.addBatch("CREATE TABLE dostawca (id_d INT(4) NOT NULL AUTO_INCREMENT,PRIMARY KEY(id_d),nazwa_d varchar (30),nip_d INT(12),miasto_k VARCHAR (20),kod_d VARCHAR (6),ulica_d VARCHAR (20),nr_domu_d INT(3),nr_lok_d INT(3));");

                st.addBatch("CREATE TABLE  pz (id_p INT(4) NOT NULL AUTO_INCREMENT,PRIMARY KEY(id_p),od_kogo_p INT(4),FOREIGN KEY (od_kogo_p) REFERENCES kontrachent(id_k), przedmiot_p INT(5),FOREIGN KEY (przedmiot_p) REFERENCES artykul(id_a),ilosc_p INT(4), nr_dok_p INT (5));");

                st.addBatch("CREATE TABLE  wz (id_w INT(4) NOT NULL AUTO_INCREMENT,PRIMARY KEY(id_w),do_kogo_w INT(4),FOREIGN KEY (do_kogo_w) REFERENCES kontrachent(id_k),przedmiot_w INT(5),FOREIGN KEY (przedmiot_w) REFERENCES artykul(id_a),ilosc_w INT(3), nr_dok_w INT (5));");

                st.addBatch("CREATE TABLE  zamowienie (id_z INT(5) NOT NULL AUTO_INCREMENT,PRIMARY KEY(id_z),dla_kogo_z INT (3),FOREIGN KEY (dla_kogo_z) REFERENCES kontrachent(id_k),artykul_z INT(4),FOREIGN KEY (artykul_z) REFERENCES artykul(id_a),data_zam DATE, nr_dok_z INT (5), liczba_z VARCHAR (11));");

                st.executeBatch();
                wynik = "Tabele zostały utworzone";
                return wynik;
            } catch (SQLException e) {
                wynik = "Uwaga! Tabela już istnieje " + e;
                return wynik;
            }
        } catch (SQLException f) {
            wynik = "Uwaga! Mamy problemy z połączeniem! " + f;
            return wynik;
        }
    }
}
