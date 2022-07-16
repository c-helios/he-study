package com.hcw;

import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallbackRegistry;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.AppResponse;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author hcw
 * @date 2022-06-26
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Slf4j
public class DubboOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboOrderApplication.class, args);
        init();
    }


    public static void init() {
        DubboFallback dubboFallback = new DubboFallback() {
            @Override
            public Result handle(Invoker<?> invoker, Invocation invocation, BlockException ex) {
                // 1.6.3
                //AsyncRpcResult appResponse = new AsyncRpcResult(invocation);
                // 1.8.1
                AsyncRpcResult appResponse = AsyncRpcResult.newDefaultAsyncResult(invocation);
                String r = null;
                try {
                    if (ex instanceof FlowException) {
                        r = "order限流逻辑"; //需要自定义，最终返回业务返回值
                        log.error("order限流逻辑");
                    } else if (ex instanceof DegradeException) {
                        r = "order降级逻辑"; //需要自定义，最终返回业务返回值
                        log.error("order降级逻辑");
                    }

                } catch (Exception e) {
                    r = "order其他错误";
                    //rs.setException(e); //业务异常注入，以便在consumer方抛出异常
                    log.error("order其他错误");
                }
                //rs.setValue(r); //设置业务返回值

                appResponse.setValue(r);
                appResponse.setException(null);
                return appResponse;
            }
        };
        // 1.6.3
        //DubboFallbackRegistry.setConsumerFallback(dubboFallback);
        //DubboFallbackRegistry.setProviderFallback(dubboFallback);
        // 1.8.1
        DubboAdapterGlobalConfig.setConsumerFallback(dubboFallback);
        DubboAdapterGlobalConfig.setProviderFallback(dubboFallback);
    }
}
