package com.artconnect.artistservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artconnect.artistservice.dto.ArtistRequestDTO;
import com.artconnect.artistservice.dto.ArtistResponseDTO;
import com.artconnect.artistservice.service.ArtistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/artists")
@RequiredArgsConstructor
public class ArtistController {

	private final ArtistService artistService;

	// ✅ Register Artist (Public)
	@PostMapping("/register")
	public ResponseEntity<ArtistResponseDTO> register(@RequestBody ArtistRequestDTO dto) {
		return ResponseEntity.ok(artistService.registerArtist(dto));
	}

	// 🔐 Get by ID
	@GetMapping("/{id}")
	public ResponseEntity<ArtistResponseDTO> getById(@PathVariable Long id) {
		return ResponseEntity.ok(artistService.getArtistById(id));
	}

	// 🔐 Get all
	@GetMapping
	public ResponseEntity<List<ArtistResponseDTO>> getAll() {
		return ResponseEntity.ok(artistService.getAllArtists());
	}

	// 🔐 Update
	@PutMapping("/{id}")
	public ResponseEntity<ArtistResponseDTO> update(@PathVariable Long id, @RequestBody ArtistRequestDTO dto) {

		return ResponseEntity.ok(artistService.updateArtist(id, dto));
	}

	// 🔐 Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		artistService.deleteArtist(id);
		return ResponseEntity.ok("Artist deleted successfully");
	}
	
	@GetMapping("/by-email")
	public ResponseEntity<ArtistResponseDTO> getByEmail(@RequestParam String email) {
	    return ResponseEntity.ok(artistService.getArtistByEmail(email));
	}
}
