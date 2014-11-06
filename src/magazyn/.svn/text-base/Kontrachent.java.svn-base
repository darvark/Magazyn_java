/*
 * klasa odpowiedzialna za przechowywanie informacji o kontrachentach
 * ich saldzie i zamowieniach
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
public class Kontrachent {

    private String nazwa;
    private String adres;
    private long NIP;
    private int regon;
    DataBase query = new DataBase();
    String sql;
//konstruktor

    Kontrachent() {
        nazwa = "";
        adres = "";
        NIP = 0000000000;
        regon = 000000;
    }

    /**
     * Dodaje kontrachenta do bazy danych
     *
     * @param NIP Numer identyfikacji podatkowej
     * @param regon REGON
     * @param nazwa nazwa kontarchenta
     * @param ulica ulica adresu korenspondencyjnego
     * @param miasto miasto adresu korenspondencyjnego
     * @param kod kod pocztowy
     * @param nr_dom nr domu/posiadłości
     * @param nr_lok nr lokalu
     * @return String z wynikiem zapytania SQL
     */
    public String dodaj_kontrachent(long NIP, int regon, String nazwa, String ulica, String miasto, String kod, int nr_dom, int nr_lok) {
        String wynik;
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            sql = "INSERT INTO `kontrachent` (`nip_k`,`regon_k`,`nazwa_k`,`miasto_k`,`ulica_k`,`kod_k`,`nr_domu_k`,`nr_lok_k`) VALUES ('" + NIP + "','" + regon + "','" + nazwa + "','" + miasto + "','" + ulica + "','" + kod + "','" + nr_dom + "','" + nr_lok + "');";
            try {
                st.executeQuery("USE " + DataBase.baza);
                st.executeUpdate(sql);
                wynik = "Dodano Kontrachenta";
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
     * Wydobywa informacje o kontrachencie na podstawie NIPu
     *
     * @param NIP
     * @return Kompeltne dane kontrachenta
     */
    public String get_kontrachent(long NIP) {
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            try {
                st.execute("USE " + DataBase.baza);
                ResultSet rs = st.executeQuery("SELECT * FROM `kontrachent` WHERE `nip_k` = '" + NIP + "'");
                while (rs.next()) {
                    String name = rs.getString("nazwa_k");
                    String nip = rs.getString("nip_k");
                    String regon = rs.getString("regon_k");
                    String miasto = rs.getString("miasto_k");
                    String ulica = rs.getString("ulica_k");
                    String kod = rs.getString("kod_k");
                    String dom = rs.getString("nr_domu_k");
                    String lokal = rs.getString("nr_lok_k");
                    String out = ("Kontrachent: " + name + "\nNIP: " + nip + "\nRegon: " + regon + "\nMiasto: " + miasto + "\nUlica: " + ulica + "\tKod: " + kod + "\nNr domu: " + dom + "\tNr mieszkania: " + lokal + "\n");
                    return out;
                }
            } catch (SQLException e) {
                System.out.println("Uwaga! Problem z wczytaniem danych " + e);
            }
        } catch (SQLException f) {
            System.out.println("Uwaga! Mamy problemy z połączeniem! " + f);
        }
        return "Brak wyszukań";
    }

    /**
     * Wydobywa informacje o kontrachencie na podstawie nazwy
     *
     * @param nazwa
     * @return Kompletne dane kontrachenta
     */
    public String get_kontrachent(String nazwa) {
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            try {
                st.execute("USE " + DataBase.baza);
                ResultSet rs = st.executeQuery("SELECT * FROM `kontrachent` WHERE `nazwa_k` LIKE '" + nazwa + "'");
                while (rs.next()) {
                    String name = rs.getString("nazwa_k");
                    String nip = rs.getString("nip_k");
                    String regon = rs.getString("regon_k");
                    String miasto = rs.getString("miasto_k");
                    String ulica = rs.getString("ulica_k");
                    String kod = rs.getString("kod_k");
                    String dom = rs.getString("nr_domu_K");
                    String lokal = rs.getString("nr_lok_k");
                    String out = ("Kontrachent: " + name + "\nNIP: " + nip + "\nRegon: " + regon + "\nMiasto: " + miasto + "\nUlica: " + ulica + "\tKod: " + kod + "\nNr domu: " + dom + "\tNr mieszkania: " + lokal + "\n");
                    return out;
                }
            } catch (SQLException e) {
                System.out.println("Uwaga! Problem z wczytaniem danych " + e);
            }
        } catch (SQLException f) {
            System.out.println("Uwaga! Mamy problemy z połączeniem! " + f);
        }
        return "Brak wyszukań";
    }
}
