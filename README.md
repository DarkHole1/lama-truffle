# Building and running

```
mvn compile
mvn exec:java -Dexec.args="FILE.lama"
```

# Comparsion

```
$ time ./interpreter ./Sort.bc < /dev/null
./interpreter ./Sort.bc < /dev/null  5,61s user 0,03s system 99% cpu 5,657 total
$ time ./tests/lamac.sh -i ./performance/Sort.lama < /dev/null
./tests/lamac.sh -i ./performance/Sort.lama < /dev/null  6,31s user 0,05s system 99% cpu 6,394 total
$ time ./interpreter -v Sort.bc < /dev/null
./interpreter -v Sort.bc < /dev/null  2,99s user 0,03s system 99% cpu 3,032 total
$ time ./tests/lamac.sh -s ./performance/Sort.lama < /dev/null
./tests/lamac.sh -s ./performance/Sort.lama < /dev/null  2,36s user 0,03s system 99% cpu 2,406 total
$ time java -Xss2m -cp "target/lama-truffle-1.0.0.jar:target/dependency/*" com.lama.truffle.App ./Sort.lama
java -Xss2m -cp "target/lama-truffle-1.0.0.jar:target/dependency/*"    1,74s user 0,15s system 209% cpu 0,899 total
```

|                               | User  | System | CPU  | Total |
| ----------------------------- | ----- | ------ | ---- | ----- |
| Bytecode Interpreter          | 5,61s | 0,03s  | 99%  | 5,657 |
| Original iterpreter           | 6,31s | 0,05s  | 99%  | 6,394 |
| Bytecode Verified Interpreter | 2,99s | 0,03s  | 99%  | 3,032 |
| Original Bytecode Interpreter | 2,36s | 0,03s  | 99%  | 2,406 |
| Truffle Interpreter           | 1,74s | 0,15s  | 209% | 0,899 |
