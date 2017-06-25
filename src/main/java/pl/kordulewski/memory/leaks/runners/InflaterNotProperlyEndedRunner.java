package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.InflaterNotProperlyEndedGenerator;

/**
 * Class to run from the command line.
 *
 * @author Michał Kordulewski
 * Date:   2017-06-09
 * 
 * @see InflaterNotProperlyEndedGenerator
 */
public class InflaterNotProperlyEndedRunner {

    public static void main (String... args) {
        System.out.println("START");
        System.out.println("... wait");
        new InflaterNotProperlyEndedGenerator().run();
    }
    
}
