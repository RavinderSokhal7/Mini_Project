package com.mini.cbse.Train;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class TrainJDBCTemplate implements TrainDAO {
	
	private JdbcTemplate jdbcTemplateObject;
	// QUERIES
	private String GET_ALL_TRAINS = "select * from train";
	private String GET_TRAIN_TRAINNO = "select * from train where train_no = ?";
	private String GET_TRAINS_FROM_TO_STATION = "select a.train_no as train_no,\r\n" + 
			"a.station as 'from', a.arrival_time as 'from_arr', a.dept_time as 'from_dept',\r\n" + 
			"b.station as 'to', b.arrival_time as 'to_arr', b.dept_time as 'to_dept' \r\n" + 
			"from train_stations a\r\n" + 
			"inner join train_stations b on a.train_no = b.train_no\r\n" + 
			"where (a.station = ? \r\n" + 
			" and b.station = ? \r\n" + 
			" and a.station_no < b.station_no)\r\n" + 
			" order by a.dept_time";
	
	@Override
	public void setDataSource(DataSource dataSource) {
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public Train getTrainByTno(int tno) {
		List<Train> train = jdbcTemplateObject.query(GET_TRAIN_TRAINNO, new Object[] {tno}, new TrainMapper());
		if(train.size()==0)	return null;	
		return train.get(0);
	}

	@Override
	public List<Train> listTrains() {
		List<Train> trains = jdbcTemplateObject.query(GET_ALL_TRAINS, new TrainMapper());
		return trains;
	}

	@Override
	public List<TrainSchedule> getTrainsByFromToStation(String from, String to) {		
		List<TrainSchedule> trains = jdbcTemplateObject.query(GET_TRAINS_FROM_TO_STATION, new Object[] {from,to}, new TrainScheduleMapper());
		return trains;
	}

}











