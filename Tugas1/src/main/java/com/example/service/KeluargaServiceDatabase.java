package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KeluargaMapper;
import com.example.model.Keluarga;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService {
	
	@Autowired
	KeluargaMapper keluargaMapper;
	
	@Override
	public Keluarga selectKeluargaByNKK(String nkk){
		log.info("select keluarga by NKK {}", nkk);
		return keluargaMapper.selectKelurgaByNKK(nkk);
	}
	
	@Override
	public Keluarga selectKeluargaById(int id){
		log.info("select keluarga by Id {}", id);
		return keluargaMapper.selectKelurgaById(id);
	}

	@Override
	public void insertKeluarga(Keluarga keluarga) {
		log.info("insert keluarga with NKK {}", keluarga.getNomor_kk());
		keluargaMapper.addKeluarga(keluarga);
	}

	@Override
	public void updateKeluarga(Keluarga keluarga) {
		log.info("update keluarga with NKK {}", keluarga.getNomor_kk());
		keluargaMapper.updateKeluarga(keluarga);
		
	}

	@Override
	public void matikanKeluarga(Keluarga keluarga) {
		log.info("update is_tidak_berlaku keluarga with NKK {}", keluarga.getNomor_kk());
		keluargaMapper.matikanKeluarga(keluarga);
	}
	
	
}
