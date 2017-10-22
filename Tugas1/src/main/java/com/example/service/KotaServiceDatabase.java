package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KotaMapper;
import com.example.model.Kota;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KotaServiceDatabase implements KotaService {
	
	@Autowired
	KotaMapper kotaMapper;
	
	@Override
	public Kota selectKotaById(int id){
		log.info("select kota by id {}", id);
		return kotaMapper.selectKotaById(id);
	}

	@Override
	public List<Kota> selectAllKota() {
		log.info("select all kota");
		return kotaMapper.selectAllKota();
	}
}
