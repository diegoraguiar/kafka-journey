
##### Start Server

```shell
./bin/zookeeper-server-start.sh config/zookeeper.properties
./bin/kafka-server-start.sh config/server.properties
```

##### Manage Topic

```shell
./bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
./bin/kafka-topics.sh --bootstrap-server localhost:9092 --describe
./bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic topic_name
./bin/kafka-topics.sh --bootstrap-server localhost:9092 --alter --topic topic_name --partitions number_of_partitions
./bin/kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic topic_name
```

##### Manage Configs

```shell
./bin/kafka-configs.sh --bootstrap-server localhost:9092 --describe --entity-type topics --entity-name topic_name
./bin/kafka-configs.sh --bootstrap-server localhost:9092 --alter --entity-type topics topic_name --add-config retention.ms=time
```

##### Producers and Consumers

```shell
./bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic topic_name

./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic topic_name --group group_name

./bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
./bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group group_name
```
