/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import services.ContestService;
import services.EssayService;
import services.OrganiserService;
import services.PublicSessionService;
import domain.Author;
import domain.Contest;
import domain.Organiser;
import domain.PublicSession;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------
	
	public AdministratorController() {
		super();
	}
		
	// Services -----------------------------------------------------
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private ContestService contestService;
	
	@Autowired
	private EssayService essayService;

	@Autowired
	private OrganiserService organiserService;
	
	@Autowired
	private PublicSessionService publicSessionService;
	
	

		
	// Listing ---------------------------------------------------------------
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(){
		ModelAndView result;
		Collection<Contest> contests1;
		Collection<Author> authors1;
		Collection<Author> authors2;
		Collection<Author> authors3;
		Double averageEssays;
		Double averageContests;
		Collection<Contest> contests2;
		Collection<Contest> contests3;
		Collection<PublicSession> publicSessions1;
		Collection<Organiser> organisers;
		Collection<PublicSession> publicSessions2;
//		
//		The list of contests in descending order of number submitted 
//		The list of authors who have submitted more essays.
//		The list of authors who have got more essays published.
//		The list of authors who have got less essays published.
//		The average number of essays submitted by an author.
//		The average number of contests organised by an organiser.
//		The list of contests that were held during the last month. *
			
		contests1 = contestService.findAllContestDescOrderNumberSubmitted();
		authors1 = authorService.findAllAuthorMoreEssaysSubmitted();
		authors2 = authorService.findAllAuthorMoreEssaysPublished();//*
		authors3 = authorService.findAllAuthorLessEssaysPublished();//*
		averageEssays = essayService.averageNumberEssaysByAuthor();
		averageContests = contestService.averageNumberContestsByOrganiser();
		contests2 = contestService.findAllContestsHeldDuringLastMonth();
		contests3 = contestService.findAllContestDescOrderOfEssays();
		publicSessions1= publicSessionService.findAllPublicSessionsDescOrderOfEssays();
		organisers= organiserService.findAllOrganisersChairmen();
		publicSessions2 = publicSessionService.findAllPublicSessionsWithChairman();
			  
		result=new ModelAndView("administrator/dashboard");
		result.addObject("contests1", contests1);
		result.addObject("authors1", authors1);
		result.addObject("authors2", authors2);
		result.addObject("authors3", authors3);
		result.addObject("averageEssays", averageEssays);
		result.addObject("averageContests", averageContests);
		result.addObject("contests2", contests2);
		result.addObject("contests3", contests3);
		result.addObject("publicSessions1", publicSessions1);
		result.addObject("organisers", organisers);
		result.addObject("publicSessions2", publicSessions2);
		result.addObject("requestURI", "administrator/dashboard.do");
		
		return result;
	}
		
	// Creation --------------------------------------------
		
	// Edition -------------------------------------------
			
	// Ancillary methods -----------------------------------
		
}