package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.Penduduk;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService {
	
	@Autowired
	private PendudukMapper pendudukMapper;
	
	@Override
	public Penduduk selectPendudukByNIK(String nik){
		log.info("select penduduk with nik {}", nik);
		return pendudukMapper.selectPendudukByNIK(nik);
	}
	
	@Override
	public Penduduk selectPendudukById(int id){
		log.info("select penduduk with id {}", id);
		return pendudukMapper.selectPendudukById(id);
	}

	@Override
	public List<Penduduk> selectPendudukByIdKeluarga(int id_keluarga) {
		// TODO Auto-generated method stub
		log.info("select penduduk with id_keluarga {}", id_keluarga);
		return pendudukMapper.selectPendudukByIdKeluarga(id_keluarga);
	}

	@Override
	public void addPenduduk(Penduduk penduduk) {
		log.info("insert penduduk with nik {}", penduduk.getNik());
		pendudukMapper.addPenduduk(penduduk);
	}

	@Override
	public void updatePenduduk(Penduduk penduduk) {
		log.info("update penduduk with nik {}", penduduk.getNik());
		pendudukMapper.updatePenduduk(penduduk);
	}

	@Override
	public void matikanPenduduk(Penduduk penduduk) {
		log.info("update is_wafat penduduk with nik {}", penduduk.getNik());
		pendudukMapper.matikanPenduduk(penduduk);
	}

	@Override
	public List<Penduduk> selectPendudukByIdKelurahan(String id_kelurahan) {
		log.info("select penduduk with id_kelurahan {}", id_kelurahan);
		return pendudukMapper.selectPendudukByIdKelurahan(id_kelurahan);
	}
}
