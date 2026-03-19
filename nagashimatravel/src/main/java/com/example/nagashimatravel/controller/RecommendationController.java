package com.example.nagashimatravel.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagashimatravel.entity.House;
import com.example.nagashimatravel.entity.User;
import com.example.nagashimatravel.security.UserDetailsImpl;
import com.example.nagashimatravel.service.RecommendationService;

@Controller
@RequestMapping("/recommendations")
public class RecommendationController {
	private final RecommendationService recommendationService;

	public RecommendationController(RecommendationService recommendationService) {
		this.recommendationService = recommendationService;
	}

	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "order", defaultValue = "createdAtDesc") String order,
			Model model) {
		User user = userDetailsImpl.getUser();
		Pageable pageable;

		//並び替えが『安い順』の時
		if ("priceAsc".equals(order)) {
			//ページを1ページ10件、安い順で表示
			pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "price"));
		} else {
			//それ以外は新着順で表示する
			pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
		}
		//recommendationPageにおすすめ宿一覧を代入
		Page<House> recommendationPage = recommendationService.getRecommendedHouses(user, pageable);

		model.addAttribute("recommendationPage", recommendationPage);
		model.addAttribute("order", order);

		return "recommendations/index";
	}
}