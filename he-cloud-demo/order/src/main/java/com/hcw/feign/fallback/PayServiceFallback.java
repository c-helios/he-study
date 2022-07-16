package com.hcw.feign.fallback;

import com.hcw.feign.PayServiceFeign;
import org.springframework.stereotype.Component;

/**
 * @author hcw
 * @date 2022-06-22
 */
@Component
public class PayServiceFallback implements PayServiceFeign {

    @Override
    public String wxPay(String orderId) {
        return "支付失败，请稍后再试...";
    }

    @Override
    public String testTimeout() {
        return "请求超时了，请稍后再试...";
    }
}
