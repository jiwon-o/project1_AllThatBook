package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.util.DbUtil;
import kosta.mvc.model.dto.Goods;


public class BookDAOImpl implements BookDAO {

	@Override
	public List<Book> bookSelect() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book bookSelectByBookNo(int bookNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;
		try {
			con = DbUtil.getConnection();
			ps= con.prepareStatement("select * from books where Ã¥¹øÈ£=?");
			ps.setInt(1, bookNo);
			rs = ps.executeQuery(); 
	        
	        if(rs.next()) {
	        	book  = new Book(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
	        	
	        }
		return null;
	}

	@Override
	public List<Book> bookSelectByWriter(String bookWriter) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> bookSelectByPublisher(String bookPublisher) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> bookSelectByCatName(String bookCatName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int bookInsert(Book book) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int bookUpdate(Book book) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int bookDelete(Book book) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
