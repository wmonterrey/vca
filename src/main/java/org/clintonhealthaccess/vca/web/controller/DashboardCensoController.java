package org.clintonhealthaccess.vca.web.controller;

import java.text.ParseException;
import java.util.List;
import javax.annotation.Resource;
import org.clintonhealthaccess.vca.service.DashboardCensoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/view/censo/*")
public class DashboardCensoController {
	
	@Resource(name="dashboardCensoService")
	private DashboardCensoService dashboardCensoService;
    private static final Logger logger = LoggerFactory.getLogger(DashboardCensoController.class);

    @RequestMapping(value = "/pordia/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Object> obtenerViviendasxDia() throws ParseException {
        logger.info("Obteniendo viviendas por dia de censo");
        List<Object> datos = dashboardCensoService.getDatosHouseholdxFecha();
        return datos;
    }
}
