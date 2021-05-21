When developing locally, I found it useful to have my MQTT broker and Kafka broker running in Docker.


## Mosquitto 
Quick way to **set up a simple MQTT broker** is to use Docker containers:
```shell
docker run -it -p 1883:1883 --name mosquitto eclipse-mosquitto mosquitto -c /mosquitto-no-auth.conf
```


To **publish to a topic**:

```shell
docker exec mosquitto mosquitto_pub -h 127.0.0.1 -t test -m "test message"
```

To **subscribe to a topic**:
```shell
docker exec mosquitto mosquitto_sub -h 127.0.0.1 -t test
```

## Kafka

In my case, the quickest way to set up Kafka cluster for development purposes was to use Docker containers.

The procedure to **set up the cluster** boils down to:

```
curl --silent --output docker-compose.yml \
  https://raw.githubusercontent.com/confluentinc/cp-all-in-one/6.1.0-post/cp-all-in-one/docker-compose.yml

docker-compose up -d
```

See https://docs.confluent.io/platform/current/quickstart/ce-docker-quickstart.html for the details.

To **create a topic**:

```
docker-compose exec broker kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test
```

where

* `broker` is the name of the container hosting Kafka broker instance
* `localhost:9092` is the broker's URL
* `test` is the topic name

To **produce some testing messages**, one can issue the following command:

```
docker-compose exec broker \
  bash -c "seq 10 | kafka-console-producer --request-required-acks 1 --broker-list localhost:9092 --topic test && echo 'Produced 10 messages.'"
```
or
```
docker-compose exec broker \
  bash -c "echo '{\"key\":\"abc\",\"value\":\"bcd\"}' | kafka-console-producer --request-required-acks 1 --broker-list localhost:9092 --topic test && echo 'Message produced.'"
```

To **consume messages** from a topic:
```shell
docker-compose exec broker \
    kafka-console-consumer --topic test --from-beginning --bootstrap-server localhost:9092
```