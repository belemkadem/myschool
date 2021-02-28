package com.lastgeneration.product.service;

import com.lastgeneration.product.domain.GeneralTimeTable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link GeneralTimeTable}.
 */
public interface GeneralTimeTableService {

    /**
     * Save a generalTimeTable.
     *
     * @param generalTimeTable the entity to save.
     * @return the persisted entity.
     */
    GeneralTimeTable save(GeneralTimeTable generalTimeTable);

    /**
     * Get all the generalTimeTables.
     *
     * @return the list of entities.
     */
    List<GeneralTimeTable> findAll();


    /**
     * Get the "id" generalTimeTable.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GeneralTimeTable> findOne(Long id);

    /**
     * Delete the "id" generalTimeTable.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
