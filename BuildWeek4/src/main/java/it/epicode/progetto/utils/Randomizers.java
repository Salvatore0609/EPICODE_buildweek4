package it.epicode.progetto.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Randomizers {
	public static LocalDateTime randomDate() {
		int randomSeconds = new Random().nextInt(3600 * 24);
		return LocalDateTime.now().plusSeconds(randomSeconds);

	}

	public static int randomSerie() {

		Random random = new Random();
		int randomNumber = random.nextInt(10000);
		return randomNumber;
	 }

	public static LocalDate randomDate2() {
		int randomInt = new Random().nextInt(3600 * 24);
		return LocalDate.now().plusDays(randomInt);

	}






	}




