package com.dagurasu.kafka.orderproducer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.dagurasu.kafka.clients.producer.CallBack.OrderCallback;

public class OrderProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerSerializer");

		KafkaProducer<String, Integer> producer = new KafkaProducer<String, Integer>(props);
		ProducerRecord<String, Integer> record = new ProducerRecord<String, Integer>("OrderTopic", "Mac Book Pro", 10);

		try {
			producer.send(record, new OrderCallback()).get();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}
}