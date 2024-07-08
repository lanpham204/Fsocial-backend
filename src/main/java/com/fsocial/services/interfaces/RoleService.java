package com.fsocial.services.interfaces;

import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Role;

import java.util.List;

public interface RoleService {
    Role create (Role role);
    List<Role> getAll();
    void Delete(String id);
    Role getRole(String id) throws DataNotFoundException;
}