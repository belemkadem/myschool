package com.lastgeneration.product.service;

import com.lastgeneration.product.domain.TutorType;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TutorType}.
 */
public interface TutorTypeService {

    /**
     * Save a tutorType.
     *
     * @param tutorType the entity to save.
     * @return the persisted entity.
     */
    TutorType save(TutorType tutorType);

    /**
     * Get all the tutorTypes.
     *
     * @return the list of entities.
     */
    List<TutorType> findAll();


    /**
     * Get the "id" tutorType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TutorType> findOne(Long id);

    /**
     * Delete the "id" tutorType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
