package util;

import java.sql.Connection;

public class Test {

	public static <JdbcTemplate> void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        Connection con = ConnectionUtil.getConnection();
        System.out.println(con);
         
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ConnectionUtil.getJdbcTemplate();
        System.out.println(jdbcTemplate);
 
  
         
    }


}
