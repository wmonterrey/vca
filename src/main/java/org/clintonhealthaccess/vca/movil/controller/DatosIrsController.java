package org.clintonhealthaccess.vca.movil.controller;


import org.clintonhealthaccess.vca.domain.irs.IrsSeason;
import org.clintonhealthaccess.vca.domain.irs.Target;
import org.clintonhealthaccess.vca.service.IrsSeasonService;
import org.clintonhealthaccess.vca.service.SupervisionService;
import org.clintonhealthaccess.vca.service.TargetService;
import org.clintonhealthaccess.vca.service.VisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class DatosIrsController {

    private static final Logger logger = LoggerFactory.getLogger(DatosMtildController.class);

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
    @RequestMapping(value = "datosirs/filtrado/{fechaActualizacion}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    DatosIrs getDatos(@PathVariable("fechaActualizacion") String fechaActualizacion)
    		throws ParseException {
    	
    	Long fecAct = null;
    	
    	if (!fechaActualizacion.matches("")) {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        	fecAct = formatter.parse(fechaActualizacion).getTime();
        }
        
        logger.info("Descargando toda la informacion de los datos temporadas");
        List<IrsSeason> temporadas = temporadaService.getTemporadasFiltrado(fecAct);
        if (temporadas == null){
        	logger.debug(new Date() + " - Temporadas - Nulo");
        }
        
        logger.info("Descargando toda la informacion de los datos metas");
        List<Target> metas = targetService.getMetasFiltrado(fecAct,SecurityContextHolder.getContext().getAuthentication().getName());
        if (metas == null){
        	logger.debug(new Date() + " - Metas - Nulo");
        }
        
        
        //Crea la clase DatosMtild
        DatosIrs datos = new DatosIrs();
        datos.setTemporadas(temporadas);
        datos.setTargets(metas);

        return  datos;
    }    
    
    /**
     * Acepta una solicitud POST con un JSON
     * @param envio Objeto serializado de Household
     * @return String con el resultado
     */
    @RequestMapping(value = "datosirs", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveDatosMtild(@RequestBody DatosMtild envio) {
        logger.debug("Insertando/Actualizando formularios");
        try{
	        if (envio == null){
	            logger.debug("Nulo");
	            return "No recibi nada!";
	        }
	        else{
	        	/*if (envio.getViviendas() != null){
		            for (Household vivienda : envio.getViviendas()){
		            	householdService.saveVivienda(vivienda);
		            }
	        	}*/
	        }
	        return "Datos recibidos!";
        }
		catch (DataIntegrityViolationException e){
			String message = e.getMostSpecificCause().getMessage();
		    return message;
		}
		catch(Exception e){
		    return e.toString();
		}
    }

}
