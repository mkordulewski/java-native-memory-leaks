# Java native memory leaks


## Summary
| Garbage Collector              |flag                     | HotSpot 6  | HotSpot 8  | HotSpot 9 | 
|:-------------------------------|:------------------------|:----------:|:----------:|:---------:|
| Serial GC                      | -XX:+UseSerialGC        |    leak    |    leak    |           | 
| Parallel GC                    | -XX:+UseParallelGC      |    leak    |    leak    |           | 
| Old Parallel GC                | -XX:+UseParallelOldGC   |    leak    |    leak    |           | 
| CMS (Concurrent Mark Sweep) GC | -XX:+UseConcMarkSweepGC |    leak    |    leak    |           | 
| G1 (Garbage First) GC          | -XX:+UseG1GC            |    leak    |**no leak** |           | 

G1 GC was called with flags:
* HotSpot 8,9: ```-XX:+UseG1GC```
* HotSpot 6:   ```-XX:+UseG1GC -XX:+UnlockExperimentalVMOptions```


## Building and running
```
mvn install
java -jar target/memory-leaks.jar
```


## Running with different garbage collectors
```
java -XX:+UseSerialGC        -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseParallelGC      -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseParallelOldGC   -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseConcMarkSweepGC -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseG1GC            -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapRunner
```

```
java -XX:+UseSerialGC        -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithCollectingRunner
java -XX:+UseParallelGC      -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithCollectingRunner
java -XX:+UseParallelOldGC   -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithCollectingRunner
java -XX:+UseConcMarkSweepGC -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithCollectingRunner
java -XX:+UseG1GC            -cp target/memory-leaks.jar pl.kordulewski.memory.leaks.runners.OutOfMemoryErrorNativeHeapWithCollectingRunner
```

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
