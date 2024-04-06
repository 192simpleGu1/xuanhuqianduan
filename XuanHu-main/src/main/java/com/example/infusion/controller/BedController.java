package com.example.infusion.controller;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.example.infusion.common.convention.Result;
import com.example.infusion.common.convention.Results;
import com.example.infusion.domain.Bed;
import com.example.infusion.dto.req.BindBedReq;
import com.example.infusion.service.BedService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BedController {

    @Autowired
    BedService bedService;

    @RequestMapping("/infusion/bind_bed")
    public Result bind_bed(HttpServletRequest request,@RequestBody BindBedReq bindBedReq){
        bedService.bind_bed(request, bindBedReq);
        return Results.success();
    }

    @RequestMapping("/infusion/unbind_bed")
    public Result unbind_bed(HttpServletRequest request,@RequestBody BindBedReq bindBedReq){
        bedService.unbind_bed(request, bindBedReq);
        return Results.success();
    }


}
