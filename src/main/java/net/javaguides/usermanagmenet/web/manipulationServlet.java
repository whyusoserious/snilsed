package net.javaguides.usermanagmenet.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.usermanagmenet.dao.UserDAO;
import net.javaguides.usermanagmenet.model.User;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Servlet implementation class manipulationServlet
 */
@WebServlet("/insert")
public class manipulationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manipulationServlet() {
        this.userDAO = new UserDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			try {
				mainPage(request, response);
				
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			insertSnils(request, response);
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
//		this.doGet(request, response);
//		try {
//			insertSnils(request, response);
//		} catch (SQLException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	private void mainPage (HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
				RequestDispatcher dispatcher = request.getRequestDispatcher("userForm.jsp");
				dispatcher.forward(request, response);
			}
	
	private void insertSnils(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String str_number = String.valueOf(request.getParameter("number"));
		String clear_number = str_number.replaceAll("-", "").replaceAll(" ", "");
        Long number = Long.parseLong(clear_number);
		String numbers[] = clear_number.split("");
		var checkValidate = validateSnils(numbers);
		if (checkValidate){
			List<User> listuser = userDAO.selectAllSnilses();
			//var containsSnils = listuser.contains(number);
			if (alreadySnilsed(number, listuser)) {
				User newUser = new User(number);
				userDAO.insertNumber(newUser);
				showMessageDialog(null, "СНИЛС успешно сохранён в базу данных");
				RequestDispatcher dispatcher = request.getRequestDispatcher("userForm.jsp");
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("userForm.jsp");
				dispatcher.forward(request, response);
				showMessageDialog(null, "СНИЛС уже добавлен в базу данных");
			}
			
		}
		else
		{
			showMessageDialog(null, "Неправильное контрольное число");
			RequestDispatcher dispatcher = request.getRequestDispatcher("userForm.jsp");
			dispatcher.forward(request, response);
		}
		
//		<display-name>WebApp</display-name>
//		<welcome-file-list>
//			<welcome-file>index.html</welcome-file>
//		</welcome-file-list>
//		<servlet-mapping>
//		    <servlet-name>mainServlet</servlet-name>
//		    <url-pattern>/insert</url-pattern>
//		</servlet-mapping>		
		
//		Integer number = Integer.valueOf(request.getParameter("number"));
//		User newUser = new User(number);
//		userDAO.insertNumber(newUser);
//		response.sendRedirect("userForm.jsp");
		
	}
	private boolean validateSnils(String[] snils) {
		boolean check = false;
		var sum = 0;
		
		for (int i = 0; i < 9; i++) {
			sum+= Integer.parseInt(snils[i]) * (9-i);
		}
		var checkDigit=0;
		if (sum<100) {
			checkDigit = sum;
		} else if (sum > 101) {
			checkDigit = Integer.valueOf(sum%101);
			if ((checkDigit == 100) ||(checkDigit == 101)) {
				checkDigit = 0;
			}
		}
		String i = snils[snils.length-2];
		String j = snils[snils.length-1];
		int k = Integer.valueOf(String.valueOf(i) + String.valueOf(j));
		if (checkDigit == k) {
			check = true;
		}
		return check;
	}
	
	private boolean alreadySnilsed(Long number, List<User> snilses) {
		var isAlreadySnilsed = true;
		for (User item : snilses) {
			if (item.getNumber() == number)
			{
				isAlreadySnilsed = false;
				break;
			}
		}
		
		return isAlreadySnilsed;
	}
	//zavtra libo v credy. generator v php. html + css -> php.
	//private void 
	
}
