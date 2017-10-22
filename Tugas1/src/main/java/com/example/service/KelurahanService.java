package com.example.service;

import java.util.List;

import com.example.model.Kelurahan;

public interface KelurahanService {
	
	Kelurahan selectKelurahanById(int id);
	List<Kelurahan> selectAllKelurahan();
	List<Kelurahan> selectKelurahanByIdKecamatan(String id_kecamatan);
}
