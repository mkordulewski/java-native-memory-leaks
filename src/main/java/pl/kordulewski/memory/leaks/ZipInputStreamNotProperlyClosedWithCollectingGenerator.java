package pl.kordulewski.memory.leaks;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * Created by Michał Kordulewski on 2017-06-09.
 */
public class ZipInputStreamNotProperlyClosedWithCollectingGenerator {

    public void run() {
        InputStream inputStream = ZipInputStreamNotProperlyClosedWithCollectingGenerator.class.getResourceAsStream("/ZipInputStreamTest.zip");
        while ( true ) {
            new ZipInputStream(inputStream);
            // ZipInputStream object is not properly closed
            // but garbage collecting is a workaround for deallocation of native heap
            System.gc();
        }
    }
    
}
