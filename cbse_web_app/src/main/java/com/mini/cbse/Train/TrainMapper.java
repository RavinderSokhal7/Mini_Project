package com.mini.cbse.Train;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class TrainMapper implements RowMapper<Train> {

	@Override
	public Train mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Train train = new Train();
		train.setTrain_no(rs.getInt("train_no"));
		train.setTrain_name(rs.getString("train_name"));
		train.setOrigin(rs.getString("origin_station"));
		train.setEnd(rs.getString("end_station"));
		train.setTotal(rs.getInt("total_seat"));
		train.setAvailable(rs.getInt("available_seat"));
		
		return train;
	}

}
