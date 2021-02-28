package com.lastgeneration.product.service;

import com.lastgeneration.product.domain.GeneralTimeTableElement;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link GeneralTimeTableElement}.
 */
public interface GeneralTimeTableElementService {

    /**
     * Save a generalTimeTableElement.
     *
     * @param generalTimeTableElement the entity to save.
     * @return the persisted entity.
     */
    GeneralTimeTableElement save(GeneralTimeTableElement generalTimeTableElement);

    /**
     * Get all the generalTimeTableElements.
     *
     * @return the list of entities.
     */
    List<GeneralTimeTableElement> findAll();


    /**
     * Get the "id" generalTimeTableElement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GeneralTimeTableElement> findOne(Long id);

    /**
     * Delete the "id" generalTimeTableElement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
