/*
 * klasa odpowiedzialna za obsluge odczytu i zapisu do plikow
 */
package magazyn;

import java.io.*;
/*
 * @author darvark
 */

public class IO {

    /**
     * @method zwraca wskaznik na plik
     *
     * @param nazwa nazwa pliku ktory chcemy obsluzyc
     * @return plik zwracany wskaznik
     */
    public void open_file(String nazwa) {
        File plik = new File(nazwa);
        if (plik.exists()) {
            this.read_file(nazwa);
        } //jesli nie istnieje to tworzy plik
        else {
            try {
                plik.createNewFile();

            } catch (IOException e) {
                System.err.println("Blad wejscia/wyjscia: " + e);
            }
        }

    }//koniec open_file

    /**
     * Odczytuje plik
     *
     * @param nazwa
     * @return
     */
    public String read_file(String nazwa) // dziala poprawnie
    {
        File plik = new File(nazwa);
        String s;
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(plik));
            try {

                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }

            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

    /**
     * Zapisuje ustawienia bazy danych do pliku konfiguracyjnego
     *
     * @param nazwa pliku konfiguracyjnego, lub jego bezwzgledna sciezka, kazdy
     * \ nalezy escape-owac
     * @throws IOException
     */
    void zapis(String nazwa) throws IOException {
        /**
         * @param nazwa nazwa pliku konfiguracyjnego, lub jego bezwzgledna
         * sciezka, kazdy \ nalezy escape-owac
         */
        File konf = new File(nazwa);
        FileWriter output_stream = new FileWriter(konf);
        BufferedWriter out = new BufferedWriter(output_stream);
        if (konf.exists()) {
            out.write("adres " + DataBase.url);
            out.write("\nbaza " + DataBase.baza);
            out.write("\nuser " + DataBase.login);
            out.write("\nhaslo " + DataBase.password);
            out.close();
        } else {
            konf.createNewFile();
            output_stream = new FileWriter(konf);
            out.write("adres " + DataBase.url);
            out.write("\nbaza " + DataBase.baza);
            out.write("\nuser " + DataBase.login);
            out.write("\nhaslo " + DataBase.password);
            out.close();
        }
    }
}
