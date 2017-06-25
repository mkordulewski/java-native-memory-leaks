package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.InflaterProperlyEndedGenerator;

/**
 * Class to run from the command line.
 *
 * @author Michał Kordulewski
 * Date:   2017-06-09
 * 
 * @see InflaterProperlyEndedGenerator
 */
public class InflaterProperlyEndedRunner {

    public static void main (String... args) {
        System.out.println("START");
        System.out.println("... wait");
        new InflaterProperlyEndedGenerator().run();
    }
    
}
