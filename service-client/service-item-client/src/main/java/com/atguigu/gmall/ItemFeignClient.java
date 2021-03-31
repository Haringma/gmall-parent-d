package com.atguigu.gmall;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "service-item")
public interface ItemFeignClient {

}
