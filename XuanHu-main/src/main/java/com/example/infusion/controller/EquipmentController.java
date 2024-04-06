package com.example.infusion.controller;

import com.example.infusion.common.convention.Result;
import com.example.infusion.common.convention.Results;
import com.example.infusion.dto.req.EquipmentBindReq;
import com.example.infusion.dto.req.EquipmentRefundReq;
import com.example.infusion.dto.req.EquipmentRenewReq;
import com.example.infusion.dto.req.EquipmentUnbindReq;
import com.example.infusion.service.BalanceService;
import com.example.infusion.service.EquipmentService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquipmentController {

    @Resource
    EquipmentService equipmentService;

    //设备续费接口
    @RequestMapping("/infusion/renew")
    public Result renew(HttpServletRequest request,@RequestBody EquipmentRenewReq equipmentRenewReq)
    {
        return Results.success(equipmentService.renew(request,equipmentRenewReq));
    }

    //绑定设备
    @RequestMapping("/infusion/bind_equipment")
    public Result bindEquipment(HttpServletRequest request,@RequestBody EquipmentBindReq equipmentBindReq){
        equipmentService.bindEquipment(request,equipmentBindReq);
        return Results.success();
    }

    //解除绑设备
    @RequestMapping("/infusion/unbind_equipment")
    public Result unbindEquipment(HttpServletRequest request,@RequestBody EquipmentUnbindReq equipmentUnbindReq){
        equipmentService.unbindEquipment(request,equipmentUnbindReq);
        return Results.success();
    }

    //设备退款
    @RequestMapping("/infusion/refund")
    public Result refund(HttpServletRequest request,@RequestBody EquipmentRefundReq equipmentRefundReq){
        equipmentService.refund(request,equipmentRefundReq);
        return Results.success();
    }

}
