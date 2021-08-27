package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.util.DbUtil;


public class BookDAOImpl implements BookDAO {

	@Override
	public List<Book> bookSelect() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<Book>();
		
			try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select * from books order by books_no desc");
			rs= ps.executeQuery();
			while(rs.next()) {
				Book book  = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6));
	        	list.add(book);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
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
	        	book = new Book(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
	        	
	        }
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return book;
	}

	@Override
	public List<Book> bookSelectByWriter(String bookWriter) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("book.bookSelectByWriter");
			
		}catch (Exception e) {
		
		}
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
