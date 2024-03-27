package com.eazybytes.eazyschool.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eazybytes.eazyschool.model.Holiday;

@Repository
public interface HolidaysRepository extends CrudRepository<Holiday,String>{
     
	
//	private final JdbcTemplate jdbcTemplate;
//	
//	@Autowired
//	public HolidaysRepository(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
//	
//	// So this function handle the SQL statement where it will return us all the attribute of the class HOLIDAYS 
//	// Because we're taking all the data of a object HOLIDAYS with not filter, we're using BeanPropertyRowMapper 
//	public List<Holiday> findAllHolidays(){
//		String sql = "SELECT * FROM HOLIDAYS";
//		var rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
//		return jdbcTemplate.query(sql, rowMapper);
//	}
	
}
