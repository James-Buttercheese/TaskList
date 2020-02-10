package TaskList;

public class Task {

	private String name;
	private String task;
	private String date;
	private boolean complete;

	public Task(String name, String task, String date) {
		this.name = name;
		this.task = task;
		this.date = date;
		complete = false;
	}

	@Override
	public String toString() {

		if (isComplete()) {
			return String.format("%-10s %-35s %-15s %-10s\n", name, task, date, "Complete");
		} else {
			return String.format("%-10s %-35s %-15s %-10s\n", name, task, date, "Incomplete");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

}
