/*
 * klasa odpowiedzialna za zarzadzanie zamowieniami na towar
 * skladanie zamowienia
 */
package magazyn;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Marcin Iwaniuk
 */
public class Zamowienie {

    String dla_kogo;
    int artykul;
    int ilosc;
    String data;
    int nr_dok;
    DataBase query = new DataBase();
    String sql;

    Zamowienie() {
        dla_kogo = "";
        artykul = 000000000;
        ilosc = 0;
        data = "";
        nr_dok = 0;

    }

    /**
     * Dodaje zamówienie do bazy danych
     *
     * @param kto_nip wbrew pozorom to muner ID klienta
     * @param ean numer ID przedmiotu
     * @param data data zamówienia format rrrr-mm-dd
     * @param nr_dokum nr dokuemntu zamówienia
     * @param liczba liczba przedmiotów danego EANu
     * @return String z info czy dodano zamówienie
     */
    public String dodaj_zamowienie(int kto_nip, int ean, String data, int nr_dokum, int liczba) {
        String wynik = "a";
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            sql = "INSERT INTO `zamowienie` (`dla_kogo_z`, `artykul_z`, `data_zam`, `nr_dok_z`, `liczba_z`) VALUES ('" + kto_nip + "','" + ean + "','" + data + "','" + nr_dokum + "','" + liczba + "');";
            try {
                st.executeQuery("USE " + DataBase.baza);
                st.executeUpdate(sql);
                wynik = "Dodano dokument PZ";
                return wynik;
            } catch (SQLException e) {
                wynik = "Uwaga! Błąd zapytania " + e;
                return wynik;
            }
        } catch (SQLException f) {
            wynik = "Uwaga! Mamy problemy z połączeniem! " + f;
            return wynik;
        }
    }

    /**
     * Wyszukuje zamowienie po jego numerze
     *
     * @param nr_d
     * @return info o zawartości zamówienia
     */
    public String szukaj_zamowienia(int nr_d) {
        String wynik = "a";
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            sql = "SELECT `nazwa_k`,`nazwa_a`,`ean_a`,`liczba_z`,`nr_dok_z` FROM `zamowienie`, `kontrachent`, `artykul` WHERE `nr_dok_z`='" + nr_d + "' AND `dla_kogo_z` = `id_k` AND `artykul_z`=`id_a`;";
            try {
                st.execute("USE " + DataBase.baza);
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    String kogo = rs.getString("nazwa_k");
                    String co = rs.getString("nazwa_a");
                    int ean = Integer.parseInt(rs.getString("ean_a"));
                    String liczba = rs.getString("liczba_z");
                    String dokum = rs.getString("nr_dok_z");
                    wynik = ("Zamówienie:\nKontrachent: " + kogo + "\nPrzedmiot: " + co + "\nIlość: " + liczba + "\nNr dokumentu: " + dokum);
                    return wynik;
                }
                return wynik;
            } catch (SQLException e) {
                wynik = "Uwaga! Błąd zapytania " + e;
                return wynik;
            }
        } catch (SQLException f) {
            wynik = "Uwaga! Mamy problemy z połączeniem! " + f;
            return wynik;
        }
    }

    /**
     * Wyszukuje zamowienie po numerze klienta
     *
     * @param nip
     * @return info o zawartości zamówienia
     */
    public String szukaj_zamowienia_nip(int nip) {
        String wynik = "a";
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            sql = "SELECT `nazwa_k`,`nazwa_a`,`ean_a`,`liczba_z`,`nr_dok_z` FROM `zamowienie`, `kontrachent`, `artykul` WHERE `id_k`='" + nip + "'AND `dla_kogo_z` = `id_k` AND `artykul_z`=`id_a`;";
            try {
                st.execute("USE " + DataBase.baza);
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    String kogo = rs.getString("nazwa_k");
                    String co = rs.getString("nazwa_a");
                    int ean = Integer.parseInt(rs.getString("ean_a"));
                    String liczba = rs.getString("liczba_z");
                    String dokum = rs.getString("nr_dok_z");
                    wynik = ("Zamówienie:\nKontrachent: " + kogo + "\nPrzedmiot: " + co + "\nIlość: " + liczba + "\nNr dokumentu: " + dokum);
                    return wynik;
                }
                return wynik;
            } catch (SQLException e) {
                wynik = "Uwaga! Błąd zapytania " + e;
                return wynik;

            }
        } catch (SQLException f) {
            wynik = "Uwaga! Mamy problemy z połączeniem! " + f;
            return wynik;
        }
    }

    /**
     *
     * @param data Wyszukuje zamowienie po dacie
     * @return info o zawartości zamówienia
     */
    public String szukaj_zamowienia(String data) {
        String wynik = "a";
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            sql = "SELECT `nazwa_k`,`nazwa_a`,`ean_a`,`liczba_z`,`nr_dok_z` FROM `zamowienie`, `kontrachent`, `artykul` WHERE `data_zam`='" + data + "';";
            try {
                st.execute("USE " + DataBase.baza);
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    String kogo = rs.getString("nazwa_k");
                    String co = rs.getString("nazwa_a");
                    int ean = Integer.parseInt(rs.getString("ean_a"));
                    String liczba = rs.getString("liczba_z");
                    String dokum = rs.getString("nr_dok_z");
                    wynik = ("Zamówienie:\nKontrachent: " + kogo + "\nPrzedmiot: " + co + "\nIlość: " + liczba + "\nNr dokumentu: " + dokum);
                    return wynik;
                }
                return wynik;
            } catch (SQLException e) {
                wynik = "Uwaga! Błąd zapytania " + e;
                return wynik;

            }
        } catch (SQLException f) {
            wynik = "Uwaga! Mamy problemy z połączeniem! " + f;
            return wynik;
        }
    }
}
