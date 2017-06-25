package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.InflaterNotProperlyEndedWithCollectingGenerator;

/**
 * Class to run from the command line.
 *
 * Testing memory utilisation in native heap when GC is called periodically.
 * It's usually safe, no memory leak.
 *
 * @author Michał Kordulewski
 * Date:   2017-06-09
 * 
 * @see InflaterNotProperlyEndedWithCollectingGenerator
 */
public class InflaterNotProperlyEndedWithCollectingRunner {

    public static void main (String... args) {
        System.out.println("START");
        System.out.println("... wait");
        new InflaterNotProperlyEndedWithCollectingGenerator().run();
    }
    
}
