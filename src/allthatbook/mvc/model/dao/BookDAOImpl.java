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

				Book book = new Book(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));

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
			ps= con.prepareStatement("select * from books where 책번호=?");
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
		Connection con = null;
		PreparedStatement ps = null;
		String sql="insert into books (책번호, 도서명, 저자명, 출판사명, 출간일, 분야, 상태) values (BOOK_SEQ_NO.nextval, ?, ?, ?, SYSDATE, ?, 0)";
		int result=0;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, book.getBookName());
			ps.setString(2, book.getBookWriter());
			ps.setString(3, book.getBookPublisher());
			ps.setString(4, book.getBookField());

			
			result=ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	@Override
	public int bookUpdate(Book book) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int bookDelete(int bookNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql="delete from books where 책번호 = ?";
		int result=0;	
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, bookNo);			
			result=ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	@Override
	public List<Book> bookRentalSelect() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<Book>();
		
			try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select * from books where 상태=1 order by 책번호 desc");
			rs= ps.executeQuery();
			while(rs.next()) {

				Book book = new Book(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));

	        	list.add(book);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<Book> bookReserveSelect() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<Book>();
		
			try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select * from books where 상태=2 order by 책번호 desc");
			rs= ps.executeQuery();
			while(rs.next()) {

				Book book = new Book(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));

	        	list.add(book);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

}
