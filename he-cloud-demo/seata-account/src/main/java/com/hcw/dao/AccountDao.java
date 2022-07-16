package com.hcw.dao;

import com.hcw.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

/**
 * @author hcw
 * @date 2022-06-26
 */
public interface AccountDao extends JpaRepository<Account, Long> {

    @Modifying
    @Query(value = "update t_account set used = used + :money, residue = residue - :money where user_id = :userId", nativeQuery = true)
    void deduct(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
