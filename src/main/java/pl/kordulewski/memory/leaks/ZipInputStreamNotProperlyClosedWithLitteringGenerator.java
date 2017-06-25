package pl.kordulewski.memory.leaks;

import java.io.InputStream;
import java.util.Date;
import java.util.zip.ZipInputStream;

/**
 * Testing memory utilisation in native heap when objects intentionally litter Java heap.
 * It's usually safe, no memory leak.
 *
 * @author Michał Kordulewski
 * Date:   2017-06-09
 */
public class ZipInputStreamNotProperlyClosedWithLitteringGenerator {

    public void run() {
        InputStream inputStream = ZipInputStreamNotProperlyClosedWithLitteringGenerator.class.getResourceAsStream(Constants.ZIP_FILENAME);
        while ( true ) {
            new ZipInputStream(inputStream);
            // ZipInputStream object is not properly closed
            // but littering is a workaround
            String s = "" + new Date();
        }
    }
    
}
