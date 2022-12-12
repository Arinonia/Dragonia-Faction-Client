package fr.arinonia.player;

import fr.arinonia.jobs.JobsPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;
import java.util.List;

public class PlayerJobsSaver {

	public List<JobsPlayer> jobs = new ArrayList<>();

	public PlayerJobsSaver() {
	}
	
	public void writeCapabilitiesToNBT(final NBTTagCompound nbt) {
		final NBTTagList jobList = new NBTTagList();
		for (final JobsPlayer job : jobs) {
			if (job.jobs != null) {
				jobList.appendTag(job.writeNbt());
			}
		}
		nbt.setTag("jobs", jobList);
	}

	public void readCapabilitiesFromNBT(final NBTTagCompound nbt) {
		final NBTTagList jobList = nbt.getTagList("jobs", 10);
		for (int i = 0; i < jobList.tagCount(); i++) {
			final JobsPlayer job = new JobsPlayer();
			job.readNbt(jobList.getCompoundTagAt(i));
			this.jobs.add(job);
		}

	}

	public List<JobsPlayer> getJobs() {
		return jobs;
	}

	public void setJobs(final List<JobsPlayer> jobs) {
		this.jobs = jobs;
	}
	
}
