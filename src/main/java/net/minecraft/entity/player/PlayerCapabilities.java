package net.minecraft.entity.player;

import fr.arinonia.jobs.JobsPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;
import java.util.List;

public class PlayerCapabilities {
    /**
     * Disables player damage.
     */
    public boolean disableDamage;

    /**
     * Sets/indicates whether the player is flying.
     */
    public boolean isFlying;

    /**
     * whether or not to allow the player to fly when they double jump.
     */
    public boolean allowFlying;

    /**
     * Used to determine if creative mode is enabled, and therefore if items should be depleted on usage
     */
    public boolean isCreativeMode;

    /**
     * Indicates whether the player is allowed to modify the surroundings
     */
    public boolean allowEdit = true;
    private float flySpeed = 0.05F;
    private float walkSpeed = 0.1F;

    private List<JobsPlayer> jobs = new ArrayList<JobsPlayer>();
    public void writeCapabilitiesToNBT(final NBTTagCompound nbtTagCompound) {
        NBTTagCompound var2 = new NBTTagCompound();
        var2.setBoolean("invulnerable", this.disableDamage);
        var2.setBoolean("flying", this.isFlying);
        var2.setBoolean("mayfly", this.allowFlying);
        var2.setBoolean("instabuild", this.isCreativeMode);
        var2.setBoolean("mayBuild", this.allowEdit);
        var2.setFloat("flySpeed", this.flySpeed);
        var2.setFloat("walkSpeed", this.walkSpeed);

        final NBTTagList list3 = new NBTTagList();
        for (final JobsPlayer job : jobs) {
            list3.appendTag(job.serializeNBT());
        }

        nbtTagCompound.setTag("abilities", var2);
        nbtTagCompound.setTag("jobs", list3);

    }

    public void readCapabilitiesFromNBT(NBTTagCompound nbtTagCompound) {
        if (nbtTagCompound.func_150297_b("abilities", 10)) {
            NBTTagCompound var2 = nbtTagCompound.getCompoundTag("abilities");
            this.disableDamage = var2.getBoolean("invulnerable");
            this.isFlying = var2.getBoolean("flying");
            this.allowFlying = var2.getBoolean("mayfly");
            this.isCreativeMode = var2.getBoolean("instabuild");

            if (var2.func_150297_b("flySpeed", 99)) {
                this.flySpeed = var2.getFloat("flySpeed");
                this.walkSpeed = var2.getFloat("walkSpeed");
            }

            if (var2.func_150297_b("mayBuild", 1)) {
                this.allowEdit = var2.getBoolean("mayBuild");
            }
        }

        final NBTTagList jobList = nbtTagCompound.getTagList("jobs", 10);
        for (int i = 0; i < jobList.tagCount(); i++) {
            final JobsPlayer job = new JobsPlayer();
            job.deserializeNBT(jobList.getCompoundTagAt(i));
            this.jobs.add(job);
        }
    }

    public float getFlySpeed() {
        return this.flySpeed;
    }

    public void setFlySpeed(float p_75092_1_) {
        this.flySpeed = p_75092_1_;
    }

    public float getWalkSpeed() {
        return this.walkSpeed;
    }

    public void setPlayerWalkSpeed(float p_82877_1_) {
        this.walkSpeed = p_82877_1_;
    }

    public List<JobsPlayer> getJobs() {
        return this.jobs;
    }

    public void setJobs(final List<JobsPlayer> jobs) {
        this.jobs = jobs;
    }

    public void addJobs(final JobsPlayer jobs) {
        this.jobs.add(jobs);
    }
}
