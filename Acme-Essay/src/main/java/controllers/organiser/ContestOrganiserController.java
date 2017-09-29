package controllers.organiser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import services.ContestService;

import controllers.AbstractController;
import domain.Contest;
import domain.Organiser;
import forms.ContestForm;

@Controller
@RequestMapping("/contest/organiser")
public class ContestOrganiserController extends AbstractController {

	// Services --------------------------------------------------------------

	@Autowired
	private ContestService contestService;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

	// Constructors ----------------------------------------------------------

	public ContestOrganiserController() {
		super();
	}
	
	// Listing ---------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Contest> contests;
		Boolean myContests;

		contests = contestService.findAllContestsByOrganiser();
		myContests = true;

		result = new ModelAndView("contest/organiser/list");
		result.addObject("contests", contests);
		result.addObject("myContests", myContests);
		result.addObject("requestURI", "contest/organiser/list.do");

		return result;
	}
	
	@RequestMapping(value="/show", method=RequestMethod.GET)
	public void show(HttpServletResponse response, @RequestParam int contestId) throws IOException, SQLException{
		Contest contest;
		byte[] picture;
		
		contest=contestService.findOne(contestId);
		picture=contest.getPicture();
		if(picture != null){
			response.setContentType("image/jpeg");
			response.setContentLength(picture.length);
			response.getOutputStream().write(picture);
			response.getOutputStream().flush();
		}
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Contest contest;

		contest = contestService.create();

		result = new ModelAndView("contest/organiser/edit");
		result.addObject("contest", contest);
		result.addObject("actionURI", "contest/organiser/edit.do");

		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam int contestId) {
		ModelAndView result;
		ContestForm contestForm;
		Collection<Organiser> organisers;

		contestForm = new ContestForm();
		organisers=contestService.findAllOrganiserNotInThisContest(contestId);
		
		contestForm.setContestId(contestId);

		result = new ModelAndView("contest/organiser/add");
		result.addObject("contestForm", contestForm);
		result.addObject("organisers", organisers);
		result.addObject("actionURI", "contest/organiser/add.do");

		return result;
	}

	// Edition ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int contestId) {
		ModelAndView result;
		Contest contest;
		Collection<Organiser> organisers;

		contest = contestService.findOne(contestId);
		organisers=contestService.findAllOrganiserNotInThisContest(contestId);
		
		result = createEditModelAndView(contest);
		
		result.addObject("organisers", organisers);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Contest contest, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView(contest);
		} else {
			try {
				contestService.save(contest);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(contest, "contest.commit.error");
				System.out.println(oops.getStackTrace());
			}
		}

		result.addObject("actionURI", "contest/organiser/edit.do");
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, params = "save2")
	public ModelAndView save(@Valid ContestForm contestForm, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView2(contestForm);
		} else {
			try {
				contestService.reconstruct(contestForm);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView2(contestForm, "contest.commit.error");
				System.out.println(oops.getStackTrace());
			}
		}

		result.addObject("actionURI", "contest/organiser/add.do");
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Contest contest, BindingResult binding) {
		ModelAndView result;

		try {
			contestService.delete(contest);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(contest, "contest.commit.error");
		}

		return result;
	}

	// Ancillary methods ---------------------------------------------------------------

	public ModelAndView createEditModelAndView(Contest contest) {
		ModelAndView result;

		result = createEditModelAndView(contest, null);

		return result;
	}

	public ModelAndView createEditModelAndView(Contest contest, String message) {
		ModelAndView result;

		result = new ModelAndView("contest/organiser/edit");
		result.addObject("contest", contest);
		result.addObject("message", message);

		return result;
	}
	
	public ModelAndView createEditModelAndView2(ContestForm contestForm) {
		ModelAndView result;

		result = createEditModelAndView2(contestForm, null);

		return result;
	}
	
	public ModelAndView createEditModelAndView2(ContestForm contestForm, String message) {
		ModelAndView result;
		Integer contestId;
		Collection<Organiser> organisers;
		
		contestId=contestForm.getContestId();
		organisers=contestService.findAllOrganiserNotInThisContest(contestId);

		result = new ModelAndView("contest/organiser/add");
		result.addObject("contestForm", contestForm);
		result.addObject("organisers", organisers);
		result.addObject("message", message);

		return result;
	}

}
