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
			ps = con.prepareStatement("select 책번호, 도서명, 저자명, 출판사명, 분야, 상태 "
					+ "from books order by 책번호");
			rs= ps.executeQuery();
			while(rs.next()) {
				Book book = new Book(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
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
			ps= con.prepareStatement("select 책번호, 도서명, 저자명, 출판사명, 분야, 상태 "
					+ "from books where 책번호=?");
			ps.setInt(1, bookNo);
			rs = ps.executeQuery(); 
	        
	        if(rs.next()) {
	        	book = new Book(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
	        }
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return book;
	}

	@Override
	public List<Book> bookSelectByBookName(String keyword) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<Book>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select 책번호, 도서명, 저자명, 출판사명, 분야, 상태 "
					+ "from books where upper(도서명) like upper(?) order by 책번호");
			ps.setString(1, "%"+keyword+"%");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Book book = new Book(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
				bookList.add(book);
			}
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return bookList;
	}
	
	@Override
	public List<Book> bookSelectByWriter(String writer) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<Book>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select 책번호, 도서명, 저자명, 출판사명, 분야, 상태 "
					+ "from books where upper(저자명) like upper(?) order by 책번호");
			ps.setString(1, "%"+writer+"%");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Book book = new Book(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
				bookList.add(book);
			}
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return bookList;
	}

	@Override
	public List<Book> bookSelectByPublisher(String publisher) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<Book>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select 책번호, 도서명, 저자명, 출판사명, 분야, 상태 "
					+ "from books where upper(출판사명) like upper(?) order by 책번호");
			ps.setString(1, "%"+publisher+"%");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Book book = new Book(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
				bookList.add(book);
			}
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return bookList;
	}

	@Override
	public List<Book> bookSelectByCategory(String category) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<Book>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select 책번호, 도서명, 저자명, 출판사명, 분야, 상태 "
					+ "from books where upper(분야) like upper(?) order by 책번호");
			ps.setString(1, "%"+category+"%");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Book book = new Book(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
				bookList.add(book);
			}
			
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return bookList;
	}

	@Override
	public List<Book> bookSelectByState(int state) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<Book>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select 책번호, 도서명, 저자명, 출판사명, 분야, 상태 "
					+ "from books where 상태 = ? order by 책번호");
			ps.setInt(1, state);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Book book = new Book(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
				bookList.add(book);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return bookList;
	}
	
	@Override
	public int bookInsert(Book book) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String sql="insert into books (책번호, 도서명, 저자명, 출판사명, 분야, 상태) values (BOOK_SEQ_NO.nextval, ?, ?, ?, ?, 0)";

		int result=0;

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, book.getBookName());
			ps.setString(2, book.getBookWriter());
			ps.setString(3, book.getBookPublisher());

			ps.setString(4, book.getBookField());

			
			result=ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps);
		}
		return result;

	}

	@Override
	public int bookUpdate(Book updatebook) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql="update books set 도서명=?, 저자명=?, 출판사명=?, 분야=? where 책번호=?";
		
		int result=0;	
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, updatebook.getBookName());
			ps.setString(2, updatebook.getBookWriter());
			ps.setString(3, updatebook.getBookPublisher());
			ps.setString(4, updatebook.getBookField());
			ps.setInt(5, updatebook.getBookNo());

			result=ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
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
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
	}


	public List<Book> bookPossibleRentalSelect() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<Book>();
			try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select 책번호, 도서명, 저자명, 출판사명, 분야, 상태 "
					+ "from books where 상태=0 order by 책번호 desc");
			rs= ps.executeQuery();
			
			while(rs.next()) {
				Book book = new Book(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
	        	list.add(book);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

	public List<Book> bookRentalSelect() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<Book>();
			try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select 책번호, 도서명, 저자명, 출판사명, 분야, 상태 "
					+ "from books where 상태=1 order by 책번호 desc");
			rs= ps.executeQuery();
			
			while(rs.next()) {
				Book book = new Book(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
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
			ps = con.prepareStatement("select 책번호, 도서명, 저자명, 출판사명, 분야, 상태 "
					+ "from books where 상태=2 order by 책번호 desc");
			rs= ps.executeQuery();
			
			while(rs.next()) {
				Book book = new Book(rs.getInt(1),  rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
	        	list.add(book);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}

}
