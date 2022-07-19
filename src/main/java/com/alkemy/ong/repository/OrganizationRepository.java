package com.alkemy.ong.repository;

import com.alkemy.ong.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long>  {
    Optional<Organization> findFirstByOrderByIdOrganization( );
}
