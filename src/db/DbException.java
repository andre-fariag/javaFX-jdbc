package db;

public class DbException extends RuntimeException { // classe responsável pelo tratamento de excessões personalizadas. 
	private static final long serialVersionUID = 1L;

	public DbException(String msg) {
		super(msg);
	}
}
