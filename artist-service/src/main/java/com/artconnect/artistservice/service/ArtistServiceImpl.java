package com.artconnect.artistservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.artconnect.artistservice.dto.ArtistRequestDTO;
import com.artconnect.artistservice.dto.ArtistResponseDTO;
import com.artconnect.artistservice.entity.Artist;
import com.artconnect.artistservice.entity.Role;
import com.artconnect.artistservice.entity.RoleName;
import com.artconnect.artistservice.repository.ArtistRepository;
import com.artconnect.artistservice.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ArtistResponseDTO registerArtist(ArtistRequestDTO dto) {

        // 1. Check duplicate email
        if (artistRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        // 2. Fetch Role from DB
        Role role = roleRepository.findByName(RoleName.ROLE_ARTIST)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // 3. Encode password
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        // 4. Create Artist entity
        Artist artist = Artist.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(encodedPassword)
                .contact(dto.getContact())
                .city(dto.getCity())
                .bio(dto.getBio())
                .artistType(dto.getArtistType())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .roles(Set.of(role))
                .build();

        // 5. Save
        Artist saved = artistRepository.save(artist);

        // 6. Convert to DTO
        return mapToDTO(saved);
    }

    @Override
    public ArtistResponseDTO getArtistById(Long id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        return mapToDTO(artist);
    }

    @Override
    public List<ArtistResponseDTO> getAllArtists() {
        return artistRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public ArtistResponseDTO updateArtist(Long id, ArtistRequestDTO dto) {

        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        artist.setUsername(dto.getUsername());
        artist.setContact(dto.getContact());
        artist.setCity(dto.getCity());
        artist.setBio(dto.getBio());
        artist.setArtistType(dto.getArtistType());
        artist.setUpdatedAt(LocalDateTime.now());

        return mapToDTO(artistRepository.save(artist));
    }

    @Override
    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }

    private ArtistResponseDTO mapToDTO(Artist artist) {
        return ArtistResponseDTO.builder()
                .id(artist.getId())
                .username(artist.getUsername())
                .email(artist.getEmail())
                .contact(artist.getContact())
                .city(artist.getCity())
                .bio(artist.getBio())
                .artistType(artist.getArtistType())
                .build();
    }
    
    @Override
    public ArtistResponseDTO getArtistByEmail(String email) {

        Artist artist = artistRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        return mapToDTO(artist);
    }
}

