package com.example.infusion.service;

import com.example.infusion.domain.Equipment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.infusion.dto.req.EquipmentBindReq;
import com.example.infusion.dto.req.EquipmentRefundReq;
import com.example.infusion.dto.req.EquipmentRenewReq;
import com.example.infusion.dto.req.EquipmentUnbindReq;
import com.example.infusion.dto.resp.BalanceRenewResp;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author admin
* @description 针对表【equipment】的数据库操作Service
* @createDate 2024-03-23 14:58:36
*/
public interface EquipmentService extends IService<Equipment> {

    BalanceRenewResp renew(HttpServletRequest request, EquipmentRenewReq equipmentRenewReq);

    void bindEquipment(HttpServletRequest request, EquipmentBindReq equipmentBindReq);

    void unbindEquipment(HttpServletRequest request, EquipmentUnbindReq equipmentUnbindReq);

    void refund(HttpServletRequest request, EquipmentRefundReq equipmentRefundReq);
}
