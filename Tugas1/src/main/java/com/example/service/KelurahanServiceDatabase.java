package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KelurahanMapper;
import com.example.model.Kelurahan;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KelurahanServiceDatabase implements KelurahanService {

	@Autowired
	KelurahanMapper kelurahanMapper;
	
	@Override
	public Kelurahan selectKelurahanById(int id){
		log.info("select kelurahan by id {}", id);
		return kelurahanMapper.selectKelurahanById(id);
	}

	@Override
	public List<Kelurahan> selectAllKelurahan() {
		log.info("select all kelurahan");
		return kelurahanMapper.selectAllKecamatan();
	}

	@Override
	public List<Kelurahan> selectKelurahanByIdKecamatan(String id_kecamatan) {
		log.info("select kelurahan by id_kecamatan{}", id_kecamatan);
		return kelurahanMapper.selectKelurahanByIdKecamatan(id_kecamatan);
	} 
}
