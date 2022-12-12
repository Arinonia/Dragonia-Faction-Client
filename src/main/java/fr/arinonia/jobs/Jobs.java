package fr.arinonia.jobs;

public class Jobs {

	public int id;
	public String name;
	public EnumJobs jobs;

	public Jobs(int id, String name, EnumJobs jobs) {
		this.id = id;
		this.name = name;
		this.jobs = jobs;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public EnumJobs getJobs() {
		return jobs;
	}

}
