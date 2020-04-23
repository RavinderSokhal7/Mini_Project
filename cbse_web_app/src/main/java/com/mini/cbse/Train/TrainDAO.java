package com.mini.cbse.Train;

import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

public interface TrainDAO {
	public void setDataSource(DataSource ds);
	public Train getTrainByTno(int tno);
	public List<Train> listTrains();
	public List<TrainSchedule> getTrainsByFromToStation(String from, String To);
	public int getTicketCost(int train_no,int from_no,int to_no, String selectedClass);
	public int getAvailableSeats(int train_no);
	public int makeReservation(String username,String name,TrainSchedule ts,
			String date,String selectedClass,int adult,int child,int total_cost);
}
