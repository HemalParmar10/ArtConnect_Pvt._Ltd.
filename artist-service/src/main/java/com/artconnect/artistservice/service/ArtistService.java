package com.artconnect.artistservice.service;

import java.util.List;

import com.artconnect.artistservice.dto.ArtistRequestDTO;
import com.artconnect.artistservice.dto.ArtistResponseDTO;

public interface ArtistService {

	ArtistResponseDTO registerArtist(ArtistRequestDTO dto);

	ArtistResponseDTO getArtistById(Long id);

	List<ArtistResponseDTO> getAllArtists();

	ArtistResponseDTO updateArtist(Long id, ArtistRequestDTO dto);

	void deleteArtist(Long id);

	ArtistResponseDTO getArtistByEmail(String email);
}
