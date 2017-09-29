package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.OrganiserService;
import domain.Organiser;
import forms.OrganiserForm;

@Controller
@RequestMapping("/organiser")
public class OrganiserController extends AbstractController {

	// Services --------------------------------------------------------------

	@Autowired
	private OrganiserService organiserService;

	// Constructors -----------------------------------------------------------

	public OrganiserController() {
		super();
	}

	// Listing ------------------------------------------------

	// Creation ---------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		OrganiserForm organiserForm;

		organiserForm = new OrganiserForm();

		result = new ModelAndView("organiser/edit");
		result.addObject("organiserForm", organiserForm);
		result.addObject("actionURI", "organiser/edit.do");

		return result;
	}

	// Edition ----------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid OrganiserForm organiserForm, BindingResult binding) {
		ModelAndView result;
		Organiser organiser;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView(organiserForm);
		} else {
			try {
				organiser = organiserService.reconstruct(organiserForm);
				organiserService.save(organiser);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(organiserForm,
						"organiser.commit.error");
				System.out.println(oops.getStackTrace());
			}
		}

		return result;
	}

	// Ancillary methods ---------------------------------------------------------------

	public ModelAndView createEditModelAndView(OrganiserForm organiserForm) {
		ModelAndView result;

		result = createEditModelAndView(organiserForm, null);

		return result;

	}

	public ModelAndView createEditModelAndView(OrganiserForm organiserForm, String message) {
		ModelAndView result;

		result = new ModelAndView("organiser/edit");
		result.addObject("organiserForm", organiserForm);
		result.addObject("message", message);

		return result;
	}

}
