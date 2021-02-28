package com.lastgeneration.product.service.impl;

import com.lastgeneration.product.service.GeneralTimeTableElementService;
import com.lastgeneration.product.domain.GeneralTimeTableElement;
import com.lastgeneration.product.repository.GeneralTimeTableElementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link GeneralTimeTableElement}.
 */
@Service
@Transactional
public class GeneralTimeTableElementServiceImpl implements GeneralTimeTableElementService {

    private final Logger log = LoggerFactory.getLogger(GeneralTimeTableElementServiceImpl.class);

    private final GeneralTimeTableElementRepository generalTimeTableElementRepository;

    public GeneralTimeTableElementServiceImpl(GeneralTimeTableElementRepository generalTimeTableElementRepository) {
        this.generalTimeTableElementRepository = generalTimeTableElementRepository;
    }

    @Override
    public GeneralTimeTableElement save(GeneralTimeTableElement generalTimeTableElement) {
        log.debug("Request to save GeneralTimeTableElement : {}", generalTimeTableElement);
        return generalTimeTableElementRepository.save(generalTimeTableElement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GeneralTimeTableElement> findAll() {
        log.debug("Request to get all GeneralTimeTableElements");
        return generalTimeTableElementRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<GeneralTimeTableElement> findOne(Long id) {
        log.debug("Request to get GeneralTimeTableElement : {}", id);
        return generalTimeTableElementRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete GeneralTimeTableElement : {}", id);
        generalTimeTableElementRepository.deleteById(id);
    }
}
