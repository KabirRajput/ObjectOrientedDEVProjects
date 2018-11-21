package com.fdmgroup.tdd.group.controller;

import java.util.Map;

public class TraineeController implements GroupControllerService{

	private DatabaseReader dbr;
	private DatabaseWriter dbw;
	
	public TraineeController(DatabaseReader dbr, DatabaseWriter dbw) {
		this.dbr = dbr;
		this.dbw = dbw;
	}

	public Map<String, Trainee> getAllTrainees() {
		return dbr.readGroup();
	}

	public void addTrainee(Trainee any) {
		dbw.addTrainee(any);
		
	}

	public void removeTraineeByUsername(String traineeUsername) {
		dbw.deleteTraineeByUsername(traineeUsername);
		
	}

}
