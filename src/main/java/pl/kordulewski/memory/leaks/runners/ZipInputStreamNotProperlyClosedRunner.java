package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.ZipInputStreamNotProperlyClosedGenerator;

/**
 * Class to run from the command line.
 *
 * Testing memory utilisation in native heap when instance of ZipInputStream class is not properly ended.
 * Be carefull, it really may cause memory leak.
 *
 * @author Michał Kordulewski
 * Date:   2017-06-09
 * 
 * @see ZipInputStreamNotProperlyClosedGenerator
 */
public class ZipInputStreamNotProperlyClosedRunner {

    public static void main (String... args) {
        System.out.println("START");
        System.out.println("... wait");
        new ZipInputStreamNotProperlyClosedGenerator().run();
    }
    
}
