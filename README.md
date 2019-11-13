# kafka-pulsar-adapter
Project showing how to use Kafka api to pubsub into Pulsar

To run it you will need:

- Start Kafka as per: https://kafka.apache.org/quickstart
- Start Pulsar as per: docker run -it -p 6650:6650 -p 8080:8080  --mount source=pulsardata,target=/pulsar/data --mount source=pulsarconf,target=/pulsar/conf apachepulsar/pulsar:2.4.1 bin/pulsar standalone


Now you can run ProducerExample.java and then ConsumerExample.java

make the changes as noted on the PPT and run the examples again. there should be no code changes except the pom dependency.
