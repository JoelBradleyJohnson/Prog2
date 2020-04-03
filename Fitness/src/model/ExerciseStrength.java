package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import db.Parameter;

public class ExerciseStrength extends Exercise {

	private int sets;

	private int reps;

	private double weightLifted;

	@Override
	public void load(int pStudentID, LocalDate pExerciseDate, String pExerciseName) {
		// TODO Auto-generated method stub

	}
	
	public static List<ExerciseStrength> getAllByPerson(int pStudentID) throws SQLException {
		Database db = new Database("db.cberkstresser.name", "Exercise");
		List<Parameter<?>> params = new ArrayList<>();

		List<ExerciseStrength> returnValues = new ArrayList<>();
		params.add(new Parameter<Integer>(pStudentID));
		ResultSet rsExercises = db.getResultSet("usp_GetStrengthExercisesByPerson", params);
		while (rsExercises.next()) {
			ExerciseStrength e = new ExerciseStrength();
			e.setStudentID(rsExercises.getInt("StudentID"));
			e.setExerciseDate(rsExercises.getDate("ExerciseDate").toLocalDate());
			e.setExerciseName(rsExercises.getString("ExerciseName"));
			e.setExerciseDuration(Duration.ofSeconds(rsExercises.getInt("ExerciseSeconds")));
			e.setSets(rsExercises.getInt("Sets"));
			e.setReps(rsExercises.getInt("Reps"));
			e.setWeightLifted(rsExercises.getDouble("WeightLifted"));
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
		params.add(new Parameter<Integer>(sets));
		params.add(new Parameter<Integer>(reps));
		params.add(new Parameter<Double>(weightLifted));
		db.getResultSet("Exercise.usp_SaveExerciseStrength", params);

	}

	@Override
	public void delete(int pStudentID, LocalDate pExerciseDate, String pExerciseName) throws SQLException {
		Database db = new Database("db.cberkstresser.name", "Exercise");
		List<Parameter<?>> params = new ArrayList<>();

		params.add(new Parameter<Integer>(pStudentID));
		params.add(new Parameter<LocalDate>(pExerciseDate));
		params.add(new Parameter<String>(pExerciseName));

		db.executeSql("usp_DeleteExerciseStrength", params);
		System.out.println("Strength Deleted");
	}

	/**
	 * @return the sets
	 */
	public int getSets() {
		return sets;
	}

	/**
	 * @param sets the sets to set
	 */
	public void setSets(int sets) {
		this.sets = sets;
	}

	/**
	 * @return the reps
	 */
	public int getReps() {
		return reps;
	}

	/**
	 * @param reps the reps to set
	 */
	public void setReps(int reps) {
		this.reps = reps;
	}

	/**
	 * @return the weightLifted
	 */
	public double getWeightLifted() {
		return weightLifted;
	}

	/**
	 * @param weightLifted the weightLifted to set
	 */
	public void setWeightLifted(double weightLifted) {
		this.weightLifted = weightLifted;
	}

}
