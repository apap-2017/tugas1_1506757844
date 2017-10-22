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
public interface KelurahanMapper {
	@Select("SELECT * FROM kelurahan where id = #{id}")
	Kelurahan selectKelurahanById(@Param("id") int id);
	
	@Select("SELECT * FROM kelurahan")
	List<Kelurahan> selectAllKecamatan();
	
	@Select("SELECT * FROM kelurahan WHERE id_kecamatan=#{id_kecamatan}")
	List<Kelurahan> selectKelurahanByIdKecamatan(String id_kecamatan);
}
