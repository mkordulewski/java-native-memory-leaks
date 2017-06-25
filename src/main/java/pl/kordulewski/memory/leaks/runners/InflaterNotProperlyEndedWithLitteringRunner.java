package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.InflaterNotProperlyEndedWithLitteringGenerator;

/**
 * Class to run from the command line.
 *
 * @author Michał Kordulewski
 * Date:   2017-06-09
 * 
 * @see InflaterNotProperlyEndedWithLitteringGenerator
 */
public class InflaterNotProperlyEndedWithLitteringRunner {

    public static void main (String... args) {
        System.out.println("START");
        System.out.println("... wait");
        new InflaterNotProperlyEndedWithLitteringGenerator().run();
    }
    
}
