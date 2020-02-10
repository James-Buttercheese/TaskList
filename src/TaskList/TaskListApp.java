package TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskListApp {

	public static void main(String[] args) {

		ArrayList<Task> tasks = new ArrayList<>();
		tasks = TaskListMethods.startingTasks();
		boolean looper = true;
		Scanner scnr = new Scanner(System.in);
		int response = 0;

		while (looper) {
			TaskListMethods.menu();
			System.out.println("What would you like to do?");
			response = TaskListValidator.getInt(scnr, 1, 8);
			looper = TaskListMethods.methodJumper(tasks, response);
		}
		System.out.println("Goodbye");

	}

}
