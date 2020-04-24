package com.mini.cbse.Train;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class TrainJDBCTemplate implements TrainDAO {
	
	private JdbcTemplate jdbcTemplateObject;
	private int reservation_id_add = 21000000;//8 digit
	// QUERIES
	private String GET_ALL_TRAINS = "select * from train";
	private String GET_TRAIN_TRAINNO = "select * from train where train_no = ?";
	private String GET_TRAINS_FROM_TO_STATION = "select a.train_no as 'train_no',\r\n" + 
			"a.station as 'from', a.arrival_time as 'from_arr', a.dept_time as 'from_dept',\r\n" + 
			"b.station as 'to', b.arrival_time as 'to_arr', b.dept_time as 'to_dept' ,\r\n" + 
			"b.station_no as 'to_no', a.station_no as 'from_no'\r\n" + 
			"from train_stations a\r\n" + 
			"inner join train_stations b on a.train_no = b.train_no\r\n" + 
			"where (a.station = ?\r\n" + 
			" and b.station = ?\r\n" + 
			" and a.station_no < b.station_no)\r\n" + 
			" order by a.dept_time;";
	private String GET_TICKET_COST = "select cost from train_cost\r\n" + 
			"where train_no = ? and from_station_no=? and to_station_no=? and class=?;";
	private String GET_TRAIN_AVAILABLE_SEATS = "select available_seat from train where train_no=?;";
	private String MAKE_RESERVATION = "INSERT INTO `cbse`.`reservations`\r\n" + 
			"(`train_no`,\r\n" + 
			"`from_station`,\r\n" + 
			"`to_station`,\r\n" + 
			"`adult`,\r\n" + 
			"`class`,\r\n" + 
			"`date_of_boarding`,\r\n" + 
			"`child`,\r\n" + 
			"`total_cost`,\r\n" + 
			"`train_name`,\r\n" + 
			"`username`,\r\n" + 
			"`name`)\r\n" + 
			"VALUES\r\n" + 
			"(?,?,?,?,?,?,?,?,?,?,?);";
	private String GET_RESR_ID = "";
	
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

	@Override
	public int getTicketCost(int train_no, int from_no, int to_no, String selectedClass) {
		SqlRowSet rs = jdbcTemplateObject.queryForRowSet(GET_TICKET_COST, train_no,from_no,to_no,selectedClass);
		if(rs.next())
		return rs.getInt("cost");
		return -1;
	}

	@Override
	public int getAvailableSeats(int train_no) {
		SqlRowSet rs = jdbcTemplateObject.queryForRowSet(GET_TRAIN_AVAILABLE_SEATS, train_no);
		if(rs.next())
		return rs.getInt("available_seat");
		return -1;
	}

	@Override
	public int makeReservation(String username, String name, TrainSchedule ts, String date, String selectedClass,
			int adult, int child, int total_cost) {
//		jdbcTemplateObject.update(MAKE_RESERVATION,ts.getTrain_no(),ts.getFrom(),ts.getTo(),
//				adult,selectedClass,date,child,total_cost,ts.getTrain_name(),username,name);
//		SqlRowSet rs = jdbcTemplateObject.queryForRowSet(GET_RESR_ID, ts.getTrain_no(),ts.getFrom(),ts.getTo(),
//				adult,selectedClass,date,child,total_cost,ts.getTrain_name(),username,name);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		 
	    jdbcTemplateObject.update(connection -> {
	        PreparedStatement ps = connection
	          .prepareStatement(MAKE_RESERVATION, Statement.RETURN_GENERATED_KEYS);
	          ps.setInt(1, ts.getTrain_no());ps.setString(2, ts.getFrom());ps.setString(3, ts.getTo());
	          ps.setInt(4, adult);ps.setString(5, selectedClass);ps.setString(6, date);ps.setInt(7, child);
	          ps.setInt(8, total_cost);ps.setString(9, ts.getTrain_name());ps.setString(10, username);ps.setString(11, name);
	          return ps;
	        }, keyHolder);
	    int rid =  keyHolder.getKey().intValue();
	    return rid+reservation_id_add;
	}

}











