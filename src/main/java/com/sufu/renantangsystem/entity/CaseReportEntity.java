package com.sufu.renantangsystem.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by lenovo on 2018/6/23.
 */
@Entity
@Table(name = "case_report", schema = "renantang", catalog = "")
public class CaseReportEntity {
    @Id
    private String id;
    private String patientId;
    private Timestamp clinicTime;
    private String complaint;
    private String diagnose;
    private String recipe;
    private String remark;
    private String modifyUserid;
    private Timestamp modifyTime;
    private String delFlg;

    @Id
    @Column(name = "id", nullable = false, length = 64)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "patient_id", nullable = true, length = 64)
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Basic
    @Column(name = "clinic_time", nullable = true)
    public Timestamp getClinicTime() {
        return clinicTime;
    }

    public void setClinicTime(Timestamp clinicTime) {
        this.clinicTime = clinicTime;
    }

    @Basic
    @Column(name = "complaint", nullable = true, length = -1)
    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    @Basic
    @Column(name = "diagnose", nullable = true, length = -1)
    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    @Basic
    @Column(name = "recipe", nullable = true, length = -1)
    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "modify_userid", nullable = true, length = 64)
    public String getModifyUserid() {
        return modifyUserid;
    }

    public void setModifyUserid(String modifyUserid) {
        this.modifyUserid = modifyUserid;
    }

    @Basic
    @Column(name = "modify_time", nullable = true)
    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "del_flg", nullable = true, length = 1)
    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CaseReportEntity that = (CaseReportEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (patientId != null ? !patientId.equals(that.patientId) : that.patientId != null) return false;
        if (clinicTime != null ? !clinicTime.equals(that.clinicTime) : that.clinicTime != null) return false;
        if (complaint != null ? !complaint.equals(that.complaint) : that.complaint != null) return false;
        if (diagnose != null ? !diagnose.equals(that.diagnose) : that.diagnose != null) return false;
        if (recipe != null ? !recipe.equals(that.recipe) : that.recipe != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (modifyUserid != null ? !modifyUserid.equals(that.modifyUserid) : that.modifyUserid != null) return false;
        if (modifyTime != null ? !modifyTime.equals(that.modifyTime) : that.modifyTime != null) return false;
        if (delFlg != null ? !delFlg.equals(that.delFlg) : that.delFlg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (patientId != null ? patientId.hashCode() : 0);
        result = 31 * result + (clinicTime != null ? clinicTime.hashCode() : 0);
        result = 31 * result + (complaint != null ? complaint.hashCode() : 0);
        result = 31 * result + (diagnose != null ? diagnose.hashCode() : 0);
        result = 31 * result + (recipe != null ? recipe.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (modifyUserid != null ? modifyUserid.hashCode() : 0);
        result = 31 * result + (modifyTime != null ? modifyTime.hashCode() : 0);
        result = 31 * result + (delFlg != null ? delFlg.hashCode() : 0);
        return result;
    }
}
