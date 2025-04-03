package it.epicode.progetto.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class RandomizeDateTimeFormat {
	public static void main(String[] args) {
		int randomSeconds = new Random().nextInt(3600 * 24);
		LocalDateTime anyTime = LocalDateTime.now().minusMinutes(randomSeconds);
		System.out.println(anyTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

	}
}
