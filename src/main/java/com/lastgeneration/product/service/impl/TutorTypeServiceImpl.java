package com.lastgeneration.product.service.impl;

import com.lastgeneration.product.service.TutorTypeService;
import com.lastgeneration.product.domain.TutorType;
import com.lastgeneration.product.repository.TutorTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TutorType}.
 */
@Service
@Transactional
public class TutorTypeServiceImpl implements TutorTypeService {

    private final Logger log = LoggerFactory.getLogger(TutorTypeServiceImpl.class);

    private final TutorTypeRepository tutorTypeRepository;

    public TutorTypeServiceImpl(TutorTypeRepository tutorTypeRepository) {
        this.tutorTypeRepository = tutorTypeRepository;
    }

    @Override
    public TutorType save(TutorType tutorType) {
        log.debug("Request to save TutorType : {}", tutorType);
        return tutorTypeRepository.save(tutorType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TutorType> findAll() {
        log.debug("Request to get all TutorTypes");
        return tutorTypeRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TutorType> findOne(Long id) {
        log.debug("Request to get TutorType : {}", id);
        return tutorTypeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TutorType : {}", id);
        tutorTypeRepository.deleteById(id);
    }
}
