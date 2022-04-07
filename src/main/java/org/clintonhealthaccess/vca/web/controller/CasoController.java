package org.clintonhealthaccess.vca.web.controller;

import org.clintonhealthaccess.vca.domain.Localidad;
import org.clintonhealthaccess.vca.domain.Caso;
import org.clintonhealthaccess.vca.domain.audit.AuditTrail;
import org.clintonhealthaccess.vca.language.MessageResource;
import org.clintonhealthaccess.vca.service.LocalidadService;
import org.clintonhealthaccess.vca.service.MessageResourceService;
import org.clintonhealthaccess.vca.service.CasoService;
import org.clintonhealthaccess.vca.service.AuditTrailService;
import org.clintonhealthaccess.vca.service.ParametroService;
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
@RequestMapping("/admin/casos/*")
public class CasoController {
	private static final Logger logger = LoggerFactory.getLogger(CasoController.class);
	@Resource(name="casoService")
	private CasoService casoService;
	@Resource(name="localidadService")
	private LocalidadService localidadService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	
	@Resource(name="parametroService")
	private ParametroService parametroService;
    
    
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String getEntities(Model model) throws ParseException { 	
    	logger.debug("Mostrando Casos en JSP");
    	List<Caso> casos = casoService.getCasos();
    	if (casos == null){
        	logger.debug("Nulo");
        }
    	else {
        	for (Caso caso:casos) {
        		MessageResource mr = null;
        		String descCatalogo = null;
        		mr = this.messageResourceService.getMensaje(caso.getEstadocaso(),"CAT_ESTADOCASO");
        		if(mr!=null) {
        			descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
        			caso.setEstadocaso(descCatalogo);
        		}
        		mr = this.messageResourceService.getMensaje(caso.getSxResult(),"CAT_RES");
        		if(mr!=null) {
        			descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
        			caso.setSxResult(descCatalogo);
        		}
        	}
        }
    	model.addAttribute("casos", casos);
    	return "caso/list";
	}
	
