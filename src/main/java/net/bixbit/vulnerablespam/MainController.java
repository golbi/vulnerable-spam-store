/*
 * Copyright (c) 2017 ICM Uniwersytet Warszawski All rights reserved.
 * See LICENCE.txt file for licensing information.
 */
package net.bixbit.vulnerablespam;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController
{
	private static final Logger LOG = LoggerFactory.getLogger(DAO.class);
	private static final String LOGGED_USER = "logged";

	@Autowired
	private DAO dao;
	
	private List<UserComment> comments = new LinkedList<>();
	
	@RequestMapping("/")
	public String index(HttpServletRequest request)
	{
		if (isLogged(request))
			return "redirect:store";
		return "index";
	}

	@RequestMapping("/store")
	public String store(Model model, HttpServletRequest request)
	{
		checkAuthn(request);
		
		fillModel(model, request);
		return "store";
	}

	
	@RequestMapping(path="/buy", method=RequestMethod.GET)
	public String buy(Model model, HttpServletRequest request, 
			@RequestParam(name="amount", defaultValue="1") int amount)
	{
		checkAuthn(request);

		User user = getCurrentUser(request);
		user.incBought(amount);
		dao.buy(user);
		fillModel(model, request);
		return "store";
	}

	@RequestMapping(path="/comment", method=RequestMethod.POST)
	public String comment(Model model, HttpServletRequest request, 
			@RequestParam(name="content") String content)
	{
		checkAuthn(request);
		
		User user = getCurrentUser(request);
		comments.add(new UserComment(user.getUsername(), content));
		fillModel(model, request);
		return "store";
	}	
	
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public String login(@RequestParam(name="user") String user, 
			@RequestParam(name="pass") String password,
			Model model, HttpServletRequest request)
	{
		User authenticated;
		try
		{
			authenticated = dao.authenticate(user, password);
		} catch (Exception e)
		{
			LOG.error("Authn problem", e);
			model.asMap().put("error", "Invalid username/password");
			model.asMap().put("errorDetail", getRootCause(e));
			return "index";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute(LOGGED_USER, authenticated);
		return "redirect:store";
	}

	@RequestMapping(path="/logout")
	public String logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();;
		return "redirect:/";
	}

	
	private void checkAuthn(HttpServletRequest request)
	{
		if (!isLogged(request))
			throw new IllegalStateException("You must be logged to access the store");
	}

	private boolean isLogged(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		return session.getAttribute(LOGGED_USER) != null;
	}
	
	private User getCurrentUser(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		return (User) session.getAttribute(LOGGED_USER);
	}

	private void fillModel(Model model, HttpServletRequest request)
	{
		User user = getCurrentUser(request);
		Map<String, Object> modelMap = model.asMap();
		modelMap.put("comments", comments);
		modelMap.put("user", user);
	}

	@ExceptionHandler(IllegalStateException.class)
	public String handleException(IllegalStateException ex, Model model) {
		model.asMap().put("error", ex.getMessage());
		return "index";
	}
	
	private String getRootCause(Exception e)
	{
		while (e.getCause() != null)
			e = (Exception) e.getCause();
		return e.getMessage();
	}
}