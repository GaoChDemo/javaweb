package daoFactory;

import DAO.CustomerDAO;
import DAO.UserDAO;

public class UserDAOFactory { //ʵ�ֽӿ�

   public static UserDAO getUserDAOInstance(){
	   return new CustomerDAO();
   }
}
