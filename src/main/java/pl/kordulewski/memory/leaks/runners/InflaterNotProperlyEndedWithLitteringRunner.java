package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.InflaterNotProperlyEndedWithLitteringGenerator;

/**
 * Created by Michał Kordulewski on 2017-06-09.
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
