package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.ZipInputStreamProperlyClosedGenerator;

/**
 * Class to run from the command line.
 *
 * Testing memory utilisation in native heap when instance of ZipInputStream class is properly ended.
 * It's safe, no memory leak.
 *
 * @author Michał Kordulewski
 * Date:   2017-06-09
 * 
 * @see ZipInputStreamProperlyClosedGenerator
 */
public class ZipInputStreamProperlyClosedRunner {

    public static void main (String... args) {
        System.out.println("START");
        System.out.println("... wait");
        new ZipInputStreamProperlyClosedGenerator().run();
    }
    
}
