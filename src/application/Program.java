package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		try {
			conn = DB.getConection();
			
//lógica para confirmação de TRANSAÇÃO: caso a operação seja verdadeira até o final 
//Neste exemplo, a transação não será confirmada, tendo o seu valor retornado e não alterado.
			
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

			//simulação de possivel erro, para aplicação de transição
			/*
			int x = 1;
			if (x < 2) {
				throw new SQLException("Fake error");
			}
			*/
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			conn.commit(); //fim da verificação de confirmação verdadeira da lógica de TRANSAÇÃO
			
			System.out.println("rows1 " + rows1);
			System.out.println("rows2 " + rows2);
		}
		catch (SQLException e) {
			try {//Erro para informar que a transação não ocorreu, está retornando a ação
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				 throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
