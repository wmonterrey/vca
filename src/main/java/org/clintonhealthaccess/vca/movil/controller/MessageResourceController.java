package org.clintonhealthaccess.vca.movil.controller;

import org.clintonhealthaccess.vca.language.MessageResource;
import org.clintonhealthaccess.vca.service.MessageResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
public class MessageResourceController {

    private static final Logger logger = LoggerFactory.getLogger(MessageResourceController.class);

    @Resource(name = "messageResourceService")
    private MessageResourceService messageResourceService;

    /**
     * Retorna catalogos. Acepta una solicitud GET para JSON
     * @return catalogos JSON
     */
    @RequestMapping(value = "catalogos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<MessageResource> getMessageResources(){
        logger.info("Descargando toda la informacion de los datos de los catalogos");
        List<MessageResource> catalogos = messageResourceService.getCatalogos();
        if (catalogos == null){
            logger.debug("Nulo");
        }
        return  catalogos;
    }

}
