package com.example.nagashimatravel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagashimatravel.entity.House;

public interface HouseRepository extends JpaRepository<House, Integer> {
	public Page<House> findByNameLike(String keyword, Pageable pageable);

	public Page<House> findByNameLikeOrAddressLikeOrderByCreatedAtDesc(String nameKeyword, String addressKeyword,
			Pageable pageable);

	public Page<House> findByNameLikeOrAddressLikeOrderByPriceAsc(String nameKeyword, String addressKeyword,
			Pageable pageable);

	public Page<House> findByAddressLikeOrderByCreatedAtDesc(String area, Pageable pageable);

	public Page<House> findByAddressLikeOrderByPriceAsc(String area, Pageable pageable);

	public Page<House> findByPriceLessThanEqualOrderByCreatedAtDesc(Integer price, Pageable pageable);

	public Page<House> findByPriceLessThanEqualOrderByPriceAsc(Integer price, Pageable pageable);

	public Page<House> findAllByOrderByCreatedAtDesc(Pageable pageable);

	public Page<House> findAllByOrderByPriceAsc(Pageable pageable);

	public List<House> findTop10ByOrderByCreatedAtDesc();

	public List<House> findByAddressContaining(String prefecture);

	//その都道府県の宿を取得、ただし過去に予約した宿は出さない
	public List<House> findByAddressContainingAndIdNotIn(String prefecture, List<Integer> ids);

	public Page<House> findAll(Pageable pageable);
}
