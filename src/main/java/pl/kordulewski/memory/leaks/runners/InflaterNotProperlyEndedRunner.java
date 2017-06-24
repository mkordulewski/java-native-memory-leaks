package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.InflaterNotProperlyEndedGenerator;

/**
 * Created by Michał Kordulewski on 2017-06-09.
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
