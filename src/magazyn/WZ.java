/*
 * zarzadzanie dokumentami PZ (przyjecie zewnetrzne)
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
public class WZ {

    /*
     * kontrachent, od kogo, co, ile. jednoczesnie odejmuje stany z magazynu
     */
    String do_kogo;
    int przedmiot;
    int ilosc;
    int nr_dok_wz;
    DataBase query = new DataBase();
    String sql;

    /**
     * kontruktor klasy
     */
    WZ() {
        do_kogo = "";
        /**
         * @param do_kogo nazwa klineta
         */
        przedmiot = 000000000;
        /**
         * @param przedmiot kod ean towaru
         */
        ilosc = 0;
        /**
         * @param ilosc ilość sztuk towaru na dok pz towaru
         */
        nr_dok_wz = 0;
        /**
         * @param nr_dok_wz numer dokumentu WZ
         */
    }

    /**
     * DOdaje dokument WZ do bazy danych, dzieki wartosci nr_dok_wz mozna
     * dopisac wiele przedmiotow do jednego dokumentu
     *
     * @param nip numer NIP klienta
     * @param EAN numer EAN produktu
     * @param ilosc ilosc przedmiotow o danym numerze ean
     * @param nr_dok_wz numer dokumentu WZ
     * @return Strign z informacją czy udało się dodać dokument WZ
     */
    public String dodaj_wz(int nip, int EAN, int ilosc, int nr_dok_wz) {
        String wynik = "a";
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            sql = "INSERT INTO `wz` (`do_kogo_w`, `przedmiot_w`, `ilosc_w`, `nr_dok_w`) VALUES ('" + nip + "','" + EAN + "','" + ilosc + "','" + nr_dok_wz + "');";
            try {
                st.executeQuery("USE " + DataBase.baza);
                st.executeUpdate(sql);
                wynik = "Dodano dokument WZ "+nr_dok_wz;
                Artykul art = new Artykul();
                art.usun_sztuk(ilosc, EAN);
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
     * Wyszukuje dokument WZ po jego numerze
     *
     * @param nr
     * @return informacje o zawartości dokumentu WZ
     */
    public String szukaj_wz_nr(int nr) {
        String wynik = "";
        try {
            Connection conn = DriverManager.getConnection(DataBase.url, DataBase.login, DataBase.password);
            Statement st = conn.createStatement();
            sql = "SELECT `nazwa_k`,`nazwa_a`,`ean_a`,`ilosc_w`,`nr_dok_w` FROM `wz`, `kontrachent`, `artykul` WHERE `nr_dok_w`='" + nr + "'AND `do_kogo_w` = `id_k` AND `przedmiot_w` = `id_a`;";
            try {
                st.execute("USE " + DataBase.baza);
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    String kogo = rs.getString("nazwa_k");
                    String co = rs.getString("nazwa_a");
                    int ean = Integer.parseInt(rs.getString("ean_a"));
                    String liczba = rs.getString("ilosc_w");
                    String dokum = rs.getString("nr_dok_w");
                    wynik = ("PZ:\nKontrachent: " + kogo + "\nPrzedmiot: " + co + "\nIlość: " + liczba + "\nNr dokumentu: " + dokum);
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
