package com.example.musicdb.service;

import com.example.musicdb.model.entity.Artist;
import com.example.musicdb.model.enums.ArtistNameEnum;

public interface ArtistService {
    void initializeArtists();

    Artist findArtistBy(ArtistNameEnum name);
}
