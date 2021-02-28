package com.lastgeneration.product.repository;

import com.lastgeneration.product.domain.TutorType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TutorType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TutorTypeRepository extends JpaRepository<TutorType, Long> {
}
