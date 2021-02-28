package com.lastgeneration.product.repository;

import com.lastgeneration.product.domain.GeneralTimeTable;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeneralTimeTable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeneralTimeTableRepository extends JpaRepository<GeneralTimeTable, Long> {
}
