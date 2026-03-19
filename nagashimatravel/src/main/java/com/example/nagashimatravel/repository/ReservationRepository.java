package com.example.nagashimatravel.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagashimatravel.entity.Reservation;
import com.example.nagashimatravel.entity.User;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	public Page<Reservation> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);

	//予約日が重なっているかチェック
	List<Reservation> findByUserAndCheckinDateLessThanAndCheckoutDateGreaterThan(
			User user, // 同じユーザーの予約を検索
			LocalDate checkoutDate, // 既存予約のチェックイン日 < 新規予約のチェックアウト日
			LocalDate checkinDate  // 既存予約のチェックアウト日 > 新規予約のチェックイン日
	);
	
	// 直近予約5件取得用
		List<Reservation> findTop5ByUserOrderByCreatedAtDesc(User user);
}
