package com.hcw.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author hcw
 * @date 2022-06-26
 */
@Table(name = "t_storage")
@Entity
@Data
public class Storage extends AbstractEntity<Long> {

    private Long userId;
    private Integer total;
    private Integer used;
    private Integer residue;
}
