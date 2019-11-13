package com.rumanski.kpa;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerExample {

	public static final String KAFKA_TOPIC = "persistent://public/default/test";
	public static final String PULSAR_TOPIC = "test";
	public static final String KAFKA_SERVER = "localhost:9092";
	public static final String PULSAR_SERVER = "pulsar://localhost:6650";

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", KAFKA_SERVER);

		props.put("key.serializer", IntegerSerializer.class.getName());
		props.put("value.serializer", StringSerializer.class.getName());

		Producer<Integer, String> producer = new KafkaProducer<>(props);

		for (int i = 0; i < 5; i++) {
			producer.send(new ProducerRecord<Integer, String>(KAFKA_TOPIC, i, "Msg-" + Integer.toString(i)));
			log.info("Message {} sent successfully", i);
		}

		producer.flush();
		producer.close();
	}

	private static final Logger log = LoggerFactory.getLogger(ProducerExample.class);
}
