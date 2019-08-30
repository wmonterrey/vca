package org.clintonhealthaccess.vca.web.controller;

import org.clintonhealthaccess.vca.domain.irs.Rociador;
import org.clintonhealthaccess.vca.domain.irs.Visit;
import org.clintonhealthaccess.vca.domain.Localidad;
import org.clintonhealthaccess.vca.domain.audit.AuditTrail;
import org.clintonhealthaccess.vca.domain.irs.IrsSeason;
import org.clintonhealthaccess.vca.language.MessageResource;
import org.clintonhealthaccess.vca.service.IrsSeasonService;
import org.clintonhealthaccess.vca.service.LocalidadService;
import org.clintonhealthaccess.vca.service.AuditTrailService;
import org.clintonhealthaccess.vca.service.RociadorService;
import org.clintonhealthaccess.vca.service.MessageResourceService;
import org.clintonhealthaccess.vca.service.VisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controlador web de peticiones
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/irs/visit/*")
public class VisitController {
	private static final Logger logger = LoggerFactory.getLogger(VisitController.class);
	@Resource(name="temporadaService")
	private IrsSeasonService temporadaService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	@Resource(name="localidadService")
	private LocalidadService localidadService;
	@Resource(name="visitService")
	private VisitService visitService;
	@Resource(name="rociadorService")
	private RociadorService rociadorService;

    
    
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String getEntities(Model model) throws ParseException { 	
		logger.debug("Mostrando Visitas en JSP");
		List<Localidad> localidades = localidadService.getActiveLocalitiesUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
    	model.addAttribute("localidades", localidades);
    	List<MessageResource> tiposvisita = messageResourceService.getCatalogo("CAT_VIS_TYPE");
    	model.addAttribute("tiposvisita",tiposvisita);
    	List<MessageResource> visitacompletasn = messageResourceService.getCatalogo("CAT_SINO");
    	model.addAttribute("visitacompletasn",visitacompletasn);
    	List<IrsSeason> temporadas = this.temporadaService.getIrsSeasons();
    	model.addAttribute("temporadas",temporadas);
    	return "irs/visit";
	}
    
    
    @RequestMapping(value = "/searchVisits/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Visit> fetchVisits(@RequestParam(value = "codeVisit", required = false) String codeVisit,
    		@RequestParam(value = "ownerName", required = false) String ownerName,
    		@RequestParam(value = "fecVisitaRange", required = false, defaultValue = "") String fecVisitaRange,
    		@RequestParam(value = "local", required = true) String local,
    		@RequestParam(value = "activity", required = true) String activity,
    		@RequestParam(value = "irsSeason", required = true) String irsSeason,
    		@RequestParam(value = "compVisit", required = true) String compVisit
    		) throws ParseException {
        logger.info("Obteniendo resultados");
        Long desde = null;
        Long hasta = null;
        
        if (!fecVisitaRange.matches("")) {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        	desde = formatter.parse(fecVisitaRange.substring(0, 10)).getTime();
        	hasta = formatter.parse(fecVisitaRange.substring(fecVisitaRange.length()-10, fecVisitaRange.length())).getTime();
        }
        List<Visit> datos = visitService.getVisitasFiltro(codeVisit, ownerName, desde, hasta, local, 
        		irsSeason, activity, compVisit, SecurityContextHolder.getContext().getAuthentication().getName(),null);
        if (datos == null){
        	logger.debug("Nulo");
        }
        else {
        	for (Visit visita:datos) {
        		MessageResource mr = null;
        		String descCatalogo = null;
        		mr = this.messageResourceService.getMensaje(visita.getActivity(),"CAT_VIS_TYPE");
        		if(mr!=null) {
        			descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
        			visita.setActivity(descCatalogo);
        		}
        		mr = this.messageResourceService.getMensaje(visita.getCompVisit(),"CAT_SINO");
        		if(mr!=null) {
        			descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
        			visita.setCompVisit(descCatalogo);
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
    	Visit visita = this.visitService.getVisit(ident);
        if(visita==null){
        	mav = new ModelAndView("403");
        }
        else{
        	mav = new ModelAndView("irs/viewVisitForm");
        	MessageResource mr = null;
    		String descCatalogo = null;
    		mr = this.messageResourceService.getMensaje(visita.getActivity(),"CAT_VIS_TYPE");
    		if(mr!=null) {
    			descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
    			visita.setActivity(descCatalogo);
    		}
    		mr = this.messageResourceService.getMensaje(visita.getCompVisit(),"CAT_SINO");
    		if(mr!=null) {
    			descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
    			visita.setCompVisit(descCatalogo);
    		}
        	mav.addObject("visita",visita);
            List<AuditTrail> bitacora = auditTrailService.getBitacora(ident);
            mav.addObject("bitacora",bitacora);
        }
        return mav;
    }
    
    /**
     * Custom handler for editing.
     * @param model Modelo enlazado a la vista
     * @param ident the ID to edit
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/editEntity/{ident}/", method = RequestMethod.GET)
	public String editEntity(@PathVariable("ident") String ident, Model model) {
    	Visit visita = this.visitService.getVisit(ident);
		if(visita!=null){
			List<Rociador> rociadores = rociadorService.getActiveRociadores();
	    	model.addAttribute("rociadores", rociadores);
	    	List<Localidad> localidades = localidadService.getActiveLocalitiesUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
	    	model.addAttribute("localidades", localidades);
	    	List<MessageResource> sinos = messageResourceService.getCatalogo("CAT_SINO");
	    	model.addAttribute("sinos",sinos);
	    	List<MessageResource> tipos = messageResourceService.getCatalogo("CAT_VIS_TYPE");
	    	model.addAttribute("tipos",tipos);
			model.addAttribute("visita",visita);
			return "irs/enterVisitForm";
		}
		else{
			return "403";
		}
	}
    
    
    /**
     * Custom handler for disabling.
     *
     * @param ident the ID to disable
     * @param redirectAttributes 
     * @return a String
     */
    @RequestMapping("/disableEntity/{ident}/")
    public String disableEntity(@PathVariable("ident") String ident, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		Visit visita = this.visitService.getVisit(ident);
    	if(visita!=null){
    		visita.setPasive('1');
    		this.visitService.saveVisit(visita);
    		redirectAttributes.addFlashAttribute("entidadDeshabilitada", true);
    		redirectAttributes.addFlashAttribute("nombreEntidad", visita.getTarget().getHousehold().getCode());
    		redirecTo = "redirect:/irs/visits/"+visita.getIdent()+"/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Custom handler for enabling.
     *
     * @param ident the ID to enable
     * @param redirectAttributes
     * @return a String
     */
    @RequestMapping("/enableEntity/{ident}/")
    public String enableEntity(@PathVariable("ident") String ident, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	Visit visita = this.visitService.getVisit(ident);
    	if(visita!=null){
    		visita.setPasive('0');
    		this.visitService.saveVisit(visita);
    		redirectAttributes.addFlashAttribute("entidadHabilitada", true);
    		redirectAttributes.addFlashAttribute("nombreEntidad", visita.getTarget().getHousehold().getCode());
    		redirecTo = "redirect:/irs/visits/"+visita.getIdent()+"/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    
    /**
     * Custom handler for saving.
     * 
     * @param ident Identificador unico
     * @param code codigo
     * @param name nombre
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping( value="/saveEntity/", method=RequestMethod.POST)
	public ResponseEntity<String> processEntity( @RequestParam(value="ident", required=true) String ident
			, @RequestParam( value="target", required=true ) String target
	        , @RequestParam( value="visitDate", required=true ) String visitDate
	        , @RequestParam( value="rociador", required=false ) String rociador
	        , @RequestParam( value="supervisor", required=false) String supervisor
	        , @RequestParam( value="brigada", required=false) String brigada
	        , @RequestParam( value="activity", required=true) String activity
	        , @RequestParam( value="compVisit", required=true) String compVisit
	        , @RequestParam( value="reasonNoVisit", required=false) String reasonNoVisit
	        , @RequestParam( value="reasonNoVisitOther", required=false) String reasonNoVisitOther
	        , @RequestParam( value="reasonReluctant", required=false) String reasonReluctant
	        , @RequestParam( value="reasonReluctantOther", required=false) String reasonReluctantOther
	        , @RequestParam( value="sprayedRooms", required=false) Integer sprayedRooms
	        , @RequestParam( value="numCharges", required=false) Integer numCharges
	        , @RequestParam( value="reasonIncomplete", required=false) String reasonIncomplete
	        , @RequestParam( value="supervised", required=false ) String supervised
	        , @RequestParam( value="obs", required=false, defaultValue ="" ) String obs
	        )
	{
    	try{
    		
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    		Date fechaVisita =  null;
    		if (!visitDate.equals("")) fechaVisita = formatter.parse(visitDate);
    		
    		/*if(!latitude.equals("")) latitud = Double.valueOf(latitude);
    		if(!longitude.equals("")) longitud = Double.valueOf(longitude);
    		if (!censusDate.equals("")) fechaCenso = formatter.parse(censusDate);
    		
    		Localidad localidad = this.localidadService.getLocal(local);
			Visit vivienda = this.householdService.getVisita(ident, SecurityContextHolder.getContext().getAuthentication().getName());
			vivienda.setCode(code);
			vivienda.setLocal(localidad);
			vivienda.setOwnerName(ownerName);
			vivienda.setCensusTaker(this.rociadorService.getRociador(censusTaker));
			vivienda.setCensusDate(fechaCenso);
			vivienda.setInhabited(inhabited);
			vivienda.setHabitants(habitants);
			vivienda.setMaterial(material);
			vivienda.setRooms(rooms);
			vivienda.setSprRooms(sprRooms);
			vivienda.setNoSprooms(noSprooms);
			vivienda.setNoSproomsReasons(noSproomsReasons);
			vivienda.setLatitude(latitud);
			vivienda.setLongitude(longitud);
			vivienda.setObs(obs);
			this.householdService.saveVisita(vivienda);*/
			
			return createJsonResponse(null);
    	}
		catch (DataIntegrityViolationException e){
			String message = e.getMostSpecificCause().getMessage();
			Gson gson = new Gson();
		    String json = gson.toJson(message);
		    return createJsonResponse(json);
		}
		catch(Exception e){
			Gson gson = new Gson();
		    String json = gson.toJson(e.toString());
		    return createJsonResponse(json);
		}
    	
	}
    
    private ResponseEntity<String> createJsonResponse( Object o )
   	{
   	    HttpHeaders headers = new HttpHeaders();
   	    headers.set("Content-Type", "application/json");
   	    Gson gson = new Gson();
   	    String json = gson.toJson(o);
   	    return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
   	}
    
	
}
