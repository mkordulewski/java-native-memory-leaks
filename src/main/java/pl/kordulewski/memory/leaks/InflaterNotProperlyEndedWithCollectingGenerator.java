package pl.kordulewski.memory.leaks;

import java.util.zip.Inflater;

/**
 * Created by Michał Kordulewski on 2017-06-09.
 */
public class InflaterNotProperlyEndedWithCollectingGenerator {
    
    public void run() {
        while ( true ) {
            new Inflater( true );
            // inflater object is not properly ended
            // but garbage collecting is a workaround for deallocation of native heap
            System.gc();
        }
    }
    
}
