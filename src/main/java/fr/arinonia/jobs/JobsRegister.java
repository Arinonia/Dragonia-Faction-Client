package fr.arinonia.jobs;

public class JobsRegister {

    public static final Jobs WOODCUTTER = new Jobs().setDesc("desc.wood_cutter");
    public static final Jobs FARMER = new Jobs().setDesc("desc.farmer");
    public static final Jobs MINER = new Jobs().setDesc("desc.farmer");
    public static final Jobs FISHER = new Jobs().setDesc("desc.farmer");

    public static void registerJobs() {
        Jobs.addJobs(1, "farmer", FARMER);
        Jobs.addJobs(2, "miner", MINER);
        Jobs.addJobs(3, "fisher", FISHER);
        Jobs.addJobs(3, "fisher", WOODCUTTER);
    }

}
