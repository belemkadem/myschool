package com.lastgeneration.product.service.impl;

import com.lastgeneration.product.service.SubjectService;
import com.lastgeneration.product.domain.Subject;
import com.lastgeneration.product.repository.SubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Subject}.
 */
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private final Logger log = LoggerFactory.getLogger(SubjectServiceImpl.class);

    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject save(Subject subject) {
        log.debug("Request to save Subject : {}", subject);
        return subjectRepository.save(subject);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subject> findAll() {
        log.debug("Request to get all Subjects");
        return subjectRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Subject> findOne(Long id) {
        log.debug("Request to get Subject : {}", id);
        return subjectRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Subject : {}", id);
        subjectRepository.deleteById(id);
    }
}
