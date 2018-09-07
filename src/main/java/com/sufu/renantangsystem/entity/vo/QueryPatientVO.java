package com.sufu.renantangsystem.entity.vo;

import com.sufu.renantangsystem.entity.PatientEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by lenovo on 2018/6/20.
 */
public class QueryPatientVO extends PatientEntity implements Serializable {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp endDate;
    private Integer pageNum;
    private Integer pageSize;
    private String doctorId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "QueryPatientVO{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", doctorId='" + doctorId + '\'' +
                '}';
    }
}
