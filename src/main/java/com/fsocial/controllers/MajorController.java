package com.fsocial.controllers;

import com.fsocial.dtos.MajorDTO;
import com.fsocial.models.Major;
import com.fsocial.services.MajorServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/majors")
@RequiredArgsConstructor
public class MajorController {
  private final MajorServiceImp majorServiceImp;

  @PostMapping
  public ResponseEntity<?> createMajor(@RequestBody MajorDTO majorDTO) {
    Major createdMajor = majorServiceImp.create(majorDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdMajor);
  }

  @GetMapping
  public ResponseEntity<List<Major>> getAllMajors() {
    List<Major> majors = majorServiceImp.getAll();
    return ResponseEntity.ok(majors);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getMajorById(@PathVariable String id) {
    try {
      Major major = majorServiceImp.getById(id);
      return ResponseEntity.ok(major);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateMajor(@RequestBody MajorDTO majorDTO, @PathVariable String id) {
    try {
      Major updatedMajor = majorServiceImp.update(majorDTO, id);
      return ResponseEntity.ok(updatedMajor);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteMajor(@PathVariable String id) {
    try {
      majorServiceImp.delete(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
