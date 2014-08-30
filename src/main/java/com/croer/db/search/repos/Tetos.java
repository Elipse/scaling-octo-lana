package com.croer.db.search.repos;

import com.croer.db.search.entities.Alineacion;
import com.croer.db.search.entities.AlineacionPK;
import com.croer.db.search.repdefinition.JpaRepositoryXeam;
import java.util.List;
import org.springframework.data.repository.query.Param;

public interface Tetos extends JpaRepositoryXeam<Alineacion, AlineacionPK> {

    List<Alineacion> findByOrtograma(@Param("ortograma") String ortograma);

    List<Alineacion> findBySimigrama(@Param("simigrama") String simigrama);

}
