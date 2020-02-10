package TaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TaskListValidator {

	public static boolean date(String date) {

		if (date.trim().equals("")) {
			System.out.println("Please enter a date.");
			return false;
		} else {
			SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
			sdfrmt.setLenient(false);
			try {
				Date javaDate = sdfrmt.parse(date);
			} catch (ParseException e) {
				System.out.println(date + " is an Invalid Date format");
				return false;
			}
			return true;
		}

	}

	public static int getInt(Scanner scnr) {
		while (!scnr.hasNextInt()) {
			scnr.nextLine();
			System.out.println("Please enter a whole number, using digits.");
		}
		int result = scnr.nextInt();
		scnr.nextLine();
		return result;
	}

	public static int getInt(Scanner scnr, int min, int max) {
		boolean isValid = false;
		int number;
		do {
			number = getInt(scnr);

			if (number < min) {
				isValid = false;
				System.out.println("The number must be at least " + min);
			} else if (number > max) {
				isValid = false;
				System.out.println("The number must not be larger than " + max);
			} else {
				isValid = true;
			}

		} while (!isValid);
		return number;
	}

}
