package com.hcw.rpc.service;

import java.math.BigDecimal;


public interface AccountServiceApi {

    String deduct(Long userId, BigDecimal money);

}