	@RequestMapping(value = "/map/", method = RequestMethod.GET)
    public String getEntitiesForMap(Model model) throws ParseException { 	
    	logger.debug("Mostrando Casos en JSP para mapear");
    	try {
    		Double latitud = 0D;
			Double longitud = 0D;
	    	Integer zoom = 0;
        	
        	if(parametroService.getParametroByCode("zoom")!=null) zoom = Integer.parseInt(parametroService.getParametroByCode("zoom").getValue());
        	if(parametroService.getParametroByCode("lat")!=null) latitud = Double.parseDouble(parametroService.getParametroByCode("lat").getValue());
        	if(parametroService.getParametroByCode("long")!=null) longitud = Double.parseDouble(parametroService.getParametroByCode("long").getValue());
        	
    		List<Caso> casos = casoService.getCasos();
    		for(Caso loc: casos) {
    			if (loc.getLatitude()== null || loc.getLatitude()==null) {
    				casos.remove(loc);
    			}
    			if (casos.size()==0) {
    				break;
    			}
    		}
        	model.addAttribute("casos", casos);
        	model.addAttribute("latitude",latitud);
        	model.addAttribute("longitude",longitud);
        	model.addAttribute("zoom",zoom);
        	return "caso/mapa";
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
    	if(parametroService.getParametroByCode("latMin")!=null) latitudMinima = Float.parseFloat(parametroService.getParametroByCode("latMin").getValue());
    	if(parametroService.getParametroByCode("latMax")!=null) latitudMaxima = Float.parseFloat(parametroService.getParametroByCode("latMax").getValue());
    	if(parametroService.getParametroByCode("longMin")!=null) longitudMinima = Float.parseFloat(parametroService.getParametroByCode("longMin").getValue());
    	if(parametroService.getParametroByCode("longMax")!=null) longitudMaxima = Float.parseFloat(parametroService.getParametroByCode("longMax").getValue());
    	model.addAttribute("latitudMinima", latitudMinima);
    	model.addAttribute("latitudMaxima", latitudMaxima);
    	model.addAttribute("longitudMinima", longitudMinima);
    	model.addAttribute("longitudMaxima", longitudMaxima);
    	return "caso/enterForm";
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
    	Caso caso = this.casoService.getCaso(ident);
    	Double latitud=0D;
    	Double longitud=0D;
    	Integer zoom=0;
        if(caso==null){
        	mav = new ModelAndView("403");
        }
        else{
        	try {
	        	mav = new ModelAndView("caso/viewForm");
	        	MessageResource mr = null;
        		String descCatalogo = null;
        		mr = this.messageResourceService.getMensaje(caso.getEstadocaso(),"CAT_ESTADOCASO");
        		if(mr!=null) {
        			descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
        			caso.setEstadocaso(descCatalogo);
        		}
	        	mav.addObject("caso",caso);
	        	if(parametroService.getParametroByCode("zoom")!=null) zoom = Integer.parseInt(parametroService.getParametroByCode("zoom").getValue());
	        	if(parametroService.getParametroByCode("lat")!=null) latitud = Double.parseDouble(parametroService.getParametroByCode("lat").getValue());
	        	if(parametroService.getParametroByCode("long")!=null) longitud = Double.parseDouble(parametroService.getParametroByCode("long").getValue());
	        	if(caso.getLatitude()!=null) latitud = caso.getLatitude();
	        	if(caso.getLongitude()!=null) longitud = caso.getLongitude();
	        	if(caso.getZoom()!=null) zoom = caso.getZoom();
	        	mav.addObject("latitude",latitud);
	        	mav.addObject("longitude",longitud);
	        	mav.addObject("zoom",zoom);
	        	List<AuditTrail> bitacora = auditTrailService.getBitacora(ident);
	            mav.addObject("bitacora",bitacora);
	            List<MessageResource> dias = this.messageResourceService.getCatalogo("CAT_DIASSX"); 
	            mav.addObject("dias",dias);
	            List<MessageResource> resultados = this.messageResourceService.getCatalogo("CAT_RES"); 
	            mav.addObject("resultados",resultados);
	            
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
		Caso caso = this.casoService.getCaso(ident);
		if(caso!=null){
			Float latitudMinima=0F;
	    	Float latitudMaxima=0F;
	    	Float longitudMinima=0F;
	    	Float longitudMaxima=0F;
	    	List<Localidad> localidades = localidadService.getActiveLocalitiesUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
	    	model.addAttribute("localidades", localidades);
			model.addAttribute("caso",caso);
			if(parametroService.getParametroByCode("latMin")!=null) latitudMinima = Float.parseFloat(parametroService.getParametroByCode("latMin").getValue());
			if(parametroService.getParametroByCode("latMax")!=null) latitudMaxima = Float.parseFloat(parametroService.getParametroByCode("latMax").getValue());
			if(parametroService.getParametroByCode("longMin")!=null) longitudMinima = Float.parseFloat(parametroService.getParametroByCode("longMin").getValue());
			if(parametroService.getParametroByCode("longMax")!=null) longitudMaxima = Float.parseFloat(parametroService.getParametroByCode("longMax").getValue());
	    	model.addAttribute("latitudMinima", latitudMinima);
	    	model.addAttribute("latitudMaxima", latitudMaxima);
	    	model.addAttribute("longitudMinima", longitudMinima);
	    	model.addAttribute("longitudMaxima", longitudMaxima);
			return "caso/enterForm";
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
		Caso caso = this.casoService.getCaso(ident);
		if(caso!=null){
			try {
				model.addAttribute("caso",caso);
				Double latitud = 0D;
				Double longitud = 0D;
		    	Integer zoom = 0;
		    	if(parametroService.getParametroByCode("zoom")!=null) zoom = Integer.parseInt(parametroService.getParametroByCode("zoom").getValue());
	        	if(parametroService.getParametroByCode("lat")!=null) latitud = Double.parseDouble(parametroService.getParametroByCode("lat").getValue());
	        	if(parametroService.getParametroByCode("long")!=null) longitud = Double.parseDouble(parametroService.getParametroByCode("long").getValue());
	        	if(caso.getLatitude()!=null) latitud = caso.getLatitude();
	        	if(caso.getLongitude()!=null) longitud = caso.getLongitude();
	        	if(caso.getZoom()!=null) zoom = caso.getZoom();
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
				return "caso/enterLocation";
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
	        , @RequestParam( value="codigo", required=true ) String codigo
	        , @RequestParam( value="local", required=true) String local
	        , @RequestParam( value="latitude", required=false, defaultValue ="" ) String latitude
	        , @RequestParam( value="longitude", required=false, defaultValue ="" ) String longitude
	        , @RequestParam( value="zoom", required=false, defaultValue ="" ) String zoom
	        , @RequestParam( value="status", required=true, defaultValue ="" ) String status
	        , @RequestParam( value="info", required=false, defaultValue ="" ) String info
	        , @RequestParam( value="fisDate", required=true ) String fisDate
	        , @RequestParam( value="mxDate", required=true ) String mxDate
	        )
	{
    	try{
    		Double latitud = null;
    		Double longitud = null;
    		Integer vista= null;
    		
    		if(!latitude.equals("")) latitud = Double.valueOf(latitude);
    		if(!longitude.equals("")) longitud = Double.valueOf(longitude);
    		if(!zoom.equals("")) vista = Integer.valueOf(zoom);
    		
    		
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    		Date fechaMuestra =  null;
    		if (!mxDate.equals("")) fechaMuestra = formatter.parse(mxDate);
    		
    		Date fechaSintomas =  null;
    		if (!fisDate.equals("")) fechaSintomas = formatter.parse(fisDate);
    		
    		
    		Localidad disLoc = this.localidadService.getLocal(local);
			Caso caso = new Caso();
			//Si el ident viene en blanco es nuevo
			if (ident.equals("")){
				//Crear nuevo
				ident = new UUID(SecurityContextHolder.getContext().getAuthentication().getName().hashCode(),new Date().hashCode()).toString();
				caso.setIdent(ident);
				caso.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
				caso.setRecordDate(new Date());
			}
			//Si el ident no viene en blanco hay que editar
			else{
				//Recupera de la base de datos
				caso = casoService.getCaso(ident);
			}
			caso.setCodigo(codigo);
			caso.setLocal(disLoc);
			caso.setLatitude(latitud);
			caso.setLongitude(longitud);
			caso.setZoom(vista);
			caso.setInfo(info);
			caso.setMxDate(fechaMuestra);
			caso.setFisDate(fechaSintomas);
			//Actualiza
			this.casoService.saveCaso(caso);
			return createJsonResponse(caso);
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
		Caso caso = this.casoService.getCaso(ident);
    	if(caso!=null){
    		caso.setPasive('1');
    		this.casoService.saveCaso(caso);
    		redirectAttributes.addFlashAttribute("entidadDeshabilitada", true);
    		redirectAttributes.addFlashAttribute("nombreEntidad", caso.getCodigo());
    		redirecTo = "redirect:/admin/casos/"+caso.getIdent()+"/";
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
		Caso caso = this.casoService.getCaso(ident);
    	if(caso!=null){
    		caso.setPasive('0');
    		this.casoService.saveCaso(caso);
    		redirectAttributes.addFlashAttribute("entidadHabilitada", true);
    		redirectAttributes.addFlashAttribute("nombreEntidad", caso.getCodigo());
    		redirecTo = "redirect:/admin/casos/"+caso.getIdent()+"/";
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
     * Custom handler for disabling.
     *
     * @param ident the ID to disable
     * @param redirectAttributes 
     * @return a String
     */
    @RequestMapping("/invno/{ident}/")
    public String invNo(@PathVariable("ident") String ident, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		Caso caso = this.casoService.getCaso(ident);
    	if(caso!=null){
    		caso.setInv("0");
    		caso.setInvDate(null);
    		this.casoService.saveCaso(caso);
    		redirectAttributes.addFlashAttribute("completo", true);
    		redirecTo = "redirect:/admin/casos/"+caso.getIdent()+"/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Custom handler for disabling.
     *
     * @param ident the ID to disable
     * @param redirectAttributes 
     * @return a String
     */
    @RequestMapping("/txno/{ident}/")
    public String txNo(@PathVariable("ident") String ident, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		Caso caso = this.casoService.getCaso(ident);
    	if(caso!=null){
    		caso.setTx("0");
    		caso.setTxDate(null);
    		this.casoService.saveCaso(caso);
    		redirectAttributes.addFlashAttribute("completo", true);
    		redirecTo = "redirect:/admin/casos/"+caso.getIdent()+"/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    /**
     * Custom handler for disabling.
     *
     * @param ident the ID to disable
     * @param redirectAttributes 
     * @return a String
     */
    @RequestMapping("/txCompno/{ident}/")
    public String txCompNo(@PathVariable("ident") String ident, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		Caso caso = this.casoService.getCaso(ident);
    	if(caso!=null){
    		caso.setTxComp("0");
    		caso.setTxCompDate(null);
    		this.casoService.saveCaso(caso);
    		redirectAttributes.addFlashAttribute("completo", true);
    		redirecTo = "redirect:/admin/casos/"+caso.getIdent()+"/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Custom handler for disabling.
     *
     * @param ident the ID to disable
     * @param redirectAttributes 
     * @return a String
     */
    @RequestMapping("/sxno/{ident}/")
    public String sxNo(@PathVariable("ident") String ident, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		Caso caso = this.casoService.getCaso(ident);
    	if(caso!=null){
    		caso.setSx("0");
    		caso.setSxDate(null);
    		caso.setSxResult(null);
    		this.casoService.saveCaso(caso);
    		redirectAttributes.addFlashAttribute("completo", true);
    		redirecTo = "redirect:/admin/casos/"+caso.getIdent()+"/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Custom handler for disabling.
     *
     * @param ident the ID to disable
     * @param redirectAttributes 
     * @return a String
     */
    @RequestMapping("/sxCompno/{ident}/")
    public String sxCompNo(@PathVariable("ident") String ident, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		Caso caso = this.casoService.getCaso(ident);
    	if(caso!=null){
    		caso.setSxComp("0");
    		caso.setSxCompDate(null);
    		caso.setSxCompResult(null);
    		this.casoService.saveCaso(caso);
    		redirectAttributes.addFlashAttribute("completo", true);
    		redirecTo = "redirect:/admin/casos/"+caso.getIdent()+"/";
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
    @RequestMapping( value="/dateValue/saveEntity/", method=RequestMethod.POST)
	public ResponseEntity<String> processInvDateEntity( @RequestParam(value="ident", required=false, defaultValue="" ) String ident
			, @RequestParam( value="dataElement", required=true ) String dataElement
	        , @RequestParam( value="dateValue", required=false, defaultValue="" ) String dateValue
	        , @RequestParam( value="dia1", required=false, defaultValue="" ) String dia1
	        , @RequestParam( value="dia2", required=false, defaultValue="" ) String dia2
	        , @RequestParam( value="dia3", required=false, defaultValue="" ) String dia3
	        , @RequestParam( value="dia4", required=false, defaultValue="" ) String dia4
	        , @RequestParam( value="dia5", required=false, defaultValue="" ) String dia5
	        , @RequestParam( value="dia6", required=false, defaultValue="" ) String dia6
	        , @RequestParam( value="dia7", required=false, defaultValue="" ) String dia7
	        , @RequestParam( value="dia8", required=false, defaultValue="" ) String dia8
	        , @RequestParam( value="dia9", required=false, defaultValue="" ) String dia9
	        , @RequestParam( value="dia10", required=false, defaultValue="" ) String dia10
	        , @RequestParam( value="dia11", required=false, defaultValue="" ) String dia11
	        , @RequestParam( value="dia12", required=false, defaultValue="" ) String dia12
	        , @RequestParam( value="dia13", required=false, defaultValue="" ) String dia13
	        , @RequestParam( value="dia14", required=false, defaultValue="" ) String dia14
	        , @RequestParam( value="resultado", required=false, defaultValue="" ) String resultado
	        )
	{
    	try{
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    		Date valorFecha =  null;
    		if (!dateValue.equals("")) valorFecha = formatter.parse(dateValue);
    		Caso caso = this.casoService.getCaso(ident);
        	if(caso!=null){
        		if(dataElement.equals("inv")) {
        			if(valorFecha.after(caso.getMxDate())) {
        				caso.setInv("1");
        				caso.setInvDate(valorFecha);
        			}
        			else {
        				return createJsonResponse("Fecha incorrecta");
        			}
        		}
        		else if(dataElement.equals("tx")) {
        			if(valorFecha.after(caso.getMxDate())) {
        				caso.setTx("1");
        				caso.setTxDate(valorFecha);
        			}
        			else {
        				return createJsonResponse("Fecha incorrecta");
        			}
        		}
        		else if(dataElement.equals("txComp")) {
        			if(valorFecha.after(caso.getMxDate()) && valorFecha.after(caso.getTxDate())) {
        				caso.setTxComp("1");
        				caso.setTxCompDate(valorFecha);
        			}
        			else {
        				return createJsonResponse("Fecha incorrecta");
        			}
        		}
        		else if(dataElement.equals("sx")) {
        			if(valorFecha.after(caso.getMxDate()) && valorFecha.after(caso.getTxDate())) {
        				caso.setSx("1");
        				caso.setSxDate(valorFecha);
        				caso.setSxResult(resultado);
        			}
        			else {
        				return createJsonResponse("Fecha incorrecta");
        			}
        		}
        		else if(dataElement.equals("sxComp")) {
        			if(valorFecha.after(caso.getMxDate()) && valorFecha.after(caso.getTxDate())) {
        				caso.setSxComp("1");
        				caso.setSxCompDate(valorFecha);
        				caso.setSxCompResult(resultado);
        			}
        			else {
        				return createJsonResponse("Fecha incorrecta");
        			}
        		}
        		else if(dataElement.equals("txSup")) {
        			caso.setTxSup(dia1 + dia2 + dia3 + dia4 + dia5 + dia6 + dia7 + dia8 + dia9 + dia10 + dia11 + dia12 + dia13 + dia14);
        		}
        		this.casoService.saveCaso(caso);
        	}
			return createJsonResponse(caso);
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
    
    

	
}
