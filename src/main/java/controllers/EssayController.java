package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.EssayService;
import domain.Essay;

@Controller
@RequestMapping("/essay")
public class EssayController extends AbstractController {

	// Services --------------------------------------------------------------

	@Autowired
	private EssayService essayService;

	// Constructors ----------------------------------------------------------

	public EssayController() {
		super();
	}

	// Listing ---------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int contestId) {
		ModelAndView result;
		Collection<Essay> essays;

		essays = essayService.findAllEssaysByContest(contestId);

		result = new ModelAndView("essay/list");
		result.addObject("essays", essays);
		result.addObject("requestURI", "essay/list.do");

		return result;
	}

	// Creation --------------------------------------------

	// Edition -------------------------------------------

	// Ancillary methods -----------------------------------

}
