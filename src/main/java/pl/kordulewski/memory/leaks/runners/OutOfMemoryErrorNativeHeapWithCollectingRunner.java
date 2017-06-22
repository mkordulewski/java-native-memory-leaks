package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.OutOfMemoryErrorNativeHeapWithCollectingTester;

/**
 * Created by Michał Kordulewski on 2017-06-09.
 * 
 * @see OutOfMemoryErrorNativeHeapWithCollectingTester
 */
public class OutOfMemoryErrorNativeHeapWithCollectingRunner {

    public static void main (String... args) {
        System.out.println("START");
        System.out.println("... wait");
        new OutOfMemoryErrorNativeHeapWithCollectingTester().run();
    }
    
}
