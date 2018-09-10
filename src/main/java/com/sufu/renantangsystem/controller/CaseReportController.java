package com.sufu.renantangsystem.controller;

import com.sufu.renantangsystem.Service.ICaseReportService;
import com.sufu.renantangsystem.Service.IPatientService;
import com.sufu.renantangsystem.Service.IUserService;
import com.sufu.renantangsystem.entity.CaseReportEntity;
import com.sufu.renantangsystem.entity.PatientEntity;
import com.sufu.renantangsystem.entity.UserEntity;
import com.sufu.renantangsystem.util.GUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2018/6/15.
 */

@Controller
public class CaseReportController {


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IPatientService patientService;
    @Autowired
    private ICaseReportService caseReportService;
    @Autowired
    private IUserService userService;

    @GetMapping("/viewReport")
    public String viewReport(Model model, @RequestParam(value = "patientId", required = false) String patientId, HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");

        List<CaseReportEntity> reportList = new ArrayList<CaseReportEntity>();
        if (StringUtils.isNoneBlank(patientId)) {
            reportList = caseReportService.findAllByPatientIdOrderByClinicTimeDesc(patientId);
            processReportList(reportList, loginUser);
        }
        model.addAttribute("reportList", reportList);
        model.addAttribute("patient", patientService.findPatientById(patientId));

        return "report";
    }

    private void processReportList(List<CaseReportEntity> reportList, UserEntity loginUser) {
        String doctorId = loginUser.getId();
        for (CaseReportEntity caseReportEntity : reportList) {
            if (!doctorId.equals(caseReportEntity.getModifyUserid())) {
                caseReportEntity.setDelFlg("1");
            }

            String modifyUserid = caseReportEntity.getModifyUserid();
            caseReportEntity.setModifyUserid(StringUtils.isNotBlank(modifyUserid) ? userService.findById(modifyUserid).getUserId() : "");
        }
    }

    @PostMapping("/insertCaseReport")
    public String insertCaseReport(Model model, CaseReportEntity caseReportEntity, BindingResult bindingResult, HttpServletRequest request) throws ParseException {

        HttpSession session = request.getSession();
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        String clinicTimeStr = request.getParameter("clinicTime");
        String patientId = "";

        if (caseReportEntity != null) {
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Timestamp clinicTime = new Timestamp(sdf.parse(clinicTimeStr).getTime());
            if(clinicTime != null){
                timestamp = clinicTime;
            }

            String reportId = caseReportEntity.getId();
            if (StringUtils.isEmpty(reportId)) {
                caseReportEntity.setId(GUID.getID());
            }
            patientId = caseReportEntity.getPatientId();
            if (StringUtils.isNotEmpty(patientId)) {
                PatientEntity patientEntity = patientService.findPatientById(patientId);
                if (patientEntity != null) {
                    patientEntity.setModifyTime(timestamp);
                    patientEntity.setModifyUserid(loginUser.getId());
                    patientService.save(patientEntity);
                }
            }

            if (caseReportEntity.getClinicTime() == null) {
                caseReportEntity.setClinicTime(timestamp);
            }
            caseReportEntity.setModifyUserid(loginUser.getId());
            caseReportEntity.setModifyTime(timestamp);
            caseReportEntity.setDelFlg("0");
            caseReportService.save(caseReportEntity);

        } else if (caseReportEntity == null) {
            //caseReportEntity = new CaseReportEntity();
        }

        model.addAttribute("caseReportEntity", caseReportEntity);
        model.addAttribute("patientId", patientId);

        return "redirect:/viewReport?patientId=" + patientId;
    }

    @GetMapping("/deleteCaseReport")
    @ResponseBody
    public void deleteCaseReport(@RequestParam("caseReportId") String caseReportId) {

        if (StringUtils.isEmpty(caseReportId)) {
            return;
        }
        caseReportService.deleteById(caseReportId);
    }


}
