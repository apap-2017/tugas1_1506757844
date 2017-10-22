package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.Kota;

@Mapper
public interface KotaMapper {
	
	@Select("SELECT * FROM kota where id = #{id}")
	Kota selectKotaById(@Param("id") int id);
	
	@Select ("SELECT * FROM kota")
	List<Kota> selectAllKota();
}
