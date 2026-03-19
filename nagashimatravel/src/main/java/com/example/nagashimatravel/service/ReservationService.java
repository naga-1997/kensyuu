package com.example.nagashimatravel.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.nagashimatravel.entity.House;
import com.example.nagashimatravel.entity.Reservation;
import com.example.nagashimatravel.entity.User;
import com.example.nagashimatravel.form.ReservationRegisterForm;
import com.example.nagashimatravel.repository.HouseRepository;
import com.example.nagashimatravel.repository.ReservationRepository;
import com.example.nagashimatravel.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservationService {
	private final ReservationRepository reservationRepository;
	private final HouseRepository houseRepository;
	private final UserRepository userRepository;

	public ReservationService(ReservationRepository reservationRepository, HouseRepository houseRepository,
			UserRepository userRepository) {
		this.reservationRepository = reservationRepository;
		this.houseRepository = houseRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public void create(ReservationRegisterForm reservationRegisterForm) {
		Reservation reservation = new Reservation();
		House house = houseRepository.getReferenceById(reservationRegisterForm.getHouseId());
		User user = userRepository.getReferenceById(reservationRegisterForm.getUserId());
		LocalDate checkinDate = LocalDate.parse(reservationRegisterForm.getCheckinDate());
		LocalDate checkoutDate = LocalDate.parse(reservationRegisterForm.getCheckoutDate());

		reservation.setHouse(house);
		reservation.setUser(user);
		reservation.setCheckinDate(checkinDate);
		reservation.setCheckoutDate(checkoutDate);
		reservation.setNumberOfPeople(reservationRegisterForm.getNumberOfPeople());
		reservation.setAmount(reservationRegisterForm.getAmount());

		reservationRepository.save(reservation);
	}

	//宿泊人数が定員以下かチェックする
	public boolean isWithinCapacity(Integer numberOfPeople, Integer capacity) {
		return numberOfPeople <= capacity;
	}

	//宿泊料金を計算する
	public Integer calculateAmount(LocalDate checkinDate, LocalDate checkoutDate, Integer price) {
		long numberOfNights = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
		int amount = price * (int) numberOfNights;
		return amount;
	}

	//予約重複してるかチェック
	public boolean isReservationAvailable(User user, LocalDate checkinDate, LocalDate checkoutDate) {

		// ↓ 重複している予約を取得
		List<Reservation> reservations = reservationRepository
				.findByUserAndCheckinDateLessThanAndCheckoutDateGreaterThan(
						user, //ユーザー取得
						checkoutDate, //既存チェックアウト日取得
						checkinDate //新規チェックイン日取得
				);

		// 1件もなければ空いてる（true）
		// 1件でもあれば重複（false）
		return reservations.isEmpty();
	}
}
