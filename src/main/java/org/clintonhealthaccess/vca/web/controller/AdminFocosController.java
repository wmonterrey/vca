package org.clintonhealthaccess.vca.web.controller;

import org.clintonhealthaccess.vca.domain.Foco;
import org.clintonhealthaccess.vca.domain.Localidad;
import org.clintonhealthaccess.vca.domain.audit.AuditTrail;
import org.clintonhealthaccess.vca.domain.relationships.FocoLocalidad;
import org.clintonhealthaccess.vca.domain.relationships.FocoLocalidadId;
import org.clintonhealthaccess.vca.service.FocoService;
import org.clintonhealthaccess.vca.service.LocalidadService;
import org.clintonhealthaccess.vca.service.AuditTrailService;
import org.clintonhealthaccess.vca.service.MessageResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Controlador web de peticiones
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/admin/foci/*")
public class AdminFocosController {
	private static final Logger logger = LoggerFactory.getLogger(AdminFocosController.class);
	@Resource(name="focoService")
	private FocoService focoService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	@Resource(name="localidadService")
	private LocalidadService localidadService;
    
    
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String getEntities(Model model) throws ParseException { 	
    	logger.debug("Mostrando Focos en JSP");
    	List<Foco> focos = focoService.getFocos();
    	model.addAttribute("focos", focos);
    	return "admin/focos/list";
	}
	
	/**
     * Custom handler for adding.
     * @param model Modelo enlazado a la vista
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/newEntity/", method = RequestMethod.GET)
	public String addEntity(Model model) {
    	List<Localidad> localidades = localidadService.getActiveLocalities();
    	model.addAttribute("localidades", localidades);
    	return "admin/focos/enterForm";
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
    	Foco foco = this.focoService.getFoco(ident);
        if(foco==null){
        	mav = new ModelAndView("403");
        }
        else{
        	mav = new ModelAndView("admin/focos/viewForm");
        	mav.addObject("foco",foco);
            List<AuditTrail> bitacora = auditTrailService.getBitacora(ident);
            mav.addObject("bitacora",bitacora);
            List<Localidad> localidadesSeleccionadas = focoService.getLocalidadesFoco(ident);
            mav.addObject("localidadesSeleccionadas",localidadesSeleccionadas);
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
		Foco foco = this.focoService.getFoco(ident);
		if(foco!=null){
			model.addAttribute("foco",foco);
			List<Localidad> localidades = localidadService.getActiveLocalities();
	    	model.addAttribute("localidades", localidades);
	    	List<Localidad> localidadesSeleccionadas = focoService.getLocalidadesFoco(ident);
	    	model.addAttribute("localidadesSeleccionadas", localidadesSeleccionadas);
			return "admin/focos/enterForm";
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
	        , @RequestParam( value="code", required=true ) String code
	        , @RequestParam( value="name", required=true ) String name
	        , @RequestParam( value="coordinates", required=true ) String coordinates
	        , @RequestParam( value="localidades", required=false, defaultValue="") List<String> localidades
	        )
	{
    	try{
    		WebAuthenticationDetails wad  = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
			Foco foco = new Foco();
			//Si el ident viene en blanco es nuevo
			if (ident.equals("")){
				//Crear nuevo
				ident = new UUID(SecurityContextHolder.getContext().getAuthentication().getName().hashCode(),new Date().hashCode()).toString();
				foco.setIdent(ident);
				foco.setCode(code);
				foco.setName(name);
				foco.setCoordinates(coordinates);
				foco.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
				foco.setRecordDate(new Date());
				//Guardar nuevo
				this.focoService.saveFoco(foco);
			}
			//Si el ident no viene en blanco hay que editar
			else{
				//Recupera de la base de datos
				foco = focoService.getFoco(ident);
				foco.setCode(code);
				foco.setName(name);
				foco.setCoordinates(coordinates);
				//Actualiza
				this.focoService.saveFoco(foco);
			}
			List<Localidad> localidadesSeleccionadas = focoService.getLocalidadesFoco(foco.getIdent());
			for(Localidad l:localidadesSeleccionadas) {
				if(!localidades.contains(l.getIdent())) {
					FocoLocalidad floc = focoService.getFocoLocalidad(foco.getIdent(), l.getIdent());
					floc.setPasive('1');
					this.focoService.saveFocoLocalidad(floc);
				}
			}
			for(String l:localidades){
				FocoLocalidad floc = focoService.getFocoLocalidad(foco.getIdent(), l);
				if(floc==null) {
					floc = new FocoLocalidad();
					floc.setFocoLocalidadId(new FocoLocalidadId(foco.getIdent(), l));
					floc.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
					floc.setRecordDate(new Date());
					floc.setDeviceid(wad.getRemoteAddress());
				}else {
					floc.setPasive('0');
				}
				this.focoService.saveFocoLocalidad(floc);
			}
			return createJsonResponse(foco);
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
		Foco foco = this.focoService.getFoco(ident);
    	if(foco!=null){
    		foco.setPasive('1');
    		this.focoService.saveFoco(foco);
    		redirectAttributes.addFlashAttribute("entidadDeshabilitada", true);
    		redirectAttributes.addFlashAttribute("nombreEntidad", foco.getName());
    		redirecTo = "redirect:/admin/foci/"+foco.getIdent()+"/";
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
		Foco foco = this.focoService.getFoco(ident);
    	if(foco!=null){
    		foco.setPasive('0');
    		this.focoService.saveFoco(foco);
    		redirectAttributes.addFlashAttribute("entidadHabilitada", true);
    		redirectAttributes.addFlashAttribute("nombreEntidad", foco.getName());
    		redirecTo = "redirect:/admin/foci/"+foco.getIdent()+"/";
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
	
}
