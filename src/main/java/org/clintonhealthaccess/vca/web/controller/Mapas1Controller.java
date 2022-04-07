package org.clintonhealthaccess.vca.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.clintonhealthaccess.vca.domain.Caso;
import org.clintonhealthaccess.vca.domain.Criadero;
import org.clintonhealthaccess.vca.domain.PuntoDiagnostico;
import org.clintonhealthaccess.vca.language.MessageResource;
import org.clintonhealthaccess.vca.movil.controller.DatosMapa;
import org.clintonhealthaccess.vca.service.CriaderoService;
import org.clintonhealthaccess.vca.service.DashboardMap1Service;
import org.clintonhealthaccess.vca.service.MessageResourceService;
import org.clintonhealthaccess.vca.service.PuntoDiagnosticoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/view/maps/*")
public class Mapas1Controller {
	
	@Resource(name="dashboardMap1Service")
	private DashboardMap1Service dashboardMap1Service;
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	@Resource(name="puntoDiagnosticoService")
	private PuntoDiagnosticoService puntoDiagnosticoService;
	@Resource(name="criaderoService")
	private CriaderoService criaderoService;
    private static final Logger logger = LoggerFactory.getLogger(Mapas1Controller.class);

    @RequestMapping(value = "/pordia/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Object> obtenerCasosxDia(@RequestParam(value = "area", required = true) String area,
    		@RequestParam(value = "district", required = true) String district,
    		@RequestParam(value = "foci", required = true) String foci,
    		@RequestParam(value = "localidad", required = true) String localidad,
    		@RequestParam(value = "tiempo", required = true) String tiempo,
    		@RequestParam(value = "fecMuestraRange", required = false, defaultValue = "") String fecMuestraRange
    		) throws ParseException {
        logger.info("Obteniendo casos por dia");
        Long desde = null;
        Long hasta = null;
        
        if (!fecMuestraRange.matches("")) {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        	desde = formatter.parse(fecMuestraRange.substring(0, 10)).getTime();
        	hasta = formatter.parse(fecMuestraRange.substring(fecMuestraRange.length()-10, fecMuestraRange.length())).getTime();
        }
        List<Object> datos = dashboardMap1Service.getDatosCasosxFecha(area,district,foci,localidad, desde, hasta,tiempo,SecurityContextHolder.getContext().getAuthentication().getName());
        return datos;
    }
    
    @RequestMapping(value = "/porou/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Object> obtenerCasosxOU(@RequestParam(value = "area", required = true) String area,
    		@RequestParam(value = "district", required = true) String district,
    		@RequestParam(value = "foci", required = true) String foci,
    		@RequestParam(value = "localidad", required = true) String localidad,
    		@RequestParam(value = "tipoou", required = true) String tipoou,
    		@RequestParam(value = "fecMuestraRange", required = false, defaultValue = "") String fecMuestraRange
    		) throws ParseException {
        logger.info("Obteniendo casos por unidad organizativa");
        Long desde = null;
        Long hasta = null;
        
        if (!fecMuestraRange.matches("")) {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        	desde = formatter.parse(fecMuestraRange.substring(0, 10)).getTime();
        	hasta = formatter.parse(fecMuestraRange.substring(fecMuestraRange.length()-10, fecMuestraRange.length())).getTime();
        }
        List<Object> datos = dashboardMap1Service.getDatosCasosxOU(area,district,foci,localidad,desde, hasta,tipoou,SecurityContextHolder.getContext().getAuthentication().getName());
        return datos;
    }
    
    @RequestMapping(value = "/porestado/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Object> obtenerCasosxEstado(@RequestParam(value = "area", required = true) String area,
    		@RequestParam(value = "district", required = true) String district,
    		@RequestParam(value = "foci", required = true) String foci,
    		@RequestParam(value = "localidad", required = true) String localidad,
    		@RequestParam(value = "tipoou", required = true) String tipoou,
    		@RequestParam(value = "fecMuestraRange", required = false, defaultValue = "") String fecMuestraRange
    		) throws ParseException {
        logger.info("Obteniendo casos por estado");
        Long desde = null;
        Long hasta = null;
        
        if (!fecMuestraRange.matches("")) {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        	desde = formatter.parse(fecMuestraRange.substring(0, 10)).getTime();
        	hasta = formatter.parse(fecMuestraRange.substring(fecMuestraRange.length()-10, fecMuestraRange.length())).getTime();
        }
        List<Object> datos = dashboardMap1Service.getDatosCasosxEstado(area,district,foci,localidad,desde, hasta,tipoou,SecurityContextHolder.getContext().getAuthentication().getName());
        return datos;
    }
    
    
    @RequestMapping(value = "/porubi/{mapa}/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody DatosMapa obtenerCasosxUbi(@PathVariable("mapa") String mapa,
    		@RequestParam(value = "area", required = true) String area,
    		@RequestParam(value = "district", required = true) String district,
    		@RequestParam(value = "foci", required = true) String foci,
    		@RequestParam(value = "localidad", required = true) String localidad,
    		@RequestParam(value = "fecMuestraRange", required = false, defaultValue = "") String fecMuestraRange
    		) throws ParseException {
        logger.info("Obteniendo casos");
        Long desde = null;
        Long hasta = null;
        
        if (!fecMuestraRange.matches("")) {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        	desde = formatter.parse(fecMuestraRange.substring(0, 10)).getTime();
        	hasta = formatter.parse(fecMuestraRange.substring(fecMuestraRange.length()-10, fecMuestraRange.length())).getTime();
        }
        List<String> estadocaso = new ArrayList<String>();
        if(mapa.equals("1")) {
        	estadocaso.add("CONF");
            estadocaso.add("TRAT");
            estadocaso.add("TRATC");
            estadocaso.add("SEG2");
        }
        else if(mapa.equals("2")) {
        	estadocaso.add("SEG4");
        }
        
        List<Caso> datos = dashboardMap1Service.getDatosCasosxUbi(area,district,foci,localidad, desde, hasta,SecurityContextHolder.getContext().getAuthentication().getName(),estadocaso);
        List<PuntoDiagnostico> puntos = puntoDiagnosticoService.getActivePuntoDiagnosticos();
        for (PuntoDiagnostico punto:puntos) {
    		MessageResource mr = null;
    		String descCatalogo = null;
    		mr = this.messageResourceService.getMensaje(punto.getTipo(),"CAT_TIPOPDX");
    		if(mr!=null) {
    			descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
    			punto.setTipo(descCatalogo);
    		}
    		mr = this.messageResourceService.getMensaje(punto.getStatus(),"CAT_ESTADOPDX");
    		if(mr!=null) {
    			descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
    			punto.setStatus(descCatalogo);
    		}
    	}
        List<Criadero> criaderos = criaderoService.getActiveCriaderos();
        for (Criadero criadero:criaderos) {
    		MessageResource mr = null;
    		String descCatalogo = null;
    		mr = this.messageResourceService.getMensaje(criadero.getTipo(),"CAT_TIPOPCR");
    		if(mr!=null) {
    			descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
    			criadero.setTipo(descCatalogo);
    		}
    	}
        
        DatosMapa datosMapa = new DatosMapa();
        datosMapa.setCasos(datos);
        datosMapa.setPuntoDiagnosticos(puntos);
        datosMapa.setCriaderos(criaderos);
        
        return datosMapa;
    }
    
}
