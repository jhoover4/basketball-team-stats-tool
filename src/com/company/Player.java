package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Player {
    private HashMap rawData;
    private int height;
    private String name;
    private boolean experience;
    private List guardians;

    Player(HashMap rawData) {
        this.rawData = rawData;
        this.setName();
        this.setHeight();
        this.setExperience();
        this.setGuardians();
    }

    private void setName() {
        this.name = (String) this.rawData.get("name");
    }

    String getName() {
        return this.name;
    }

    private void setHeight() {
        String heightStr = (String) this.rawData.get("height");
        this.height = Integer.parseInt(heightStr.replace(" inches", ""));
    }

    int getHeight() {
        return this.height;
    }

    private void setExperience() {
        String expString = (String) this.rawData.get("experience");
        this.experience = expString.toLowerCase().equals("yes");
    }

    boolean hasExperience() {
        return experience;
    }

    private void setGuardians() {
        String guardians = (String) this.rawData.get("guardians");
        this.guardians = Arrays.asList(guardians.split(" and "));
    }

    List getGuardians() {
        return guardians;
    }
}
