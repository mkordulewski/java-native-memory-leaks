package pl.kordulewski.memory.leaks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Michał Kordulewski
 * Date:   2017-06-09
 */
public class MainClass {

    public static void main(String... args) {
        InputStream input = MainClass.class.getResourceAsStream("/help.txt");
        String result = getStringFromInputStream(input);
        System.out.println(result);
        try {
            input.close();
        } catch (IOException e) {
            System.out.println("Unknown error");
        }
    }

    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            System.out.println("Unknown error");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Unknown error");
                }
            }
        }
        return sb.toString();
    }

}
