package com.example.service;

import java.util.List;

import com.example.model.Kota;

public interface KotaService {
	
	Kota selectKotaById(int id);
	List<Kota> selectAllKota();
}
