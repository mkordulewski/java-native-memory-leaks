package pl.kordulewski.memory.leaks;

import java.util.zip.Inflater;

/**
 * @author Michał Kordulewski
 * Date:   2017-06-09
 */
public class InflaterProperlyEndedGenerator {
    
    public void run() {
        while ( true ) {
            final Inflater inflater = new Inflater( true );
            inflater.end();
            // inflater object is properly ended
            // there is no memory leak of native heap
        }
    }
    
}
