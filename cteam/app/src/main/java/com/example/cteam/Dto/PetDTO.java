package com.example.cteam.Dto;

import java.io.Serializable;

/**
 * Created by LG on 2017-02-08.
 */

public class PetDTO implements Serializable {
    public String petname;
    public String petage;
    public String petweight;
    public String petgender;
    public String petimage_path;

    public PetDTO(String patname, String petage, String petweight, String petgender, String petimage_path) {
        this.petname = patname;
        this.petage = petage;
        this.petweight = petweight;
        this.petgender = petgender;
        this.petimage_path = petimage_path;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String patname) {
        this.petname = patname;
    }

    public String getPetage() {
        return petage;
    }

    public void setPetage(String petage) {
        this.petage = petage;
    }

    public String getPetweight() {
        return petweight;
    }

    public void setPetweight(String petweight) {
        this.petweight = petweight;
    }

    public String getPetgender() {
        return petgender;
    }

    public void setPetgender(String petgender) {
        this.petgender = petgender;
    }

    public String getPetimage_path() {
        return petimage_path;
    }

    public void setPetimage_path(String petimage_path) {
        this.petimage_path = petimage_path;
    }
}
