package com.jwat.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.jwat.dto.NewDTO;

public interface INewService {
	List<NewDTO> findAll(Pageable pageable);
	int getTotalItem();
	NewDTO findById(long id);
	NewDTO save(NewDTO dto);
	void delete(long[] ids);
	void Count(long id);
	List<NewDTO> findBycategoryid(long categoryid, Pageable pageable);
	List<NewDTO> searchByTitleLike(String title);
	List<NewDTO> report(Pageable pageable);
}
