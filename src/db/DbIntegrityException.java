package db;

public class DbIntegrityException extends RuntimeException {
//Excessão personalidade de integridade referencial
	private static final long serialVersionUID = 1L;
	
	public DbIntegrityException(String msg) {
		super(msg);
	}
}
