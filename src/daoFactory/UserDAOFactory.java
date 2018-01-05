package daoFactory;

import DAO.CustomerDAO;
import DAO.UserDAO;

public class UserDAOFactory { //实现接口

   public static UserDAO getUserDAOInstance(){
	   return new CustomerDAO();
   }
}
