package com.gok.kafkaexample;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.kafka.KafkaConfiguration;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaExampleApplication {

	public static void main(String[] args) {
		try {
			CamelContext context = new DefaultCamelContext();
			context.addComponent("kafka", createKafkaComponent());
			context.addRoutes(createProducerRouteBuilder());
			context.addRoutes(createConsumerRouteBuilder());

			context.start();
			Thread.sleep(10000);
			context.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}

		SpringApplication.run(KafkaExampleApplication.class, args);
	}
	static KafkaComponent createKafkaComponent() {
		KafkaConfiguration kafkaConfig = new KafkaConfiguration();
		kafkaConfig.setBrokers("localhost:9092"); //Connect to 3 kafka brokers
		kafkaConfig.setGroupId("group1"); //Set group ID
		kafkaConfig.setAutoCommitEnable(true); //Turn on autocommit
		kafkaConfig.setAutoCommitIntervalMs(5000); //Set autocommit interval
		kafkaConfig.setAutoOffsetReset("earliest"); //Behavior when reading a partition with no committed offset
		kafkaConfig.setRequestRequiredAcks("all"); //Conditions for judging that publish was successful
		kafkaConfig.setConsumersCount(1); //Number of consumers

		KafkaComponent kafka = new KafkaComponent();
		kafka.setConfiguration(kafkaConfig);

		return kafka;
	}

	static RouteBuilder createProducerRouteBuilder() {
		return new RouteBuilder() {
			public void configure() throws Exception {
				from("timer:trigger?period=1000") //Run every 1000 milliseconds
						.routeId("kafka_producer_route")
						.setBody(simple("${date:now:yyyy-MM-dd HH:mm:ss}"))
						.to("kafka:test01");
			}
		};
	}

	static RouteBuilder createConsumerRouteBuilder() {
		return new RouteBuilder() {
			public void configure() throws Exception {
				from("kafka:test01")
						.routeId("kafka_consumer_route")
						.log("body = ${body}");
			}
		};
	}
}
