package com.lastgeneration.product.web.rest;

import com.lastgeneration.product.domain.GeneralTimeTable;
import com.lastgeneration.product.service.GeneralTimeTableService;
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
 * REST controller for managing {@link com.lastgeneration.product.domain.GeneralTimeTable}.
 */
@RestController
@RequestMapping("/api")
public class GeneralTimeTableResource {

    private final Logger log = LoggerFactory.getLogger(GeneralTimeTableResource.class);

    private static final String ENTITY_NAME = "generalTimeTable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeneralTimeTableService generalTimeTableService;

    public GeneralTimeTableResource(GeneralTimeTableService generalTimeTableService) {
        this.generalTimeTableService = generalTimeTableService;
    }

    /**
     * {@code POST  /general-time-tables} : Create a new generalTimeTable.
     *
     * @param generalTimeTable the generalTimeTable to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new generalTimeTable, or with status {@code 400 (Bad Request)} if the generalTimeTable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/general-time-tables")
    public ResponseEntity<GeneralTimeTable> createGeneralTimeTable(@RequestBody GeneralTimeTable generalTimeTable) throws URISyntaxException {
        log.debug("REST request to save GeneralTimeTable : {}", generalTimeTable);
        if (generalTimeTable.getId() != null) {
            throw new BadRequestAlertException("A new generalTimeTable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeneralTimeTable result = generalTimeTableService.save(generalTimeTable);
        return ResponseEntity.created(new URI("/api/general-time-tables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /general-time-tables} : Updates an existing generalTimeTable.
     *
     * @param generalTimeTable the generalTimeTable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated generalTimeTable,
     * or with status {@code 400 (Bad Request)} if the generalTimeTable is not valid,
     * or with status {@code 500 (Internal Server Error)} if the generalTimeTable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/general-time-tables")
    public ResponseEntity<GeneralTimeTable> updateGeneralTimeTable(@RequestBody GeneralTimeTable generalTimeTable) throws URISyntaxException {
        log.debug("REST request to update GeneralTimeTable : {}", generalTimeTable);
        if (generalTimeTable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeneralTimeTable result = generalTimeTableService.save(generalTimeTable);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, generalTimeTable.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /general-time-tables} : get all the generalTimeTables.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of generalTimeTables in body.
     */
    @GetMapping("/general-time-tables")
    public List<GeneralTimeTable> getAllGeneralTimeTables() {
        log.debug("REST request to get all GeneralTimeTables");
        return generalTimeTableService.findAll();
    }

    /**
     * {@code GET  /general-time-tables/:id} : get the "id" generalTimeTable.
     *
     * @param id the id of the generalTimeTable to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the generalTimeTable, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/general-time-tables/{id}")
    public ResponseEntity<GeneralTimeTable> getGeneralTimeTable(@PathVariable Long id) {
        log.debug("REST request to get GeneralTimeTable : {}", id);
        Optional<GeneralTimeTable> generalTimeTable = generalTimeTableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(generalTimeTable);
    }

    /**
     * {@code DELETE  /general-time-tables/:id} : delete the "id" generalTimeTable.
     *
     * @param id the id of the generalTimeTable to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/general-time-tables/{id}")
    public ResponseEntity<Void> deleteGeneralTimeTable(@PathVariable Long id) {
        log.debug("REST request to delete GeneralTimeTable : {}", id);
        generalTimeTableService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
