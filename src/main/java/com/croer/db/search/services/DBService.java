/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.croer.db.search.services;

import com.croer.db.search.entities.Alineacion;
import com.croer.db.search.entities.Diccionario;
import com.croer.db.search.entities.DiccionarioOrtograma;
import com.croer.db.search.entities.ItemOrtograma;
import com.croer.db.search.entities.Itembusq;
import com.croer.db.search.entities.Ortograma;
import com.croer.db.search.entities.Simigrama;
import com.croer.db.search.repositories.AlineacionRepository;
import com.croer.db.search.repositories.DiccionarioOrtogramaRepository;
import com.croer.db.search.repositories.DiccionarioRepository;
import com.croer.db.search.repositories.ItemOrtogramaRepository;
import com.croer.db.search.repositories.ItembusqRepository;
import com.croer.db.search.repositories.OrtogramaRepository;
import com.croer.db.search.repositories.SimigramaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 *
 * @author elialva
 */
@Service
public class DBService {

    @Autowired
    private AlineacionRepository alineacionRepository;
    @Autowired
    private ItemOrtogramaRepository itemOrtogramaRepository;
    @Autowired
    private ItembusqRepository itembusqRepository;
    @Autowired
    private OrtogramaRepository ortogramaRepository;
    @Autowired
    private SimigramaRepository simigramaRepository;
    @Autowired
    private DiccionarioRepository diccionarioRepository;
    @Autowired
    private DiccionarioOrtogramaRepository diccOrtoRepository;

    public void saveDic(Diccionario diccionario) {
        diccionarioRepository.save(diccionario);
    }

//    @Autowired
//    public LogMDataAccess(AlineacionRepository alineacionRepository,
//            ItemOrtogramaRepository itemOrtogramaRepository,
//            ItembusqRepository itembusqRepository,
//            OrtogramaRepository ortogramaRepository,
//            SimigramaRepository simigramaRepository) {
//        this.alineacionRepository = alineacionRepository;
//        this.itemOrtogramaRepository = itemOrtogramaRepository;
//        this.itembusqRepository = itembusqRepository;
//        this.ortogramaRepository = ortogramaRepository;
//        this.simigramaRepository = simigramaRepository;
//
//    }
    
    @Transactional
    public void saveContext(Itembusq itembusq, List<Ortograma> ortoList, List<Simigrama> simiList, Diccionario diccionario) {
        List<Ortograma> ortoListOld = new ArrayList<>();
        if (itembusqRepository.exists(itembusq.getItembusqPK())) {
            Itembusq findOne = itembusqRepository.findOne(itembusq.getItembusqPK());
            List<ItemOrtograma> tmpIO = findOne.getItemOrtogramaList();
            tmpIO.removeAll(itembusq.getItemOrtogramaList());
            for (ItemOrtograma itemOrtograma : tmpIO) {
                ortoListOld.add(itemOrtograma.getOrtograma1());
            }
        }

        diccionarioRepository.save(diccionario);
        ortogramaRepository.save(ortoList);           //Da de alta los DO's pero no borra al salvar
        itembusqRepository.save(itembusq);            //Da de alta los IO's borra al salvar 
        simigramaRepository.save(simiList);
        //flush
        borraOrtograma(ortoListOld, itembusq);
    }

    @Transactional
    public void borraOrtograma(List<Ortograma> ortoList, Itembusq itembusq) {
        for (Ortograma ortograma : ortoList) {
            String tmpType = itembusq.getItembusqPK().getType();
            System.out.println("itemOrtogramaRepository " + itemOrtogramaRepository);
            System.out.println("Ortis " + ortograma);
            List<ItemOrtograma> tmp = itemOrtogramaRepository.findByOrtogramaAndType(ortograma.getOrtograma(), tmpType);
            if (!tmp.isEmpty()) {                                                    //* ¿Se usa para describir otros types?
                return;
            }

            List<DiccionarioOrtograma> dol = ortograma.getDiccionarioOrtogramaList();
            dol.remove(new DiccionarioOrtograma(tmpType, ortograma.getOrtograma())); //* Borra del diccionario de Productos
            if (dol.size() > 0) {                                                    //* ¿Se usa en otros diccionarios?  
                ortogramaRepository.save(ortograma);                                 //* Actualiza BD
                return;
            }

            //Dame sus simigramas asociados (vía sus alineaciones) 
            List<Alineacion> alinList = ortograma.getAlineacionList();
            List<Simigrama> simiList = new ArrayList<>();
            for (Alineacion alineacion : alinList) {
                simiList.add(alineacion.getSimigrama1());
            }

            //Borra las alineaciones del ortograma a borrar, y en su caso el simigrama 
            for (Simigrama simigrama : simiList) {
                List<Alineacion> alinListT = simigrama.getAlineacionList();
                System.out.println("D " + simigrama.getSimigrama() + ":" + alinListT);
                System.out.println("MemEAM " + simigrama.hashCode() + ':' + alinListT.hashCode());

                Alineacion t = new Alineacion(simigrama.getSimigrama(), ortograma.getOrtograma());
                alinListT.remove(t);                       //* Borra las Alineacion(es) del ortograma
                if (alinListT.isEmpty()) {
                    System.out.println("Borrando simigrama " + simigrama.getSimigrama() + ":" + alinListT);
                    simigramaRepository.delete(simigrama); //* Borra Simigrama y todas sus Alineacion(es)
                } else {
                    System.out.println("Dejando simigrama " + simigrama.getSimigrama() + ":" + alinListT);
                    simigramaRepository.save(simigrama);   //* Solo conserva las alineaciones de los otros ortogramas 
                }
            }

            ortogramaRepository.delete(ortograma);         //* Borra el ortograma y sus DiccOrto
        }
    }    
}
