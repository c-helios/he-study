package com.hcw.feign;

import org.springframework.stereotype.Component;

/**
 * @author hcw
 * @date 2022-07-10
 */
@Component
public class DiscoveryCalleeServiceFallback implements DiscoveryCalleeService {

    @Override
    public int sum(int value1, int value2) {
        return 0;
    }

    @Override
    public String info() {
        return "trigger the refuse for service b";
    }

}
