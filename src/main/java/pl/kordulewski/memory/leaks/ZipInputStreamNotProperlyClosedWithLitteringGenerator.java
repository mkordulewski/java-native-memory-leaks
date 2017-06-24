package pl.kordulewski.memory.leaks;

import java.io.InputStream;
import java.util.Date;
import java.util.zip.ZipInputStream;

/**
 * Created by Michał Kordulewski on 2017-06-09.
 */
public class ZipInputStreamNotProperlyClosedWithLitteringGenerator {

    public void run() {
        InputStream inputStream = ZipInputStreamNotProperlyClosedWithLitteringGenerator.class.getResourceAsStream("/ZipInputStreamTest.zip");
        while ( true ) {
            new ZipInputStream(inputStream);
            // ZipInputStream object is not properly closed
            // but littering is a workaround
            String s = "" + new Date();
        }
    }
    
}
