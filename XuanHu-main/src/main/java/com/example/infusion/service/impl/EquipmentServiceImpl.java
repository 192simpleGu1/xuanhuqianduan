package com.example.infusion.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.infusion.common.enums.CustomizeCode;
import com.example.infusion.common.error.ServiceException;
import com.example.infusion.domain.Balance;
import com.example.infusion.domain.Equipment;
import com.example.infusion.domain.User;
import com.example.infusion.dto.req.EquipmentBindReq;
import com.example.infusion.dto.req.EquipmentRefundReq;
import com.example.infusion.dto.req.EquipmentRenewReq;
import com.example.infusion.dto.req.EquipmentUnbindReq;
import com.example.infusion.dto.resp.BalanceRenewResp;
import com.example.infusion.mapper.BalanceMapper;
import com.example.infusion.mapper.UserMapper;
import com.example.infusion.service.EquipmentService;
import com.example.infusion.mapper.EquipmentMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
* @author admin
* @description 针对表【equipment】的数据库操作Service实现
* @createDate 2024-03-23 14:58:36
*/
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment>
    implements EquipmentService{

    BalanceMapper balanceMapper;

    EquipmentMapper equipmentMapper;

    @Override
    @Transactional
    public BalanceRenewResp renew(HttpServletRequest request, EquipmentRenewReq equipmentRenewReq) {

        //从request中拿到phone
        String phone = String.valueOf(request.getAttribute("phone"));

        Balance balance = balanceMapper.selectOne(Wrappers.lambdaQuery(Balance.class).eq(Balance::getPhone, phone));
        //余额不足
        if(balance.getBalance()<0.5) throw new ServiceException(CustomizeCode.BALANCE_NOT_ENOUGH);
        //修改余额
        int update = balanceMapper.update(Wrappers.lambdaUpdate(Balance.class)
                .eq(Balance::getPhone, phone)
                .setSql("balance=balance-0.5"));
        //修改失败
        if(update!=1) throw new ServiceException(CustomizeCode.BALANCE_RENEW_ERROR);

        //修改成功,给设备增加余额
        int update1 = equipmentMapper.update(Wrappers.lambdaUpdate(Equipment.class)
                .eq(Equipment::getEquipmentId, equipmentRenewReq.getEquipmentId())
                .eq(Equipment::getPhone, phone)
                .setSql("equipment_balance = equipment_balance + 0.5"));
        if(update1!=1) throw new ServiceException(CustomizeCode.BALANCE_RENEW_ERROR);

        return BalanceRenewResp.builder().message("续费成功").build();
    }

    @Override
    public void bindEquipment(HttpServletRequest request, EquipmentBindReq equipmentBindReq) {
        //查询设备有没有被其他床位绑定
        Equipment equipment = equipmentMapper.selectOne(Wrappers.lambdaQuery(Equipment.class).eq(Equipment::getEquipmentId, equipmentBindReq.getEquipmentId()));
        if(equipment!=null) throw new ServiceException(CustomizeCode.EQUIPMENT_IS_BIND);
        //绑定
        int insert = equipmentMapper.insert(Equipment.builder()
                .equipmentId(equipmentBindReq.getEquipmentId())
                .bedId(equipmentBindReq.getBedId())
                .equipmentBalance(0.0)
                .phone(String.valueOf(request.getAttribute("phone"))).build());
        if(insert!=1) throw new ServiceException(CustomizeCode.EQUIPMENT_IS_BIND);

    }

    @Override
    @Transactional
    public void unbindEquipment(HttpServletRequest request, EquipmentUnbindReq equipmentUnbindReq) {
        //将钱退到用户余额
        Equipment equipment = equipmentMapper.selectOne(Wrappers.lambdaQuery(Equipment.class)
                .eq(Equipment::getEquipmentId, equipmentUnbindReq.getEquipmentId()));

        LambdaUpdateWrapper<Balance> updateWrapper = Wrappers.lambdaUpdate(Balance.class)
                .eq(Balance::getPhone, equipment.getPhone())
                .setSql("balance = balance+" + equipment.getEquipmentBalance());
        balanceMapper.update(updateWrapper);


        //删除设备
        int delete = equipmentMapper.delete(Wrappers.lambdaQuery(Equipment.class)
                .eq(Equipment::getEquipmentId, equipmentUnbindReq.getEquipmentId())
                .eq(Equipment::getPhone, String.valueOf(request.getAttribute("phone"))));
        if(delete!=1) throw new ServiceException(CustomizeCode.EQUIPMENT_IS_BIND);
    }

    @Override
    public void refund(HttpServletRequest request, EquipmentRefundReq equipmentRefundReq) {
        Equipment equipment = equipmentMapper.selectOne(Wrappers.lambdaQuery(Equipment.class)
                .eq(Equipment::getEquipmentId, equipmentRefundReq.getEquipmentId()));

        int update = balanceMapper.update(Wrappers.lambdaUpdate(Balance.class)
                .eq(Balance::getPhone, equipment.getPhone())
                .setSql("balance = balance+" + equipment.getEquipmentBalance()));
        if(update!=1) throw new ServiceException(CustomizeCode.EQUIPMENT_REFUND_ERROR);
    }
}




