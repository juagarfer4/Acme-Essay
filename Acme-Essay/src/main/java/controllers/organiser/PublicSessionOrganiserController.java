package controllers.organiser;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ContestService;
import services.EssayService;
import services.PublicSessionService;

import controllers.AbstractController;
import domain.Contest;
import domain.Essay;
import domain.Organiser;
import domain.PublicSession;
import forms.PublicSessionForm;

@Controller
@RequestMapping("/publicsession/organiser")
public class PublicSessionOrganiserController extends AbstractController {

	// Services ------------------------------------------------------------

	@Autowired
	private PublicSessionService publicSessionService;
	
	@Autowired
	private ContestService contestService;
	
	@Autowired
	private EssayService essayService;

	// Constructors --------------------------------------------------------

	public PublicSessionOrganiserController() {
		super();
	}

	// Listing -------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int contestId) {
		ModelAndView result;
		Collection<PublicSession> publicSessions;
		Date date;

		publicSessions = publicSessionService.findAllPublicSessionsByContest(contestId);
		date=new Date(System.currentTimeMillis()-1);

		result = new ModelAndView("publicsession/organiser/list");
		result.addObject("publicSessions", publicSessions);
		result.addObject("date", date);
		result.addObject("requestURI", "publicsession/organiser/list");

		return result;
	}

	// Creation -----------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer contestId) {
		ModelAndView result;
		PublicSession publicSession;
		Collection<Organiser> organisers;

		publicSession = publicSessionService.create(contestId);
		organisers=contestService.findAllOrganisersByContest(contestId);

		result = new ModelAndView("publicsession/organiser/edit");
		result.addObject("publicSession", publicSession);
		result.addObject("organisers", organisers);
		result.addObject("actionURI", "publicsession/organiser/edit.do");

		return result;
	}

	// Edition ---------------------------------------------------------
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam int publicSessionId) {
		ModelAndView result;
		PublicSessionForm publicSessionForm;
		Collection<Essay> essays;

		publicSessionForm = new PublicSessionForm();
		essays=essayService.findAllEssaysToAddToAPublicSession(publicSessionId);
		
		publicSessionForm.setPublicSessionId(publicSessionId);

		result = new ModelAndView("publicsession/organiser/add");
		result.addObject("publicSessionForm", publicSessionForm);
		result.addObject("essays", essays);
		result.addObject("actionURI", "publicsession/organiser/add.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int publicSessionId) {
		ModelAndView result;
		PublicSession publicSession;

		publicSession = publicSessionService.findOne(publicSessionId);
		result = createEditModelAndView(publicSession);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid PublicSession publicSession, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView(publicSession);
		} else {
			try {
				publicSessionService.save(publicSession);
				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = createEditModelAndView(publicSession, "publicSession.commit.error");
				System.out.println(oops.getStackTrace());
			}
		}

		result.addObject("actionURI", "publicsession/organiser/edit.do");
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, params = "save2")
	public ModelAndView save(@Valid PublicSessionForm publicSessionForm, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView2(publicSessionForm);
		} else {
			try {
				publicSessionService.reconstruct(publicSessionForm);
				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = createEditModelAndView2(publicSessionForm, "publicSession.commit.error");
				System.out.println(oops.getStackTrace());
			}
		}

		result.addObject("actionURI", "publicsession/organiser/add.do");
		return result;
	}

	// Ancillary methods ----------------------------------

	public ModelAndView createEditModelAndView(PublicSession publicSession) {
		ModelAndView result;

		result = createEditModelAndView(publicSession, null);

		return result;
	}

	public ModelAndView createEditModelAndView(PublicSession publicSession, String message) {
		ModelAndView result;
		Contest contest;
		Integer contestId;
		Collection<Organiser> organisers;
		
		contest=publicSession.getContest();
		contestId=contest.getId();
		organisers=contestService.findAllOrganisersByContest(contestId);

		result = new ModelAndView("publicsession/organiser/edit");
		result.addObject("publicSession", publicSession);
		result.addObject("organisers", organisers);
		result.addObject("message", message);

		return result;
	}
	
	public ModelAndView createEditModelAndView2(PublicSessionForm publicSessionForm) {
		ModelAndView result;

		result = createEditModelAndView2(publicSessionForm, null);

		return result;
	}
	
	public ModelAndView createEditModelAndView2(PublicSessionForm publicSessionForm, String message) {
		ModelAndView result;
		Integer publicSessionId;
		Collection<Essay> essays;
		
		publicSessionId=publicSessionForm.getPublicSessionId();
		essays=essayService.findAllEssaysToAddToAPublicSession(publicSessionId);

		result = new ModelAndView("publicsession/organiser/add");
		result.addObject("publicSessionForm", publicSessionForm);
		result.addObject("essays", essays);
		result.addObject("message", message);

		return result;
	}

}
