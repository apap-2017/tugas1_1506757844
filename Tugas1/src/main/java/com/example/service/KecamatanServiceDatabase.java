package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KecamatanMapper;
import com.example.model.Kecamatan;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KecamatanServiceDatabase implements KecamatanService {
	
	@Autowired
	KecamatanMapper kecamatanMapper;
	
	@Override
	public Kecamatan selectKecamatanById(int id){
		log.info("select kecamatan by id {}", id);
		return kecamatanMapper.selectKecamatanById(id);
	}

	@Override
	public List<Kecamatan> selectAllKecamatan() {
		log.info("select all kecamatan");
		return kecamatanMapper.selectAllKecamatan();
	}

	@Override
	public List<Kecamatan> selectKecamatanByIdKota(String id_kota) {
		log.info("select kecamatan by id_kota {}", id_kota);
		return kecamatanMapper.selectKecamatanByIdKota(id_kota);
	}
}
