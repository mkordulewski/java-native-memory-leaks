# Java native memory leaks

Testing native heap memory leaks caused by native method ```java.util.zip.Inflater.init(boolean)```.

## Summary
| Garbage Collector              |flag                     | HotSpot 6 @ Windows 7 x64 | HotSpot 8 @ Windows 7 x64 | HotSpot 8 @ Solaris 11 |  
|:-------------------------------|:------------------------|:-------------------------:|:-------------------------:|:----------------------:|
| Serial GC                      | -XX:+UseSerialGC        |          leak (1)         |         leak (1)          |        leak (2)        | 
| Parallel GC                    | -XX:+UseParallelGC      |          leak (1)         |         leak (1)          |        leak (2)        | 
| Old Parallel GC                | -XX:+UseParallelOldGC   |          leak (1)         |         leak (1)          |        leak (2)        | 
| CMS (Concurrent Mark Sweep) GC | -XX:+UseConcMarkSweepGC |          leak (1)         |         leak (1)          |        leak (2)        | 
| G1 (Garbage First) GC          | -XX:+UseG1GC            |          leak (1)         |       **no leak**         |        leak (2)        | 

Table legend:
* leak (1) - used whole OS memory, OutOfMemoryError
* leak (2) - used part of OS memory, OutOfMemoryError or Exception

G1 GC was called with flags:
* HotSpot 8,9: ```-XX:+UseG1GC```
* HotSpot 6:   ```-XX:+UseG1GC -XX:+UnlockExperimentalVMOptions```


## Building and running
```
mvn install
java -jar target/memory-leaks.jar
```


## Running with different garbage collectors
Testing memory leak in native heap - **WARNING, it really cause memory leak** (except G1 GC with HotSpot 8):
```
java -XX:+UseSerialGC        -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseParallelGC      -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseParallelOldGC   -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseConcMarkSweepGC -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseG1GC            -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
```

Testing memory leak in native heap when GC is called periodically - it's safe, no memory leak:
```
java -XX:+UseSerialGC        -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithCollectingRunner
java -XX:+UseParallelGC      -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithCollectingRunner
java -XX:+UseParallelOldGC   -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithCollectingRunner
java -XX:+UseConcMarkSweepGC -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithCollectingRunner
java -XX:+UseG1GC            -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithCollectingRunner
```

Testing memory leak in native heap when objects intentionally litter Java heap - it's safe, no memory leak:
```
java -XX:+UseSerialGC        -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithLitteringRunner
java -XX:+UseParallelGC      -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithLitteringRunner
java -XX:+UseParallelOldGC   -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithLitteringRunner
java -XX:+UseConcMarkSweepGC -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithLitteringRunner
java -XX:+UseG1GC            -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithLitteringRunner
```


### Alternative flags
```
java -XX:+AggressiveHeap     -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
```


## Error messages

Windows 7 x64, OpenSuse 42 (x64), Solaris 11 (x63):
```
java.lang.OutOfMemoryError
    at java.util.zip.Inflater.init(Native Method)
```

Solaris 11 (x64):
```
Exception in thread "main" #
# There is insufficient memory for the Java Runtime Environment to continue.
```
