package com.neo.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@JmsListener(destination = "neo.queue")
    public void receiveQueue(String text) {
		System.out.println("Consumer queue msg : "+text);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("i am broke");
	}
}