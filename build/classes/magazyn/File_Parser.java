/*
 * klasa odpowiedzialna za parsowanie odczytanych plikow
 */
package magazyn;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import javax.xml.*;

/**
 *
 * @author darvark
 */
public class File_Parser {

    /**
     * @throws FileNotFoundException
     */
    public void parse() throws FileNotFoundException {
        File_Parser parser = new File_Parser();
        parser.processLineByLine();
    }

    /**
     * @throws FileNotFoundException
     */
    public final void processLineByLine() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("db.cf"));
        try {
            //while (scanner.hasNextLine()) {
            DataBase.url = processLine(scanner.nextLine());
            DataBase.baza = processLine(scanner.nextLine());
            DataBase.login = processLine(scanner.nextLine());
            DataBase.password = processLine(scanner.nextLine());
        } finally {
            scanner.close();
        }
    }

    /**
     * Parsuje do postaci klucz:wartość
     *
     * @param aLine
     * @return
     */
    protected String processLine(String aLine) {
        Scanner scanner = new Scanner(aLine);
        scanner.useDelimiter(" ");
        if (scanner.hasNext()) {
            String name = scanner.next();
            String value = scanner.next();
            return value;

        } else {
            System.out.println("brak lini");
        }
        return "";
    }
}
