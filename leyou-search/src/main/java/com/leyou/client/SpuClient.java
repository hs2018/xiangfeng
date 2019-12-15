package com.leyou.client;

import com.leyou.item.api.SpuApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "item-service")
public interface SpuClient extends SpuApi {
}
