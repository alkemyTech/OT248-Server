package com.alkemy.ong.repository;

import com.alkemy.ong.model.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD:src/main/java/com/alkemy/ong/repository/SlidesRepository.java
public interface SlidesRepository extends JpaRepository<Slides, Long> {
    
    @Query("SELECT MAX(s.order) FROM Slides")
    public Integer lastOrder();
=======
public interface SlideRepository extends JpaRepository<Slide, Long> {
>>>>>>> 910e257382331c81bcf12398eb3a7f062278fd32:src/main/java/com/alkemy/ong/repository/SlideRepository.java
}
