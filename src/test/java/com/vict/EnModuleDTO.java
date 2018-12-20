package com.vict;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author:linfu.wei
 * @Description: 电池模组传输对象类
 * @Date:created in 13:36 2018/8/24
 */
public class EnModuleDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;
    private String tenantId;
    private String enBatteryPackId;
    private String moduleCode;
    private String enterprise;
    private String batteryType;
    private String speciCode;
    private String productType;
    private String moduleSpecific;
    private String traceCode;
    private String seq;
    private String model;
    private Integer monomerNum;
    private String monomerMode;
    private String monomerBatteryModel;
    private String coolMode;
    private String size;
    private BigDecimal ratedCapacity;
    private BigDecimal nominalVoltage;
    private BigDecimal ratedQuality;
    private BigDecimal ratedEnergy;
    private BigDecimal dcrAcr;
    private BigDecimal pressureDifference;
    private BigDecimal insulationResistance;
    private Integer cycleLife;
    private Integer pushStatus;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp productTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp pushTime;
    private Timestamp lastUpdateTime;
    private String protocolVersion;
    private String softwareVersion;
    private String position;
    private List<EnBatteryDTO> enBatterys;
    private String batteryPackCode;

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

    public String getEnBatteryPackId() {
        return enBatteryPackId;
    }

    public void setEnBatteryPackId(String enBatteryPackId) {
        this.enBatteryPackId = enBatteryPackId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
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

    public String getSpeciCode() {
        return speciCode;
    }

    public void setSpeciCode(String speciCode) {
        this.speciCode = speciCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getModuleSpecific() {
        return moduleSpecific;
    }

    public void setModuleSpecific(String moduleSpecific) {
        this.moduleSpecific = moduleSpecific;
    }

    public String getTraceCode() {
        return traceCode;
    }

    public void setTraceCode(String traceCode) {
        this.traceCode = traceCode;
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

    public Integer getMonomerNum() {
        return monomerNum;
    }

    public void setMonomerNum(Integer monomerNum) {
        this.monomerNum = monomerNum;
    }

    public String getMonomerMode() {
        return monomerMode;
    }

    public void setMonomerMode(String monomerMode) {
        this.monomerMode = monomerMode;
    }

    public String getMonomerBatteryModel() {
        return monomerBatteryModel;
    }

    public void setMonomerBatteryModel(String monomerBatteryModel) {
        this.monomerBatteryModel = monomerBatteryModel;
    }

    public String getCoolMode() {
        return coolMode;
    }

    public void setCoolMode(String coolMode) {
        this.coolMode = coolMode;
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

    public BigDecimal getDcrAcr() {
        return dcrAcr;
    }

    public void setDcrAcr(BigDecimal dcrAcr) {
        this.dcrAcr = dcrAcr;
    }

    public BigDecimal getPressureDifference() {
        return pressureDifference;
    }

    public void setPressureDifference(BigDecimal pressureDifference) {
        this.pressureDifference = pressureDifference;
    }

    public BigDecimal getInsulationResistance() {
        return insulationResistance;
    }

    public void setInsulationResistance(BigDecimal insulationResistance) {
        this.insulationResistance = insulationResistance;
    }

    public Integer getCycleLife() {
        return cycleLife;
    }

    public void setCycleLife(Integer cycleLife) {
        this.cycleLife = cycleLife;
    }

    public Integer getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Integer pushStatus) {
        this.pushStatus = pushStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getProductTime() {
        return productTime;
    }

    public void setProductTime(Timestamp productTime) {
        this.productTime = productTime;
    }

    public Timestamp getPushTime() {
        return pushTime;
    }

    public void setPushTime(Timestamp pushTime) {
        this.pushTime = pushTime;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<EnBatteryDTO> getEnBatterys() {
        return enBatterys;
    }

    public void setEnBatterys(List<EnBatteryDTO> enBatterys) {
        this.enBatterys = enBatterys;
    }

    public String getBatteryPackCode() {
        return batteryPackCode;
    }

    public void setBatteryPackCode(String batteryPackCode) {
        this.batteryPackCode = batteryPackCode;
    }

}
