package TaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TaskListMethods {
	private static Scanner scnr = new Scanner(System.in);

	public static void menu() {

		System.out.printf("%15s\n", "Task App");
		System.out.printf("***************************\n");
		System.out.printf("%s\n", "1)     List Tasks");
		System.out.printf("%s\n", "2)     Add Tasks");
		System.out.printf("%s\n", "3)     Delete Tasks");
		System.out.printf("%s\n", "4)     Mark Tasks Complete");
		System.out.printf("%s\n", "5)     See One Persons Tasks");
		System.out.printf("%s\n", "6)     See Tasks Before Date");
		System.out.printf("%s\n", "7)     Edit Task");
		System.out.printf("%s\n", "8)     Quit");
		System.out.printf("***************************\n");

	}

	public static ArrayList<Task> startingTasks() {
		ArrayList<Task> tasks = new ArrayList<>();

		tasks.add(new Task("Jon", "Win the lottery.", "03/14/2020"));
		tasks.add(new Task("Toya", "Repeatedly say \" All the world.\"", "05/17/2020"));
		tasks.add(new Task("Shane", "Replenish drywall.", "02/12/2020"));
		tasks.add(new Task("Sarah", "Write the schedule.", "02/13/2020"));
		tasks.add(new Task("Brian", "Do a BOOK.", "08/14/2010"));

		return tasks;

	}

	public static void displayTasks(ArrayList<Task> tasks) {
		int i = 0;

		System.out.println();
		System.out.println();
		System.out.printf("%11s %5s %39s %13s \n", "Employee", "  Task", "Due Date", "Status");
		System.out.println("=============================================================================");
		for (Task task : tasks) {
			i++;
			System.out.print(i + ") " + task);
		}
		System.out.println("=============================================================================");
		System.out.println();

	}

	public static ArrayList<Task> addTasks(ArrayList<Task> tasks) {
		String name = "";
		String task = "";
		String date = "";
		boolean looper = false;

		System.out.println("Who will be completing this task?");
		name = scnr.nextLine();
		System.out.println("What task would you have " + name + " complete?");
		task = scnr.nextLine();
		while (looper == false) {
			System.out.println("When must this task be complete by? (MM/dd/yyyy)");
			date = scnr.nextLine();
			looper = TaskListValidator.date(date);
		}

		tasks.add(new Task(name, task, date));

		return tasks;
	}

	public static ArrayList<Task> removeTasks(ArrayList<Task> tasks) {
		int i = 0;
		String response = "";

		displayTasks(tasks);
		System.out.println("Which task would you like to remove?");
		i = TaskListValidator.getInt(scnr, 1, tasks.size());

		System.out.println("Are you sure you would like to remove:");
		System.out.println(tasks.get(i - 1));
		System.out.println("(Y/N)");
		response = scnr.nextLine();
		response = response.toUpperCase();
		if (response.startsWith("Y")) {
			tasks.remove(i - 1);
		}
		return tasks;
	}

	public static void completeTasks(ArrayList<Task> tasks) {
		int i = 0;
		String response = "";

		displayTasks(tasks);
		System.out.println("Which task would you like to mark complete?");
		i = TaskListValidator.getInt(scnr, 1, tasks.size());

		System.out.println("Are you sure you would like to mark complete:");
		System.out.println(tasks.get(i - 1));
		System.out.println("(Y/N)");
		response = scnr.nextLine();
		response = response.toUpperCase();
		if (response.startsWith("Y")) {
			tasks.get(i - 1).setComplete(true);
		}
	}

	public static boolean quit() {
		String response = "";

		System.out.println("Are you sure you want to quit?");
		System.out.println("(Y/N)");
		response = scnr.nextLine();
		response = response.toUpperCase();
		if (response.startsWith("Y")) {
			return false;
		}
		return true;
	}

	public static void oneMember(ArrayList<Task> tasks) {
		String response = "";

		displayTasks(tasks);
		System.out.println("Who would you like to see tasks for? (Person's name)");
		response = scnr.nextLine();

		for (Task task : tasks) {
			if (task.getName().equalsIgnoreCase(response)) {
				System.out.println(task);
			}
		}
	}

	public static void byDate(ArrayList<Task> tasks) {
		String date = "";
		boolean looper = false;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		displayTasks(tasks);

		while (looper == false) {
			System.out.println("What date would you like to see tasks due before?");
			System.out.println("(MM/dd/yyyy)");
			date = scnr.nextLine();
			looper = TaskListValidator.date(date);
		}
		try {
			Date date1 = sdf.parse(date);
			for (Task task : tasks) {
				Date date2 = sdf.parse(task.getDate());
				if (date1.compareTo(date2) > 0) {
					System.out.println(task);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public static void editTask(ArrayList<Task> tasks) {
		int i = 0;
		String response = "";
		String response2 = "";
		boolean looper = false;

		displayTasks(tasks);
		System.out.println("Which task would you like to edit?");
		i = TaskListValidator.getInt(scnr, 1, tasks.size());

		System.out.println("What would you like to edit? (name, task, or date)");
		response = scnr.nextLine();

		if (response.equalsIgnoreCase("name")) {
			System.out.println("Who would you like to give the task to?");
			response2 = scnr.nextLine();
			tasks.get(i - 1).setName(response2);
		} else if (response.equalsIgnoreCase("task")) {
			System.out.println("What would you like to make the task?");
			response2 = scnr.nextLine();
			tasks.get(i - 1).setTask(response2);
		} else if (response.equalsIgnoreCase("date")) {
			while (looper == false) {
				System.out.println("What would you like to set the date to?");
				System.out.println("(MM/dd/yyyy)");
				response2 = scnr.nextLine();
				looper = TaskListValidator.date(response2);

				tasks.get(i - 1).setDate(response2);
			}
		}

	}

	public static boolean methodJumper(ArrayList<Task> tasks, int response) {
		boolean continueTasking = true;

		switch (response) {
		case 1:
			displayTasks(tasks);
			break;
		case 2:
			addTasks(tasks);
			break;
		case 3:
			removeTasks(tasks);
			break;
		case 4:
			completeTasks(tasks);
			break;
		case 5:
			oneMember(tasks);
			break;
		case 6:
			byDate(tasks);
			break;
		case 7:
			editTask(tasks);
			break;
		case 8:
			continueTasking = quit();
			break;

		}
		return continueTasking;
	}
}
