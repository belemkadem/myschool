package com.lastgeneration.product.service;

import com.lastgeneration.product.domain.GradeClass;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link GradeClass}.
 */
public interface GradeClassService {

    /**
     * Save a gradeClass.
     *
     * @param gradeClass the entity to save.
     * @return the persisted entity.
     */
    GradeClass save(GradeClass gradeClass);

    /**
     * Get all the gradeClasses.
     *
     * @return the list of entities.
     */
    List<GradeClass> findAll();


    /**
     * Get the "id" gradeClass.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GradeClass> findOne(Long id);

    /**
     * Delete the "id" gradeClass.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
