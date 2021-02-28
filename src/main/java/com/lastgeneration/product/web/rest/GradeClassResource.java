package com.lastgeneration.product.web.rest;

import com.lastgeneration.product.domain.GradeClass;
import com.lastgeneration.product.service.GradeClassService;
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
 * REST controller for managing {@link com.lastgeneration.product.domain.GradeClass}.
 */
@RestController
@RequestMapping("/api")
public class GradeClassResource {

    private final Logger log = LoggerFactory.getLogger(GradeClassResource.class);

    private static final String ENTITY_NAME = "gradeClass";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GradeClassService gradeClassService;

    public GradeClassResource(GradeClassService gradeClassService) {
        this.gradeClassService = gradeClassService;
    }

    /**
     * {@code POST  /grade-classes} : Create a new gradeClass.
     *
     * @param gradeClass the gradeClass to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gradeClass, or with status {@code 400 (Bad Request)} if the gradeClass has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/grade-classes")
    public ResponseEntity<GradeClass> createGradeClass(@RequestBody GradeClass gradeClass) throws URISyntaxException {
        log.debug("REST request to save GradeClass : {}", gradeClass);
        if (gradeClass.getId() != null) {
            throw new BadRequestAlertException("A new gradeClass cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GradeClass result = gradeClassService.save(gradeClass);
        return ResponseEntity.created(new URI("/api/grade-classes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /grade-classes} : Updates an existing gradeClass.
     *
     * @param gradeClass the gradeClass to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gradeClass,
     * or with status {@code 400 (Bad Request)} if the gradeClass is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gradeClass couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/grade-classes")
    public ResponseEntity<GradeClass> updateGradeClass(@RequestBody GradeClass gradeClass) throws URISyntaxException {
        log.debug("REST request to update GradeClass : {}", gradeClass);
        if (gradeClass.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GradeClass result = gradeClassService.save(gradeClass);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gradeClass.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /grade-classes} : get all the gradeClasses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gradeClasses in body.
     */
    @GetMapping("/grade-classes")
    public List<GradeClass> getAllGradeClasses() {
        log.debug("REST request to get all GradeClasses");
        return gradeClassService.findAll();
    }

    /**
     * {@code GET  /grade-classes/:id} : get the "id" gradeClass.
     *
     * @param id the id of the gradeClass to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gradeClass, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/grade-classes/{id}")
    public ResponseEntity<GradeClass> getGradeClass(@PathVariable Long id) {
        log.debug("REST request to get GradeClass : {}", id);
        Optional<GradeClass> gradeClass = gradeClassService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gradeClass);
    }

    /**
     * {@code DELETE  /grade-classes/:id} : delete the "id" gradeClass.
     *
     * @param id the id of the gradeClass to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/grade-classes/{id}")
    public ResponseEntity<Void> deleteGradeClass(@PathVariable Long id) {
        log.debug("REST request to delete GradeClass : {}", id);
        gradeClassService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
