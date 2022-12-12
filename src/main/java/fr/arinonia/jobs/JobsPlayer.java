package fr.arinonia.jobs;

import net.minecraft.nbt.NBTTagCompound;

public class JobsPlayer {
    private Jobs job;
    private int level = 1;
    private long xp = 0;
    private final long[] xpToUP = new long[] { 663, 1396, 2206, 3101, 4090, 5182, 6389, 7721,9192,10817,12611,14591,16777,19191,21855,24796,28043,31627,35583,39950,44771,50092,55966,62450,69607,77508,
            86229,95857,106485,118217,131186,145465,161248,178671,197606,219140,242582,268461,297031,328571,363391,401833,444276,491127,542854,599962,663010,732617,809466,1000000};


    public JobsPlayer(final Jobs job) {
        this.job = job;
    }

    public JobsPlayer() {
    }


    public JobsPlayer(final Jobs job, final int level, final long xp) {
        this.job = job;
        this.level = level;
        this.xp = xp;
    }

    public NBTTagCompound serializeNBT() {
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setShort("id", (short) Jobs.getIdFromJobs(this.job));
        tag.setInteger("level", this.level);
        tag.setLong("xp", this.xp);
        return tag;
    }

    public void addXp(final long xp) {
        canUp();
        this.xp = this.xp + xp;
        canUp();
    }

    public void canUp() {
        if(this.xp >= this.xpToUP[getLevel()-1]) {
            this.setLevel(this.getLevel()+1);
        }
    }

    public void deserializeNBT(final NBTTagCompound nbt) {
        this.job = Jobs.getJobsById(nbt.getShort("id"));
        this.level = nbt.getInteger("level");
        this.xp = nbt.getLong("xp");
    }

    public void setJob(final Jobs job) {
        this.job = job;
    }

    public void setLevel(final int level) {
        this.level = level;
    }

    public void setXp(final long xp) {
        this.xp = xp;
    }

    public Jobs getJob() {
        return this.job;
    }

    public int getLevel() {
        return this.level;
    }

    public long getXp() {
        return this.xp;
    }
    public long getXpToUP() {
        return this.xpToUP[getLevel()-1];
    }

    public int getMaxLevel() {
        return this.xpToUP.length;
    }
}
