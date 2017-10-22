package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.*;

@Mapper
public interface KeluargaMapper {

	@Select("SELECT * FROM keluarga where nomor_kk = #{nkk}")
	Keluarga selectKelurgaByNKK(@Param("nkk") String nkk);
	
	@Select("SELECT * FROM keluarga where id = #{id}")
	Keluarga selectKelurgaById(@Param("id") int id);
	
	@Insert("INSERT INTO keluarga (nomor_kk, alamat, RT, RW, "
			+ "id_kelurahan, is_tidak_berlaku) VALUES ('${nomor_kk}', "
			+ "'${alamat}' , '${RT}' , '${RW}' , "
			+ "${id_kelurahan} , ${is_tidak_berlaku})")
	void addKeluarga(Keluarga keluarga);
	
	@Update("UPDATE keluarga SET nomor_kk = '${nomor_kk}', alamat = "
			+ "'${alamat}', RT = '${RT}', RW = '${RW}', id_kelurahan = "
			+ "'${id_kelurahan}', is_tidak_berlaku = 0 WHERE id=${id}")
	void updateKeluarga(Keluarga keluarga);
	
	@Update("UPDATE keluarga SET is_tidak_berlaku = 1 WHERE id = ${id}")
	void matikanKeluarga(Keluarga keluarga);
}
