package com.hcw.dao;

import com.hcw.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

/**
 * @author hcw
 * @date 2022-06-26
 */
public interface StorageDao extends JpaRepository<Storage, Long> {

    @Modifying
    @Query(value = "update t_storage set used = used + :count, residue = residue - :count where product_id = :productId", nativeQuery = true)
    void deduct(@Param("productId") Long productId, @Param("count") Integer count);
}
