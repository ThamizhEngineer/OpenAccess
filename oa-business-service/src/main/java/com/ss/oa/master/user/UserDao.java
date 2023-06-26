package com.ss.oa.master.user;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.master.vo.User;
@Scope("prototype")
public interface UserDao {
	public abstract List<User> getAllUser(Map<String, String> criteria);
	public abstract User getUserById(String id);
	public abstract String addUser(User user);
	public abstract String updateUser(String id,User user);
	public abstract String deleteUser(String id);
	

}
