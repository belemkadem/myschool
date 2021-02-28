package com.lastgeneration.product.web.rest;

import com.lastgeneration.product.MyschoolApp;
import com.lastgeneration.product.domain.GeneralTimeTableElement;
import com.lastgeneration.product.repository.GeneralTimeTableElementRepository;
import com.lastgeneration.product.service.GeneralTimeTableElementService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lastgeneration.product.domain.enumeration.DayOfWeek;
/**
 * Integration tests for the {@link GeneralTimeTableElementResource} REST controller.
 */
@SpringBootTest(classes = MyschoolApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GeneralTimeTableElementResourceIT {

    private static final DayOfWeek DEFAULT_DAY_OF_WEEK = DayOfWeek.MONDAY;
    private static final DayOfWeek UPDATED_DAY_OF_WEEK = DayOfWeek.TUESDAY;

    private static final Integer DEFAULT_HOURE_FROM = 0;
    private static final Integer UPDATED_HOURE_FROM = 1;

    private static final Integer DEFAULT_MINUTE_FROM = 0;
    private static final Integer UPDATED_MINUTE_FROM = 1;

    private static final Integer DEFAULT_HOURE_TO = 0;
    private static final Integer UPDATED_HOURE_TO = 1;

    private static final Integer DEFAULT_MINUTE_TO = 0;
    private static final Integer UPDATED_MINUTE_TO = 1;

    @Autowired
    private GeneralTimeTableElementRepository generalTimeTableElementRepository;

    @Autowired
    private GeneralTimeTableElementService generalTimeTableElementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGeneralTimeTableElementMockMvc;

    private GeneralTimeTableElement generalTimeTableElement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeneralTimeTableElement createEntity(EntityManager em) {
        GeneralTimeTableElement generalTimeTableElement = new GeneralTimeTableElement()
            .dayOfWeek(DEFAULT_DAY_OF_WEEK)
            .houreFrom(DEFAULT_HOURE_FROM)
            .minuteFrom(DEFAULT_MINUTE_FROM)
            .houreTo(DEFAULT_HOURE_TO)
            .minuteTo(DEFAULT_MINUTE_TO);
        return generalTimeTableElement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeneralTimeTableElement createUpdatedEntity(EntityManager em) {
        GeneralTimeTableElement generalTimeTableElement = new GeneralTimeTableElement()
            .dayOfWeek(UPDATED_DAY_OF_WEEK)
            .houreFrom(UPDATED_HOURE_FROM)
            .minuteFrom(UPDATED_MINUTE_FROM)
            .houreTo(UPDATED_HOURE_TO)
            .minuteTo(UPDATED_MINUTE_TO);
        return generalTimeTableElement;
    }

    @BeforeEach
    public void initTest() {
        generalTimeTableElement = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeneralTimeTableElement() throws Exception {
        int databaseSizeBeforeCreate = generalTimeTableElementRepository.findAll().size();
        // Create the GeneralTimeTableElement
        restGeneralTimeTableElementMockMvc.perform(post("/api/general-time-table-elements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(generalTimeTableElement)))
            .andExpect(status().isCreated());

        // Validate the GeneralTimeTableElement in the database
        List<GeneralTimeTableElement> generalTimeTableElementList = generalTimeTableElementRepository.findAll();
        assertThat(generalTimeTableElementList).hasSize(databaseSizeBeforeCreate + 1);
        GeneralTimeTableElement testGeneralTimeTableElement = generalTimeTableElementList.get(generalTimeTableElementList.size() - 1);
        assertThat(testGeneralTimeTableElement.getDayOfWeek()).isEqualTo(DEFAULT_DAY_OF_WEEK);
        assertThat(testGeneralTimeTableElement.getHoureFrom()).isEqualTo(DEFAULT_HOURE_FROM);
        assertThat(testGeneralTimeTableElement.getMinuteFrom()).isEqualTo(DEFAULT_MINUTE_FROM);
        assertThat(testGeneralTimeTableElement.getHoureTo()).isEqualTo(DEFAULT_HOURE_TO);
        assertThat(testGeneralTimeTableElement.getMinuteTo()).isEqualTo(DEFAULT_MINUTE_TO);
    }

    @Test
    @Transactional
    public void createGeneralTimeTableElementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = generalTimeTableElementRepository.findAll().size();

        // Create the GeneralTimeTableElement with an existing ID
        generalTimeTableElement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeneralTimeTableElementMockMvc.perform(post("/api/general-time-table-elements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(generalTimeTableElement)))
            .andExpect(status().isBadRequest());

        // Validate the GeneralTimeTableElement in the database
        List<GeneralTimeTableElement> generalTimeTableElementList = generalTimeTableElementRepository.findAll();
        assertThat(generalTimeTableElementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeneralTimeTableElements() throws Exception {
        // Initialize the database
        generalTimeTableElementRepository.saveAndFlush(generalTimeTableElement);

        // Get all the generalTimeTableElementList
        restGeneralTimeTableElementMockMvc.perform(get("/api/general-time-table-elements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(generalTimeTableElement.getId().intValue())))
            .andExpect(jsonPath("$.[*].dayOfWeek").value(hasItem(DEFAULT_DAY_OF_WEEK.toString())))
            .andExpect(jsonPath("$.[*].houreFrom").value(hasItem(DEFAULT_HOURE_FROM)))
            .andExpect(jsonPath("$.[*].minuteFrom").value(hasItem(DEFAULT_MINUTE_FROM)))
            .andExpect(jsonPath("$.[*].houreTo").value(hasItem(DEFAULT_HOURE_TO)))
            .andExpect(jsonPath("$.[*].minuteTo").value(hasItem(DEFAULT_MINUTE_TO)));
    }
    
    @Test
    @Transactional
    public void getGeneralTimeTableElement() throws Exception {
        // Initialize the database
        generalTimeTableElementRepository.saveAndFlush(generalTimeTableElement);

        // Get the generalTimeTableElement
        restGeneralTimeTableElementMockMvc.perform(get("/api/general-time-table-elements/{id}", generalTimeTableElement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(generalTimeTableElement.getId().intValue()))
            .andExpect(jsonPath("$.dayOfWeek").value(DEFAULT_DAY_OF_WEEK.toString()))
            .andExpect(jsonPath("$.houreFrom").value(DEFAULT_HOURE_FROM))
            .andExpect(jsonPath("$.minuteFrom").value(DEFAULT_MINUTE_FROM))
            .andExpect(jsonPath("$.houreTo").value(DEFAULT_HOURE_TO))
            .andExpect(jsonPath("$.minuteTo").value(DEFAULT_MINUTE_TO));
    }
    @Test
    @Transactional
    public void getNonExistingGeneralTimeTableElement() throws Exception {
        // Get the generalTimeTableElement
        restGeneralTimeTableElementMockMvc.perform(get("/api/general-time-table-elements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeneralTimeTableElement() throws Exception {
        // Initialize the database
        generalTimeTableElementService.save(generalTimeTableElement);

        int databaseSizeBeforeUpdate = generalTimeTableElementRepository.findAll().size();

        // Update the generalTimeTableElement
        GeneralTimeTableElement updatedGeneralTimeTableElement = generalTimeTableElementRepository.findById(generalTimeTableElement.getId()).get();
        // Disconnect from session so that the updates on updatedGeneralTimeTableElement are not directly saved in db
        em.detach(updatedGeneralTimeTableElement);
        updatedGeneralTimeTableElement
            .dayOfWeek(UPDATED_DAY_OF_WEEK)
            .houreFrom(UPDATED_HOURE_FROM)
            .minuteFrom(UPDATED_MINUTE_FROM)
            .houreTo(UPDATED_HOURE_TO)
            .minuteTo(UPDATED_MINUTE_TO);

        restGeneralTimeTableElementMockMvc.perform(put("/api/general-time-table-elements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedGeneralTimeTableElement)))
            .andExpect(status().isOk());

        // Validate the GeneralTimeTableElement in the database
        List<GeneralTimeTableElement> generalTimeTableElementList = generalTimeTableElementRepository.findAll();
        assertThat(generalTimeTableElementList).hasSize(databaseSizeBeforeUpdate);
        GeneralTimeTableElement testGeneralTimeTableElement = generalTimeTableElementList.get(generalTimeTableElementList.size() - 1);
        assertThat(testGeneralTimeTableElement.getDayOfWeek()).isEqualTo(UPDATED_DAY_OF_WEEK);
        assertThat(testGeneralTimeTableElement.getHoureFrom()).isEqualTo(UPDATED_HOURE_FROM);
        assertThat(testGeneralTimeTableElement.getMinuteFrom()).isEqualTo(UPDATED_MINUTE_FROM);
        assertThat(testGeneralTimeTableElement.getHoureTo()).isEqualTo(UPDATED_HOURE_TO);
        assertThat(testGeneralTimeTableElement.getMinuteTo()).isEqualTo(UPDATED_MINUTE_TO);
    }

    @Test
    @Transactional
    public void updateNonExistingGeneralTimeTableElement() throws Exception {
        int databaseSizeBeforeUpdate = generalTimeTableElementRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeneralTimeTableElementMockMvc.perform(put("/api/general-time-table-elements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(generalTimeTableElement)))
            .andExpect(status().isBadRequest());

        // Validate the GeneralTimeTableElement in the database
        List<GeneralTimeTableElement> generalTimeTableElementList = generalTimeTableElementRepository.findAll();
        assertThat(generalTimeTableElementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeneralTimeTableElement() throws Exception {
        // Initialize the database
        generalTimeTableElementService.save(generalTimeTableElement);

        int databaseSizeBeforeDelete = generalTimeTableElementRepository.findAll().size();

        // Delete the generalTimeTableElement
        restGeneralTimeTableElementMockMvc.perform(delete("/api/general-time-table-elements/{id}", generalTimeTableElement.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeneralTimeTableElement> generalTimeTableElementList = generalTimeTableElementRepository.findAll();
        assertThat(generalTimeTableElementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
