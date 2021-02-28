package com.lastgeneration.product.web.rest;

import com.lastgeneration.product.domain.GeneralTimeTableElement;
import com.lastgeneration.product.service.GeneralTimeTableElementService;
import com.lastgeneration.product.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lastgeneration.product.domain.GeneralTimeTableElement}.
 */
@RestController
@RequestMapping("/api")
public class GeneralTimeTableElementResource {

    private final Logger log = LoggerFactory.getLogger(GeneralTimeTableElementResource.class);

    private static final String ENTITY_NAME = "generalTimeTableElement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeneralTimeTableElementService generalTimeTableElementService;

    public GeneralTimeTableElementResource(GeneralTimeTableElementService generalTimeTableElementService) {
        this.generalTimeTableElementService = generalTimeTableElementService;
    }

    /**
     * {@code POST  /general-time-table-elements} : Create a new generalTimeTableElement.
     *
     * @param generalTimeTableElement the generalTimeTableElement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new generalTimeTableElement, or with status {@code 400 (Bad Request)} if the generalTimeTableElement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/general-time-table-elements")
    public ResponseEntity<GeneralTimeTableElement> createGeneralTimeTableElement(@Valid @RequestBody GeneralTimeTableElement generalTimeTableElement) throws URISyntaxException {
        log.debug("REST request to save GeneralTimeTableElement : {}", generalTimeTableElement);
        if (generalTimeTableElement.getId() != null) {
            throw new BadRequestAlertException("A new generalTimeTableElement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeneralTimeTableElement result = generalTimeTableElementService.save(generalTimeTableElement);
        return ResponseEntity.created(new URI("/api/general-time-table-elements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /general-time-table-elements} : Updates an existing generalTimeTableElement.
     *
     * @param generalTimeTableElement the generalTimeTableElement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated generalTimeTableElement,
     * or with status {@code 400 (Bad Request)} if the generalTimeTableElement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the generalTimeTableElement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/general-time-table-elements")
    public ResponseEntity<GeneralTimeTableElement> updateGeneralTimeTableElement(@Valid @RequestBody GeneralTimeTableElement generalTimeTableElement) throws URISyntaxException {
        log.debug("REST request to update GeneralTimeTableElement : {}", generalTimeTableElement);
        if (generalTimeTableElement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeneralTimeTableElement result = generalTimeTableElementService.save(generalTimeTableElement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, generalTimeTableElement.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /general-time-table-elements} : get all the generalTimeTableElements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of generalTimeTableElements in body.
     */
    @GetMapping("/general-time-table-elements")
    public List<GeneralTimeTableElement> getAllGeneralTimeTableElements() {
        log.debug("REST request to get all GeneralTimeTableElements");
        return generalTimeTableElementService.findAll();
    }

    /**
     * {@code GET  /general-time-table-elements/:id} : get the "id" generalTimeTableElement.
     *
     * @param id the id of the generalTimeTableElement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the generalTimeTableElement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/general-time-table-elements/{id}")
    public ResponseEntity<GeneralTimeTableElement> getGeneralTimeTableElement(@PathVariable Long id) {
        log.debug("REST request to get GeneralTimeTableElement : {}", id);
        Optional<GeneralTimeTableElement> generalTimeTableElement = generalTimeTableElementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(generalTimeTableElement);
    }

    /**
     * {@code DELETE  /general-time-table-elements/:id} : delete the "id" generalTimeTableElement.
     *
     * @param id the id of the generalTimeTableElement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/general-time-table-elements/{id}")
    public ResponseEntity<Void> deleteGeneralTimeTableElement(@PathVariable Long id) {
        log.debug("REST request to delete GeneralTimeTableElement : {}", id);
        generalTimeTableElementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
