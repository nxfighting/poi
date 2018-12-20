package com.vict;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Author:linfu.wei
 * @Description: 电池单体DTO
 * @Date:created in 13:53 2018/8/22
 */
public class EnBatteryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String tenantId;
    private String enModuleId;
    private String moduleCode;
    private String batteryPackCode;
    private String batteryCode;
    private String enterprise;
    private String batteryType;
    private String batteryPackSpeci;
    private String traceCode;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp productTime;
    private String seq;
    private String model;
    private String locationNum;
    private String cathodeMaterial;
    private String negativeMaterial;
    private String septumType;
    private String electrolyteType;
    private String position;
    private String appearance;
    private String size;
    private BigDecimal ratedCapacity;
    private BigDecimal nominalVoltage;
    private BigDecimal ratedQuality;
    private BigDecimal ratedEnergy;
    private BigDecimal internalResistance;
    private Integer cycleLife;

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

    public String getEnModuleId() {
        return enModuleId;
    }

    public void setEnModuleId(String enModuleId) {
        this.enModuleId = enModuleId;
    }

    public String getBatteryCode() {
        return batteryCode;
    }

    public void setBatteryCode(String batteryCode) {
        this.batteryCode = batteryCode;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getBatteryPackSpeci() {
        return batteryPackSpeci;
    }

    public void setBatteryPackSpeci(String batteryPackSpeci) {
        this.batteryPackSpeci = batteryPackSpeci;
    }

    public String getTraceCode() {
        return traceCode;
    }

    public void setTraceCode(String traceCode) {
        this.traceCode = traceCode;
    }

    public Timestamp getProductTime() {
        return productTime;
    }

    public void setProductTime(Timestamp productTime) {
        this.productTime = productTime;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLocationNum() {
        return locationNum;
    }

    public void setLocationNum(String locationNum) {
        this.locationNum = locationNum;
    }

    public String getCathodeMaterial() {
        return cathodeMaterial;
    }

    public void setCathodeMaterial(String cathodeMaterial) {
        this.cathodeMaterial = cathodeMaterial;
    }

    public String getNegativeMaterial() {
        return negativeMaterial;
    }

    public void setNegativeMaterial(String negativeMaterial) {
        this.negativeMaterial = negativeMaterial;
    }

    public String getSeptumType() {
        return septumType;
    }

    public void setSeptumType(String septumType) {
        this.septumType = septumType;
    }

    public String getElectrolyteType() {
        return electrolyteType;
    }

    public void setElectrolyteType(String electrolyteType) {
        this.electrolyteType = electrolyteType;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getRatedCapacity() {
        return ratedCapacity;
    }

    public void setRatedCapacity(BigDecimal ratedCapacity) {
        this.ratedCapacity = ratedCapacity;
    }

    public BigDecimal getNominalVoltage() {
        return nominalVoltage;
    }

    public void setNominalVoltage(BigDecimal nominalVoltage) {
        this.nominalVoltage = nominalVoltage;
    }

    public BigDecimal getRatedQuality() {
        return ratedQuality;
    }

    public void setRatedQuality(BigDecimal ratedQuality) {
        this.ratedQuality = ratedQuality;
    }

    public BigDecimal getRatedEnergy() {
        return ratedEnergy;
    }

    public void setRatedEnergy(BigDecimal ratedEnergy) {
        this.ratedEnergy = ratedEnergy;
    }

    public BigDecimal getInternalResistance() {
        return internalResistance;
    }

    public void setInternalResistance(BigDecimal internalResistance) {
        this.internalResistance = internalResistance;
    }

    public Integer getCycleLife() {
        return cycleLife;
    }

    public void setCycleLife(Integer cycleLife) {
        this.cycleLife = cycleLife;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getBatteryPackCode() {
        return batteryPackCode;
    }

    public void setBatteryPackCode(String batteryPackCode) {
        this.batteryPackCode = batteryPackCode;
    }
}

