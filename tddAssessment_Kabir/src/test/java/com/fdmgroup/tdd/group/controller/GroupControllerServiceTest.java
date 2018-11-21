package com.fdmgroup.tdd.group.controller;

import org.junit.Test;
import static org.mockito.Mockito.*;

public class GroupControllerServiceTest {

	@Test
	public void test_Add_trainee() {

		//Arrange or Mock
		DatabaseReader mockDBR = mock(DatabaseReader.class);
		DatabaseWriter mockDBW = mock(DatabaseWriter.class);
		TraineeController cont = new TraineeController(mockDBR, mockDBW);
		Trainee kabir = new Trainee();

		//act
		cont.addTrainee(kabir);

		//verify
		verify(mockDBW).addTrainee(kabir);

	}

	@Test
	public void test_Delete_trainee() {

		//Arrange or Mock
		DatabaseReader mockDBR = mock(DatabaseReader.class);
		DatabaseWriter mockDBW = mock(DatabaseWriter.class);
		TraineeController cont = new TraineeController(mockDBR, mockDBW);
		Trainee kabir = new Trainee();

		//act
		cont.addTrainee(kabir);
		cont.removeTraineeByUsername("kabir");

		//verify
		verify(mockDBW).deleteTraineeByUsername("kabir");

	}
	
	@Test
	public void test_getAll_trainee() {

		//Arrange or Mock
		DatabaseReader mockDBR = mock(DatabaseReader.class);
		DatabaseWriter mockDBW = mock(DatabaseWriter.class);
		TraineeController cont = new TraineeController(mockDBR, mockDBW);
		Trainee kabir = new Trainee();

		//act
		cont.addTrainee(kabir);
		cont.getAllTrainees();

		//verify
		verify(mockDBR).readGroup();

	}

}
