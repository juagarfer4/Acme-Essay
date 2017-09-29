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

import services.RepoService;

import controllers.AbstractController;
import domain.Repo;

@Controller
@RequestMapping("/repo/organiser")
public class RepoOrganiserController extends AbstractController {

	// Services --------------------------------------------------------------

	@Autowired
	private RepoService repoService;

	// Constructors --------------------------------------------------------

	public RepoOrganiserController() {
		super();
	}

	// Listing ---------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int publicSessionId) {
		ModelAndView result;
		Collection<Repo> repos;
		Date date;

		repos = repoService.findAllReposByPublicSession(publicSessionId);
		date=new Date(System.currentTimeMillis()-1);

		result = new ModelAndView("repo/organiser/list");
		result.addObject("repos", repos);
		result.addObject("date", date);
		result.addObject("requestURI", "repo/organiser/list.do");

		return result;
	}

	// Creation ------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer publicSessionId) {
		ModelAndView result;
		Repo repo;

		repo = repoService.create(publicSessionId);

		result = new ModelAndView("repo/organiser/edit");
		result.addObject("repo", repo);
		result.addObject("actionURI", "repo/organiser/edit.do");

		return result;
	}

	// Edition -------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int repoId) {
		ModelAndView result;
		Repo repo;

		repo = repoService.findOne(repoId);
		result = createEditModelAndView(repo);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Repo repo, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView(repo);
		} else {
			try {
				repoService.save(repo);
				result = new ModelAndView("redirect:/contest/organiser/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(repo, "repo.commit.error");
				System.out.println(oops.getStackTrace());
			}
		}

		result.addObject("actionURI", "repo/organiser/edit.do");
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Repo repo, BindingResult binding){
		ModelAndView result;
		
		try{
			repoService.delete(repo);
			result = new ModelAndView("redirect:/contest/organiser/list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(repo, "repo.commit.error");
		}
			
		return result;
	}

	// Ancillary methods ----------------------------------------------------

	public ModelAndView createEditModelAndView(Repo repo) {
		ModelAndView result;

		result = createEditModelAndView(repo, null);

		return result;
	}

	public ModelAndView createEditModelAndView(Repo repo, String message) {
		ModelAndView result;

		result = new ModelAndView("repo/organiser/edit");
		result.addObject("repo", repo);
		result.addObject("message", message);

		return result;
	}

}
