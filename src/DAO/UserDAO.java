package DAO;

import java.sql.Connection;
import java.util.ArrayList;

import bean.Customer;
// �ӿ�
public interface UserDAO { //�ӿ�
	public Customer getUserByuserUsername(String username); //ͨ���û�����ȡ�û���Ϣ
	public boolean updateUser(Customer user);// �����û���Ϣ
	public boolean insertPay(Customer user); //�����˵���Ϣ
	public ArrayList getAll(); //��ȡ�����˵���Ϣ
	public ArrayList getAlluser(); //��ȡ�����û���Ϣ
	public Customer getUserBypayid(String payid);//ͨ���˵��Ż�ȡ�˵���Ϣ
	public String login(Customer user);//��֤��¼��Ϣ
	public boolean register(Customer user);//ע��
	public boolean updatePay(Customer user);//�����˵���Ϣ
	public boolean delpay(Customer user);//ɾ���˵���Ϣ
	public boolean deluser(Customer user);//ɾ���û���Ϣ
	public Customer selectmoneybyname(Customer user);//ͨ���û�����ѯ���
	public Customer selectmoneybypayid(Customer user);//ͨ���˵��Ų�ѯ�˵��Ľ��
	public ArrayList getAllu(String username);//��ȡ���û����˵���Ϣ
	public boolean delpaym(Customer user);//ɾ���˵���Ϣǰ���Ľ��
	public boolean deluserm(Customer user);//ɾ���û�ǰɾ���˵�
	public boolean insertPaym(Customer user);//�����˵���Ϣǰ���Ľ��
	public boolean updatePaym(Customer user);//�����˵���Ϣǰ���Ľ��
}