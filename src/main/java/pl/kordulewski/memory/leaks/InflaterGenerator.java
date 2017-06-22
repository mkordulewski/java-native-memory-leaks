package pl.kordulewski.memory.leaks;

import java.util.zip.Inflater;

/**
 * Created by Michał Kordulewski on 2017-06-09.
 */
public class InflaterGenerator {
    
    public void run() {
        while ( true ) {
            final Inflater inflater = new Inflater( true );
            inflater.end();
            // inflater object is not properly ended
            // there is no memory leak of native heap
        }
    }
    
}
