package pl.kordulewski.memory.leaks;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * Testing memory utilisation in native heap when GC is called periodically.
 * It's usually safe, no memory leak.
 *
 * @author Michał Kordulewski
 * Date:   2017-06-09
 */
public class ZipInputStreamNotProperlyClosedWithCollectingGenerator {

    public void run() {
        InputStream inputStream = ZipInputStreamNotProperlyClosedWithCollectingGenerator.class.getResourceAsStream(Constants.ZIP_FILENAME);
        while ( true ) {
            new ZipInputStream(inputStream);
            // ZipInputStream object is not properly closed
            // but garbage collecting is a workaround for deallocation of native heap
            System.gc();
        }
    }
    
}
