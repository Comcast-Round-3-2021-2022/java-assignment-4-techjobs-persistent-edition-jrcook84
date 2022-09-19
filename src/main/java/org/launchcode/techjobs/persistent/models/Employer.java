package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank(message="Can't be Blank, I mean sheesh!!")
    @Size(min=3, max=550, message="Must be between 3 and 550 characters, either enter more or enter less accordingly. ")
    private String location;

    @OneToMany
    //got this from root add.html
    //one employer can have many jobs
    @JoinColumn(name = "employer_id")
    private List<Job> jobs = new ArrayList<>();
    // no arg allows hibernate to make a object
    public Employer(){}
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
