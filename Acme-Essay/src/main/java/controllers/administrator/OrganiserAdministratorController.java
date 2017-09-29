package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.OrganiserService;

import controllers.AbstractController;
import domain.Organiser;

@Controller
@RequestMapping("/organiser/administrator")
public class OrganiserAdministratorController extends AbstractController {

	// Services -------------------------------------------------

	@Autowired
	private OrganiserService organiserService;

	// Constructors ---------------------------------------------

	public OrganiserAdministratorController() {
		super();
	}

	// Listing ---------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Organiser> organisers;

		organisers = organiserService.findAll();

		result = new ModelAndView("organiser/administrator/list");
		result.addObject("organisers", organisers);
		result.addObject("requestURI", "organiser/administrator/list.do");

		return result;
	}
	
	// Creation -----------------------------------------
	
	// Edition --------------------------------------
		
	// Ancillary methods --------------------------------

}
