package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import allthatbook.mvc.model.dto.Rental;
import allthatbook.mvc.util.DbUtil;

public class RentalDAOImpl implements RentalDAO {
	  /**
	   * �뿩�ϱ�
	   *  1) å���� Ȯ���ϱ� (0�̸� �뿩����)
	   *  2) Rental ���̺� insert
	   *  3) å���� �����ϱ� (1�� ����)
	   * */

	@Override
	public int rentalInsert(Rental rental) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 String sql="INSERT INTO RENTAL(�뿩��ȣ, å��ȣ, ȸ����ȣ, �ݳ���������, �뿩����, �ݳ�����)" + 
		  		" VALUES(RENTAL_SEQ_NO.NEXTVAL, ?, ?, ?, ?, null)";
		 int result=0;
		 try {
			 con = DbUtil.getConnection();
			 con.setAutoCommit(false); //�ڵ�Ŀ������
			   
			 ps = con.prepareStatement(sql);
			 ps.setInt(1, rental.getBookNo());
			 ps.setInt(2, rental.getUserNo());
			 ps.setDate(3, rental.getExreturnDate());
			 ps.setDate(4, rental.getRentDate());  
			 result = ps.executeUpdate();
			   if(result==0) {
				   con.rollback();
				   throw new SQLException("�뿩 ����");
			   }
			   else {
				   //å �������� å ���¿� ���� ���� 
				   
				   int re = getBookState(con, rental);
				   
				   if (re == 0) {
					   //�뿩���� --> å���� 1�� ����
					   changeBookState(con, rental);
				   }else if(re == 1) {
					   con.rollback();
					   throw new SQLException("�������ε���");
				   }else {
					   //���°� 2�� ��� ����� ȸ����ȣ�� �뿩�Ϸ��� ȸ���� ��ȣ�� ������ ��������
				   }
				}//else��   
				   
			con.commit();
	     }finally {
	    	con.commit();
	      	DbUtil.close(con, ps , null);
	      }
			
		return result;
	}//�޼ҵ峡



	/**
	 * å����(bookState) ��������
	 * */
	public int getBookState(Connection con, Rental rental) throws SQLException{
		  PreparedStatement ps=null;
		  ResultSet rs = null;
		  String sql="select ���� from books where å��ȣ=?";
		  int result = 0;
		 try {
			 ps = con.prepareStatement(sql);
			 ps.setInt(1, rental.getBookNo());
			 rs = ps.executeQuery();
			 result = rs.getInt(1);
		  }	   
        finally {
    	    DbUtil.close(null, ps , rs);
        }
		
		return result;
	}//�޼ҵ� ��
	
	
	/**
	 * ������ ���� å���� 1�� �ٲ��ֱ�
	 * */
	public int changeBookState(Connection con, Rental rental) throws SQLException{
		  PreparedStatement ps=null;
		  String sql="update books set ���� = 1 where å��ȣ = ?";
		  int result = 0;
		 try {
			 ps = con.prepareStatement(sql);
			 ps.setInt(1, rental.getBookNo());
			 result = ps.executeUpdate();
		  }	   
      finally {
  	    DbUtil.close(null, ps);
      }
		
		return result;
	}//�޼ҵ� ��

}//Ŭ���� ��
