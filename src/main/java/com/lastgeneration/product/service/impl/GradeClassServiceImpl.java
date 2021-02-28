package com.lastgeneration.product.service.impl;

import com.lastgeneration.product.service.GradeClassService;
import com.lastgeneration.product.domain.GradeClass;
import com.lastgeneration.product.repository.GradeClassRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link GradeClass}.
 */
@Service
@Transactional
public class GradeClassServiceImpl implements GradeClassService {

    private final Logger log = LoggerFactory.getLogger(GradeClassServiceImpl.class);

    private final GradeClassRepository gradeClassRepository;

    public GradeClassServiceImpl(GradeClassRepository gradeClassRepository) {
        this.gradeClassRepository = gradeClassRepository;
    }

    @Override
    public GradeClass save(GradeClass gradeClass) {
        log.debug("Request to save GradeClass : {}", gradeClass);
        return gradeClassRepository.save(gradeClass);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GradeClass> findAll() {
        log.debug("Request to get all GradeClasses");
        return gradeClassRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<GradeClass> findOne(Long id) {
        log.debug("Request to get GradeClass : {}", id);
        return gradeClassRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete GradeClass : {}", id);
        gradeClassRepository.deleteById(id);
    }
}
