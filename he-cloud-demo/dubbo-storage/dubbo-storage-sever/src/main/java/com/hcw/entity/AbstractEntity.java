package com.hcw.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.domain.Persistable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author hcw
 * @date 2022-06-26
 */
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractEntity<PK extends Serializable> implements Persistable<PK>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private PK id;

    @Override
    public PK getId() {
        return this.id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }
}
