package fr.arinonia.jobs;

import fr.arinonia.network.PacketPlayerJobs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class JobHelper {

	public static int[] xpRequired = {

			0, 271, 905, 1911, 3297, 5078, 7280, 9898, 12985, 16564, 20678, 25353, 30656, 36641, 43378, 50976, 59437, 68960, 79640, 91624, 105082, 120215, 137256, 156481, 178214, 202839, 230808, 262660, 299037, 340705

	};

	public static void addXpToJobs(EntityPlayer player, EnumJobs jobs, int xp) {
		if (!player.worldObj.isClient) {
			addJobsToPlayer(player, jobs);
			for (JobsPlayer p : player.playerJobs.jobs) {
				if (p.getJobs() != null) {
					if (p.getJobs().getJobs() == jobs) {
						if (p.getLevel() < 30) {
							p.addXp(xp);
							upPlayer(player, p);
						}
					}
				}
			}
		}
	}

	private static void upPlayer(EntityPlayer player, JobsPlayer jobs) {
		int lvl = jobs.getLevel();
		int xp = jobs.getXp();
		if (lvl < 30) {
			if (xp >= xpRequired[lvl] - xpRequired[lvl - 1]) {
				xp = xp - (xpRequired[lvl] - xpRequired[lvl - 1]);
				jobs.addlevel(1);
				jobs.setXp(xp);
				if (player instanceof EntityPlayerMP) {
					((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new PacketPlayerJobs(player.playerJobs));
					//((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new PacketSendNotification("Votre métier monte niveau " + jobs.getLevel()));
				}
			}
		}
	}

	public static boolean addJobsToPlayer(EntityPlayer player, EnumJobs jobs) {
		if (!hasJobs(player, jobs)) {
			switch (jobs) {
			case FARMER:
				player.playerJobs.jobs.add(new JobsPlayer(JobsRegister.farmer));
				return true;
			case FISHER:
				player.playerJobs.jobs.add(new JobsPlayer(JobsRegister.fisher));
				return true;
			case MINER:
				player.playerJobs.jobs.add(new JobsPlayer(JobsRegister.miner));
				return true;
			}
			return false;
		}
		return true;
	}

	public static JobsPlayer getJob(EntityPlayer player, EnumJobs jobs) {
		addJobsToPlayer(player, jobs);
		for (JobsPlayer p : player.playerJobs.jobs) {
			if (p.getJobs() != null)
				if (p.getJobs().getJobs() == jobs) {
					return p;
				}
		}
		return null;
	}

	public static boolean hasJobs(EntityPlayer player, EnumJobs jobs) {
		for (JobsPlayer p : player.playerJobs.jobs) {
			if (p.getJobs() != null)
				if (p.getJobs().getJobs() == jobs) {
					return true;
				}
		}
		return false;
	}

}
