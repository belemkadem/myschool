package com.lastgeneration.product.repository;

import com.lastgeneration.product.domain.GradeClass;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GradeClass entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GradeClassRepository extends JpaRepository<GradeClass, Long> {
}
