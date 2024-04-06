package com.example.infusion.controller;

import com.example.infusion.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @Autowired
    BalanceService balanceService;

    //充值接口
//    @RequestMapping("/infusion/recharge")
//    public Result recharge(){
//        return Results.success(balanceService.recharge());
//    }


}
