package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import model.Book;
import util.ConnectionUtil;


public class BookDAO {
	
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	
	public void register(Book book) throws Exception {
		String sql = "insert into books(name,price,pub_date,author_id)values(?,?,?,?)";

		Connection con = ConnectionUtil.getConnection();
		System.out.println("Conn:" + con);
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, book.getName());
		pst.setFloat(2,book.getPrice());
		pst.setDate(3, Date.valueOf(book.getPub_date()));
		pst.setInt(4, book.getAutuor_id());
		
		

		int rows = jdbcTemplate.update(sql,book.getName(),book.getPrice(),Date.valueOf(book.getPub_date()),book.getAutuor_id());
		System.out.println(rows);

		System.out.println("BookDAO-> register: " + book);
	}
	public List<Book> listbook() throws Exception {
		Connection con = ConnectionUtil.getConnection();
		String sql = "select id,name,price,author_id,pub_date from books";
		PreparedStatement pst = con.prepareStatement(sql);
		List<Book> booklist = new ArrayList<Book>();
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int price = rs.getInt("price");
			int authour_id = rs.getInt("author_id");
			Date pub_date = rs.getDate("pub_date");
			Book book = new Book();
			book.setId(id);
			book.setName(name);
			book.setPrice(price);
			book.setAutuor_id(authour_id);
			book.setPub_date(pub_date.toLocalDate());
			booklist.add(book);
		}
		System.out.println(booklist);
		for (Book book : booklist) {
			System.out.println(book);
		}
		return booklist;

	}

}
