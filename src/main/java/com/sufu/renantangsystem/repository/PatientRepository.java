package com.sufu.renantangsystem.repository;

import com.sufu.renantangsystem.entity.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by lenovo on 2018/6/18.
 */
public interface PatientRepository extends JpaRepository<PatientEntity, String>, JpaSpecificationExecutor<PatientEntity> {

    Page<PatientEntity> findAll(Specification<PatientEntity> spec, Pageable pageable);

    List<PatientEntity> findAll(Specification<PatientEntity> spec);
}
