package db;

public class DbIntegrityException extends RuntimeException {
//Excess�o personalidade de integridade referencial
	private static final long serialVersionUID = 1L;
	
	public DbIntegrityException(String msg) {
		super(msg);
	}
}
