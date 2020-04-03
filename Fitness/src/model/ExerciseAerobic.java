package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import db.Parameter;

public class ExerciseAerobic extends Exercise {

	private int maxHeartRate;

	private int averageHeartRate;

	private double distance;

	@Override
	public void load(int pStudentID, LocalDate pExerciseDate, String pExerciseName) {
		
	}
	
	public static List<ExerciseAerobic> getAllByPerson(int pStudentID) throws SQLException {
		Database db = new Database("db.cberkstresser.name", "Exercise");
		List<Parameter<?>> params = new ArrayList <>();
		
		List<ExerciseAerobic> returnValues = new ArrayList<>();
		params.add(new Parameter<Integer>(pStudentID));
		ResultSet rsExercises = db.getResultSet("usp_GetAerobicExercisesByPerson", params);
		while (rsExercises.next()) {
			ExerciseAerobic e = new ExerciseAerobic();
			e.setStudentID(rsExercises.getInt("StudentID"));
			e.setExerciseDate(rsExercises.getDate("ExerciseDate").toLocalDate());
			e.setExerciseName(rsExercises.getString("ExerciseName"));
			e.setExerciseDuration(Duration.ofSeconds(rsExercises.getInt("ExerciseSeconds")));
			e.setMaxHeartRate(rsExercises.getInt("MaxHeartRate"));
            e.setAverageHeartRate(rsExercises.getInt("AverageHeartRate"));
            e.setDistance(rsExercises.getDouble("Distance"));
            returnValues.add(e);			
		}
		return returnValues;
		
	}

	@Override
	public void save() throws SQLException {
		Database db = new Database("db.cberkstresser.name", "Exercise");
		List<Parameter<?>> params = new ArrayList<>();

		params.add(new Parameter<Integer>(studentID));
		params.add(new Parameter<LocalDate>(exerciseDate));
		params.add(new Parameter<String>(exerciseName));
		params.add(new Parameter<Long>(exerciseDuration.getSeconds()));
		params.add(new Parameter<Integer>(maxHeartRate));
		params.add(new Parameter<Integer>(averageHeartRate));
		params.add(new Parameter<Double>(distance));
		db.getResultSet("usp_SaveExerciseAerobic", params);

	}

	@Override
	public void delete(int pStudentID, LocalDate pExerciseDate, String pExerciseName) throws SQLException {
		Database db = new Database("db.cberkstresser.name", "Exercise");
		List<Parameter<?>> params = new ArrayList<>();

		params.add(new Parameter<Integer>(pStudentID));
		params.add(new Parameter<LocalDate>(pExerciseDate));
		params.add(new Parameter<String>(pExerciseName));

		db.executeSql("usp_DeleteExerciseAerobic", params);
		System.out.println("Aerobic Deleted");
	}

	/**
	 * @return the maxHeartRate
	 */
	public int getMaxHeartRate() {
		return maxHeartRate;
	}

	/**
	 * @param maxHeartRate the maxHeartRate to set
	 */
	public void setMaxHeartRate(int maxHeartRate) {
		this.maxHeartRate = maxHeartRate;
	}

	/**
	 * @return the averageHeartRate
	 */
	public int getAverageHeartRate() {
		return averageHeartRate;
	}

	/**
	 * @param averageHeartRate the averageHeartRate to set
	 */
	public void setAverageHeartRate(int averageHeartRate) {
		this.averageHeartRate = averageHeartRate;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
}
