package com.lastgeneration.product.repository;

import com.lastgeneration.product.domain.GeneralTimeTableElement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GeneralTimeTableElement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeneralTimeTableElementRepository extends JpaRepository<GeneralTimeTableElement, Long> {
}
