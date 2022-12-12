package fr.arinonia.jobs;

import net.minecraft.util.RegistryNamespaced;

public class JobsRegister {

	public static final RegistryNamespaced jobsRegistry = new RegistryNamespaced();

	public static final Jobs farmer = new Jobs(1, "farmer", EnumJobs.FARMER);
	public static final Jobs miner = new Jobs(2, "miner", EnumJobs.MINER);
	public static final Jobs fisher = new Jobs(3, "fisher", EnumJobs.FISHER);

	public static void registerJobs() {
		jobsRegistry.addObject(1, "farmer", farmer);
		jobsRegistry.addObject(2, "miner", miner);
		jobsRegistry.addObject(3, "fisher", fisher);
	}
	

	public static int getIdFromJobs(Jobs p_150891_0_) {
		return p_150891_0_ == null ? 0 : jobsRegistry.getIDForObject(p_150891_0_);
	}

	public static Jobs getJobsById(int p_150899_0_) {
		return (Jobs) jobsRegistry.getObjectForID(p_150899_0_);
	}
	


}
