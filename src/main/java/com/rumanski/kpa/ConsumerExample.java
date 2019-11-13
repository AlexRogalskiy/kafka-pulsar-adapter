package com.rumanski.kpa;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerExample {

	public static final String KAFKA_TOPIC = "persistent://public/default/test";
	public static final String PULSAR_TOPIC = "test";
	public static final String KAFKA_SERVER = "localhost:9092";
	public static final String PULSAR_SERVER = "pulsar://localhost:6650";

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", KAFKA_SERVER);

		props.put("group.id", "my-subscription-name");
		props.put("enable.auto.commit", "false");
		props.put("key.deserializer", IntegerDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());

		@SuppressWarnings("resource")
		Consumer<Integer, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList(KAFKA_TOPIC));

		while (true) {
			ConsumerRecords<Integer, String> records = consumer.poll((100));
			records.forEach(record -> {
				log.info("Received record: {}", record);
				consumer.commitSync();
			});

		}
	}

	private static final Logger log = LoggerFactory.getLogger(ConsumerExample.class);
}
