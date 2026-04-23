package com.artconnect.licenseservice.repository;

import com.artconnect.licenseservice.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LicenseRepository extends JpaRepository<License, Long> {

    List<License> findByArtistId(Long artistId);
}
