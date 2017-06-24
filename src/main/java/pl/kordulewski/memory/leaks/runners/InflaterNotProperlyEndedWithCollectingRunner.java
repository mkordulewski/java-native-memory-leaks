package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.InflaterNotProperlyEndedWithCollectingGenerator;

/**
 * Created by Michał Kordulewski on 2017-06-09.
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
