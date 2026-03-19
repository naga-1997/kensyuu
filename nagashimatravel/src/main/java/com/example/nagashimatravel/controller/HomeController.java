package com.example.nagashimatravel.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.nagashimatravel.entity.House;
import com.example.nagashimatravel.entity.User;
import com.example.nagashimatravel.repository.HouseRepository;
import com.example.nagashimatravel.security.UserDetailsImpl;
import com.example.nagashimatravel.service.RecommendationService;

@Controller
public class HomeController {
	private final HouseRepository houseRepository;
	private final RecommendationService recommendationService;

	public HomeController(HouseRepository houseRepository, RecommendationService recommendationService) {
		this.houseRepository = houseRepository;
		this.recommendationService = recommendationService;
	}

	@GetMapping("/")
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		List<House> newHouses = houseRepository.findTop10ByOrderByCreatedAtDesc();
		model.addAttribute("newHouses", newHouses);

		//ログイン済みならおすすめ5件表示
		if (userDetailsImpl != null) {
			User user = userDetailsImpl.getUser();
			List<House> recommendedHouses = recommendationService.getTop5RecommendedHouses(user);
			model.addAttribute("recommendedHouses", recommendedHouses);
		}

		return "index";
	}
}
