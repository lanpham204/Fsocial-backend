package com.fsocial.controllers;

import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Role;
import com.fsocial.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/roles")
public class RoleController {
  private final RoleService roleService;

  @GetMapping
  public ResponseEntity<List<Role>> getAllRoles() {
    return ResponseEntity.ok(roleService.getAll());
  }

  @PostMapping
  public ResponseEntity<Role> createRole(@RequestBody Role role) {
    return ResponseEntity.status(HttpStatus.CREATED).body(roleService.create(role));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Role> deleteRole(@PathVariable String id) {
    roleService.Delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("{id}")
  public ResponseEntity<Role> getRole(@PathVariable String id) throws DataNotFoundException {
    return ResponseEntity.ok(roleService.getRole(id));
  }
}
