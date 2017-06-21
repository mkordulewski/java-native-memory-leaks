# Java native memory leaks


## Summary
| Garbage Collector              |flag                     | HotSpot 6  | HotSpot 8  | HotSpot 9 | 
|:-------------------------------|:------------------------|:----------:|:----------:|:---------:|
| Serial GC                      | -XX:+UseSerialGC        |            |            |           | 
| Parallel GC                    | -XX:+UseParallelGC      |            |            |           | 
| Old Parallel GC                | -XX:+UseParallelOldGC   |            |            |           | 
| CMS (Concurrent Mark Sweep) GC | -XX:+UseConcMarkSweepGC |            |            |           | 
| G1 (Garbage First) GC          | -XX:+UseG1GC            |            |            |           | 


## Building and running
```
mvn install
java -jar target/memory-leaks.jar
```


## Running with different garbage collectors
```
java -XX:+UseSerialGC        -cp target/tests-memory.jar pl.kordulewski.memory.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseParallelGC      -cp target/tests-memory.jar pl.kordulewski.memory.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseParallelOldGC   -cp target/tests-memory.jar pl.kordulewski.memory.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseConcMarkSweepGC -cp target/tests-memory.jar pl.kordulewski.memory.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseG1GC            -cp target/tests-memory.jar pl.kordulewski.memory.runners.OutOfMemoryErrorNativeHeapRunner
```


### Alternative flags
```
java -XX:+AggressiveHeap     -cp target/tests-memory.jar pl.kordulewski.memory.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -cp target/tests-memory.jar pl.kordulewski.memory.runners.OutOfMemoryErrorNativeHeapRunner
```
