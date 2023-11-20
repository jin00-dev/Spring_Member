package com.itwillbs.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class DataSourceTest {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceTest.class);
	
	@Inject
	private DataSource ds;
	
	@Autowired
	public MemberDAO mdao; 
	
	
//	@Test
	public void ds_test() {
		logger.debug("ds_test() 실행");
		
		try {
			Connection con = ds.getConnection();
			logger.debug("con : "+ con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void sql구문실행테스트() {
		logger.debug("sql 구문 실행 테스트");
		
		logger.debug(mdao.getTime());
	}
	
	
}//DataSourceTest
