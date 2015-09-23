package com.springmvc.note.controller;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.note.model.Note;
import com.springmvc.note.utility.PMHelper;

@Controller
@RequestMapping("/note")
public class NoteController {
	
	
	//Return Note list page
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public String getNotes(ModelMap model){
		
		PersistenceManager pm = PMHelper.get().getPersistenceManager();
		Query q = pm.newQuery(Note.class);
		q.setOrdering("date desc");
		List<Note> results = null;

		try {
			results = (List<Note>) q.execute();
			if (results.isEmpty()) {
				model.addAttribute("notes", null);
			}
			else{
				model.addAttribute("notes", results);
			}
				
		} finally {
			q.closeAll();
			pm.close();
		}
		
		return "index";
		
	}
	
	//Return add VIew
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String showAddNote(ModelMap model){
		
		return "add";
	}
	
	//Add a Note
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView AddNote(HttpServletRequest request, ModelMap model){
		
		
		String note = request.getParameter("note");
		String email = request.getParameter("email");
		Note n = new Note();
		n.setNote(note);
		n.setDate(new Date());
		n.setEmail(email);
		
		PersistenceManager pm = PMHelper.get().getPersistenceManager();
		try {
			pm.makePersistent(n);
		} finally {
			pm.close();
		}
		return new ModelAndView("redirect:../note");
	}
	
	//Delete
	
	@RequestMapping(value = "/delete/{key}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable String key,
			HttpServletRequest request, ModelMap model) {

		PersistenceManager pm = PMHelper.get().getPersistenceManager();

		try {

			Note note = pm.getObjectById(Note.class, key);
			pm.deletePersistent(note);

		} finally {
			pm.close();
		}

		// return to list
		return new ModelAndView("redirect:../");

	}
	
	//Edit View
	
	@RequestMapping(value = "/edit/{key}", method = RequestMethod.GET)
	public String getUpdateCustomerPage(@PathVariable String key,HttpServletRequest request, ModelMap model) {

		PersistenceManager pm = PMHelper.get().getPersistenceManager();
		try {
			Note note = pm.getObjectById(Note.class,key);
			
			if (note==null) {
				model.addAttribute("note", null);
			} else {
				model.addAttribute("note", note);
			}
		} finally {
			pm.close();
		}

		return "edit";

	}
	
	//Edit Post
	
	@RequestMapping(value = "/edit/{key}", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, ModelMap model) {

		String note = request.getParameter("note");
		String email = request.getParameter("email");
		String key = request.getParameter("key");

		PersistenceManager pm = PMHelper.get().getPersistenceManager();

		try {

			Note n = pm.getObjectById(Note.class, key);

			n.setNote(note);
			n.setEmail(email);
			

		} finally {

			pm.close();
		}

		// return to list
		return new ModelAndView("redirect:../");

	}
}
