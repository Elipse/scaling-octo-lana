package com.croer.javalog.mvc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.croer.db.search.services.DBService;
import com.croer.db.business.entities.ProductoLog;
import com.croer.db.search.entities.Alineacion;
import com.croer.db.search.entities.AlineacionPK;
import com.croer.db.search.entities.Diccionario;
import com.croer.db.search.entities.DiccionarioOrtograma;
import com.croer.db.search.entities.ItemOrtograma;
import com.croer.db.search.entities.Itembusq;
import com.croer.db.search.entities.Ortograma;
import com.croer.db.search.entities.Simigrama;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import util.Utilities;
import util.Configuration;

/**
 *
 * @author elialva
 */
public class LogMProcess {

    private static CompositeConfiguration CONFIGURATION;
    private static DBService DBSERVICE;

    public LogMProcess() {      //CREA una instancia que informa los valores estáticos
        CONFIGURATION = Configuration.getCONFIGURATION();
        DBSERVICE = Configuration.getApplicationContext().getBean(DBService.class);
    }

    private static List<String> generatePropList(Object bean, String suffix) {
        String type = bean.getClass().getName();
        String key = CONFIGURATION.getString(type + "." + suffix);

        if (key == null) {
            throw new IllegalArgumentException("Missing properties for: " + type + "." + suffix);
        }

        return Arrays.asList(StringUtils.split(key, "|"));
    }

    private static String stringProps(Object bean, List<String> props, char separator) {
        String string = "";

        for (String property : props) {
            try {
                Object data = PropertyUtils.getProperty(bean, property);
                string = string.isEmpty() ? data.toString() : string + separator + data;
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                Logger.getLogger(LogMProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return string;
    }

    private static Itembusq createItembusq(Object bean) {
        String type = bean.getClass().getName();
        List<String> keyList = generatePropList(bean, "key");
        List<String> contextList = generatePropList(bean, "context");
        String idItem = stringProps(bean, keyList, ':');
        String context = stringProps(bean, contextList, ' ');
        Itembusq itembusq = new Itembusq(type, idItem);
        itembusq.setContexto(context);

        return itembusq;
    }

    public static Map<String, Integer> processLogs(List<JpaRepository> repoList) {
        Map<String, Integer> map = new HashMap();
        for (JpaRepository name : repoList) {
            String repoName = name.toString();
            map.put(repoName, updateLog(name));
        }

        return map;
    }

    @Transactional
    private static int updateLog(JpaRepository repoName) {
//        if (repoName.equals("productoLogRepository")) {
//            throw new IllegalAccessError("dedededeAAAA");
//        }        
//        repository = null;
        List findAll = repoName.findAll();
        for (Object object : findAll) {
            process(object);
        }

        repoName.delete(findAll); //hace el borrado de simigrama pero no de producto por ejemplo. si no procesa no puede borrar
        return findAll.size();
    }

    private static void process(Object object) {
        //
        String actionType = "";
        try {
            actionType = (String) PropertyUtils.getProperty(object, "actionType");
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(LogMProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Acction del Log " + actionType);
        //Create Itembusq   
        Itembusq itembusq = createItembusq(object);
        String type = itembusq.getItembusqPK().getType();
        String idItem = itembusq.getItembusqPK().getIdItem();
        String context = itembusq.getContexto();

        //Create Diccionario
        Diccionario d = new Diccionario(type);
        d.setNombre(CONFIGURATION.getString("diccionario.prefijo") + type);

        //Placeholders for the others entities
        List<ItemOrtograma> itorList = new ArrayList<>();
        List<Ortograma> ortoList = new ArrayList<>();
        List<Simigrama> simiList = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : Utilities.entries(context).entrySet()) {
            //Create an ItemOrtograma for each ortograma
            String ortograma = entry.getKey();
            int frequency = entry.getValue();
            ItemOrtograma io = new ItemOrtograma(type, idItem, ortograma);
            io.setFrecuencia(frequency);
            itorList.add(io);
            //Create a Ortograma for each ortograma and attach a DiccionarioOrtograma
            Ortograma o = new Ortograma(ortograma, Utilities.numegram(ortograma));
            o.setDiccionarioOrtogramaList(Arrays.asList(new DiccionarioOrtograma(type, ortograma)));
            ortoList.add(o);
            //Create Simigrams and its Alineacions
            Map<String, String> variations = Utilities.variations(ortograma);
            for (Map.Entry<String, String> variation : variations.entrySet()) {
                String simigram = variation.getKey();
                String alignment = variation.getValue();
                Simigrama s = new Simigrama(simigram, Utilities.numegram(simigram));
                s.setAlineacionList(Arrays.asList(new Alineacion(new AlineacionPK(simigram, ortograma), alignment)));
                simiList.add(s);
            }
        }

        itembusq.setItemOrtogramaList(itorList);
        //Record on DB
        System.out.println("DBesos " + DBSERVICE);
        switch (actionType) {
            case "Delete":
                DBSERVICE.deleteContext(itembusq);
                break;
            default:
                DBSERVICE.saveContext(itembusq, ortoList, simiList, d);
                break;
        }
    }

    public static void main(String[] args) {

        LogMProcess lp = new LogMProcess();
        ProductoLog pl = new ProductoLog(13579, null);
        pl.setActionType("create");
        pl.setIdProducto(13579);
        pl.setDescripcion("Tetris");
        pl.setCantidad(new BigDecimal("12.30"));
        pl.setPrecio(BigDecimal.ZERO);
        process(pl);
    }
}
