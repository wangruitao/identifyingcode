package org.template.com.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.template.com.common.config.SystemUserDetail;
import org.template.com.mapper.RoleMapper;
import org.template.com.mapper.UserMapper;
import org.template.com.model.Role;
import org.template.com.model.Users;
import org.template.com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	RoleMapper roleMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userMapper.loadUserByUsername(username);
		SystemUserDetail sud = new SystemUserDetail();
		if (user != null) {

			List<Role> roles = roleMapper.loadRolesByUserName(username);
			user.setRoles(roles);
		}
		BeanUtils.copyProperties(user, sud);
		return sud;
	}

	@Override
	public boolean insert(String name) {

		return userMapper.insert(name) > 0 ? true : false;
	}

//	@Cacheable(value = "users", key = "#id + '-users'")
	@Override
	public Users queryEntry(Long id) {
		return userMapper.getOne(id);
	}

//	@CacheEvict(value = "users", key = "#id + '-users'")
	@Override
	public boolean delete(Long id) {

		return userMapper.delete(id) > 0 ? true : false;
	}
//	@CachePut(value = "users", key = "#us.id + '-users'")
	@Override
	public Long insertModel(Users us) {
		userMapper.insertUser(us);
		Long id =  us.getId();
		return id;
	}

//	@CachePut(value = "users", key = "#us.id + '-users'")
	@Override
	public Users update(Users us) {
		boolean isSuc = userMapper.update(us) > 0 ? true : false;
		if(isSuc) {
			return queryEntry(us.getId());
		}
		return null;
	}
}
