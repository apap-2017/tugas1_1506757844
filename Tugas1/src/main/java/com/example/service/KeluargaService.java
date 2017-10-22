package com.example.service;

import com.example.model.Keluarga;

public interface KeluargaService {
	
	Keluarga selectKeluargaByNKK(String nkk);
	Keluarga selectKeluargaById(int id);
	void insertKeluarga(Keluarga keluarga);
	void updateKeluarga(Keluarga keluarga);
	void matikanKeluarga(Keluarga keluarga);
}
