package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

private String description;

// many jobs can have many skills mapped by argument from Job class
@ManyToMany(mappedBy = "skills")
private List<Job> jobs = new ArrayList<>();

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Skill(){}
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}