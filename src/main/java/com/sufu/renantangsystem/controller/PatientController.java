package com.sufu.renantangsystem.controller;

import com.sufu.renantangsystem.Service.IPatientService;
import com.sufu.renantangsystem.Service.IUserService;
import com.sufu.renantangsystem.entity.PatientEntity;
import com.sufu.renantangsystem.entity.vo.QueryPatientVO;
import com.sufu.renantangsystem.entity.UserEntity;
import com.sufu.renantangsystem.util.GUID;
import com.sufu.renantangsystem.util.MyConstant;

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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2018/6/15.
 */

@Controller
public class PatientController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IPatientService patientService;
    @Autowired
    private IUserService userService;


    @GetMapping("/showList")
    public String showList(Model model) {

        List<PatientEntity> list = patientService.findAll();
        processPatientList(list);
        model.addAttribute("patientPage", list);
        model.addAttribute("queryPatientVO", new QueryPatientVO());
        model.addAttribute("doctorList", userService.findAll());
        return "showList";
    }

    @PostMapping("/showList")
    public String showList(Model model, @Valid QueryPatientVO queryPatientVO,
                           BindingResult bindingResult, HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        try {
            if (StringUtils.isNotBlank(startDateStr)) {
                queryPatientVO.setStartDate(new Timestamp(sdf.parse(startDateStr).getTime()));
            }
            if (StringUtils.isNotBlank(endDateStr)) {
                queryPatientVO.setEndDate(new Timestamp(sdf.parse(endDateStr).getTime()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Integer pageNum = queryPatientVO.getPageNum();
        Integer pageSize = queryPatientVO.getPageSize();

        if (pageNum == null) {
            queryPatientVO.setPageNum(0);
        }
        if (pageSize == null) {
            queryPatientVO.setPageSize(10);
        }

        List<PatientEntity> list = patientService.findAllByQueryPatientVO(queryPatientVO);
        processPatientList(list);
        model.addAttribute("patientPage", list);
        model.addAttribute("queryPatientVO", queryPatientVO);
        model.addAttribute("doctorList", userService.findAll());
        return "showList";

    }

    private void processPatientList(List<PatientEntity> list) {
        for (PatientEntity p : list) {
            String sex = p.getSex();
            p.setSex(StringUtils.isNotBlank(sex) ? MyConstant.getSex(Integer.valueOf(sex)) : "");
            String modifyUserid = p.getModifyUserid();
            if (StringUtils.isNotBlank(modifyUserid)) {
                UserEntity userEntity = userService.findById(modifyUserid);
                p.setModifyUserid( userEntity != null ? userEntity.getUserId() : "");
            }
        }
    }
    /**
     * 新建患者信息
     * @param model
     * @return
     */
    @GetMapping("/report")
    public String report(Model model){
    	model.addAttribute("patientEntity", new PatientEntity());
    	return "patient";
    }
    
    /**
     * 保存患者信息
     * @param request
     * @param model
     * @param patientEntity
     * @return
     */
    @PostMapping("/doSave")
    public String doSave(HttpServletRequest request,Model model,PatientEntity patientEntity) {
    	UserEntity userEntity = (UserEntity)request.getSession().getAttribute("loginUser");
    	String optUserId ="" ;
    	if(userEntity!=null) {
    		optUserId = userEntity.getId();
    	}
    	patientEntity.setModifyUserid(optUserId);
    	patientEntity.setDelFlg("0");
    	if("".equals(patientEntity.getId())) {
    		patientEntity.setId(GUID.getID());
        	patientEntity.setModifyTime(new Timestamp(new Date().getTime()));
    	}
    	patientService.save(patientEntity);
    	if(patientEntity==null) {
    		patientEntity = new PatientEntity();
    	}
    	model.addAttribute("patientEntity", patientEntity);
    	model.addAttribute("saveFlg", "true");
    	return "patient";
    }
    
    /**
     * 编辑患者信息
     * @param model
     * @param id 患者id
     * @return
     */
    @GetMapping("/editReport")
    public String report(Model model,@RequestParam("id") String id){
    	PatientEntity patientEntity = patientService.findPatientById(id);
    	model.addAttribute("patientEntity", patientEntity);
    	return "patient";
    }

}
