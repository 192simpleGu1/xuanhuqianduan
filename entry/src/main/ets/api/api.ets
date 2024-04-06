import httpRequest from "../tool/request"
import axios, { AxiosRequestConfig,AxiosError, AxiosResponse, InternalAxiosRequestConfig } from '@ohos/axios';


export function  login(param){
  return httpRequest({
    // 接口地址
    url: '/infusion/login',
    method: 'post',
    params:param
  })
}


export function  reg(param:any){
  return httpRequest({
    // 接口地址
    url: 'http://localhost:8080/infusion/login',
    method: 'post',
    data:param
  })
}
