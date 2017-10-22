package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kelurahan {
	public int id;
	public int id_kecamatan;
	public String kode_kelurahan;
	public String nama_kelurahan;
	public String kode_pos;
	
}
