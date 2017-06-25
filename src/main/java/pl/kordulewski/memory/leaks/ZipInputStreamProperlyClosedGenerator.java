package pl.kordulewski.memory.leaks;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * Testing memory utilisation in native heap when instance of ZipInputStream class is properly ended.
 * It's safe, no memory leak.
 *
 * @author Michał Kordulewski
 * Date:   2017-06-09
 */
public class ZipInputStreamProperlyClosedGenerator {

    public void run() {
        InputStream inputStream = ZipInputStreamProperlyClosedGenerator.class.getResourceAsStream(Constants.ZIP_FILENAME);
        while ( true ) {
            final ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            try {
                zipInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
