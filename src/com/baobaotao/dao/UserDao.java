package com.baobaotao.dao;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.resource.cci.ResultSet;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.baobaotao.domain.User;

@Repository
public class UserDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public int getMatchCount(String userName, String password) {
		String sqlStr = "SELECT　count(*) FROM t_user WHERE user_name=?and name=?";
		return jdbcTemplate.queryForInt(sqlStr, new Object[] { userName,
				password });
	}
	
	public User findUserByUserName(final String userName){
		String sqlStr="SELECT user_id,user_name,credits FROM t_user WHERE user_name=?";
		final User user=new User();
		
		jdbcTemplate.query(sqlStr, new Object[]{userName}, 
				new RowCallbackHandler(){

			@Override
			public void processRow(java.sql.ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(userName);
				user.setCredits(rs.getInt("credits"));
			}

		
		
		});
		return user ;
	}
	
	public void updateLoginInfo(User user){
		String sqlStr="UPDATE t_user SET last_visit=?,last_ip=?,credits=? WHERE user_id=?";
		
		jdbcTemplate.update(sqlStr, new Object[]{user.getLastVisit(),user.getLastIp(),user.getCredits(),user.getUserId()});
	}
}


