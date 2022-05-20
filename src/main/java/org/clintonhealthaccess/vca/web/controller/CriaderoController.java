package org.clintonhealthaccess.vca.web.controller;

import org.clintonhealthaccess.vca.domain.Localidad;
import org.clintonhealthaccess.vca.domain.CriaderoTx;
import org.clintonhealthaccess.vca.domain.Criadero;
import org.clintonhealthaccess.vca.domain.audit.AuditTrail;
import org.clintonhealthaccess.vca.language.MessageResource;
import org.clintonhealthaccess.vca.service.LocalidadService;
import org.clintonhealthaccess.vca.service.CriaderoService;
import org.clintonhealthaccess.vca.service.AuditTrailService;
import org.clintonhealthaccess.vca.service.MessageResourceService;
import org.clintonhealthaccess.vca.service.ParametroService;
import org.clintonhealthaccess.vca.service.CriaderoTxService;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Controlador web de peticiones
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/admin/criaderos/*")
public class CriaderoController {
	private static final Logger logger = LoggerFactory.getLogger(CriaderoController.class);
	@Resource(name="criaderoService")
	private CriaderoService criaderoService;
	@Resource(name="localidadService")
	private LocalidadService localidadService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	
	@Resource(name="parametroService")
	private ParametroService parametroService;
	
	@Resource(name="criaderoTxService")
	private CriaderoTxService criaderoTxService;
    
    
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String getEntities(Model model) throws ParseException { 	
    	logger.debug("Mostrando Criaderos en JSP");
    	List<Criadero> criaderos = criaderoService.getCriaderos();
    	if (criaderos == null){
        	logger.debug("Nulo");
        }
        else {
        	for (Criadero criadero:criaderos) {
        		MessageResource mr = null;
        		String descCatalogo = null;
        		mr = this.messageResourceService.getMensaje(criadero.getTipo(),"CAT_TIPOPCR");
        		if(mr!=null) {
        			descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
        			criadero.setTipo(descCatalogo);
        		}
        	}
        }
    	model.addAttribute("criaderos", criaderos);
    	return "criadero/list";
	}
	
	@RequestMapping(value = "/map/", method = RequestMethod.GET)
    public String getEntitiesForMap(Model model) throws ParseException { 	
    	logger.debug("Mostrando Criaderos en JSP para mapear");
    	try {
    		Double latitud = 0D;
			Double longitud = 0D;
	    	Integer zoom = 0;
        	
        	if(parametroService.getParametroByCode("zoom")!=null) zoom = Integer.parseInt(parametroService.getParametroByCode("zoom").getValue());
        	if(parametroService.getParametroByCode("lat")!=null) latitud = Double.parseDouble(parametroService.getParametroByCode("lat").getValue());
        	if(parametroService.getParametroByCode("long")!=null) longitud = Double.parseDouble(parametroService.getParametroByCode("long").getValue());
        	
    		List<Criadero> criaderos = criaderoService.getCriaderos();
    		for(Criadero loc: criaderos) {
    			if (loc.getLatitude()== null || loc.getLatitude()==null) {
    				criaderos.remove(loc);
    			}
    			if (criaderos.size()==0) {
    				break;
    			}
    		}
        	model.addAttribute("criaderos", criaderos);
        	model.addAttribute("latitude",latitud);
        	model.addAttribute("longitude",longitud);
        	model.addAttribute("zoom",zoom);
        	return "criadero/mapa";
    	}
    	catch (Exception e) {
    		model.addAttribute("errormsg",e.getLocalizedMessage());
    		return "505";
    	}
	}
	
	/**
     * Custom handler for adding.
     * @param model Modelo enlazado a la vista
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/newEntity/", method = RequestMethod.GET)
	public String addEntity(Model model) {
    	Float latitudMinima=0F;
    	Float latitudMaxima=0F;
    	Float longitudMinima=0F;
    	Float longitudMaxima=0F;
    	List<Localidad> localidades = localidadService.getActiveLocalitiesUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
    	model.addAttribute("localidades", localidades);
    	List<MessageResource> tipoCriadero = this.messageResourceService.getCatalogo("CAT_TIPOPCR"); 
    	model.addAttribute("tipoCriadero", tipoCriadero);
    	List<MessageResource> tipoEspecies = this.messageResourceService.getCatalogo("CAT_ESPCR"); 
    	model.addAttribute("tipoEspecies", tipoEspecies);
    	if(parametroService.getParametroByCode("latMin")!=null) latitudMinima = Float.parseFloat(parametroService.getParametroByCode("latMin").getValue());
    	if(parametroService.getParametroByCode("latMax")!=null) latitudMaxima = Float.parseFloat(parametroService.getParametroByCode("latMax").getValue());
    	if(parametroService.getParametroByCode("longMin")!=null) longitudMinima = Float.parseFloat(parametroService.getParametroByCode("longMin").getValue());
    	if(parametroService.getParametroByCode("longMax")!=null) longitudMaxima = Float.parseFloat(parametroService.getParametroByCode("longMax").getValue());
    	model.addAttribute("latitudMinima", latitudMinima);
    	model.addAttribute("latitudMaxima", latitudMaxima);
    	model.addAttribute("longitudMinima", longitudMinima);
    	model.addAttribute("longitudMaxima", longitudMaxima);
    	return "criadero/enterForm";
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
    	Criadero criadero = this.criaderoService.getCriadero(ident);
    	Double latitud=0D;
    	Double longitud=0D;
    	Integer zoom=0;
        if(criadero==null){
        	mav = new ModelAndView("403");
        }
        else{
        	try {
	        	mav = new ModelAndView("criadero/viewForm");
	        	MessageResource mr = null;
	    		String descCatalogo = null;
	    		mr = this.messageResourceService.getMensaje(criadero.getTipo(),"CAT_TIPOPCR");
	    		if(mr!=null) {
	    			descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
	    			criadero.setTipo(descCatalogo);
	    		}
	        	mav.addObject("criadero",criadero);
	        	if(parametroService.getParametroByCode("zoom")!=null) zoom = Integer.parseInt(parametroService.getParametroByCode("zoom").getValue());
	        	if(parametroService.getParametroByCode("lat")!=null) latitud = Double.parseDouble(parametroService.getParametroByCode("lat").getValue());
	        	if(parametroService.getParametroByCode("long")!=null) longitud = Double.parseDouble(parametroService.getParametroByCode("long").getValue());
	        	if(criadero.getLatitude()!=null) latitud = criadero.getLatitude();
	        	if(criadero.getLongitude()!=null) longitud = criadero.getLongitude();
	        	if(criadero.getZoom()!=null) zoom = criadero.getZoom();
	        	mav.addObject("latitude",latitud);
	        	mav.addObject("longitude",longitud);
	        	mav.addObject("zoom",zoom);
	        	List<AuditTrail> bitacora = auditTrailService.getBitacora(ident);
	            mav.addObject("bitacora",bitacora);
	            List<CriaderoTx> visitas = criaderoTxService.getCriaderoTxs(ident);
	            for (CriaderoTx visita:visitas) {
	        		mr = this.messageResourceService.getMensaje(visita.getTxType(),"CAT_TIPOTRAT");
	        		if(mr!=null) {
	        			descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
	        			visita.setTxType(descCatalogo);
	        		}
	        	}
	            mav.addObject("visitas",visitas);
        	}
        	catch (Exception e) {
        		mav = new ModelAndView("505");
        		mav.addObject("errormsg","Error: " +  e.getLocalizedMessage());
        	}
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
		Criadero criadero = this.criaderoService.getCriadero(ident);
		if(criadero!=null){
			Float latitudMinima=0F;
	    	Float latitudMaxima=0F;
	    	Float longitudMinima=0F;
	    	Float longitudMaxima=0F;
	    	List<Localidad> localidades = localidadService.getActiveLocalitiesUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
	    	model.addAttribute("localidades", localidades);
	    	List<MessageResource> tipoCriadero = this.messageResourceService.getCatalogo("CAT_TIPOPCR"); 
	    	model.addAttribute("tipoCriadero", tipoCriadero);
			model.addAttribute("criadero",criadero);
			List<MessageResource> tipoEspecies = this.messageResourceService.getCatalogo("CAT_ESPCR"); 
	    	model.addAttribute("tipoEspecies", tipoEspecies);
			if(parametroService.getParametroByCode("latMin")!=null) latitudMinima = Float.parseFloat(parametroService.getParametroByCode("latMin").getValue());
			if(parametroService.getParametroByCode("latMax")!=null) latitudMaxima = Float.parseFloat(parametroService.getParametroByCode("latMax").getValue());
			if(parametroService.getParametroByCode("longMin")!=null) longitudMinima = Float.parseFloat(parametroService.getParametroByCode("longMin").getValue());
			if(parametroService.getParametroByCode("longMax")!=null) longitudMaxima = Float.parseFloat(parametroService.getParametroByCode("longMax").getValue());
	    	model.addAttribute("latitudMinima", latitudMinima);
	    	model.addAttribute("latitudMaxima", latitudMaxima);
	    	model.addAttribute("longitudMinima", longitudMinima);
	    	model.addAttribute("longitudMaxima", longitudMaxima);
			return "criadero/enterForm";
		}
		else{
			return "403";
		}
	}
    
    /**
     * Custom handler for editing.
     * @param model Modelo enlazado a la vista
     * @param ident the ID to edit
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/enterLocation/{ident}/", method = RequestMethod.GET)
	public String enterLocation(@PathVariable("ident") String ident, Model model) {
		Criadero criadero = this.criaderoService.getCriadero(ident);
		if(criadero!=null){
			try {
				model.addAttribute("criadero",criadero);
				Double latitud = 0D;
				Double longitud = 0D;
		    	Integer zoom = 0;
		    	if(parametroService.getParametroByCode("zoom")!=null) zoom = Integer.parseInt(parametroService.getParametroByCode("zoom").getValue());
	        	if(parametroService.getParametroByCode("lat")!=null) latitud = Double.parseDouble(parametroService.getParametroByCode("lat").getValue());
	        	if(parametroService.getParametroByCode("long")!=null) longitud = Double.parseDouble(parametroService.getParametroByCode("long").getValue());
	        	if(criadero.getLatitude()!=null) latitud = criadero.getLatitude();
	        	if(criadero.getLongitude()!=null) longitud = criadero.getLongitude();
	        	if(criadero.getZoom()!=null) zoom = criadero.getZoom();
	        	model.addAttribute("latitude",latitud);
	        	model.addAttribute("longitude",longitud);
	        	model.addAttribute("zoom",zoom);
	        	Float latitudMinima=0F;
		    	Float latitudMaxima=0F;
		    	Float longitudMinima=0F;
		    	Float longitudMaxima=0F;
		    	if(parametroService.getParametroByCode("latMin")!=null) latitudMinima = Float.parseFloat(parametroService.getParametroByCode("latMin").getValue());
		    	if(parametroService.getParametroByCode("latMax")!=null) latitudMaxima = Float.parseFloat(parametroService.getParametroByCode("latMax").getValue());
		    	if(parametroService.getParametroByCode("longMin")!=null) longitudMinima = Float.parseFloat(parametroService.getParametroByCode("longMin").getValue());
		    	if(parametroService.getParametroByCode("longMax")!=null) longitudMaxima = Float.parseFloat(parametroService.getParametroByCode("longMax").getValue());
		    	model.addAttribute("latitudMinima", latitudMinima);
		    	model.addAttribute("latitudMaxima", latitudMaxima);
		    	model.addAttribute("longitudMinima", longitudMinima);
		    	model.addAttribute("longitudMaxima", longitudMaxima);
				return "criadero/enterLocation";
			}
        	catch (Exception e) {
        		model.addAttribute("errormsg","Error: " + e.getLocalizedMessage());
        		return "505";
        	}
		}
		else{
			return "403";
		}
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
	public ResponseEntity<String> processEntity( @RequestParam(value="ident", required=false, defaultValue="" ) String ident
	        , @RequestParam( value="tipo", required=true ) String tipo
	        , @RequestParam( value="local", required=true) String local
	        , @RequestParam( value="latitude", required=false, defaultValue ="" ) String latitude
	        , @RequestParam( value="longitude", required=false, defaultValue ="" ) String longitude
	        , @RequestParam( value="zoom", required=false, defaultValue ="" ) String zoom
	        , @RequestParam( value="size", required=false, defaultValue ="" ) String size
	        , @RequestParam( value="info", required=false, defaultValue ="" ) String info
	        , @RequestParam( value="especie", required=false, defaultValue ="" ) String especie
	        )
	{
    	try{
    		Double latitud = null;
    		Double longitud = null;
    		Integer vista= null;
    		Double tam =null;
    		
    		if(!latitude.equals("")) latitud = Double.valueOf(latitude);
    		if(!longitude.equals("")) longitud = Double.valueOf(longitude);
    		if(!zoom.equals("")) vista = Integer.valueOf(zoom);
    		if(!size.equals("")) tam = Double.valueOf(size);
    		
    		Localidad disLoc = this.localidadService.getLocal(local);
			Criadero criadero = new Criadero();
			//Si el ident viene en blanco es nuevo
			if (ident.equals("")){
				//Crear nuevo
				ident = new UUID(SecurityContextHolder.getContext().getAuthentication().getName().hashCode(),new Date().hashCode()).toString();
				criadero.setIdent(ident);
				criadero.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
				criadero.setRecordDate(new Date());
			}
			//Si el ident no viene en blanco hay que editar
			else{
				//Recupera de la base de datos
				criadero = criaderoService.getCriadero(ident);
			}
			criadero.setLocal(disLoc);
			criadero.setLatitude(latitud);
			criadero.setLongitude(longitud);
			criadero.setZoom(vista);
			criadero.setTipo(tipo);
			criadero.setInfo(info);
			criadero.setSize(tam);
			criadero.setEspecie(especie);
			//Actualiza
			this.criaderoService.saveCriadero(criadero);
			return createJsonResponse(criadero);
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
		Criadero pdx = this.criaderoService.getCriadero(ident);
    	if(pdx!=null){
    		pdx.setPasive('1');
    		this.criaderoService.saveCriadero(pdx);
    		redirectAttributes.addFlashAttribute("entidadDeshabilitada", true);
    		redirectAttributes.addFlashAttribute("nombreEntidad", pdx.getInfo());
    		redirecTo = "redirect:/admin/criaderos/"+pdx.getIdent()+"/";
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
		Criadero pdx = this.criaderoService.getCriadero(ident);
    	if(pdx!=null){
    		pdx.setPasive('0');
    		this.criaderoService.saveCriadero(pdx);
    		redirectAttributes.addFlashAttribute("entidadHabilitada", true);
    		redirectAttributes.addFlashAttribute("nombreEntidad", pdx.getInfo());
    		redirecTo = "redirect:/admin/criaderos/"+pdx.getIdent()+"/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
	
    private ResponseEntity<String> createJsonResponse( Object o )
	{
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-Type", "application/json");
	    Gson gson = new Gson();
	    String json = gson.toJson(o);
	    return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
	}
    
    
	/**
     * Custom handler for adding.
     * @param model Modelo enlazado a la vista
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/visits/newEntity/{ident}/", method = RequestMethod.GET)
	public String addVisitEntity(Model model,@PathVariable("ident") String ident) {
    	Criadero criadero = this.criaderoService.getCriadero(ident);
    	model.addAttribute("criadero", criadero);
    	List<MessageResource> tipoVisita = this.messageResourceService.getCatalogo("CAT_TIPOTRAT"); 
    	model.addAttribute("tipoVisita", tipoVisita);
    	return "criadero/enterVisitForm";
	}
    
    /**
     * Custom handler for saving.
     * 
     * @param ident Identificador unico
     * @param code codigo
     * @param name nombre
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping( value="/visits/saveEntity/", method=RequestMethod.POST)
	public ResponseEntity<String> processVisitEntity( @RequestParam(value="ident", required=false, defaultValue="" ) String ident
			, @RequestParam( value="criadero", required=true ) String criadero
	        , @RequestParam( value="txType", required=true ) String txType
	        , @RequestParam( value="txDate", required=true ) String txDate
	        , @RequestParam( value="obs", required=false, defaultValue ="" ) String obs
	        )
	{
    	try{
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    		Date fechaVisita =  null;
    		if (!txDate.equals("")) fechaVisita = formatter.parse(txDate);
    		
    		Criadero criaderoVis = this.criaderoService.getCriadero(criadero);
    		CriaderoTx criaderoVisit = new CriaderoTx();
			//Si el ident viene en blanco es nuevo
			if (ident.equals("")){
				//Crear nuevo
				ident = new UUID(SecurityContextHolder.getContext().getAuthentication().getName().hashCode(),new Date().hashCode()).toString();
				criaderoVisit.setIdent(ident);
				criaderoVisit.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
				criaderoVisit.setRecordDate(new Date());
			}
			//Si el ident no viene en blanco hay que editar
			else{
				//Recupera de la base de datos
				criaderoVisit = criaderoTxService.getCriaderoTx(ident);
			}
			criaderoVisit.setCriadero(criaderoVis);
			criaderoVisit.setTxDate(fechaVisita);
			criaderoVisit.setTxType(txType);
			criaderoVisit.setObs(obs);
			//Actualiza
			this.criaderoTxService.saveCriaderoTx(criaderoVisit);
			return createJsonResponse(criaderoVisit);
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
    
    
    /**
     * Custom handler for disabling.
     *
     * @param ident the ID to disable
     * @param redirectAttributes 
     * @return a String
     */
    @RequestMapping("/visits/disableEntity/{ident}/")
    public String disableVisitEntity(@PathVariable("ident") String ident, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	CriaderoTx criaderotx = this.criaderoTxService.getCriaderoTx(ident);
    	if(criaderotx!=null){
    		criaderotx.setPasive('1');
    		this.criaderoTxService.saveCriaderoTx(criaderotx);
    		redirectAttributes.addFlashAttribute("entidadDeshabilitada", true);
    		redirectAttributes.addFlashAttribute("nombreEntidad", criaderotx.getCriadero().getIdent());
    		redirecTo = "redirect:/admin/criaderos/"+criaderotx.getCriadero().getIdent()+"/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    /**
     * Custom handler for editing.
     * @param model Modelo enlazado a la vista
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/visits/editEntity/{ident}/", method = RequestMethod.GET)
	public String editVisitEntity(Model model,@PathVariable("ident") String ident) {
    	CriaderoTx criaderotx = this.criaderoTxService.getCriaderoTx(ident);
    	if(criaderotx!=null){
	    	model.addAttribute("criaderotx", criaderotx);
	    	Criadero criadero = this.criaderoService.getCriadero(criaderotx.getCriadero().getIdent());
	    	model.addAttribute("criadero", criadero);
	    	List<MessageResource> tipoVisita = this.messageResourceService.getCatalogo("CAT_TIPOTRAT"); 
	    	model.addAttribute("tipoVisita", tipoVisita);
	    	return "criadero/enterVisitForm";
    	}
    	else {
    		return "403";
    	}
	}
    

	
}