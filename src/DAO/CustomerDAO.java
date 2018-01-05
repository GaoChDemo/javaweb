package DAO;
import java.sql.*;
import java.util.*;



import bean.Customer;

import db.DbCon;

// 数据库操作
public class CustomerDAO implements UserDAO{
	public Connection conn=null;

	public CustomerDAO() {
		conn=new DbCon().getConnection();
	}

	public String login(Customer user){
		String sql="select * from iuser where username ='"+user.getUsername() +"'";
		String flag = "-1";
		String userpower = new String();
		String userpasswd = new String();
		String input = new String();
		try{			

			Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				input = user.getUserpasswd().trim();
				userpasswd = rs.getString("userpasswd").trim();
				System.out.println(userpasswd);
				if(input.equals(userpasswd))
				{
					flag = rs.getString("userpower").trim();System.out.println("ok");
				}
			}

			rs.close();
			stmt.close();
			conn.close();


		}catch(SQLException e){e.printStackTrace();
		}
		return flag;

	}

	public Customer getUserByuserUsername(String username){
		String sql="select * from iuser where username ='"+username +"'";
		Customer userInfo=null;
		try{			

			Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				userInfo=new Customer();
				userInfo.setUsername(rs.getString("username").trim());
				userInfo.setUserpasswd(rs.getString("userpasswd").trim());
				userInfo.setUserpower(rs.getString("userpower").trim());
				userInfo.setUsermoney(rs.getString("Usermoney").trim());
			}
			rs.close();
			stmt.close();
			conn.close();

		}catch(SQLException e){e.printStackTrace();
		}
		return userInfo;

	}

	public Customer getUserBypayid(String payid){
		String sql="select * from pay where payid ='"+payid +"'";
		Customer userInfo=null;
		String paytype = new String();
		try{			

			Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				userInfo=new Customer();
				userInfo.setPayid(rs.getString("payid").trim());
				userInfo.setUsername(rs.getString("username").trim());
				userInfo.setPaytime(rs.getString("paytime").trim());
				paytype = rs.getString("paytype").trim();
				if (paytype.equals("0"))
				{
					userInfo.setPaytype("存款");
				}
				else if (paytype.equals("1"))
				{
					userInfo.setPaytype("取款");
				}
				else if (paytype.equals("2"))
				{
					userInfo.setPaytype("缴纳水电费");
				}
				else if (paytype.equals("3"))
				{
					userInfo.setPaytype("缴纳电话费");
				}
				userInfo.setIncome_money(rs.getString("income_money").trim());
			}
			rs.close();
			stmt.close();
			conn.close();

		}catch(SQLException e){e.printStackTrace();
		}
		return userInfo;

	}

	public boolean updateUser(Customer user){
		String sql="update iuser set userpasswd=? where username=?";
		boolean flag=false;
		int count=0;
		PreparedStatement stmt=null;
		try{	

			stmt=conn.prepareStatement(sql);
			stmt.setString(1, user.getUserpasswd().trim());
			stmt.setString(2, user.getUsername().trim());
			count=stmt.executeUpdate();
			System.out.println(user.getUsername().trim()+user.getUserpasswd().trim());
			conn.commit();
			stmt.close();
			conn.close();

		}catch(SQLException e){
			e.printStackTrace();
			//flag=false;
		}
		if(count==0)
			flag=false;
		else flag=true;

		return flag;
	}

	public Customer selectmoneybyname(Customer user){
		String sql="select * from iuser where username ='"+user.getUsername() +"'";
		boolean flag=false;
		int count=0;
		Customer userInfo = new Customer();
		try{			

			Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				userInfo.setUsermoney(rs.getString("usermoney").trim());
				userInfo.setUsername(rs.getString("username").trim());
			}
			conn.commit();
			stmt.close();
			

		}catch(SQLException e){e.printStackTrace();
		}
		return userInfo;
	}
	
	public Customer selectmoneybypayid(Customer user){
		String sql="select * from pay where payid ='"+user.getPayid() +"'";
		boolean flag=false;
		int count=0;
		Customer userInfo = new Customer();
		try{			

			Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				userInfo.setIncome_money(rs.getString("income_money").trim());
				userInfo.setUsername(rs.getString("username").trim());
				userInfo.setPaytype(rs.getString("paytype").trim());
			}
			conn.commit();
			stmt.close();

		}catch(SQLException e){e.printStackTrace();
		}
		return userInfo;
	}

	public boolean delpaym(Customer user){
		Customer id_money = selectmoneybypayid(user);
		Customer name_money = selectmoneybyname(id_money);
		
		int omoney =  Integer.parseInt(name_money.getUsermoney().trim());
		int nmoney =  Integer.parseInt(id_money.getIncome_money().trim());
		int money = 0;
		
		if(id_money.getPaytype().trim().equals("0")){
			money = omoney - nmoney;
		}
		else
		{
			money = omoney + nmoney;
		}
		
		if(money<0){
			return false;
		}

		String sql="update iuser set usermoney=? where username=?";
		boolean flag=false;
		int count=0;
		PreparedStatement stmt=null;
		try{	

			stmt=conn.prepareStatement(sql);
			stmt.setString(1, String.valueOf(money));
			stmt.setString(2, id_money.getUsername().trim());
			count=stmt.executeUpdate();
			conn.commit();
			stmt.close();

		}catch(SQLException e){
			e.printStackTrace();
			//flag=false;
		}
		if(count==0)
			flag=false;
		else flag=true;

		return flag;
		
	}

	public boolean delpay(Customer user){
		String sql="delete from pay where payid=?";
		boolean flag=false;
		PreparedStatement stmt=null;
		try{	
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, user.getPayid().trim());
			stmt.executeUpdate();	
			flag=true;
			stmt.close();
			conn.close();

		}catch(SQLException e){
			e.printStackTrace();
			//flag=false;
		}

		return flag;
	}
	
	public boolean deluserm(Customer user){
		String sql="delete from pay where username=?";
		boolean flag=false;
		PreparedStatement stmt=null;
		try{	
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername().trim());
			stmt.executeUpdate();	
			flag=true;
			stmt.close();

		}catch(SQLException e){
			e.printStackTrace();
			//flag=false;
		}

		return flag;
	}

	public boolean deluser(Customer user){
		deluserm(user);
		String sql="delete from iuser where username=?";
		boolean flag=false;
		PreparedStatement stmt=null;
		try{	
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername().trim());
			stmt.executeUpdate();	
			flag=true;
			stmt.close();
			conn.close();

		}catch(SQLException e){
			e.printStackTrace();
			//flag=false;
		}

		return flag;
	}
	
	public boolean insertPaym(Customer user){
		Customer name_money = selectmoneybyname(user);
		
		int omoney =  Integer.parseInt(name_money.getUsermoney().trim());
		int nmoney =  Integer.parseInt(user.getIncome_money());
		int money = 0;
		
		if(user.getPaytype().trim().equals("0")){
			money = omoney + nmoney;
		}
		else
		{
			money = omoney - nmoney;
		}
		
		if(money<0){
			return false;
		}

		String sql="update iuser set usermoney=? where username=?";
		boolean flag=false;
		int count=0;
		PreparedStatement stmt=null;
		try{	

			stmt=conn.prepareStatement(sql);
			stmt.setString(1, String.valueOf(money));
			stmt.setString(2, user.getUsername().trim());
			count=stmt.executeUpdate();
			conn.commit();
			stmt.close();

		}catch(SQLException e){
			e.printStackTrace();
			//flag=false;
		}
		if(count==0)
			flag=false;
		else flag=true;

		return flag;
		
	}
	
	public boolean updatePaym(Customer user){
		String newmoney = user.getIncome_money();
		int inewmoney = Integer.parseInt(newmoney);
		Customer id_money = selectmoneybypayid(user);
		Customer name_money = selectmoneybyname(id_money);
		
		int omoney =  Integer.parseInt(name_money.getUsermoney().trim());
		int nmoney =  Integer.parseInt(id_money.getIncome_money().trim());
		int money = 0;
		
		if(id_money.getPaytype().trim().equals("0")){
			omoney = omoney + inewmoney - nmoney ;
		}
		else
		{
			omoney = omoney - inewmoney + nmoney ;
		}
		
		if(omoney<0){
			return false;
		}

		String sql="update iuser set usermoney=? where username=?";
		boolean flag=false;
		int count=0;
		PreparedStatement stmt=null;
		try{	

			stmt=conn.prepareStatement(sql);
			stmt.setString(1, String.valueOf(omoney));
			stmt.setString(2, id_money.getUsername().trim());
			count=stmt.executeUpdate();
			conn.commit();
			stmt.close();

		}catch(SQLException e){
			e.printStackTrace();
			//flag=false;
		}
		if(count==0)
			flag=false;
		else flag=true;

		return flag;
	}


	public boolean updatePay(Customer user){
		String sql="update pay set username=?,paytime=?,paytype=?,income_money=? where payid=?";
		boolean flag=false;
		String paytype = new String();
		int count=0;
		PreparedStatement stmt=null;
		try{	

			stmt=conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername().trim());
			stmt.setString(2, user.getPaytime().trim());
			paytype = user.getPaytype().trim();
			System.out.println(paytype);
			if (paytype.equals("存款"))
			{
				stmt.setString(3,"0");
			}
			else if (paytype.equals("取款"))
			{
				stmt.setString(3,"1");
			}
			else if (paytype.equals("缴纳水电费"))
			{
				stmt.setString(3,"2");
			}
			else if (paytype.equals("缴纳电话费"))
			{
				stmt.setString(3,"3");
			}
			stmt.setString(4, user.getIncome_money().trim());
			stmt.setString(5, user.getPayid().trim());
			count=stmt.executeUpdate();
			conn.commit();
			stmt.close();
			conn.close();

		}catch(SQLException e){
			e.printStackTrace();
			//flag=false;
		}
		if(count==0)
			flag=false;
		else flag=true;

		return flag;
	}


	public boolean insertPay(Customer user){
		String sql="INSERT INTO pay(payid,username,paytime,paytype,income_money) VALUES (?,?,?,?,?);";
		boolean flag=false;
		int count=0;
		PreparedStatement stmt=null;
		try{	

			stmt=conn.prepareStatement(sql);
			stmt.setString(1, user.getPayid().trim());
			stmt.setString(2, user.getUsername().trim());
			stmt.setString(3, user.getPaytime().trim());
			stmt.setString(4, user.getPaytype().trim());
			stmt.setString(5, user.getIncome_money().trim());
			count=stmt.executeUpdate();
			conn.commit();
			stmt.close();
			conn.close();

		}catch(SQLException e){
			e.printStackTrace();
			//flag=false;
		}
		if(count==0)
			flag=false;
		else flag=true;

		return flag;
	}

	public boolean register(Customer user){
		String sql="INSERT INTO iuser(username,userpasswd,userpower,usermoney) VALUES (?,?,'0', '0');";
		boolean flag=false;
		int count=0;
		PreparedStatement stmt=null;
		try{	

			stmt=conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername().trim());
			stmt.setString(2, user.getUserpasswd().trim());
			count=stmt.executeUpdate();
			conn.commit();
			stmt.close();
			conn.close();

		}catch(SQLException e){
			e.printStackTrace();
			//flag=false;
		}
		if(count==0)
			flag=false;
		else flag=true;

		return flag;
	}

	public ArrayList getAll(){
		String sql="select * from pay;";
		String paytype= new String();
		ArrayList<Customer> list=new ArrayList();
		Customer userInfo=null;
		try{

			Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs=stmt.executeQuery(sql);

			while(rs.next()){
				userInfo=new Customer();
				userInfo.setPayid(rs.getString("payid").trim());
				userInfo.setUsername(rs.getString("username").trim());
				userInfo.setPaytime(rs.getString("paytime").trim());
				paytype = rs.getString("paytype").trim();
				if (paytype.equals("0"))
				{
					userInfo.setPaytype("存款");
				}
				else if (paytype.equals("1"))
				{
					userInfo.setPaytype("取款");
				}
				else if (paytype.equals("2"))
				{
					userInfo.setPaytype("缴纳水电费");
				}
				else if (paytype.equals("3"))
				{
					userInfo.setPaytype("缴纳电话费");
				}
				userInfo.setIncome_money(rs.getString("income_money").trim());
				list.add(userInfo);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException e){e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList getAllu(String username){
		String sql="select * from pay where username = '"+username +"';";
		String paytype= new String();
		ArrayList<Customer> list=new ArrayList();
		Customer userInfo=null;
		try{

			Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs=stmt.executeQuery(sql);

			while(rs.next()){
				userInfo=new Customer();
				userInfo.setPayid(rs.getString("payid").trim());
				userInfo.setUsername(rs.getString("username").trim());
				userInfo.setPaytime(rs.getString("paytime").trim());
				paytype = rs.getString("paytype").trim();
				if (paytype.equals("0"))
				{
					userInfo.setPaytype("存款");
				}
				else if (paytype.equals("1"))
				{
					userInfo.setPaytype("取款");
				}
				else if (paytype.equals("2"))
				{
					userInfo.setPaytype("缴纳水电费");
				}
				else if (paytype.equals("3"))
				{
					userInfo.setPaytype("缴纳电话费");
				}
				userInfo.setIncome_money(rs.getString("income_money").trim());
				list.add(userInfo);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException e){e.printStackTrace();
		}
		return list;
	}

	public ArrayList getAlluser(){
		String sql="select * from iuser;";
		String paytype= new String();
		ArrayList<Customer> list=new ArrayList();
		Customer userInfo=null;
		try{

			Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs=stmt.executeQuery(sql);

			while(rs.next()){
				userInfo=new Customer();
				userInfo.setUsername(rs.getString("username").trim());
				userInfo.setUserpasswd(rs.getString("userpasswd").trim());
				userInfo.setUsermoney(rs.getString("usermoney").trim());
				if(rs.getString("userpower").trim().equals("0") ){
					list.add(userInfo);
				}
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException e){e.printStackTrace();
		}
		return list;

	}

}
