/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.croer.db.search.repositories;

import com.croer.db.business.entities.ProductoLog;
import com.croer.db.business.entities.ProductoLogPK;
import com.croer.db.search.repdefinition.JpaRepositoryXeam;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author elialva
 */
public interface ProductoLogRepository extends JpaRepositoryXeam<ProductoLog, ProductoLogPK>{
    
    @Override
    @Query("SELECT p FROM ProductoLog p ORDER BY p.productoLogPK.logDate")
    public List<ProductoLog> findAll();
    
}
