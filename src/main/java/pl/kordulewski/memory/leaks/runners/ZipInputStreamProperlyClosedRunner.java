package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.ZipInputStreamProperlyClosedGenerator;

/**
 * Created by Michał Kordulewski on 2017-06-09.
 * 
 * @see ZipInputStreamProperlyClosedGenerator
 */
public class ZipInputStreamProperlyClosedRunner {

    public static void main (String... args) {
        System.out.println("START");
        System.out.println("... wait");
        new ZipInputStreamProperlyClosedGenerator().run();
    }
    
}
