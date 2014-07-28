/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.croer.db.search.repositories;

import com.croer.db.business.entities.ProductoLog;
import com.croer.db.business.entities.ProductoLogPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author elialva
 */
public interface ProductoLogRepository extends JpaRepository<ProductoLog, ProductoLogPK>{
    
}
