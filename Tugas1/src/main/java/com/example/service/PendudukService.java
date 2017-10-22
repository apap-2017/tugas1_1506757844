package com.example.service;

import java.util.List;

import com.example.model.Penduduk;

public interface PendudukService {
	
	Penduduk selectPendudukById(int id);
	Penduduk selectPendudukByNIK(String nik);
	List<Penduduk> selectPendudukByIdKeluarga(int id_keluarga);
	void addPenduduk(Penduduk penduduk);
	void updatePenduduk(Penduduk penduduk);
	void matikanPenduduk(Penduduk penduduk);
	List<Penduduk> selectPendudukByIdKelurahan(String id_kelurahan);
}
