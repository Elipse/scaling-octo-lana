/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.croer.db.search.repositories;

import com.croer.db.search.entities.Itembusq;
import com.croer.db.search.entities.ItembusqPK;
import com.croer.db.search.repdefinition.JpaRepositoryXeam;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 *
 * @author elialva
 */
public interface ItembusqRepository extends JpaRepositoryXeam<Itembusq, ItembusqPK>{
    
}
