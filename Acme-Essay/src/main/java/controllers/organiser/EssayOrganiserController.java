package controllers.organiser;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.EssayService;

import controllers.AbstractController;
import domain.Essay;

@Controller
@RequestMapping("/essay/organiser")
public class EssayOrganiserController extends AbstractController {

	// Services --------------------------------------------------------------

	@Autowired
	private EssayService essayService;

	// Constructors ----------------------------------------------------------

	public EssayOrganiserController() {
		super();
	}

	// Listing ---------------------------------------------------------------

	// Creation ------------------------------------------------------------

	// Edition ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int essayId) {
		ModelAndView result;
		Essay essay;

		essay = essayService.findOne(essayId);
		result = createEditModelAndView(essay);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Essay essay, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView(essay);
		} else {
			try {
				essayService.publish(essay);
				result = new ModelAndView("redirect:/contest/organiser/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(essay, "essay.commit.error");
				System.out.println(oops.getStackTrace());
			}
		}

		result.addObject("actionURI", "essay/organiser/edit.do");
		return result;
	}

	// Ancillary methods ----------------------------------------------------

	public ModelAndView createEditModelAndView(Essay essay) {
		ModelAndView result;

		result = createEditModelAndView(essay, null);

		return result;
	}

	public ModelAndView createEditModelAndView(Essay essay, String message) {
		ModelAndView result;

		result = new ModelAndView("essay/organiser/edit");
		result.addObject("essay", essay);
		result.addObject("message", message);

		return result;
	}

}
