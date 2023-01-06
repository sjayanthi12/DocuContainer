package main.java.core.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import main.java.core.domain.Drugs;
import main.java.core.service.drug.DrugService;

@RestController
public class DrugController {

	private static final Logger log = LoggerFactory.getLogger(DrugController.class);

	private final DrugService drugService;

	@Autowired
	public DrugController(DrugService drugService) {
		this.drugService = drugService;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/drug/retrieve")
	public ModelAndView retrieveDrugs() throws IOException {
		return new ModelAndView("drugs", "drugs", drugService.getAllDrugs());
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/drug/retrieve/new", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Drugs> retrieveSearchedDrugs(@RequestParam(value="name", required=false) String name) throws IOException {
		log.debug("&&&&&&&&&&& " + name);
		return drugService.getSearchedDrugs(name+"%");

	}

}

