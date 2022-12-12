package fr.arinonia.jobs;

import net.minecraft.nbt.NBTTagCompound;

public class JobsPlayer {

	public Jobs jobs;
	public int level = 1;
	public int xp = 0;

	public JobsPlayer() {
	}

	public JobsPlayer(Jobs jobs) {
		this.jobs = jobs;
	}

	public JobsPlayer(Jobs jobById, int levelJob, int xpJob) {
		jobs = jobById;
		level = levelJob;
		xp = xpJob;
	}

	public void readNbt(NBTTagCompound tag) {
		jobs = JobsRegister.getJobsById(tag.getInteger("jobsId"));
		level = tag.getInteger("level");
		xp = tag.getInteger("xp");
	}

	public NBTTagCompound writeNbt() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("jobsId", jobs.getId());
		tag.setInteger("level", level);
		tag.setInteger("xp", xp);
		return tag;
	}

	public Jobs getJobs() {
		return jobs;
	}

	public int getLevel() {
		return level;
	}

	public void addXp(int xp) {
		System.out.println("xp: " + this.xp);
		this.xp += xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public void resetXp() {
		this.xp = 0;
	}

	public void addlevel(int level) {
		this.level += level;
	}

	public void setlevel(int level) {
		this.level = level;
	}

	public int getXp() {
		return xp;
	}

}
