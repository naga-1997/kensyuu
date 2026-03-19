package com.example.nagashimatravel.controller;

import java.time.LocalDate;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagashimatravel.entity.House;
import com.example.nagashimatravel.entity.Reservation;
import com.example.nagashimatravel.entity.User;
import com.example.nagashimatravel.form.ReservationInputForm;
import com.example.nagashimatravel.form.ReservationRegisterForm;
import com.example.nagashimatravel.repository.HouseRepository;
import com.example.nagashimatravel.repository.ReservationRepository;
import com.example.nagashimatravel.security.UserDetailsImpl;
import com.example.nagashimatravel.service.ReservationService;
import com.example.nagashimatravel.service.StripeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ReservationController {
	private final ReservationRepository reservationRepository;
	private final HouseRepository houseRepository;
	private final ReservationService reservationService;
	private final MessageSource messageSource;
	private final StripeService stripeService;

	public ReservationController(ReservationRepository reservationRepository, HouseRepository houseRepository,
			ReservationService reservationService, MessageSource messageSource, StripeService stripeService) {
		this.reservationRepository = reservationRepository;
		this.houseRepository = houseRepository;
		this.reservationService = reservationService;
		this.messageSource = messageSource;
		this.stripeService = stripeService;
	}

	@GetMapping("/reservations")
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			Model model) {

		User user = userDetailsImpl.getUser();
		Page<Reservation> reservationPage = reservationRepository.findByUserOrderByCreatedAtDesc(user, pageable);

		model.addAttribute("reservationPage", reservationPage);

		return "reservations/index";
	}

	@GetMapping("/houses/{id}/reservations/input")
	public String input(@PathVariable(name = "id") Integer id,
			@ModelAttribute @Validated ReservationInputForm reservationInputForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model) {
		House house = houseRepository.getReferenceById(id);
		Integer numberOfPeople = reservationInputForm.getNumberOfPeople();
		Integer capacity = house.getCapacity();

		if (numberOfPeople != null) {
			if (!reservationService.isWithinCapacity(numberOfPeople, capacity)) {
				FieldError fieldError = new FieldError(bindingResult.getObjectName(), "numberOfPeople",
						messageSource.getMessage("reservation.capa.over", null, Locale.getDefault()));
				bindingResult.addError(fieldError);
			}
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("house", house);
			model.addAttribute("errorMessage",
					messageSource.getMessage("reservation.error", null, Locale.getDefault()));

			return "houses/show";
		}

		redirectAttributes.addFlashAttribute("reservationInputForm", reservationInputForm);

		return "redirect:/houses/{id}/reservations/confirm";
	}

	@GetMapping("/houses/{id}/reservations/confirm")
	public String confirm(@PathVariable(name = "id") Integer id,
			@ModelAttribute ReservationInputForm reservationInputForm,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			HttpServletRequest httpServletRequest,
			Model model) {

		House house = houseRepository.getReferenceById(id);
		User user = userDetailsImpl.getUser();

		//チェックイン日とチェックアウト日を取得する
		LocalDate checkinDate = reservationInputForm.getCheckinDate();
		LocalDate checkoutDate = reservationInputForm.getCheckoutDate();

		//宿泊料金を計算する
		Integer price = house.getPrice();
		Integer amount = reservationService.calculateAmount(checkinDate, checkoutDate, price);

		ReservationRegisterForm reservationRegisterForm = new ReservationRegisterForm(house.getId(), user.getId(),
				checkinDate.toString(), checkoutDate.toString(), reservationInputForm.getNumberOfPeople(), amount);
		
		// 予約が重複しているかチェック
		if (!reservationService.isReservationAvailable(user, checkinDate, checkoutDate)) {
			model.addAttribute("house", house);
			model.addAttribute("errorMessage",
					messageSource.getMessage("reservation.overlap.error", null, Locale.getDefault()));
			return "houses/show";
		}

		String sessionId = stripeService.createStripeSession(house.getName(), reservationRegisterForm,
				httpServletRequest);

		model.addAttribute("house", house);
		model.addAttribute("reservationRegisterForm", reservationRegisterForm);
		model.addAttribute("sessionId", sessionId);

		return "reservations/confirm";
	}

	@PostMapping("/houses/{id}/reservations/create")
	public String register(
			@ModelAttribute ReservationRegisterForm reservationRegisterForm,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			RedirectAttributes redirectAttributes) {

		// フォームから値を取り出す
		User user = userDetailsImpl.getUser();
		Integer houseId = reservationRegisterForm.getHouseId();
		LocalDate checkinDate = LocalDate.parse(reservationRegisterForm.getCheckinDate());
		LocalDate checkoutDate = LocalDate.parse(reservationRegisterForm.getCheckoutDate());
		//予約が重複しているかチェック
		if (!reservationService.isReservationAvailable(user, checkinDate, checkoutDate)) {
			redirectAttributes.addFlashAttribute("errorMessage",
					messageSource.getMessage("reservation.overlap.error", null, Locale.getDefault()));
			return "redirect:/houses/" + houseId;
		}

		//  問題なければ保存
		reservationService.create(reservationRegisterForm);

		return "redirect:/reservations?reserved";
	}
	
}
