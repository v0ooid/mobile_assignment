package my.edu.tarc.jobseek.ui.headHunting;

import java.lang.reflect.Array;

public class Candidate {
    String name;
    String position;
    String location;
    Array skills;

    public Candidate(String name, String position,
                     String location, Array skills) {
        this.name = name;
        this.position = position;
        this.location = location;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getLocation() {
        return location;
    }

    public Array getSkills() {
        return skills;
    }
}
