package views;

import java.util.List;

import Objects.User;
import modelDAO.UserDao;

public class HibernateApp {

	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		/*
		 * User user5 = new User("pol", "pol@hotmail.com", "123");
		 * userDao.saveUser(user5); User user6 = new User("jesus", "jesus@hotmail.com",
		 * "123"); userDao.saveUser(user6);
		 */
		User user3 = userDao.getUserById(1);
		User user4 = userDao.getUserByName("lady"); //Esto no va
		System.out.println(user3);
		System.out.println(user4);
		
		List <User>users = userDao.getAllUsers();
		
		for(User element: users) {
			System.out.println(element);
		}
		
		
	}

}
