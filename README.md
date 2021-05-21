# mqtt-kafka-bridge project

This project uses Quarkus and Maven for receiving messages on a given MQTT topic
and sending them to specified Kafka topic.


See [DEVELOPMENT.md](./DEVELOPMENT.md) for information on building and running the project.

See [testing-with-docker.md](./testing-with-docker.md) for information on using Kafka and MQTT locally (in Docker).


## Service configuration

The service reads the following **environment variables**:

| Variable               | Description                             |  Default      |
|------------------------|-----------------------------------------|--------------:|
| BOOTSTRAP_SERVERS      | comma-separated list of Kafka brokers   | 127.0.0.1:9092|
| KAFKA_TOPIC            | name of the target topic                |       outgoing|
| MQTT_HOST              | IP of MQTT broker                       |      127.0.0.1|
| MQTT_PORT              | port of the broker                      |           1883|
| MQTT_TOPIC             | name of the source topic                |       incoming|
| LOG_LEVEL              | TRACE, DEBUG, INFO, WARN, ERROR, FATAL  |           INFO|

All the configuration parameters are defined
in [./src/main/resources/application.properties](./src/main/resources/application.properties).

## Start in development mode
To start the service in development mode, run:
```shell
./mvnw quarkus:dev
```

Go to http://localhost:8080/q/dev to see the development console.