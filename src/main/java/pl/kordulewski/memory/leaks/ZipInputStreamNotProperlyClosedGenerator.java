package pl.kordulewski.memory.leaks;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * Testing memory utilisation in native heap when instance of ZipInputStream class is not properly ended.
 * Be carefull, it really may cause memory leak.
 *
 * @author Michał Kordulewski
 * Date:   2017-06-09
 */
public class ZipInputStreamNotProperlyClosedGenerator {

    public void run() {
        InputStream inputStream = ZipInputStreamNotProperlyClosedGenerator.class.getResourceAsStream(Constants.ZIP_FILENAME);
        while ( true ) {
            new ZipInputStream(inputStream);
        }
    }
    
}
