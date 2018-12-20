package com.vict;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A DTO for the BatteryPackageRepairSheet entity.
 */
public class BatteryPackageRepairSheetDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String repairOrderNo;
    private String tenantId;

    private String batteryPackageCode;

    private String vin;

    private String carNo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date repairTime;

    private String repairFrom;
    private String repairFromDisplay;

    private String repairInternetInfo;

    private String repairToCompanyName;

    private String repairToCompanyCode;

    private String comments;

    private String batteryPackageRepairId;
    private String repairType;
    private String repairTypeDisplay;
    private String beforeRepairCode;
    private String afterRepairCode;
    private String moduleCode;
    private Integer pushStatus = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getBatteryPackageCode() {
        return batteryPackageCode;
    }

    public void setBatteryPackageCode(String batteryPackageCode) {
        this.batteryPackageCode = batteryPackageCode;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }


    public Date getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Date repairTime) {
        this.repairTime = repairTime;
    }

    public String getRepairFrom() {
        return repairFrom;
    }

    public void setRepairFrom(String repairFrom) {
        this.repairFrom = repairFrom;
    }

    public String getRepairInternetInfo() {
        return repairInternetInfo;
    }

    public void setRepairInternetInfo(String repairInternetInfo) {
        this.repairInternetInfo = repairInternetInfo;
    }

    public String getRepairToCompanyName() {
        return repairToCompanyName;
    }

    public void setRepairToCompanyName(String repairToCompanyName) {
        this.repairToCompanyName = repairToCompanyName;
    }


    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public String getBeforeRepairCode() {
        return beforeRepairCode;
    }

    public void setBeforeRepairCode(String beforeRepairCode) {
        this.beforeRepairCode = beforeRepairCode;
    }

    public String getAfterRepairCode() {
        return afterRepairCode;
    }

    public void setAfterRepairCode(String afterRepairCode) {
        this.afterRepairCode = afterRepairCode;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getRepairToCompanyCode() {
        return repairToCompanyCode;
    }

    public void setRepairToCompanyCode(String repairToCompanyCode) {
        this.repairToCompanyCode = repairToCompanyCode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BatteryPackageRepairSheetDTO batteryPackageRepairSheetDTO = (BatteryPackageRepairSheetDTO) o;
        if (batteryPackageRepairSheetDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), batteryPackageRepairSheetDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BatteryPackageRepairSheetDTO{" +
            "id=" + getId() +
            ", tenantId='" + getTenantId() + "'" +
            ", batteryPackageCode='" + getBatteryPackageCode() + "'" +
            ", vin='" + getVin() + "'" +
            ", carNo='" + getCarNo() + "'" +
            ", repairTime='" + getRepairTime() + "'" +
            ", repairFrom='" + getRepairFrom() + "'" +
            ", repairInternetInfo='" + getRepairInternetInfo() + "'" +
            ", repairToCompanyName='" + getRepairToCompanyName() + "'" +
            ", repairToCompanyCOde='" + getRepairToCompanyCode() + "'" +
            ", comments='" + getComments() + "'" +
            "}";
    }

    public String getRepairFromDisplay() {
        return repairFromDisplay;
    }

    public void setRepairFromDisplay(String repairFromDisplay) {
        this.repairFromDisplay = repairFromDisplay;
    }

    public String getRepairTypeDisplay() {
        return repairTypeDisplay;
    }

    public void setRepairTypeDisplay(String repairTypeDisplay) {
        this.repairTypeDisplay = repairTypeDisplay;
    }

    public String getRepairOrderNo() {
        return repairOrderNo;
    }

    public void setRepairOrderNo(String repairOrderNo) {
        this.repairOrderNo = repairOrderNo;
    }

    public String getBatteryPackageRepairId() {
        return batteryPackageRepairId;
    }

    public void setBatteryPackageRepairId(String batteryPackageRepairId) {
        this.batteryPackageRepairId = batteryPackageRepairId;
    }

  public Integer getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Integer pushStatus) {
        this.pushStatus = pushStatus;
    }
}
