package com.mini.cbse.Train;

import java.util.List;

import javax.sql.DataSource;

public interface TrainDAO {
	public void setDataSource(DataSource ds);
	public Train getTrainByTno(int tno);
	public List<Train> listTrains();
	public List<TrainSchedule> getTrainsByFromToStation(String from, String To);
}
