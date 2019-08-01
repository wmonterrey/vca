package org.clintonhealthaccess.vca.movil.controller;

import org.clintonhealthaccess.vca.domain.Censador;
import org.clintonhealthaccess.vca.domain.irs.Brigada;
import org.clintonhealthaccess.vca.domain.irs.Rociador;
import org.clintonhealthaccess.vca.domain.irs.Supervisor;
import org.clintonhealthaccess.vca.language.MessageResource;
import org.clintonhealthaccess.vca.service.BrigadaService;
import org.clintonhealthaccess.vca.service.CensadorService;
import org.clintonhealthaccess.vca.service.MessageResourceService;
import org.clintonhealthaccess.vca.service.RociadorService;
import org.clintonhealthaccess.vca.service.SupervisorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * Controlador que provee los mapeos de la aplicacion movil para:
 * 
 * <ul>
 * <li>Pedir los catalogos de la aplicacion
 * </ul>
 * 
 * @author William Aviles
 **/
@Controller
@RequestMapping("/movil/*")
public class CatalogosController {

    private static final Logger logger = LoggerFactory.getLogger(CatalogosController.class);

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;
    @Resource(name = "censadorService")
    private CensadorService censadorService;
    @Resource(name = "rociadorService")
    private RociadorService rociadorService;
    @Resource(name = "supervisorService")
    private SupervisorService supervisorService;
    @Resource(name = "brigadaService")
    private BrigadaService brigadaService;

    /**
     * Retorna catalogos. Acepta una solicitud GET para JSON
     * @return Catalogo JSON
     */
    @RequestMapping(value = "catalogos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Catalogos getCatalogos(){
        logger.info("Descargando toda la informacion de los datos de los catalogos");
        List<MessageResource> messages = messageResourceService.getCatalogos();
        if (messages == null){
        	logger.debug(new Date() + " - Mensajes - Nulo");
        }
        logger.info("Descargando toda la informacion de los datos de los censadores");
        List<Censador> censadores = censadorService.getActiveCensadores();
        if (censadores == null){
        	logger.debug(new Date() + " - Censadores - Nulo");
        }
        logger.info("Descargando toda la informacion de los datos de los rociadores");
        List<Rociador> rociadores = rociadorService.getActiveRociadores();
        if (rociadores == null){
        	logger.debug(new Date() + " - Rociadores - Nulo");
        }
        logger.info("Descargando toda la informacion de los datos de los supervisores");
        List<Supervisor> supervisores = supervisorService.getActiveSupervisores();
        if (supervisores == null){
        	logger.debug(new Date() + " - Supervisores - Nulo");
        }
        logger.info("Descargando toda la informacion de los datos de los brigadas");
        List<Brigada> brigadas = brigadaService.getActiveBrigadas();
        if (brigadas == null){
        	logger.debug(new Date() + " - Brigadas - Nulo");
        }
        
        //Crea la clase Catalogos
        Catalogos catalogos = new Catalogos();
        catalogos.setCatalogos(messages);
        catalogos.setCensadores(censadores);
        catalogos.setRociadores(rociadores);
        catalogos.setSupervisores(supervisores);
        catalogos.setBrigadas(brigadas);
        return  catalogos;
    }

}
