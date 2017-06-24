package pl.kordulewski.memory.leaks;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * Created by Michał Kordulewski on 2017-06-09.
 */
public class ZipInputStreamProperlyClosedGenerator {

    public void run() {
        InputStream inputStream = ZipInputStreamProperlyClosedGenerator.class.getResourceAsStream("/ZipInputStreamTest.zip");
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
