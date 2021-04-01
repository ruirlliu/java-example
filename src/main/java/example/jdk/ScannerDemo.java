package example.jdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author lr
 * @date 2020/10/30
 */
public class ScannerDemo {

    public static void main(String[] args) {
        System.out.println((int)' ');
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            int read = reader.read();
            System.out.println(read);
            int count = 0;
            for (char c : line.toCharArray()) {
                if ((int)c == read) {
                    count++;
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
