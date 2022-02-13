package com.example.musicdb.service;

import com.example.musicdb.model.service.AlbumServiceModel;
import com.example.musicdb.model.view.AlbumViewModel;

import java.util.List;

public interface AlbumService {
    void addAlbum(AlbumServiceModel albumServiceModel);
    List<AlbumViewModel> findAllAlbums();

    Integer totalCopies();

    void delete(Long id);
}
