package pl.kordulewski.memory.leaks;

import java.util.zip.Inflater;

/**
 * Testing memory utilisation in native heap when GC is called periodically.
 * It's usually safe, no memory leak.
 *
 * @author Michał Kordulewski
 * Date:   2017-06-09
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
