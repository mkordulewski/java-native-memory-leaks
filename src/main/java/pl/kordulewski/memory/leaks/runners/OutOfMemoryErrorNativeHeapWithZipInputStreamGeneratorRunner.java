package pl.kordulewski.memory.leaks.runners;

import pl.kordulewski.memory.leaks.OutOfMemoryErrorNativeHeapWithZipInputStreamGenerator;

/**
 * Created by Michał Kordulewski on 2017-06-09.
 * 
 * @see OutOfMemoryErrorNativeHeapWithZipInputStreamGenerator
 */
public class OutOfMemoryErrorNativeHeapWithZipInputStreamGeneratorRunner {

    public static void main (String... args) {
        System.out.println("START");
        new OutOfMemoryErrorNativeHeapWithZipInputStreamGenerator().run();
        System.out.println("END");
    }
    
}
