# number-generator

This is an application of some code exam.

## Usage

### Generators

```bash
$ sbt runGenerator -Dapp.port=9991 -Dapp.processTime=10 -Dgenerator.threadNumber=10
$ sbt runGenerator -Dapp.port=9992 -Dapp.processTime=10 -Dgenerator.threadNumber=10
```

The processes will count up random number with 10 threads every 10 ms and wait TCP request on the port.

### Aggregator

```bash
$ sbt runAggregator -Dapp.port=9990 -Dapp.processTime=1000 -Daggregator.hosts.1=localhost:9991 -Daggregator.hosts.2=localhost:9992
```

The aggregator retrieve counts from other generators and aggregators every 1000 ms.

