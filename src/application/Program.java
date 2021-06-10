package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConection();
			
			st = conn.prepareStatement( //c�digos SQL
					"DELETE FROM department " //objeto a ser deletado
					+ "WHERE " //condi��o para deletar apenas o desejado
					+ "Id = ?");//campo a ser deletado
			
			st.setInt(1, 5);//1= ?, 5= valor procurado na tabela para deletar
			//N�o se pode deletar ou atualizar registros PAI, por v�nculo com chaves estrangeiras(herdeiras) 
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done! Rows affected: " + rowsAffected);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
