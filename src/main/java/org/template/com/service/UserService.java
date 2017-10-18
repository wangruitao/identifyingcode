package org.template.com.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.template.com.model.Users;

@Service
public interface UserService extends  UserDetailsService {

	public boolean insert(String name);

	public Users queryEntry(Long id);
	
	public boolean delete(Long id);

	public Long insertModel(Users us);
	
	public Users update(Users us);
}
