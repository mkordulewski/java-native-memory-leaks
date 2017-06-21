# Java memory leaks


## Budowa i uruchomienie
```
mvn install
java -jar target/memory-leaks.jar
```


## Uruchomienie z różnymi garbage collectorami

java -XX:+UseSerialGC        -cp target/tests-memory.jar pl.kordulewski.memory.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseParallelGC      -cp target/tests-memory.jar pl.kordulewski.memory.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseParallelOldGC   -cp target/tests-memory.jar pl.kordulewski.memory.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseConcMarkSweepGC -cp target/tests-memory.jar pl.kordulewski.memory.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UseG1GC            -cp target/tests-memory.jar pl.kordulewski.memory.runners.OutOfMemoryErrorNativeHeapRunner

nie mają wpływu na test:
java -XX:+AggressiveHeap     -cp target/tests-memory.jar pl.kordulewski.memory.runners.OutOfMemoryErrorNativeHeapRunner
java -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -cp target/tests-memory.jar pl.kordulewski.memory.runners.OutOfMemoryErrorNativeHeapRunner
