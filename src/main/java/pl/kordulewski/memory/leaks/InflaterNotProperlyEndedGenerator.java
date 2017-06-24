package pl.kordulewski.memory.leaks;

import java.util.zip.Inflater;

/**
 * Created by Michał Kordulewski on 2017-06-09.
 */
public class InflaterNotProperlyEndedGenerator {
    
    public void run() {
        while ( true ) {
            new Inflater( true );
            // inflater object is not properly ended
            // WARNING: this causes memory leaks of native heap
            // memory is allocated, can be deallocated but it's not yet
        }
    }
    
}
