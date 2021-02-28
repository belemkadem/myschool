package com.lastgeneration.product.service.impl;

import com.lastgeneration.product.service.GeneralTimeTableService;
import com.lastgeneration.product.domain.GeneralTimeTable;
import com.lastgeneration.product.repository.GeneralTimeTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link GeneralTimeTable}.
 */
@Service
@Transactional
public class GeneralTimeTableServiceImpl implements GeneralTimeTableService {

    private final Logger log = LoggerFactory.getLogger(GeneralTimeTableServiceImpl.class);

    private final GeneralTimeTableRepository generalTimeTableRepository;

    public GeneralTimeTableServiceImpl(GeneralTimeTableRepository generalTimeTableRepository) {
        this.generalTimeTableRepository = generalTimeTableRepository;
    }

    @Override
    public GeneralTimeTable save(GeneralTimeTable generalTimeTable) {
        log.debug("Request to save GeneralTimeTable : {}", generalTimeTable);
        return generalTimeTableRepository.save(generalTimeTable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GeneralTimeTable> findAll() {
        log.debug("Request to get all GeneralTimeTables");
        return generalTimeTableRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<GeneralTimeTable> findOne(Long id) {
        log.debug("Request to get GeneralTimeTable : {}", id);
        return generalTimeTableRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete GeneralTimeTable : {}", id);
        generalTimeTableRepository.deleteById(id);
    }
}
