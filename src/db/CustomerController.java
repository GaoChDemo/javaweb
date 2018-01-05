package db;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.impl.protocol.RequestDispatcherRegistryImpl;

import daoFactory.UserDAOFactory;

import DAO.CustomerDAO;
import DAO.UserDAO;
import bean.Customer;

import java.util.*;

public class CustomerController extends HttpServlet { //servlet

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//对用户提交的请求用utf-8来解码，否则会出现乱码
		request.setCharacterEncoding("utf-8");
		//获取用户请求的资源路径
		String actionurl = request.getServletPath();
		System.out.println(actionurl);
		//根据用户提交请求资源路径不同调用不同的方法处理
		UserDAO dao = UserDAOFactory.getUserDAOInstance();
		HttpSession session = request.getSession(); //声明session

		if (actionurl.equals("/moduser.action")) {
			//把表单的数据存入到UserInfo类中，返回一个UserInfo对象
			Customer cus = new Customer();
			cus.setUsername(request.getParameter("username").trim());
			cus.setUserpasswd(request.getParameter("userpasswd").trim());

			//调用Operation.insertUserInfo方法将存入到UserInfo中的数据存入数据库
			boolean flag = dao.updateUser(cus);// 更改用户信息
			if(flag)
				System.out.println("成功");
			//判断rl的结果，来显示注册成功或者失败
			if (flag) {
				response.sendRedirect("./ok.jsp");
			} else {
				response.sendRedirect("./lost.jsp");
			}

		} 
		else if (actionurl.equals("/insert.action")) {
			//把表单的数据存入到UserInfo类中，返回一个UserInfo对象
			Customer cus = new Customer();
			cus.setPayid(request.getParameter("payid").trim());
			cus.setUsername(request.getParameter("username").trim());
			cus.setPaytime(request.getParameter("paytime").trim());
			cus.setPaytype(request.getParameter("paytype").trim());
			cus.setIncome_money(request.getParameter("income_money").trim());

			if(!dao.insertPaym(cus)){ //判断金额是否小于零
				response.sendRedirect("./nomoney.jsp");
			}else{			
				boolean flag = dao.insertPay(cus);//插入账单信息
				if(flag)
					System.out.println(request.getParameter("paytime").trim());
				//判断rl的结果，来显示注册成功或者失败
				if (flag) {
					response.sendRedirect("./ok.jsp");
				} else {
					response.sendRedirect("./lost.jsp");
				}
			}



		}
		else if (actionurl.equals("/login.action")) {
			//把表单的数据存入到UserInfo类中，返回一个UserInfo对象
			Customer cus = new Customer();
			cus.setUsername(request.getParameter("username").trim());
			cus.setUserpasswd(request.getParameter("userpasswd").trim());

			String flag = dao.login(cus);//验证登录信息
			if(flag.equals("-1")) //未登录
				response.sendRedirect("./loginok.jsp");
			else if(flag.equals("1")){ //超级用户
				session.setAttribute("username",cus.getUsername());
				response.sendRedirect("./indexa.jsp");
			}
			else{ //普通用户
				session.setAttribute("username",cus.getUsername());
				response.sendRedirect("./indexu.jsp");
			}
		}
		else if (actionurl.equals("/register.action")) {
			//把表单的数据存入到UserInfo类中，返回一个UserInfo对象
			Customer cus = new Customer();
			cus.setUsername(request.getParameter("username").trim());
			cus.setUserpasswd(request.getParameter("userpasswd").trim());

			boolean flag = dao.register(cus); //注册
			if(flag)
				response.sendRedirect("./indexok.jsp");
			else{
				response.sendRedirect("./indexlost.jsp");
			}


		}
		else if (actionurl.equals("/logout.action")) {
			//把表单的数据存入到UserInfo类中，返回一个UserInfo对象
			Customer cus = new Customer();

			session.removeAttribute("username"); //移除session 退出登录
			response.sendRedirect("./logoutok.jsp");



		}
		else if(actionurl.equals("/updateu.action")){ //修改用户密码
			//让学生完成modify.jsp及相关配置
			System.out.println(request.getParameter("username"));
			request.getRequestDispatcher("/moduser.jsp?username="+request.getParameter("username")).forward(request, response); // 传参跳转新页面
		}
		else if(actionurl.equals("/updatep.action")){ //修改账单信息
			//让学生完成modify.jsp及相关配置
			System.out.println(request.getParameter("payid"));
			request.getRequestDispatcher("/modpay.jsp?payid="+request.getParameter("payid")).forward(request, response);// 传参跳转新页面
		}
		else if(actionurl.equals("/modpay.action")){ //修改账单信息
			//让学生完成modify.jsp及相关配置
			Customer cus = new Customer();
			cus.setPayid(request.getParameter("payid").trim());
			cus.setUsername(request.getParameter("username").trim());
			cus.setPaytime(request.getParameter("paytime").trim());
			cus.setPaytype(request.getParameter("paytype").trim());
			cus.setIncome_money(request.getParameter("income_money").trim());

			if(!dao.updatePaym(cus)){ //验证金额是否小于零
				response.sendRedirect("./nomoney.jsp");
			}
			else{
				boolean flag = dao.updatePay(cus);//更新账单信息

				//判断rl的结果，来显示注册成功或者失败
				if (flag) {
					response.sendRedirect("./ok.jsp");
				} else {
					response.sendRedirect("./lost.jsp");
				}

			}

		}
		else if(actionurl.equals("/deletep.action")){  
			//让学生完成modify.jsp及相关配置
			Customer cus = new Customer();
			cus.setPayid(request.getParameter("payid").trim());

			if(!dao.delpaym(cus)){
				response.sendRedirect("./nomoney.jsp");
			}
			else{
				boolean flag = dao.delpay(cus); //删除账单信息
				System.out.println(request.getParameter("payid").trim());
				//判断rl的结果，来显示注册成功或者失败
				if (flag) {
					response.sendRedirect("./ok.jsp");
				} else {
					response.sendRedirect("./lost.jsp");
				}
			}



		}
		else if(actionurl.equals("/deleteu.action")){
			//让学生完成modify.jsp及相关配置
			Customer cus = new Customer();
			cus.setUsername(request.getParameter("username").trim());
			boolean flag = dao.deluser(cus);				//删除用户信息
			System.out.println(request.getParameter("username").trim());
			//判断rl的结果，来显示注册成功或者失败
			if (flag) {
				response.sendRedirect("./ok.jsp");
			} else {
				response.sendRedirect("./lost.jsp");
			}

		}
		else {
			System.out.println(actionurl);
		}

	}

}
