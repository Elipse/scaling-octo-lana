/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.croer.db.search.repositories;

import com.croer.db.search.entities.ItemOrtograma;
import com.croer.db.search.entities.ItemOrtogramaPK;
import com.croer.db.search.repdefinition.JpaRepositoryXeam;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author elialva
 */
public interface ItemOrtogramaRepository extends JpaRepositoryXeam<ItemOrtograma, ItemOrtogramaPK> {

    @Query("SELECT i FROM ItemOrtograma i WHERE i.itemOrtogramaPK.type = :type and i.itemOrtogramaPK.idItem = :idItem")
    List<ItemOrtograma> findByTypeAndIdItem(@Param("type") String type, @Param("idItem") String idItem);

    @Query("SELECT i FROM ItemOrtograma i WHERE i.itemOrtogramaPK.type = :type and i.itemOrtogramaPK.ortograma = :ortograma")
    List<ItemOrtograma> findByOrtogramaAndType(@Param("ortograma") String ortograma, @Param("type") String type);

    @Query("SELECT i FROM ItemOrtograma i WHERE i.itemOrtogramaPK.ortograma = :ortograma")
    List<ItemOrtograma> findByOrtograma(@Param("ortograma") String ortograma);

}
