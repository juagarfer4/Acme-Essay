package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Author;

import forms.AuthorForm;

import services.AuthorService;

@Controller
@RequestMapping("/author")
public class AuthorController extends AbstractController {

	// Services --------------------------------------------------------------

	@Autowired
	private AuthorService authorService;

	// Constructors -----------------------------------------------------------

	public AuthorController() {
		super();
	}

	// Listing ------------------------------------------------

	// Creation ---------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		AuthorForm authorForm;

		authorForm = new AuthorForm();

		result = new ModelAndView("author/edit");
		result.addObject("authorForm", authorForm);
		result.addObject("actionURI", "author/edit.do");

		return result;
	}

	// Edition ----------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid AuthorForm authorForm, BindingResult binding) {
		ModelAndView result;
		Author author;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView(authorForm);
		} else {
			try {
				author = authorService.reconstruct(authorForm);
				authorService.save(author);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(authorForm,
						"author.commit.error");
				System.out.println(oops.getStackTrace());
			}
		}

		return result;
	}

	// Ancillary methods ---------------------------------------------------------------

	public ModelAndView createEditModelAndView(AuthorForm authorForm) {
		ModelAndView result;

		result = createEditModelAndView(authorForm, null);

		return result;

	}

	public ModelAndView createEditModelAndView(AuthorForm authorForm, String message) {
		ModelAndView result;

		result = new ModelAndView("author/edit");
		result.addObject("authorForm", authorForm);
		result.addObject("message", message);

		return result;
	}

}
