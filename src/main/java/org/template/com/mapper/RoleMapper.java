package org.template.com.mapper;

import java.util.List;

import org.template.com.model.Role;

public interface RoleMapper {

	public List<Role> loadRolesByUserName(String username);
}
