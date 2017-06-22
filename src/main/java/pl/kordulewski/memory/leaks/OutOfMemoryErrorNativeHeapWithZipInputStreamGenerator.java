package pl.kordulewski.memory.leaks;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * Created by Michał Kordulewski on 2017-06-09.
 */
public class OutOfMemoryErrorNativeHeapWithZipInputStreamGenerator {

    public void run() {
        InputStream inputStream = OutOfMemoryErrorNativeHeapWithZipInputStreamGenerator.class.getResourceAsStream("/ZipInputStreamTest.zip");
        while ( true ) {
            new ZipInputStream(inputStream);
        }
    }
    
}
