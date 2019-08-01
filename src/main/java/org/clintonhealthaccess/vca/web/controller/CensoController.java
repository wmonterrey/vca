package org.clintonhealthaccess.vca.web.controller;

import org.clintonhealthaccess.vca.domain.Censador;
import org.clintonhealthaccess.vca.domain.Household;
import org.clintonhealthaccess.vca.domain.Localidad;
import org.clintonhealthaccess.vca.domain.audit.AuditTrail;
import org.clintonhealthaccess.vca.language.MessageResource;
import org.clintonhealthaccess.vca.service.DistritoService;
import org.clintonhealthaccess.vca.service.HouseholdService;
import org.clintonhealthaccess.vca.service.LocalidadService;
import org.clintonhealthaccess.vca.service.AuditTrailService;
import org.clintonhealthaccess.vca.service.CensadorService;
import org.clintonhealthaccess.vca.service.MessageResourceService;
import org.clintonhealthaccess.vca.service.ParametroService;
import org.clintonhealthaccess.vca.service.UsuarioService;
import org.clintonhealthaccess.vca.users.model.UserSistema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Controlador web de peticiones
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/census/*")
public class CensoController {
	private static final Logger logger = LoggerFactory.getLogger(CensoController.class);
	@Resource(name="householdService")
	private HouseholdService householdService;
	@Resource(name="localidadService")
	private LocalidadService localidadService;
	@Resource(name="distritoService")
	private DistritoService distritoService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	@Resource(name="censadorService")
	private CensadorService censadorService;
	@Resource(name="parametroService")
	private ParametroService parametroService;
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
    
    
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String getEntities(Model model) throws ParseException { 	
		logger.debug("Mostrando Viviendas en JSP");
    	List<Localidad> localidades = localidadService.getLocalities();
    	model.addAttribute("localidades", localidades);
    	List<Censador> censadores = censadorService.getCensadores();
    	model.addAttribute("censadores", censadores);
    	List<UserSistema> usuarios = this.usuarioService.getUsers();
    	model.addAttribute("usuarios", usuarios);
    	List<MessageResource> materiales = messageResourceService.getCatalogo("CAT_MAT");
    	model.addAttribute("materiales", materiales);
    	return "censo/censo";
	}
	
	/**
     * Custom handler for adding.
     * @param model Modelo enlazado a la vista
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/newEntity/", method = RequestMethod.GET)
	public String addEntity(Model model) {
    	Household vivienda = new Household();
    	model.addAttribute("vivienda", vivienda);
    	return "censo/enterForm";
	}
    
    
    @RequestMapping(value = "/searchHouses/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Household> fetchHouses(@RequestParam(value = "codeHouse", required = false) String codeHouse,
    		@RequestParam(value = "ownerName", required = false) String ownerName,
    		@RequestParam(value = "fecCensoRange", required = false, defaultValue = "") String fecCensoRange,
    		@RequestParam(value = "local", required = true) String local,
    		@RequestParam(value = "censusTaker", required = true) String censusTaker,
    		@RequestParam(value = "recordUser", required = true) String recordUser
    		) throws ParseException {
        logger.info("Obteniendo resultados");
        Long desde = null;
        Long hasta = null;
        
        if (!fecCensoRange.matches("")) {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        	desde = formatter.parse(fecCensoRange.substring(0, 10)).getTime();
        	hasta = formatter.parse(fecCensoRange.substring(fecCensoRange.length()-10, fecCensoRange.length())).getTime();
        }
        List<Household> datos = householdService.getHousesFiltro(codeHouse, ownerName, desde, hasta, local, censusTaker, recordUser);
        if (datos == null){
        	logger.debug("Nulo");
        }
        else {
        	for (Household vivienda:datos) {
        		MessageResource mr = null;
        		String descCatalogo = null;
        		mr = this.messageResourceService.getMensaje(vivienda.getMaterial(),"CAT_MAT");
        		if(mr!=null) {
        			descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
        			vivienda.setMaterial(descCatalogo);
        		}
        		mr = this.messageResourceService.getMensaje(vivienda.getInhabited(),"CAT_SINO");
        		if(mr!=null) {
        			descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
        			vivienda.setInhabited(descCatalogo);
        		}
        		if(!(vivienda.getNoSproomsReasons()==null)) {
        			String[] partsNSP = vivienda.getNoSproomsReasons().split(",");
        			descCatalogo = null;
        			for (int i =0; i<partsNSP.length; i++) {
        				mr = this.messageResourceService.getMensaje(partsNSP[i],"CAT_RNR");
                		if(mr!=null) {
                			if(descCatalogo==null) {
                				descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
                			}
                			else {
                				String nuevo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
                				descCatalogo = descCatalogo + ", " +nuevo;
                			}
                		}
        			}
        			vivienda.setNoSproomsReasons(descCatalogo);
        		}
        	}
        }
        return datos;
    }
    
    /**
     * Custom handler for displaying.
     *
     * @param ident the ID to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/{ident}/")
    public ModelAndView showEntity(@PathVariable("ident") String ident) {
    	ModelAndView mav;
    	Float latitud;
    	Float longitud;
    	Integer zoom;
    	Household vivienda = this.householdService.getVivienda(ident);
        if(vivienda==null){
        	mav = new ModelAndView("403");
        }
        else{
        	mav = new ModelAndView("censo/viewForm");
        	zoom = Integer.parseInt(parametroService.getParametroByCode("zoom").getValue());
        	latitud = Float.parseFloat(parametroService.getParametroByCode("lat").getValue());
        	longitud = Float.parseFloat(parametroService.getParametroByCode("long").getValue());
        	if(vivienda.getLatitude()!=null) latitud = vivienda.getLatitude();
        	if(vivienda.getLongitude()!=null) longitud = vivienda.getLongitude();
        	
        	mav.addObject("vivienda",vivienda);
        	mav.addObject("zoom",zoom);
        	mav.addObject("latitud",latitud);
        	mav.addObject("longitud",longitud);
            List<AuditTrail> bitacora = auditTrailService.getBitacora(ident);
            mav.addObject("bitacora",bitacora);
        }
        return mav;
    }
    
	
}
