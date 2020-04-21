package com.mini.cbse.Train;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TrainScheduleMapper implements RowMapper<TrainSchedule> {

	@Override
	public TrainSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
		TrainSchedule t = new TrainSchedule();
		t.setTrain_no(rs.getInt("train_no"));
		t.setFrom(rs.getString("from"));
		t.setFrom_arr(rs.getString("from_arr"));
		t.setFrom_dept(rs.getString("from_dept"));
		t.setTo(rs.getString("to"));
		t.setTo_arr(rs.getString("to_arr"));
		t.setTo_dept(rs.getString("to_dept"));
		
		return t;
	}

}
