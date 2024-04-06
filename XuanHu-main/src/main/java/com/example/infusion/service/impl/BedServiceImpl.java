package com.example.infusion.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.infusion.common.enums.CustomizeCode;
import com.example.infusion.common.error.ServiceException;
import com.example.infusion.domain.Bed;
import com.example.infusion.domain.Equipment;
import com.example.infusion.dto.req.BindBedReq;
import com.example.infusion.mapper.EquipmentMapper;
import com.example.infusion.service.BedService;
import com.example.infusion.mapper.BedMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author admin
* @description 针对表【bed】的数据库操作Service实现
* @createDate 2024-03-23 14:58:35
*/
@Service
public class BedServiceImpl extends ServiceImpl<BedMapper, Bed>
    implements BedService{


    BedMapper bedMapper;

    EquipmentMapper equipmentMapper;
    @Override
    @Transactional
    public void bind_bed(HttpServletRequest request, BindBedReq bindBedReq) {
        //查询此床位是否已被绑定
        if (bedMapper.selectOne(Wrappers.lambdaQuery(Bed.class).eq(Bed::getBedId, bindBedReq.getBedId())) != null) {
            throw new ServiceException(CustomizeCode.BED_IS_BIND);
        }
        //绑定床位，并插入到数据库
        Bed bed = Bed.builder().bedId(bindBedReq.getBedId())
                .phone(String.valueOf(request.getAttribute("phone"))).build();
        bedMapper.insert(bed);
    }

    @Override
    public void unbind_bed(HttpServletRequest request, BindBedReq bindBedReq) {
        //查询此床位是否已被设备绑定
        if (equipmentMapper.selectOne(Wrappers.lambdaQuery(Equipment.class).eq(Equipment::getBedId, bindBedReq.getBedId())) == null) {
            //未绑定就直接删床
            bedMapper.delete(Wrappers.lambdaQuery(Bed.class).eq(Bed::getBedId, bindBedReq.getBedId()));
        }
        //绑定了就提示先解绑设备
        throw new ServiceException(CustomizeCode.BED_BIND_EQUIPMENT);
    }
}




