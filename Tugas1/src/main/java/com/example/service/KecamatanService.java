package com.example.service;

import java.util.List;

import com.example.model.Kecamatan;

public interface KecamatanService {
	
	Kecamatan selectKecamatanById(int id);
	List<Kecamatan> selectAllKecamatan();
	List<Kecamatan> selectKecamatanByIdKota(String id_kota);
}
