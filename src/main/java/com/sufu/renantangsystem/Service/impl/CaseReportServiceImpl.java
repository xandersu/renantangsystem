package com.sufu.renantangsystem.Service.impl;

import com.sufu.renantangsystem.Service.ICaseReportService;
import com.sufu.renantangsystem.entity.CaseReportEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lenovo on 2018/6/18.
 */
@Service("caseReportService")
public class CaseReportServiceImpl implements ICaseReportService {

//    @Autowired
//    private CaseReportRepository caseReportRepository;


    @Override
    public List<CaseReportEntity> findAllByPatientIdOrderByClinicTimeDesc(String patientId) {
        return null;
    }

    @Override
    @Transactional
    public CaseReportEntity save(CaseReportEntity caseReportEntity) {
        return null;
    }

    @Override
    @Transactional
    public void deleteById(String id) {

    }

}
