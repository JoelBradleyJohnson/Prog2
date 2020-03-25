package model;

import java.time.LocalDate;

public class ExerciseAerobic extends Exercise {

	private int maxHeartRate;

	private int averageHeartRate;

	private double distance;

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
