/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.croer.db.search.repositories;

import com.croer.db.search.entities.Alineacion;
import com.croer.db.search.entities.AlineacionPK;
import com.croer.db.search.repdefinition.JpaRepositoryXeam;
import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author elialva
 */
public interface AlineacionRepository extends JpaRepositoryXeam<Alineacion, AlineacionPK> {

    List<Alineacion> findByOrtograma(@Param("ortograma") String ortograma);
    List<Alineacion> findBySimigrama(@Param("simigrama") String simigrama);
    

}
