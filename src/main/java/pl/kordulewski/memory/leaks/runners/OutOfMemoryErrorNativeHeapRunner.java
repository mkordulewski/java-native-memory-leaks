package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.OutOfMemoryErrorNativeHeapGenerator;

/**
 * Created by Michał Kordulewski on 2017-06-09.
 * 
 * @see OutOfMemoryErrorNativeHeapGenerator
 */
public class OutOfMemoryErrorNativeHeapRunner {

    public static void main (String... args) {
        System.out.println("START");
        System.out.println("... wait");
        new OutOfMemoryErrorNativeHeapGenerator().run();
    }
    
}
