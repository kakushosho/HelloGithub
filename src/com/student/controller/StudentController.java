package com.student.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.student.bean.Student;
import com.student.dao.StudentDao;

@Controller
public class StudentController {
	/**
	 *
	 * 浠庢暟鎹簱涓幏鍙栧叏閮ㄥ鐢熶俊鎭紝灏嗘暟鎹繑鍥炵粰涓婚〉index,jsp
	 * @param model
	 * @return 杩斿洖鍊肩被鍨嬶細 String
	 */
	@RequestMapping(value = "/all")
	public String queryAll(Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//浠巌oc瀹瑰櫒涓幏鍙杁ao
		StudentDao dao = (StudentDao) context.getBean("dao");
		model.addAttribute("students", dao.queryAll());

		return "index";
	}

	/**
	 * 閫氳繃濮撳悕鏌ユ壘瀛︾敓锛屼娇鐢ㄦā绯婃煡鎵撅紝灏嗙粨鏋滆繑鍥炵粰index.jsp
	 *
	 * @param name
	 * @param model
	 * @return 杩斿洖鍊肩被鍨嬶細 String
	 */
	@RequestMapping(value = "/queryByName")
	public String queryByName(String name, Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//浠巌oc瀹瑰櫒涓幏鍙杁ao
		StudentDao dao = (StudentDao) context.getBean("dao");
		model.addAttribute("students", dao.queryByName(name));
		return "index";
	}

	/**
	 * 娣诲姞鏂板鐢燂紝骞跺皢缁撴灉杩斿洖缁檌ndex椤甸潰锛岀敱index杞彂鍒颁富椤�
	 * @param name
	 * @param birthday
	 * @param age
	 * @param score
	 * @param model
	 * @return 杩斿洖鍊肩被鍨嬶細 String
	 */
	@RequestMapping(value = "/add")
	public String addStu(String name, String birthday, String age, String score,String sex, Model model) {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentDao dao = (StudentDao) context.getBean("dao");
		Student student = new Student();
		student.setName(name);
		student.setBirthday(birthday);
		student.setAge(Integer.valueOf(age));
		student.setScore(Double.parseDouble(score));
		student.setSex(sex);
		System.out.println(sex);
		model.addAttribute("students", dao.queryAll());
		boolean result = dao.addStu(student);

		if (result) {
			model.addAttribute("msg", "<script>alert('娣诲姞鎴愬姛锛�')</script>");
		} else {
			model.addAttribute("msg", "<script>alert('娣诲姞澶辨晽锛�')</script>");
		}

		model.addAttribute("students", dao.queryAll());
		return "index";
	}

	/**
	 * 閫氳繃id鍒犻櫎瀛︾敓
	 * @param id
	 * @param model
	 * @return 杩斿洖鍊肩被鍨嬶細 String
	 */
	@RequestMapping(value = "/deleteById")
	public String deleteById(String id, Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentDao dao = (StudentDao) context.getBean("dao");
		boolean result = dao.deleteStu(Integer.parseInt(id));

		if (result) {
			model.addAttribute("msg", msg("鍒犻櫎鎴愬姛锛�"));
		} else {
			model.addAttribute("msg", msg("鍒犻櫎澶辨晽锛�"));
		}

		model.addAttribute("students", dao.queryAll());
		return "index";
	}

	/**
	 *
	 * @param id
	 * @param name
	 * @param birthday
	 * @param age
	 * @param score
	 * @param model
	 * @return 杩斿洖鍊肩被鍨嬶細 String
	 */
	@RequestMapping(value = "/update")
	public String updateStu(String id, String name, String birthday, String age, String score, Model model) {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentDao dao = (StudentDao) context.getBean("dao");
		Student student = new Student();
		student.setId(Integer.parseInt(id));
		student.setName(name);
		student.setBirthday(birthday);
		student.setAge(Integer.valueOf(age));
		student.setScore(Double.parseDouble(score));
		boolean result = dao.updateStu(student);

		if (result) {
			model.addAttribute("msg", msg("淇敼鎴愬姛"));
		} else {
			model.addAttribute("msg", msg("淇敼澶辫触"));
		}

		model.addAttribute("students", dao.queryAll());
		return "index";
	}

	/**
	 * 瑕佸脊鍑虹殑椤甸潰娑堟伅
	 * @param msg
	 * @return 杩斿洖鍊肩被鍨嬶細 String
	 */
	public String msg(String msg) {
		return "<script>alert('" + msg + "')</script>";
	}
}
