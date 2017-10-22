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
public interface KecamatanMapper {
	@Select("SELECT * FROM kecamatan where id = #{id}")
	Kecamatan selectKecamatanById(@Param("id") int id);
	
	@Select("SELECT * FROM kecamatan")
	List<Kecamatan> selectAllKecamatan();
	
	@Select("SELECT * FROM kecamatan WHERE id_kota=#{id_kota}")
	List<Kecamatan> selectKecamatanByIdKota(String id_kota);
}
