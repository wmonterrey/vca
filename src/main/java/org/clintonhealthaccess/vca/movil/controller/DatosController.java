package org.clintonhealthaccess.vca.movil.controller;

import org.clintonhealthaccess.vca.domain.Household;
import org.clintonhealthaccess.vca.domain.irs.IrsSeason;
import org.clintonhealthaccess.vca.domain.irs.Supervision;
import org.clintonhealthaccess.vca.domain.irs.Target;
import org.clintonhealthaccess.vca.domain.irs.Visit;
import org.clintonhealthaccess.vca.service.HouseholdService;
import org.clintonhealthaccess.vca.service.IrsSeasonService;
import org.clintonhealthaccess.vca.service.SupervisionService;
import org.clintonhealthaccess.vca.service.TargetService;
import org.clintonhealthaccess.vca.service.VisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * Controlador que provee los mapeos de la aplicacion movil para:
 * 
 * <ul>
 * <li>Pedir los datos de la aplicacion
 * </ul>
 * 
 * @author William Aviles
 **/
@Controller
@RequestMapping("/movil/*")
public class DatosController {

    private static final Logger logger = LoggerFactory.getLogger(DatosController.class);

    @Resource(name = "householdService")
    private HouseholdService householdService;
    @Resource(name = "temporadaService")
    private IrsSeasonService temporadaService;
    @Resource(name = "targetService")
    private TargetService targetService;
    @Resource(name = "visitService")
    private VisitService visitService;
    @Resource(name = "supervisionService")
    private SupervisionService supervisionService;
    

    /**
     * Retorna datos. Acepta una solicitud GET para JSON
     * @return Datos JSON
     */
    @RequestMapping(value = "datos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Datos getDatos(){
        logger.info("Descargando toda la informacion de los datos vivienda");
        List<Household> viviendas = householdService.getHousesFiltro(null, null, null, null,"ALL", "ALL", "ALL",SecurityContextHolder.getContext().getAuthentication().getName(), "0");
        if (viviendas == null){
        	logger.debug(new Date() + " - Viviendas - Nulo");
        }
        logger.info("Descargando toda la informacion de los datos temporadas");
        List<IrsSeason> temporadas = temporadaService.getActiveIrsSeasons();
        if (temporadas == null){
        	logger.debug(new Date() + " - Temporadas - Nulo");
        }
        logger.info("Descargando toda la informacion de los datos metas");
        List<Target> metas = targetService.getMetasFiltro(null, null, null, null, "ALL", "ALL", "ALL", SecurityContextHolder.getContext().getAuthentication().getName(), "0");
        if (metas == null){
        	logger.debug(new Date() + " - Metas - Nulo");
        }
        logger.info("Descargando toda la informacion de los datos visitas");
        List<Visit> visitas = visitService.getVisitasFiltro(null, null, null, null, "ALL", "ALL", "ALL", "ALL", SecurityContextHolder.getContext().getAuthentication().getName(), "0");
        if (visitas == null){
        	logger.debug(new Date() + " - Visitas - Nulo");
        }
        logger.info("Descargando toda la informacion de los datos supervisiones");
        List<Supervision> supervisiones = supervisionService.getSupervisionsFiltro(null, null, null, null, "ALL", "ALL", "ALL", "ALL", SecurityContextHolder.getContext().getAuthentication().getName(), "0");
        if (supervisiones == null){ 
        	logger.debug(new Date() + " - Supervisiones - Nulo");
        }
        //Crea la clase Datos
        Datos datos = new Datos();
        datos.setViviendas(viviendas);
        datos.setTemporadas(temporadas);
        datos.setMetas(metas);
        datos.setVisitas(visitas);
        datos.setSupervisiones(supervisiones);
        return  datos;
    }

}
