package com.lastgeneration.product.service.impl;

import com.lastgeneration.product.service.TutorService;
import com.lastgeneration.product.domain.Tutor;
import com.lastgeneration.product.repository.TutorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Tutor}.
 */
@Service
@Transactional
public class TutorServiceImpl implements TutorService {

    private final Logger log = LoggerFactory.getLogger(TutorServiceImpl.class);

    private final TutorRepository tutorRepository;

    public TutorServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public Tutor save(Tutor tutor) {
        log.debug("Request to save Tutor : {}", tutor);
        return tutorRepository.save(tutor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tutor> findAll() {
        log.debug("Request to get all Tutors");
        return tutorRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Tutor> findOne(Long id) {
        log.debug("Request to get Tutor : {}", id);
        return tutorRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tutor : {}", id);
        tutorRepository.deleteById(id);
    }
}
