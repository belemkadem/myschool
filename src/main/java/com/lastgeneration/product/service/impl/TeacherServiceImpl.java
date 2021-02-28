package com.lastgeneration.product.service.impl;

import com.lastgeneration.product.service.TeacherService;
import com.lastgeneration.product.domain.Teacher;
import com.lastgeneration.product.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Teacher}.
 */
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher save(Teacher teacher) {
        log.debug("Request to save Teacher : {}", teacher);
        return teacherRepository.save(teacher);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> findAll() {
        log.debug("Request to get all Teachers");
        return teacherRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Teacher> findOne(Long id) {
        log.debug("Request to get Teacher : {}", id);
        return teacherRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Teacher : {}", id);
        teacherRepository.deleteById(id);
    }
}
