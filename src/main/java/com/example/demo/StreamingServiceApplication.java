package com.example.demo;

import com.example.demo.Model.Client;
import com.example.demo.Model.Video;
import com.example.demo.Model.VideoGenre;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;

/**
 * The main application class and method.
 */
@SpringBootApplication
public class StreamingServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(StreamingServiceApplication.class, args);

		Calendar h = Calendar.getInstance();
		Video v1 = new Video("Observer Design Pattern in Java", "Telusko", VideoGenre.Tutorials, false, "98DiwRp-KZk", 15, 19);
		Video v2 = new Video("Place Holder", "Chanchan", VideoGenre.Comedy, true, "yavjksbj", 18, 20);

		Client c1 = new Client("John", "Doe", "abc@gmail.com", "1234556", 25, "USA");
		Client c2 = new Client("Alice", "Sue", "alicesue@yahoo.com", "cat1234", 20, "USA");
		Client c3 = new Client("Viorel", "Anghel", "vio@gmail.com", "bmv800", 30, "Romania");

		v1.addClient(c1);
		v1.addClient(c2);
		v1.addClient(c3);
		v2.addClient(c1);
		v2.addClient(c2);
		v2.addClient(c3);

		c1.watch(v1);
		c2.watch(v1);
		c3.watch(v2);

		c1.stopWatching(v1);
		if (h.get(Calendar.HOUR_OF_DAY) >= v1.getEndHour()) {
			v1.onEndHour();
		}
		v2.removeClient(c2);
		if (h.get(Calendar.HOUR_OF_DAY) >= v2.getEndHour()) {
			v2.onEndHour();
		}
		/*OperatiiDobanda op = new OperatiiDobanda();
		System.out.println(op.calculDobanda(50));
		DbOperation repo = new Repo();
		OperatiiDobanda operations = new OperatiiDobanda(repo);
		operations.riskCheck();*/
	}

}
