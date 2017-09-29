package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;

import controllers.AbstractController;
import domain.Author;

@Controller
@RequestMapping("/author/administrator")
public class AuthorAdministratorController extends AbstractController {

	// Services -------------------------------------------------

	@Autowired
	private AuthorService authorService;

	// Constructors ---------------------------------------------

	public AuthorAdministratorController() {
		super();
	}

	// Listing ---------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Author> authors;
			
		authors = authorService.findAll();

		result = new ModelAndView("author/administrator/list");
		result.addObject("authors", authors);
		result.addObject("requestURI", "author/administrator/list.do");

		return result;
	}
	
	// Creation -----------------------------------------
	
	// Edition --------------------------------------
	
	// Ancillary methods --------------------------------

}
