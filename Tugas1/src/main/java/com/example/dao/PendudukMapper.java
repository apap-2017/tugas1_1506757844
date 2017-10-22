package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.Penduduk;

@Mapper
public interface PendudukMapper {
	
	@Select("SELECT * FROM penduduk where id = #{id}")
	Penduduk selectPendudukById(@Param("id") int id);
	
	@Select("SELECT * FROM penduduk where nik = #{nik}")
	Penduduk selectPendudukByNIK(@Param("nik") String nik);
	
	@Select ("SELECT * FROM penduduk where id_keluarga = #{id_keluarga}")
	List<Penduduk> selectPendudukByIdKeluarga(@Param("id_keluarga") int id_keluarga);
	
	@Insert ("INSERT INTO penduduk (nik, nama, tempat_lahir, "
			+ "tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, "
			+ "agama, pekerjaan, status_perkawinan, status_dalam_keluarga, "
			+ "golongan_darah, is_wafat) VALUES ('${nik}', '${nama}', '${tempat_lahir}', "
			+ "'${tanggal_lahir}', ${jenis_kelamin}, ${is_wni}, ${id_keluarga}, '${agama}', "
			+ "'${pekerjaan}', '${status_perkawinan}', '${status_dalam_keluarga}', '${golongan_darah}', ${is_wafat})")
	void addPenduduk(Penduduk penduduk);
	
	@Update("UPDATE penduduk SET nik = '${nik}', nama = '${nama}', tempat_lahir = '${tempat_lahir}',"
			+ "tanggal_lahir = '${tanggal_lahir}', jenis_kelamin = ${jenis_kelamin},"
			+ "is_wni = ${is_wni}, id_keluarga = ${id_keluarga}, agama='${agama}', "
			+ "pekerjaan='${pekerjaan}', status_perkawinan='${status_perkawinan}',"
			+ "status_dalam_keluarga = '${status_dalam_keluarga}', golongan_darah = '${golongan_darah}',"
			+ "is_wafat=${is_wafat} WHERE id=${id}")
	void updatePenduduk(Penduduk penduduk);
	
	@Update("UPDATE penduduk SET is_wafat=1 WHERE nik='${nik}'")
	void matikanPenduduk(Penduduk penduduk);
	
	@Select("SELECT nik, nama, jenis_kelamin, tanggal_lahir FROM penduduk JOIN keluarga ON penduduk.id_keluarga = keluarga.id JOIN "
			+ "kelurahan ON keluarga.id_kelurahan = kelurahan.id JOIN kecamatan ON "
			+ "kelurahan.id_kecamatan = kecamatan.id JOIN kota ON kecamatan.id_kota = kota.id "
			+ "WHERE keluarga.id_kelurahan = #{id_kelurahan}")
	List<Penduduk> selectPendudukByIdKelurahan(@Param("id_kelurahan") String id_kelurahan);
}
