package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import db.Parameter;

/**
 * This class tracks book.
 * 
 * @author jbjohnson
 */
public class Book {
	/**
	 * This is a primitive variable that holds the place of a row ID.
	 */
	private int rowID;
	/**
	 * This is a primitive variable that holds place of author.
	 */
	private String author;
	/**
	 * this is a primitive variable that holds place of title.
	 */
	private String title;
	/**
	 * this is a primitive variable that holds place of genre.
	 */
	private String genre;
	/**
	 * this is a primitive variable that holds place of location.
	 */
	private String location;

	/**
	 *  Accessor RowID.
	 * @return returns rowID.
	 */
	public int getRowID() {
		return rowID;
	}
	
	public void setRowID(int pRowID) {
		rowID = pRowID;
	}

	/**
	 * Accessor Author.
	 * 
	 * @return author returns the author stored in the primitive field.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Mutator Author.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Accessor Title.
	 * 
	 * @return title returns the title stored in the primitive field.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Mutator Title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Accessor Genre
	 * 
	 * @return genre returns the primitive data stored in the genre field.
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Mutator for Genre.
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Accessor for Location.
	 * 
	 * @return location returns the primitive value stored in location.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Mutator for Location.
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * This method create.
	 * 
	 * @throws SQLException This stops the bad bad.
	 */
	public void create() throws SQLException {
		Database db = new Database("db.cberkstresser.name", "library");
		List<Parameter<?>> params = new ArrayList<>();

		params.add(new Parameter<String>(author));
		params.add(new Parameter<String>(title));
		params.add(new Parameter<String>(genre));
		params.add(new Parameter<String>(location));

		ResultSet rsBook = db.getResultSet("usp_CreateBook", params);
		if (rsBook.next()) {
			rowID = rsBook.getInt(1);
		}

	}

	/**
	 * This method update.
	 * 
	 * @throws SQLException
	 */
	public void update() throws SQLException {
		Database db = new Database("db.cberkstresser.name", "library");
		List<Parameter<?>> params = new ArrayList<>();

		// Databases are relational, order does matter. RowID must be created first.
		params.add(new Parameter<Integer>(rowID));
		params.add(new Parameter<String>(author));
		params.add(new Parameter<String>(title));
		params.add(new Parameter<String>(genre));
		params.add(new Parameter<String>(location));

		db.executeSql("usp_UpdateBook", params);
	}

	/**
	 * This method get.
	 * 
	 * @throws SQLException
	 */
	public void get(int rowID) throws SQLException {
		Database db = new Database("db.cberkstresser.name", "library");
		List<Parameter<?>> params = new ArrayList<>();

		params.add(new Parameter<Integer>(rowID));

		ResultSet rsBook = db.getResultSet("usp_GetBook", params);
		if (rsBook.next()) {
			this.rowID = rsBook.getInt("RowID");
			this.author = rsBook.getString("Author");
			this.title = rsBook.getString("Title");
			this.genre = rsBook.getString("Genre");
			this.location = rsBook.getString("Location");			
		} else {
			throw new IllegalArgumentException("That book was not found");
		}
	}

	/**
	 * This method delete.
	 * 
	 * @throws SQLException
	 */
	public void delete() throws SQLException {
		Database db = new Database("db.cberkstresser.name", "library");
		List<Parameter<?>> params = new ArrayList<>();

		params.add(new Parameter<Integer>(rowID));

		db.executeSql("usp_DeleteBook", params);
	}
	/**
	 * This Static.
	 * @return allBooks returns all the books idiot.
	 * @throws SQLException boy.
	 */
	public static List<Book> getAll() throws SQLException {
		Database db = new Database("db.cberkstresser.name", "library");
		List<Book> allBooks = new ArrayList<>();
		
		ResultSet rsBook = db.getResultSet("usp_GetAllBooks");

		while (rsBook.next()) {
			Book b = new Book();
			b.rowID = rsBook.getInt("RowID");
			b.author = rsBook.getString("Author");
			b.title = rsBook.getString("Title");
			b.genre = rsBook.getString("Genre");
			b.location = rsBook.getString("Location");	
			allBooks.add(b);
		}
		return allBooks;		
	}
}
