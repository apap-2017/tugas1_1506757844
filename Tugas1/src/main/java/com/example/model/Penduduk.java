package com.example.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.service.KeluargaService;
import com.example.service.PendudukService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Penduduk implements Comparable<Penduduk>{
	
	private int id;
	private String nik;
	private String nama;
	private String tempat_lahir;
	private String tanggal_lahir;
	private int jenis_kelamin;
	private int is_wni;
	private int id_keluarga;
	private String agama;
	private String pekerjaan;
	private String status_perkawinan;
	private String status_dalam_keluarga;
	private String golongan_darah;
	private int is_wafat;
	
	public String generateNIK(PendudukService pendudukDAO, KeluargaService keluargaDAO,Penduduk penduduk){
		
		Keluarga keluarga = keluargaDAO.selectKeluargaById(penduduk.getId_keluarga());
		String kodeLokasi = keluarga.getNomor_kk().substring(0, 6);
		String kodeTanggalLahir = "";
		String kodeNomorUrut = "0001";
		
		//Generate kode from tanggal lahir
		if(penduduk.getJenis_kelamin() == 0){
			String[] tanggalLahir = penduduk.getTanggal_lahir().split("-");
			kodeTanggalLahir = tanggalLahir[2] + tanggalLahir[1] + tanggalLahir[0].substring(2);
		} else if (penduduk.getJenis_kelamin() == 1){
			String[] tanggalLahir = penduduk.getTanggal_lahir().split("-");
			int hariLahir = Integer.parseInt(tanggalLahir[2]) + 40;
			kodeTanggalLahir = hariLahir + tanggalLahir[1] + tanggalLahir[0].substring(2);
		}
		String NIK = kodeLokasi + kodeTanggalLahir + kodeNomorUrut;
		
		//Cheking nomor urut
		while(true){
			Penduduk checker = pendudukDAO.selectPendudukByNIK(NIK);
			if(checker != null){
				long tempNIK = Long.parseLong(NIK);
				tempNIK+=1;
				NIK = "" + tempNIK;
			}else{
				break;
			}
		}
		
		return NIK;
	}
	
	public String generateNIK(PendudukService pendudukDAO,String kode_lokasi){
		String cutNIK = this.nik.substring(6);
		String NIK = kode_lokasi + cutNIK;
		
		while(true){
			Penduduk checker = pendudukDAO.selectPendudukByNIK(NIK);
			if(checker != null){
				long tempNIK = Long.parseLong(NIK);
				tempNIK += 1;
				NIK = "" + tempNIK;
			}else{
				break;
			}
		}
		return NIK;
	}
	
	public int compareTo(Penduduk other){
		return this.tanggal_lahir.compareTo(other.getTanggal_lahir());
	}
}
