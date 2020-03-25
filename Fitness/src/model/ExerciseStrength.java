package model;

import java.time.LocalDate;

public class ExerciseStrength extends Exercise {

	private int sets;

	private int reps;

	private double weightLifted;

	@Override
	void load(int pStudentID, LocalDate pExerciseDate, String pExerciseName) {
		// TODO Auto-generated method stub

	}

	@Override
	void save() {
		// TODO Auto-generated method stub

	}

	@Override
	void delete() {
		// TODO Auto-generated method stub

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
