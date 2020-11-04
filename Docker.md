
##### Manage Topic

```shell
docker container exec -it container_name kafka-topics.sh --bootstrap-server localhost:9092 --list
docker container exec -it container_name kafka-topics.sh --bootstrap-server localhost:9092 --describe
docker container exec -it container_name kafka-topics.sh --bootstrap-server localhost:9092 --create --topic topic_name
docker container exec -it container_name kafka-topics.sh --bootstrap-server localhost:9092 --alter --topic topic_name --partitions number_of_partitions
docker container exec -it container_name kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic topic_name
```

##### Manage Configs

```shell
docker container exec -it container_name kafka-configs.sh --bootstrap-server localhost:9092 --describe --entity-type topics --entity-name topic_name
docker container exec -it container_name kafka-configs.sh --bootstrap-server localhost:9092 --alter --entity-type topics topic_name --add-config retention.ms=time
```

##### Producers and Consumers

```shell
docker container exec -it container_name kafka-console-producer.sh --bootstrap-server localhost:9092 --topic topic_name

docker container exec -it container_name kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic topic_name --group group_name

docker container exec -it container_name kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
docker container exec -it container_name kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group group_name
```