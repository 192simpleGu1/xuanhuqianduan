package com.example.infusion.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName equipment
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipment implements Serializable {
    /**
     * 设备编号
     */
    private String equipmentId;

    /**
     * 绑定日期
     */
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bindDate;

    /**
     * 设备余额
     */
    private Double equipmentBalance;

    /**
     * 床位编号
     */
    private String bedId;

    /**
     * 用户
     */
    private String phone;

    private static final long serialVersionUID = 1L;

    /**
     * 设备编号
     */
    public String getEquipmentId() {
        return equipmentId;
    }

    /**
     * 设备编号
     */
    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    /**
     * 绑定日期
     */
    public Date getBindDate() {
        return bindDate;
    }

    /**
     * 绑定日期
     */
    public void setBindDate(Date bindDate) {
        this.bindDate = bindDate;
    }

    /**
     * 设备余额
     */
    public Double getEquipmentBalance() {
        return equipmentBalance;
    }

    /**
     * 设备余额
     */
    public void setEquipmentBalance(Double equipmentBalance) {
        this.equipmentBalance = equipmentBalance;
    }

    /**
     * 用户
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 用户
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Equipment other = (Equipment) that;
        return (this.getEquipmentId() == null ? other.getEquipmentId() == null : this.getEquipmentId().equals(other.getEquipmentId()))
            && (this.getBindDate() == null ? other.getBindDate() == null : this.getBindDate().equals(other.getBindDate()))
            && (this.getEquipmentBalance() == null ? other.getEquipmentBalance() == null : this.getEquipmentBalance().equals(other.getEquipmentBalance()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEquipmentId() == null) ? 0 : getEquipmentId().hashCode());
        result = prime * result + ((getBindDate() == null) ? 0 : getBindDate().hashCode());
        result = prime * result + ((getEquipmentBalance() == null) ? 0 : getEquipmentBalance().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", bindDate=").append(bindDate);
        sb.append(", equipmentBalance=").append(equipmentBalance);
        sb.append(", phone=").append(phone);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}