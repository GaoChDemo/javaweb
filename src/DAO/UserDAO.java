package DAO;

import java.sql.Connection;
import java.util.ArrayList;

import bean.Customer;
// 接口
public interface UserDAO { //接口
	public Customer getUserByuserUsername(String username); //通过用户名获取用户信息
	public boolean updateUser(Customer user);// 更改用户信息
	public boolean insertPay(Customer user); //插入账单信息
	public ArrayList getAll(); //获取所有账单信息
	public ArrayList getAlluser(); //获取所有用户信息
	public Customer getUserBypayid(String payid);//通过账单号获取账单信息
	public String login(Customer user);//验证登录信息
	public boolean register(Customer user);//注册
	public boolean updatePay(Customer user);//更新账单信息
	public boolean delpay(Customer user);//删除账单信息
	public boolean deluser(Customer user);//删除用户信息
	public Customer selectmoneybyname(Customer user);//通过用户名查询金额
	public Customer selectmoneybypayid(Customer user);//通过账单号查询账单的金额
	public ArrayList getAllu(String username);//获取该用户的账单信息
	public boolean delpaym(Customer user);//删除账单信息前更改金额
	public boolean deluserm(Customer user);//删除用户前删除账单
	public boolean insertPaym(Customer user);//插入账单信息前更改金额
	public boolean updatePaym(Customer user);//更改账单信息前更改金额
}