package fr.arinonia.jobs;

import net.minecraft.util.RegistryNamespaced;

public class Jobs {

    public static final RegistryNamespaced jobs = new RegistryNamespaced();

    public String name;
    public String desc;

    public static void addJobs(final int id, final String name, final Jobs obj) {
        obj.setName(name);
        jobs.addObject(id, name, obj);
    }
    public static int getIdFromJobs(final Jobs jobs) {
        return jobs == null ? 0 : Jobs.jobs.getIDForObject(jobs);
    }

    public static Jobs getJobsById(final int id) {
        return (Jobs) jobs.getObjectForID(id);
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDesc() {
        return this.desc;
    }

    public Jobs setDesc(final String desc) {
        this.desc = desc;
        return this;
    }

}
