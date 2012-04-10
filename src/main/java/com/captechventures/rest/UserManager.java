package com.captechventures.rest;

import java.util.Collection;
import java.util.Hashtable;

import com.captechventures.schema.ObjectFactory;
import com.captechventures.schema.User;
import com.captechventures.schema.UserEntryListType;
import com.captechventures.schema.UserList;

public class UserManager {

	private static Hashtable<Integer, User> userMap = new Hashtable<Integer, User>();
	
	static {
		ObjectFactory objectFactory = new ObjectFactory();
		userMap.put(100, objectFactory.createUser());
		userMap.get(100).setUserId(100);
		userMap.get(100).setUserName("Fake 1");
		userMap.get(100).setShoeSize("8.5M");
		
		userMap.put(101, objectFactory.createUser());
		userMap.get(101).setUserId(101);
		userMap.get(101).setUserName("Fake 2");
		userMap.get(101).setShoeSize("12W");
	}
	
	public User lookupUser(Integer userId) {
		User User = userMap.get(userId);
		
		
		return User;
	}
	
	public UserList getUserList() {
		ObjectFactory objectFactory = new ObjectFactory();
		
		UserList userList = objectFactory.createUserList();
		
		Collection<User> values = userMap.values();
		
		for(User u : values) {
			UserEntryListType uelt = objectFactory.createUserEntryListType();
			uelt.setUserId(u.getUserId());
			uelt.setUserName(u.getUserName());
			userList.getEntry().add(uelt);
		}
		
		return userList;
	}
	
	public void addUser(User newUser) {
		
		userMap.put(newUser.getUserId(), newUser);
		
	}
	public void deleteUser(Integer delUser) {
		
		userMap.remove(delUser);
		
	}
}
