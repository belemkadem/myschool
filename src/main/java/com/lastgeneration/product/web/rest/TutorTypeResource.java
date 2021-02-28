package com.lastgeneration.product.web.rest;

import com.lastgeneration.product.domain.TutorType;
import com.lastgeneration.product.service.TutorTypeService;
import com.lastgeneration.product.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lastgeneration.product.domain.TutorType}.
 */
@RestController
@RequestMapping("/api")
public class TutorTypeResource {

    private final Logger log = LoggerFactory.getLogger(TutorTypeResource.class);

    private static final String ENTITY_NAME = "tutorType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TutorTypeService tutorTypeService;

    public TutorTypeResource(TutorTypeService tutorTypeService) {
        this.tutorTypeService = tutorTypeService;
    }

    /**
     * {@code POST  /tutor-types} : Create a new tutorType.
     *
     * @param tutorType the tutorType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tutorType, or with status {@code 400 (Bad Request)} if the tutorType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tutor-types")
    public ResponseEntity<TutorType> createTutorType(@RequestBody TutorType tutorType) throws URISyntaxException {
        log.debug("REST request to save TutorType : {}", tutorType);
        if (tutorType.getId() != null) {
            throw new BadRequestAlertException("A new tutorType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TutorType result = tutorTypeService.save(tutorType);
        return ResponseEntity.created(new URI("/api/tutor-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tutor-types} : Updates an existing tutorType.
     *
     * @param tutorType the tutorType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tutorType,
     * or with status {@code 400 (Bad Request)} if the tutorType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tutorType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tutor-types")
    public ResponseEntity<TutorType> updateTutorType(@RequestBody TutorType tutorType) throws URISyntaxException {
        log.debug("REST request to update TutorType : {}", tutorType);
        if (tutorType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TutorType result = tutorTypeService.save(tutorType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tutorType.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tutor-types} : get all the tutorTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tutorTypes in body.
     */
    @GetMapping("/tutor-types")
    public List<TutorType> getAllTutorTypes() {
        log.debug("REST request to get all TutorTypes");
        return tutorTypeService.findAll();
    }

    /**
     * {@code GET  /tutor-types/:id} : get the "id" tutorType.
     *
     * @param id the id of the tutorType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tutorType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tutor-types/{id}")
    public ResponseEntity<TutorType> getTutorType(@PathVariable Long id) {
        log.debug("REST request to get TutorType : {}", id);
        Optional<TutorType> tutorType = tutorTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tutorType);
    }

    /**
     * {@code DELETE  /tutor-types/:id} : delete the "id" tutorType.
     *
     * @param id the id of the tutorType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tutor-types/{id}")
    public ResponseEntity<Void> deleteTutorType(@PathVariable Long id) {
        log.debug("REST request to delete TutorType : {}", id);
        tutorTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
