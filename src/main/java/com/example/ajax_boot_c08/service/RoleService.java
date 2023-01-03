package com.example.ajax_boot_c08.service;

import com.example.ajax_boot_c08.model.Role;
import com.example.ajax_boot_c08.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private IRoleRepository iRoleRepository;

    public Role findByName(String name) {
        return iRoleRepository.findByName(name);
    }
}
