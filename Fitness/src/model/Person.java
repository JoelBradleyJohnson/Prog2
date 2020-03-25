package model;

import java.time.LocalDate;
import java.util.List;

public class Person {

	private int studentID;

	private List<Exercise> exercises;

	private String firstName;

	private double height;

	private double weight;

	private Gender gender;

	private LocalDate birthdate;

	public int getAge() {
		return 0;
	}

	public void addExercise(Exercise pExercise) {

	}

	public void removeExcise(int pIndexToRemove) {

	}

	public void load(int pStudentID) {

	}

	public void save() {

	}

	public void delete() {

	}
	
	/**
	 * @return the studentID
	 */
	public int getStudentID() {
		return studentID;
	}

	/**
	 * @param studentID the studentID to set
	 */
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	/**
	 * @return the exercises
	 */
	public List<Exercise> getExercises() {
		return exercises;
	}

	/**
	 * @param exercises the exercises to set
	 */
	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthdate
	 */
	public LocalDate getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

}