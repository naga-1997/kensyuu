package com.example.nagashimatravel.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagashimatravel.entity.House;
import com.example.nagashimatravel.form.ReservationInputForm;
import com.example.nagashimatravel.repository.HouseRepository;
import com.example.nagashimatravel.service.WeatherService;

@Controller
@RequestMapping("/houses")
public class HouseController {
	private final HouseRepository houseRepository;
	@Autowired
	private WeatherService weatherService;

	public HouseController(HouseRepository houseRepository) {
		this.houseRepository = houseRepository;
	}

	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "area", required = false) String area,
			@RequestParam(name = "price", required = false) Integer price,
			@RequestParam(name = "order", required = false) String order,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			Model model) {
		Page<House> housePage;

		// 1. キーワード検索（民宿名または住所）が指定されている場合
		if (keyword != null && !keyword.isEmpty()) {
			// 並び替え順が「料金が安い順」の場合
			if (order != null && order.equals("priceAsc")) {
				housePage = houseRepository.findByNameLikeOrAddressLikeOrderByPriceAsc("%" + keyword + "%",
						"%" + keyword + "%", pageable);
			}
			// それ以外（デフォルト）は「新着順」
			else {
				housePage = houseRepository.findByNameLikeOrAddressLikeOrderByCreatedAtDesc("%" + keyword + "%",
						"%" + keyword + "%", pageable);
			}
		}
		// 2. エリア（都道府県など）が指定されている場合
		else if (area != null && !area.isEmpty()) {
			// 並び替え順が「料金が安い順」の場合
			if (order != null && order.equals("priceAsc")) {
				housePage = houseRepository.findByAddressLikeOrderByPriceAsc("%" + area + "%", pageable);
			}
			// それ以外は「新着順」
			else {
				housePage = houseRepository.findByAddressLikeOrderByCreatedAtDesc("%" + area + "%", pageable);
			}
		}
		// 3. 予算（1泊あたりの上限料金）が指定されている場合
		else if (price != null) {
			// 並び替え順が「料金が安い順」の場合
			if (order != null && order.equals("priceAsc")) {
				housePage = houseRepository.findByPriceLessThanEqualOrderByPriceAsc(price, pageable);
			}
			// それ以外は「新着順」
			else {
				housePage = houseRepository.findByPriceLessThanEqualOrderByCreatedAtDesc(price, pageable);
			}
		}
		// 4. 検索条件が何もない（全件表示）の場合
		else {
			// 並び替え順が「料金が安い順」の場合
			if (order != null && order.equals("priceAsc")) {
				housePage = houseRepository.findAllByOrderByPriceAsc(pageable);
			}
			// それ以外は「新着順」
			else {
				housePage = houseRepository.findAllByOrderByCreatedAtDesc(pageable);
			}
		}

		model.addAttribute("housePage", housePage);
		model.addAttribute("keyword", keyword);
		model.addAttribute("area", area);
		model.addAttribute("price", price);
		model.addAttribute("order", order);

		return "houses/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model) {
		House house = houseRepository.getReferenceById(id);
		// 住所 or エリアから都市名を決める
		String city = "Tokyo"; // 後で改善
		// 天気取得
		Map<String, Object> weather = weatherService.getWeather(city);

		model.addAttribute("house", house);
		model.addAttribute("reservationInputForm", new ReservationInputForm());
		model.addAttribute("weather", weather);

		return "houses/show";
	}
}
