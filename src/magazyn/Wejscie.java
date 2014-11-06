/*
 * klasa obslugujaca Wejscie i zczytanie zanku z klawiatury.
 */
package magazyn;

/**
 * @author Marcin Iwaniuk
 */
public class Wejscie {

    public static int getInteger() //metoda obslugujaca Wejscie
	//oryginalnie getDouble, modyfikacja na zmienne typu integer
    {
        char c;
        StringBuffer buf = new StringBuffer();
        String sx;
        int x;

        //System.out.print();
        // pobierz x z konsoli jako ciag znakow
        try
        { // wiersze konczasie dwoma znakami kontrolnymi: '\r' i '\n'
            // ale w BuleJ tylko '\n'
            while ((c = (char) System.in.read() ) != '\n')
                buf.append(c);
            // przekonwertuj x z typu String na typ double
            sx = buf.toString();
            x = Integer.parseInt( sx );
        } catch (java.io.IOException e) { x = 0; };
        buf.setLength(0); // oczysc bufor
        return x;
    }

    public static String getString() //metoda obslugujaca Wejscie
	//oryginalnie getDouble, modyfikacja na zmienne typu String
    {
        char c;
        StringBuffer buf = new StringBuffer();
        String sx;
        //int x;

        //System.out.print(s);
        // pobierz x z konsoli jako ciag znakow
        try
        { // wiersze konczasie dwoma znakami kontrolnymi: '\r' i '\n'
            // ale w BuleJ tylko '\n'
            while ((c = (char) System.in.read() ) != '\n')
                buf.append(c);
            // przekonwertuj x z typu String na typ double
            sx = buf.toString();
            //x = Integer.parseInt( sx );
        } catch (java.io.IOException e) { sx = ""; };
        buf.setLength(0); // oczysc bufor
        return sx;
    }

}
