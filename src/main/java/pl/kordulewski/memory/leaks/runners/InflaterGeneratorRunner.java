package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.InflaterGenerator;

/**
 * Created by Michał Kordulewski on 2017-06-09.
 * 
 * @see InflaterGenerator
 */
public class InflaterGeneratorRunner {

    public static void main (String... args) {
        System.out.println("START");
        System.out.println("... wait");
        new InflaterGenerator().run();
    }
    
}
