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
public class Dostawca {

    private String nazwa;
    private String adres;
    private int NIP;
    private int regon;
    DataBase query = new DataBase();
    String sql;
//konstruktor

    Dostawca() {
        nazwa = "";
        adres = "";
        NIP = 0000000000;
        regon = 000000;
    }

    public String dodaj_dostawce(int NIP, int regon, String nazwa, String ulica, String miasto, String kod, int nr_dom, int nr_lok) {
        /**
         * @param NIP NIP dostawcy, moze byc null
         * @param regon regon dostawcy, moze byc null
         * @param nazwa nazwa definiujaca dostacy
         * @param adres adres dostawcy, kontaktowy.
         */
        String wynik = "a";
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            sql = "INSERT dostawca (nip_d, regon_d, nazwa_d, miasto_d, ulica_d, kod_d, nr_dom_d, nr_lok_d) VALUES ('" + NIP + "' , '" + regon + "', '" + nazwa + "','" + miasto + "','" + ulica + "','" + kod + "','" + nr_dom + "','" + nr_lok + "','" + nazwa + "');'";
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

    public String get_nazwa(int NIP) {
        /**
         * @param NIP
         */
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            try {
                st.execute("USE projekt");
                ResultSet rs = st.executeQuery("SELECT nazwa FROM dostawca WHERE nip LIKE '" + NIP + "';'");
                while (rs.next()) {
                    String name = rs.getString("nazwa_d");
                    String nip = rs.getString("nip_d");
                    String regon = rs.getString("regon_d");
                    String miasto = rs.getString("miasto_d");
                    String ulica = rs.getString("ulica_d");
                    String kod = rs.getString("kod_d");
                    String dom = rs.getString("dom_d");
                    String lokal = rs.getString("lok_d");
                    String out = ("Dostawca: " + name + "\n" + nip + "\n" + regon + "\n" + miasto + "\n" + ulica + "\t" + kod + "\n" + dom + "\t" + lokal + "\n");
                }
            } catch (SQLException e) {
                System.out.println("Uwaga! Problem z wczytaniem danych " + e);
            }
        } catch (SQLException f) {
            System.out.println("Uwaga! Mamy problemy z polaczeniem! " + f);
        }
        return "Brak wyszukań";
    }

    public String get_NIP(String nazwa) {
        /**
         * @param nazwa
         */
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            try {
                st.execute("USE projekt");
                ResultSet rs = st.executeQuery("SELECT nip FROM dostawca WHERE nazwa LIKE '" + nazwa + "';'");
                while (rs.next()) {
                    String name = rs.getString("nazwa_d");
                    String nip = rs.getString("nip_d");
                    String regon = rs.getString("regon_d");
                    String miasto = rs.getString("miasto_d");
                    String ulica = rs.getString("ulica_d");
                    String kod = rs.getString("kod_d");
                    String dom = rs.getString("dom_d");
                    String lokal = rs.getString("lok_d");
                    String out = ("Dostawca: " + name + "\n" + nip + "\n" + regon + "\n" + miasto + "\n" + ulica + "\t" + kod + "\n" + dom + "\t" + lokal + "\n");
                }
            } catch (SQLException e) {
                System.out.println("Uwaga! Problem z wczytaniem danych " + e);
            }
        } catch (SQLException f) {
            System.out.println("Uwaga! Mamy problemy z polaczeniem! " + f);
        }
        return "Brak wyszukań";
    }
}
