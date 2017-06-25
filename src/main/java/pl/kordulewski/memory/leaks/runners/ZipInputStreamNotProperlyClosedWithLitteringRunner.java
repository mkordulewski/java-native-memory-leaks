package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.ZipInputStreamNotProperlyClosedWithLitteringGenerator;

/**
 * Class to run from the command line.
 *
 * Testing memory utilisation in native heap when objects intentionally litter Java heap.
 * It's usually safe, no memory leak.
 *
 * @author Michał Kordulewski
 * Date:   2017-06-09
 * 
 * @see ZipInputStreamNotProperlyClosedWithLitteringGenerator
 */
public class ZipInputStreamNotProperlyClosedWithLitteringRunner {

    public static void main (String... args) {
        System.out.println("START");
        System.out.println("... wait");
        new ZipInputStreamNotProperlyClosedWithLitteringGenerator().run();
    }
    
}
