package com.student.dao;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.student.bean.Student;
import com.student.mapper.StudentMapper;

public class StudentDao {

	/**
	 * @Fields jdbcTemplate
	 */
	private JdbcTemplate jdbcTemplate;

	/**
	 * spring鎻愪緵鐨勭被
	 *
	 * @param jdbcTemplate
	 * 杩斿洖鍊肩被鍨嬶細 void
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 鏌ヨ鎵�鏈夊鐢�
	 * @return 杩斿洖鍊肩被鍨嬶細 List<Student>
	 */
	public List<Student> queryAll() {
		String sql = "select id,name,birthday,age,score,sex,tel from student";
		//灏嗘煡璇㈢粨鏋滄槧灏勫埌Student绫讳腑锛屾坊鍔犲埌list涓紝骞惰繑鍥�
		return jdbcTemplate.query(sql, new StudentMapper());
	}

	/**
	 * 閫氳繃濮撳悕鏌ヨ
	 * @param name
	 * @return 杩斿洖鍊肩被鍨嬶細 List<Student>
	 */
	public List<Student> queryByName(String name) {
		String sql = "select id,name,birthday,age,score  from student where name like '%" + name + "%'";

		return jdbcTemplate.query(sql, new StudentMapper());
	}

	/**
	 * 娣诲姞瀛︾敓
	 * @param student
	 * @return 杩斿洖鍊肩被鍨嬶細 boolean
	 */
	public boolean addStu(Student student) {
		String sql = "insert into student(id,name,birthday,age,score,sex,tel) values(0,?,?,?,?,?,?)";

		return jdbcTemplate.update(sql,
				new Object[] { student.getName(), student.getBirthday(),student.getAge(),
						student.getScore() ,  student.getSex(),student.getTel()},
				new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.DOUBLE , Types.INTEGER,Types.VARCHAR}) == 1;
	}

	/**
	 * 鍒犻櫎瀛︾敓
	 * @param id
	 * @return 杩斿洖鍊肩被鍨嬶細 boolean
	 */
	public boolean deleteStu(Integer id) {

		String sql = "delete from student where id = ?";
		return jdbcTemplate.update(sql, id) == 1;
	}

	/**
	 * 鏇存柊瀛︾敓淇℃伅
	 * @param student
	 * @return 杩斿洖鍊肩被鍨嬶細 boolean
	 */
	public boolean updateStu(Student student) {

		String sql = "update student set name=? ,age=?,birthday = ? ,score = ? where id = ?";
		Object stuObj[] = new Object[] {student.getName(), student.getAge(), student.getBirthday(),
				student.getScore(), student.getId() };

		return jdbcTemplate.update(sql, stuObj) == 1;
	}

}
