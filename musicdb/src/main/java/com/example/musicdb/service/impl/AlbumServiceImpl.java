package com.example.musicdb.service.impl;


import com.example.musicdb.model.entity.Album;
import com.example.musicdb.model.service.AlbumServiceModel;
import com.example.musicdb.model.view.AlbumViewModel;
import com.example.musicdb.repository.AlbumRepository;
import com.example.musicdb.security.CurrentUser;
import com.example.musicdb.service.AlbumService;
import com.example.musicdb.service.ArtistService;
import com.example.musicdb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;
    private final ArtistService artistService;
    private final UserService userService;
    private final CurrentUser currentUser;

    public AlbumServiceImpl(AlbumRepository albumRepository, ModelMapper modelMapper, ArtistService artistService, UserService userService, CurrentUser currentUser) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
        this.artistService = artistService;
        this.userService = userService;
        this.currentUser = currentUser;
    }


    @Override
    public void addAlbum(AlbumServiceModel albumServiceModel) {
        Album albumEntity = modelMapper.map(albumServiceModel, Album.class);
        albumEntity.setAddedFrom(userService.findUserById(currentUser.getId()));
        albumEntity.setArtist(artistService.findArtistBy(albumServiceModel.getArtist()));

        albumRepository.save(albumEntity);
    }

    @Override
    public List<AlbumViewModel> findAllAlbums() {
        return albumRepository.findAll().stream()
                .map(albumEntity -> modelMapper.map(albumEntity, AlbumViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public Integer totalCopies() {
        return albumRepository.findAll().stream()
                .mapToInt(Album::getCopies)
                .reduce(Integer::sum).orElse(0);
    }

    @Override
    public void delete(Long id) {
        this.albumRepository.deleteById(id);
    }
}
