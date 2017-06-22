package pl.kordulewski.memory.leaks;

import java.util.Date;
import java.util.zip.Inflater;

/**
 * Created by Michał Kordulewski on 2017-06-09.
 */
public class OutOfMemoryErrorNativeHeapWithLitteringTester {
    
    public void run() {
        while ( true ) {
            new Inflater( true );
            // inflater object is not properly ended
            // but littering is a workaround
            String s = "" + new Date();
        }
    }
    
}
