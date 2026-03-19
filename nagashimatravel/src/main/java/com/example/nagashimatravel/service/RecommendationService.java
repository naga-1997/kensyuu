package com.example.nagashimatravel.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.nagashimatravel.entity.House;
import com.example.nagashimatravel.entity.Reservation;
import com.example.nagashimatravel.entity.User;
import com.example.nagashimatravel.repository.HouseRepository;
import com.example.nagashimatravel.repository.ReservationRepository;

@Service
public class RecommendationService {
	private final ReservationRepository reservationRepository;
	private final HouseRepository houseRepository;

	public RecommendationService(ReservationRepository reservationRepository, HouseRepository houseRepository) {
		this.reservationRepository = reservationRepository;
		this.houseRepository = houseRepository;
	}

	public Page<House> getRecommendedHouses(User user, Pageable pageable) {
		//直近５県の予約を取得
		List<Reservation> latestReservations = reservationRepository.findTop5ByUserOrderByCreatedAtDesc(user);

		//予約がなければ全件返す
		if (latestReservations.isEmpty()) {
			return houseRepository.findAll(pageable);
		}
		//都道府県ごとの件数をカウントするprefectureCountMapを宣言
		Map<String, Integer> prefectureCountMap = new HashMap<>();
		//過去予約した宿IDを保存（除外用）するreservedHouseIdを宣言
		List<Integer> reservedHouseIds = new ArrayList<>();

		//予約を１件ずつ処理する
		for (Reservation reservation : latestReservations) {
			House house = reservation.getHouse();
			//予約済み宿を保存
			reservedHouseIds.add(house.getId());
			//住所から都道府県を取得
			String prefecture = extractPrefecture(house.getAddress());
			//空でなければカウント
			if (!prefecture.isEmpty()) {
				if (prefectureCountMap.containsKey(prefecture)) {
					//すでにある場合　+1
					prefectureCountMap.put(prefecture, prefectureCountMap.get(prefecture) + 1);
				} else {
					//初めて　1をセット
					prefectureCountMap.put(prefecture, 1);
				}
			}
		}
		//MapをListに変換して並び替え
		List<Map.Entry<String, Integer>> sortedPrefectures = new ArrayList<>(prefectureCountMap.entrySet());
		//件数が多い順にソート
		sortedPrefectures.sort(Map.Entry.<String, Integer> comparingByValue(Comparator.reverseOrder()));
		//重複しないようにSetで宿を集める
		Set<House> recommendedSet = new LinkedHashSet<>();
		//都道府県ごとに宿を取得
		for (Map.Entry<String, Integer> entry : sortedPrefectures) {
			String prefecture = entry.getKey(); //都道府県を取得
			//prefectureの宿を取得、ただしreservedHouseIdsの宿は出さない
			List<House> houses = houseRepository.findByAddressContainingAndIdNotIn(prefecture, reservedHouseIds);
			//setに追加（重複は自動で排除）
			recommendedSet.addAll(houses);
		}
		//SetからListに変換
		List<House> recommendedList = new ArrayList<>(recommendedSet);
		//もし空なら全件表示
		if (recommendedList.isEmpty()) {
			return houseRepository.findAll(pageable);
		}
		//手動ページング(おすすめはDBからの取得じゃないから手動でやる)
		int start = (int) pageable.getOffset(); //現在のページが何番目から始まるか
		int end = Math.min(start + pageable.getPageSize(), recommendedList.size()); //終了位置がリストの全体を超えないようにする
		//範囲外対策
		if (start > recommendedList.size()) { //指定されたページ番号が、実際のデータ件数を超えてしまった時
			return new PageImpl<>(new ArrayList<>(), pageable, recommendedList.size());
		}

		List<House> pagedList = recommendedList.subList(start, end);

		return new PageImpl<>(pagedList, pageable, recommendedList.size());
	}

	//都道府県を取り出す
	private String extractPrefecture(String address) {
		if (address == null) {
			return "";
		}
		String[] prefectures = { "北海道",
				"青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県",
				"茨城県", "栃木県", "群馬県",
				"埼玉県", "千葉県", "東京都", "神奈川県",
				"新潟県", "富山県", "石川県", "福井県",
				"山梨県", "長野県",
				"岐阜県", "静岡県", "愛知県", "三重県",
				"滋賀県", "京都府", "大阪府", "兵庫県", "奈良県", "和歌山県",
				"鳥取県", "島根県", "岡山県", "広島県", "山口県",
				"徳島県", "香川県", "愛媛県", "高知県",
				"福岡県", "佐賀県", "長崎県", "熊本県", "大分県", "宮崎県", "鹿児島県",
				"沖縄県" };
		//住所の文字列の中から、どの都道府県が含まれているかを特定して返す
		for (String prefecture : prefectures) {
			//今取り出した都道府県名が含まれているかチェック
			if (address.contains(prefecture)) {
				return prefecture;
			}
		}
		return "";
	}

	//トップページ用おすすめ５件を取得
	public List<House> getTop5RecommendedHouses(User user) {
		//1ページ目を、最大5件だけ取得する
		Pageable pageable = PageRequest.of(0, 5);

		Page<House> recommendationPage = getRecommendedHouses(user, pageable);

		return recommendationPage.getContent();
	}
}
