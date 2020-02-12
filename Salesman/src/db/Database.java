package db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * This class provides the ability to save to permanent storage.
 * 
 * @author jjohnson
 * 
 */
public class Database {
	/** The default db name to connect to if none specified. */
	public static final String DEFAULT_DB = "qc";
	/** A data source to the mysql db. */
	public static final String DEFAULT_PASSWORD = "test";
	/** The default server name to connect to if none specified. */
	public static final String DEFAULT_SERVER = "db.cberkstresser.name";
	/** A data source to the mysql db. */
	public static final String DEFAULT_USER = "prog2";
	/** A data source to the mysql db. */
	private MysqlDataSource ds = new MysqlDataSource();

	/**
	 * Constructor.
	 */
	public Database() {
		this(DEFAULT_SERVER, DEFAULT_DB, DEFAULT_USER, DEFAULT_PASSWORD);
	}

	/**
	 * Constructor.
	 * 
	 * @param serverName The server's dns name or ip address.
	 */
	public Database(final String serverName) {
		this(serverName, DEFAULT_DB, DEFAULT_USER, DEFAULT_PASSWORD);
	}

	/**
	 * Constructor.
	 * 
	 * @param serverName   The server's dns name or ip address.
	 * @param databaseName The database's name.
	 */
	public Database(final String serverName, final String databaseName) {
		this(serverName, databaseName, DEFAULT_USER, DEFAULT_PASSWORD);
	}

	/**
	 * Constructor.
	 * 
	 * @param serverName   The server's dns name or ip address.
	 * @param databaseName The database's name.
	 * @param userName     The user name with rights to the database.
	 * @param password     The password with the user.
	 */
	public Database(final String serverName, final String databaseName, final String userName, final String password) {
		ds.setURL("jdbc:mysql://" + serverName + "/" + databaseName + "?noAccessToProcedureBodies=true&useSSL=false");
		ds.setUser(userName);
		ds.setPassword(password);
		try {
			ds.setServerTimezone("America/Chicago");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Execute a query in the database with no return value.
	 * 
	 * @param storedProcedure The stored procedure name to call.
	 * @throws SQLException
	 */
	public final int executeSql(final String storedProcedure) throws SQLException {
		return executeSql(storedProcedure, new ArrayList<Parameter<?>>());
	}

	/**
	 * Execute a query in the database with no return value.
	 * 
	 * @param storedProcedure The stored procedure name to call.
	 * @param arrayList       The arguments that the stored procedure requires to
	 *                        run.
	 * @return 
	 * @throws SQLException
	 */
	public final int executeSql(final String storedProcedure, final List<Parameter<?>> arrayList) throws SQLException {
		CallableStatement c = setStoredProcedureParameters(storedProcedure, arrayList);
		return c.executeUpdate();
	}

	/**
	 * Get a result set from a stored procedure.
	 * 
	 * @param storedProcedure The stored procedure name to call.
	 * @return A resultset provided by the stored procedure.
	 * @throws SQLException
	 */
	public final ResultSet getResultSet(final String storedProcedure) throws SQLException {
		return getResultSet(storedProcedure, new ArrayList<Parameter<?>>());
	}

	/**
	 * Get a result set from a stored procedure.
	 * 
	 * @param storedProcedure The stored procedure name to call.
	 * @param pParameters     The arguments that the stored procedure requires to
	 *                        run.
	 * @return A resultset provided by the stored procedure.
	 * @throws SQLException
	 */
	public final ResultSet getResultSet(final String storedProcedure, final List<Parameter<?>> pParameters)
			throws SQLException {
		return setStoredProcedureParameters(storedProcedure, pParameters).executeQuery();
	}

	/**
	 * Get a resultset from raw sql.
	 * 
	 * @param sql The sql statement to execute.
	 * @return Resultset from the sql statement.
	 * @throws SQLException
	 */
	public final ResultSet getResultSetSql(final String sql) throws SQLException {
		Connection cn = ds.getConnection();
		PreparedStatement preparedStatement = cn.prepareStatement(sql);
		return preparedStatement.executeQuery();
	}

	/**
	 * Builds the stored procedure header from name and parameters.
	 * 
	 * @param storedProcedure The stored procedure name to execute.
	 * @param pParameters     The parameter list to attach (in order).
	 * @throws SQLException When this process fails.
	 * 
	 * @return Returns a callable statement after setting parameters.
	 */
	public final CallableStatement setStoredProcedureParameters(final String storedProcedure,
			final List<Parameter<?>> pParameters) throws SQLException {
		CallableStatement callableStatement = null;

		Connection cn = ds.getConnection();
		if (pParameters.size() == 0) {
			callableStatement = cn.prepareCall("{call " + storedProcedure + "}");
		} else {
			StringBuilder storedProcedureParamList = new StringBuilder("{call " + storedProcedure + " ( ");
			for (int n = 0; n < pParameters.size(); ++n) {
				storedProcedureParamList.append("?,");
			}
			storedProcedureParamList.replace(storedProcedureParamList.length() - 1, storedProcedureParamList.length(),
					")}");

			callableStatement = cn.prepareCall(storedProcedureParamList.toString());

			for (int n = 0; n < pParameters.size(); ++n) {
				if (pParameters.get(n).getParameter() instanceof Integer) {
					if (pParameters.get(n).getDefaultToNull() && (int) pParameters.get(n).getParameter() == 0) {
						callableStatement.setNull(n + 1, java.sql.Types.INTEGER);
					} else {
						callableStatement.setInt(n + 1, (int) pParameters.get(n).getParameter());
					}
				} else if (pParameters.get(n).getParameter() instanceof Long) {
					if (pParameters.get(n).getDefaultToNull() && (long) pParameters.get(n).getParameter() == 0.0) {
						callableStatement.setNull(n + 1, java.sql.Types.INTEGER);
					} else {
						callableStatement.setLong(n + 1, (long) pParameters.get(n).getParameter());
					}
				} else if (pParameters.get(n).getParameter() instanceof Double) {
					if (pParameters.get(n).getDefaultToNull() && (double) pParameters.get(n).getParameter() == 0.0) {
						callableStatement.setNull(n + 1, java.sql.Types.DOUBLE);
					} else {
						callableStatement.setDouble(n + 1, (double) pParameters.get(n).getParameter());
					}
				} else if (pParameters.get(n).getParameter() instanceof Date) {
					callableStatement.setTimestamp(n + 1,
							new Timestamp(((java.util.Date) pParameters.get(n).getParameter()).getTime()));
				} else if (pParameters.get(n).getParameter() instanceof LocalDate) {
					callableStatement.setTimestamp(n + 1,
							Timestamp.valueOf(((LocalDate) pParameters.get(n).getParameter()).atStartOfDay()));
				} else if (pParameters.get(n).getParameter() instanceof LocalDateTime) {
					callableStatement.setTimestamp(n + 1,
							Timestamp.valueOf(((LocalDateTime) pParameters.get(n).getParameter())));
				} else if (pParameters.get(n).getParameter() instanceof String) {
					if (pParameters.get(n).getDefaultToNull()
							&& ((String) pParameters.get(n).getParameter()).length() == 0) {
						callableStatement.setNull(n + 1, java.sql.Types.VARCHAR);
					} else {
						callableStatement.setString(n + 1, (String) pParameters.get(n).getParameter());
					}
				} else if (pParameters.get(n).getParameter() instanceof Boolean) {
					callableStatement.setBoolean(n + 1, (Boolean) pParameters.get(n).getParameter());
				}
			}

		}
		return callableStatement;
	}
}
