package com.sufu.renantangsystem.Service.impl;

import com.sufu.renantangsystem.Service.IPatientService;
import com.sufu.renantangsystem.entity.PatientEntity;
import com.sufu.renantangsystem.entity.QueryPatientVO;
import com.sufu.renantangsystem.repository.PatientRepository;
import com.sufu.renantangsystem.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by lenovo on 2018/6/18.
 */
@Service("patientService")
public class PatientServiceImpl implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<PatientEntity> findAllWithPageCondition(PatientEntity patientEntity, int pageNum, int pageSize) {
        String name = "";
        String doctorId = "";

        if (patientEntity != null) {
            name = patientEntity.getName();
            doctorId = patientEntity.getModifyUserid();
        }

        final String queryName = name;
        final String queryDoctorId = doctorId;

        Specification<PatientEntity> specification = new Specification<PatientEntity>() {
            @Override
            public Specification<PatientEntity> and(Specification<PatientEntity> other) {
                return null;
            }
            @Override
            public Specification<PatientEntity> or(Specification<PatientEntity> other) {
                return null;
            }

            @Override
            public Predicate toPredicate(Root<PatientEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(queryName)) {
                    list.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + queryName + "%"));
                }
                if (StringUtils.isNotBlank(queryDoctorId)) {
                    list.add(criteriaBuilder.equal(root.get("modifyUserid").as(String.class), queryDoctorId));
                }

                if (list.size() > 0) {
                    Predicate[] p = new Predicate[list.size()];
                    query.where(criteriaBuilder.and(list.toArray(p)));
                }
                query.orderBy(criteriaBuilder.desc(root.get("modifyTime")));

                return query.getRestriction();
            }
        };
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);

        Page<PatientEntity> all = patientRepository.findAll(specification, pageRequest);
        return all;
    }

    public List<PatientEntity> findAll() {
        Specification<PatientEntity> specification = new Specification<PatientEntity>() {
            @Override
            public Specification<PatientEntity> and(Specification<PatientEntity> other) {
                return null;
            }
            @Override
            public Specification<PatientEntity> or(Specification<PatientEntity> other) {
                return null;
            }

            @Override
            public Predicate toPredicate(Root<PatientEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.orderBy(criteriaBuilder.desc(root.get("modifyTime")));
                return query.getRestriction();
            }
        };
        List<PatientEntity> all = patientRepository.findAll(specification);
        return all;
    }

    @Override
    public List<PatientEntity> findAllByQueryPatientVO(QueryPatientVO queryPatientVO) {
        Specification<PatientEntity> specification = getPageEntitySpecificationByQueryPatientVO(queryPatientVO);
        List<PatientEntity> all = patientRepository.findAll(specification);
        return all;
    }

    @Override
    public Page<PatientEntity> findAllWithPageByQueryPatientVO(QueryPatientVO queryPatientVO) {

        Specification<PatientEntity> specification = getPageEntitySpecificationByQueryPatientVO(queryPatientVO);
        PageRequest pageRequest = PageRequest.of(queryPatientVO.getPageNum(), queryPatientVO.getPageSize());
        Page<PatientEntity> all = patientRepository.findAll(specification, pageRequest);
        return all;
    }

    private Specification<PatientEntity> getPageEntitySpecificationByQueryPatientVO(QueryPatientVO queryPatientVO){
        return  new Specification<PatientEntity>() {
            @Override
            public Specification<PatientEntity> and(Specification<PatientEntity> other) {
                return null;
            }
            @Override
            public Specification<PatientEntity> or(Specification<PatientEntity> other) {
                return null;
            }
            @Override
            public Predicate toPredicate(Root<PatientEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(queryPatientVO.getDoctorId())) {
                    list.add(criteriaBuilder.equal(root.get("modifyUserid").as(String.class), queryPatientVO.getDoctorId() ));
                }
                if (StringUtils.isNotBlank(queryPatientVO.getName())) {
                    list.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + queryPatientVO.getName() + "%"));
                }
                if (StringUtils.isNotBlank(queryPatientVO.getModifyUserid())) {
                    list.add(criteriaBuilder.equal(root.get("modifyUserid").as(String.class), queryPatientVO.getModifyUserid()));
                }

                if (queryPatientVO.getStartDate() != null) {
                    list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("modifyTime").as(Timestamp.class), queryPatientVO.getStartDate()));
                }
                if (queryPatientVO.getEndDate() != null) {
                    Timestamp endDate = queryPatientVO.getEndDate();
                    long newLongDate = endDate.getTime() + 1000 * 60 * 60 * 24;
                    Timestamp newEndDate = new Timestamp(newLongDate);
                    list.add(criteriaBuilder.lessThanOrEqualTo(root.get("modifyTime").as(Timestamp.class), newEndDate));
                }

                if (list.size() > 0) {
                    Predicate[] p = new Predicate[list.size()];
                    query.where(criteriaBuilder.and(list.toArray(p)));
                }
                query.orderBy(criteriaBuilder.desc(root.get("modifyTime")));

                return query.getRestriction();
            }
        };
    }
	@Transactional 
    public void save(PatientEntity patientEntity) {
    	patientRepository.save(patientEntity);
    }

	@Override
	public PatientEntity findPatientById(String id) {
		Optional<PatientEntity> opt = patientRepository.findById(id);
		PatientEntity patientEntity = new PatientEntity();
		if(opt!=null) {
			patientEntity = opt.get();
		}
		return patientEntity;
	}
}
