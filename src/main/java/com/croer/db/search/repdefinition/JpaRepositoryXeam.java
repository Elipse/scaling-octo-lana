/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.croer.db.search.repdefinition;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author elialva
 * @param <T>
 * @param <ID>
 */
public interface JpaRepositoryXeam<T, ID extends Serializable> extends JpaRepository<T, ID> {

    void refresh(T t);
    boolean nomas(T t);

}
