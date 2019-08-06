package com.cafe24.provider;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.service.MainService;

@Controller
public class MainProvider {

	@Autowired
	private MainService mainService;
	
	@RequestMapping(value= {"", "/", "/main", "/index", "/main/{pageNo}", "/index/{pageNo}"}, method=RequestMethod.GET)
	public String main(
			Model model,
			@PathVariable(value="pageNo") Optional<String> pageNo) {
		model.addAttribute("infos", mainService.getInfoForMainPage(pageNo));
		return "/client/main/index";
	}
}
