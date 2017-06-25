package pl.kordulewski.memory.leaks;

import java.util.Date;
import java.util.zip.Inflater;

/**
 * @author Michał Kordulewski
 * Date:   2017-06-09
 */
public class InflaterNotProperlyEndedWithLitteringGenerator {
    
    public void run() {
        while ( true ) {
            new Inflater( true );
            // inflater object is not properly ended
            // but littering is a workaround
            String s = "" + new Date();
        }
    }
    
}
