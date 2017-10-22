package com.example.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import com.example.service.KeluargaService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Keluarga {
	public int id;
	public String nomor_kk;
	public String alamat;
	public String RT;
	public String RW;
	public int id_kelurahan;
	public int is_tidak_berlaku;
	
	public String generateNKK(String kode_kelurahan, Keluarga keluarga, KeluargaService keluargaDAO){
		String NKK = "";
		String kode_wilayah = kode_kelurahan.substring(0,6);
		String nomor_urut = "0001";
		
		StringTokenizer tanggalTokenizer = new StringTokenizer(new SimpleDateFormat("yy-MM-dd").format(new Date()), "-");
		String tahun = tanggalTokenizer.nextToken();
		String bulan = tanggalTokenizer.nextToken();
		String tanggal = tanggalTokenizer.nextToken();
		
		log.info("tahun masukin ituan {}", tahun);
		NKK = kode_wilayah + tanggal + bulan + tahun + nomor_urut;
		while(true){
			Keluarga checker = keluargaDAO.selectKeluargaByNKK(NKK);
			if(checker != null){
				long tempNKK = Long.parseLong(NKK);
				tempNKK+=1;
				NKK = "" + tempNKK;
			}else{
				break;
			}
		}
		return NKK;
	}
	
}
