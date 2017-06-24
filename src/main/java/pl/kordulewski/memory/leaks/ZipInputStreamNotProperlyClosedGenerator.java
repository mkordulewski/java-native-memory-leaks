package pl.kordulewski.memory.leaks;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * Created by Michał Kordulewski on 2017-06-09.
 */
public class ZipInputStreamNotProperlyClosedGenerator {

    public void run() {
        InputStream inputStream = ZipInputStreamNotProperlyClosedGenerator.class.getResourceAsStream("/ZipInputStreamTest.zip");
        while ( true ) {
            new ZipInputStream(inputStream);
        }
    }
    
}
