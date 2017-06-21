package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.OutOfMemoryErrorNativeHeapWithLitteringTester;

/**
 * Created by Michał Kordulewski on 2017-06-09.
 * 
 * @see OutOfMemoryErrorNativeHeapWithLitteringTester
 */
public class OutOfMemoryErrorNativeHeapWithLitteringRunner {

    public static void main (String... args) {
        System.out.println("START");
        new OutOfMemoryErrorNativeHeapWithLitteringTester().run();
        System.out.println("END");
    }
    
}
