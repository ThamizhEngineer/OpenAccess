package com.ss.oa.master.user;
import java.util.List;

import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.master.vo.User;

@Component
@Scope("prototype")
public class UserService {
	
	@Autowired
	UserDao dao;
	
	public UserService(){
		super();
	}
	public List<User> getAllUser(Map<String, String> criteria)
	
	{
		return dao.getAllUser(criteria);
	} 
	public User getUserById(String id)
	{
		return dao.getUserById(id);
	}
	public String addUser(User user)
	{
		return dao.addUser(user);
	}
	public String updateUser(String id,User user)
	{
		return dao.updateUser(id, user);
	}
	public String deleteUser(String id)
	{
		return dao.deleteUser(id);
	}
}
