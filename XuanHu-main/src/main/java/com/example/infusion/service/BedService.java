package com.example.infusion.service;

import com.example.infusion.domain.Bed;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.infusion.dto.req.BindBedReq;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author admin
* @description 针对表【bed】的数据库操作Service
* @createDate 2024-03-23 14:58:35
*/
public interface BedService extends IService<Bed> {

    void bind_bed(HttpServletRequest request, BindBedReq bindBedReq);

    void unbind_bed(HttpServletRequest request, BindBedReq bindBedReq);
}
