package com.student.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.student.bean.Student;

/**
 * StudentMapper鏁版嵁搴撴槧灏�
 * @ClassName StudentMapper
 */

public class StudentMapper implements RowMapper<Student> {

	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student();

		student.setId(rs.getInt(1));
		student.setName(rs.getString(2));

		student.setBirthday(getStringDate(rs.getString(3)));
		student.setAge(rs.getInt(4));
		student.setScore(rs.getDouble(5));
		student.setSex(rs.getString(6));

		student.setAddress(rs.getString(7));
		return student;
	}

	/**
	   * 鑾峰彇鏃ユ湡
	   * @return 杩斿洖鏃ユ湡瀛楃涓叉牸寮弝yyy-MM-dd
	   * @throws ParseException
	   */
	private String getStringDate(String birthday) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateString = new Date();
		String strRtnDate = "";
		try {
			dateString = formatter.parse(birthday);
			strRtnDate = new SimpleDateFormat("yyyy-MM-dd").format(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return strRtnDate;
	}

}