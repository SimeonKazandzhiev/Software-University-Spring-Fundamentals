package com.example.musicdb.model.entity;


import com.example.musicdb.model.enums.ArtistNameEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "artists")
public class Artist extends BaseEntity {

    private ArtistNameEnum name;
    private String information;

    public Artist() {
    }

    public Artist(ArtistNameEnum name, String information) {
        this.name = name;
        this.information = information;
    }

    @Enumerated(EnumType.STRING)
    public ArtistNameEnum getName() {
        return name;
    }

    public void setName(ArtistNameEnum name) {
        this.name = name;
    }
    @Column(name = "information",columnDefinition = "LONGTEXT")
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
