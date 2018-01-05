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

		//���û��ύ��������utf-8�����룬������������
		request.setCharacterEncoding("utf-8");
		//��ȡ�û��������Դ·��
		String actionurl = request.getServletPath();
		System.out.println(actionurl);
		//�����û��ύ������Դ·����ͬ���ò�ͬ�ķ�������
		UserDAO dao = UserDAOFactory.getUserDAOInstance();
		HttpSession session = request.getSession(); //����session

		if (actionurl.equals("/moduser.action")) {
			//�ѱ������ݴ��뵽UserInfo���У�����һ��UserInfo����
			Customer cus = new Customer();
			cus.setUsername(request.getParameter("username").trim());
			cus.setUserpasswd(request.getParameter("userpasswd").trim());

			//����Operation.insertUserInfo���������뵽UserInfo�е����ݴ������ݿ�
			boolean flag = dao.updateUser(cus);// �����û���Ϣ
			if(flag)
				System.out.println("�ɹ�");
			//�ж�rl�Ľ��������ʾע��ɹ�����ʧ��
			if (flag) {
				response.sendRedirect("./ok.jsp");
			} else {
				response.sendRedirect("./lost.jsp");
			}

		} 
		else if (actionurl.equals("/insert.action")) {
			//�ѱ������ݴ��뵽UserInfo���У�����һ��UserInfo����
			Customer cus = new Customer();
			cus.setPayid(request.getParameter("payid").trim());
			cus.setUsername(request.getParameter("username").trim());
			cus.setPaytime(request.getParameter("paytime").trim());
			cus.setPaytype(request.getParameter("paytype").trim());
			cus.setIncome_money(request.getParameter("income_money").trim());

			if(!dao.insertPaym(cus)){ //�жϽ���Ƿ�С����
				response.sendRedirect("./nomoney.jsp");
			}else{			
				boolean flag = dao.insertPay(cus);//�����˵���Ϣ
				if(flag)
					System.out.println(request.getParameter("paytime").trim());
				//�ж�rl�Ľ��������ʾע��ɹ�����ʧ��
				if (flag) {
					response.sendRedirect("./ok.jsp");
				} else {
					response.sendRedirect("./lost.jsp");
				}
			}



		}
		else if (actionurl.equals("/login.action")) {
			//�ѱ������ݴ��뵽UserInfo���У�����һ��UserInfo����
			Customer cus = new Customer();
			cus.setUsername(request.getParameter("username").trim());
			cus.setUserpasswd(request.getParameter("userpasswd").trim());

			String flag = dao.login(cus);//��֤��¼��Ϣ
			if(flag.equals("-1")) //δ��¼
				response.sendRedirect("./loginok.jsp");
			else if(flag.equals("1")){ //�����û�
				session.setAttribute("username",cus.getUsername());
				response.sendRedirect("./indexa.jsp");
			}
			else{ //��ͨ�û�
				session.setAttribute("username",cus.getUsername());
				response.sendRedirect("./indexu.jsp");
			}
		}
		else if (actionurl.equals("/register.action")) {
			//�ѱ������ݴ��뵽UserInfo���У�����һ��UserInfo����
			Customer cus = new Customer();
			cus.setUsername(request.getParameter("username").trim());
			cus.setUserpasswd(request.getParameter("userpasswd").trim());

			boolean flag = dao.register(cus); //ע��
			if(flag)
				response.sendRedirect("./indexok.jsp");
			else{
				response.sendRedirect("./indexlost.jsp");
			}


		}
		else if (actionurl.equals("/logout.action")) {
			//�ѱ������ݴ��뵽UserInfo���У�����һ��UserInfo����
			Customer cus = new Customer();

			session.removeAttribute("username"); //�Ƴ�session �˳���¼
			response.sendRedirect("./logoutok.jsp");



		}
		else if(actionurl.equals("/updateu.action")){ //�޸��û�����
			//��ѧ�����modify.jsp���������
			System.out.println(request.getParameter("username"));
			request.getRequestDispatcher("/moduser.jsp?username="+request.getParameter("username")).forward(request, response); // ������ת��ҳ��
		}
		else if(actionurl.equals("/updatep.action")){ //�޸��˵���Ϣ
			//��ѧ�����modify.jsp���������
			System.out.println(request.getParameter("payid"));
			request.getRequestDispatcher("/modpay.jsp?payid="+request.getParameter("payid")).forward(request, response);// ������ת��ҳ��
		}
		else if(actionurl.equals("/modpay.action")){ //�޸��˵���Ϣ
			//��ѧ�����modify.jsp���������
			Customer cus = new Customer();
			cus.setPayid(request.getParameter("payid").trim());
			cus.setUsername(request.getParameter("username").trim());
			cus.setPaytime(request.getParameter("paytime").trim());
			cus.setPaytype(request.getParameter("paytype").trim());
			cus.setIncome_money(request.getParameter("income_money").trim());

			if(!dao.updatePaym(cus)){ //��֤����Ƿ�С����
				response.sendRedirect("./nomoney.jsp");
			}
			else{
				boolean flag = dao.updatePay(cus);//�����˵���Ϣ

				//�ж�rl�Ľ��������ʾע��ɹ�����ʧ��
				if (flag) {
					response.sendRedirect("./ok.jsp");
				} else {
					response.sendRedirect("./lost.jsp");
				}

			}

		}
		else if(actionurl.equals("/deletep.action")){  
			//��ѧ�����modify.jsp���������
			Customer cus = new Customer();
			cus.setPayid(request.getParameter("payid").trim());

			if(!dao.delpaym(cus)){
				response.sendRedirect("./nomoney.jsp");
			}
			else{
				boolean flag = dao.delpay(cus); //ɾ���˵���Ϣ
				System.out.println(request.getParameter("payid").trim());
				//�ж�rl�Ľ��������ʾע��ɹ�����ʧ��
				if (flag) {
					response.sendRedirect("./ok.jsp");
				} else {
					response.sendRedirect("./lost.jsp");
				}
			}



		}
		else if(actionurl.equals("/deleteu.action")){
			//��ѧ�����modify.jsp���������
			Customer cus = new Customer();
			cus.setUsername(request.getParameter("username").trim());
			boolean flag = dao.deluser(cus);				//ɾ���û���Ϣ
			System.out.println(request.getParameter("username").trim());
			//�ж�rl�Ľ��������ʾע��ɹ�����ʧ��
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
