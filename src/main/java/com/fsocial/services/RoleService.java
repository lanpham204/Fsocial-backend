package com.fsocial.services;

import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Role;
import com.fsocial.respositories.RoleRepository;
import com.fsocial.services.interfaces.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role create(Role role) {
       return roleRepository.save(role);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public void Delete(String id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getRole(String id) throws DataNotFoundException {
        return roleRepository.findById(id).orElseThrow(()->new DataNotFoundException("Could not find Role with id: "+id));
    }
}
