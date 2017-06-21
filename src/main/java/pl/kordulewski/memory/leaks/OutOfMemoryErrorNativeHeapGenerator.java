package pl.kordulewski.memory.leaks;

import java.util.zip.Inflater;

/**
 * Created by Michał Kordulewski on 2017-06-09.
 */
public class OutOfMemoryErrorNativeHeapGenerator {
    
    public void run() {
        while ( true ) {
            new Inflater( true );
        }
    }
    
}
