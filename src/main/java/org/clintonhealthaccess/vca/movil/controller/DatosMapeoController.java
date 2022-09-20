package org.clintonhealthaccess.vca.movil.controller;

import org.clintonhealthaccess.vca.domain.Caso;
import org.clintonhealthaccess.vca.domain.Muestra;
import org.clintonhealthaccess.vca.service.CasoService;
import org.clintonhealthaccess.vca.service.MuestraService;
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
 * <li>Pedir los datos de la aplicacion
 * </ul>
 * 
 * @author William Aviles
 **/
@Controller
@RequestMapping("/movil/*")
public class DatosMapeoController {

    private static final Logger logger = LoggerFactory.getLogger(DatosMapeoController.class);

    @Resource(name = "casoService")
    private CasoService casoService;
    
    @Resource(name = "muestraService")
    private MuestraService muestraService;
    
    

    /**
     * Retorna datos. Acepta una solicitud GET para JSON
     * @return Datos JSON
     */
    @RequestMapping(value = "datosmapeo", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    DatosMapeo getDatos(){
        
        logger.info("Descargando toda la informacion de los casos");
        List<Caso> casos = casoService.getActiveCasos();
        if (casos == null){
        	logger.debug(new Date() + " - Casos - Nulo");
        }
        
        List<Muestra> muestras = muestraService.getActiveMuestras();
        if (muestras == null){
        	logger.debug(new Date() + " - Muestras - Nulo");
        }
        
        
        //Crea la clase DatosMapeo
        DatosMapeo datos = new DatosMapeo();
        datos.setCasos(casos);
        datos.setMuestras(muestras);
        return  datos;
    }
    
    
    /**
     * Acepta una solicitud POST con un JSON
     * @param envio Objeto serializado de Caso
     * @return String con el resultado
     */
    @RequestMapping(value = "datosmapeo", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String saveDatosMtild(@RequestBody DatosMapeo envio) {
        logger.debug("Insertando/Actualizando formularios casos");
        if (envio == null){
            logger.debug("Nulo");
            return "No recibi nada!";
        }
        else{
        	if (envio.getCasos() != null){
	            for (Caso caso : envio.getCasos()){
	            	casoService.saveCaso(caso);;
	            }
        	}
        	if (envio.getMuestras() != null){
	            for (Muestra muestra : envio.getMuestras()){
	            	muestraService.saveMuestra(muestra);
	            }
        	}
        }
        return "Datos recibidos!";
    }

}
