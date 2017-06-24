package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.ZipInputStreamNotProperlyClosedWithLitteringGenerator;

/**
 * Created by Michał Kordulewski on 2017-06-09.
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
