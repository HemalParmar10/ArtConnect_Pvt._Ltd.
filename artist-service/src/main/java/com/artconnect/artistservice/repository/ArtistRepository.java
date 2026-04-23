package com.artconnect.artistservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artconnect.artistservice.entity.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

	Optional<Artist> findByEmail(String email);

	boolean existsByEmail(String email);
}
