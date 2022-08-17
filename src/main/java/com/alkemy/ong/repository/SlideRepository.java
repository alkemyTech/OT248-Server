package com.alkemy.ong.repository;

import com.alkemy.ong.model.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {


    @Query("SELECT s FROM Slide s Where s.organization.idOrganization=?1 ORDER BY s.position")
    List<Slide> findByOrganizationId(Long id);

    @Query("SELECT MAX(s.position) FROM Slide s")
    public Integer lastPosition();

}
