package controllers;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Contest;

import services.ContestService;

@Controller
@RequestMapping("/contest")
public class ContestController extends AbstractController {

	// Services --------------------------------------------------------------

	@Autowired
	private ContestService contestService;

	// Constructors ----------------------------------------------------------

	public ContestController() {
		super();
	}

	// Listing ---------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Contest> contests;
		Date date;

		contests = contestService.findAll();
		date=new Date(System.currentTimeMillis()-1);

		result = new ModelAndView("contest/list");
		result.addObject("contests", contests);
		result.addObject("date", date);
		result.addObject("requestURI", "contest/list.do");

		return result;
	}

	// Creation --------------------------------------------

	// Edition -------------------------------------------

	// Ancillary methods -----------------------------------

}
