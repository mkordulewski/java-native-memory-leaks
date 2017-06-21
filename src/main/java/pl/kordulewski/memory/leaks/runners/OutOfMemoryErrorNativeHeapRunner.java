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
        new OutOfMemoryErrorNativeHeapGenerator().run();
        System.out.println("END");
    }
    
}
