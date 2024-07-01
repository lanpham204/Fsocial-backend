package com.fsocial.controllers;

import com.fsocial.dtos.NotificationDTO;
import com.fsocial.services.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/notifications")
@RequiredArgsConstructor
public class NotificationController {
  private final NotificationService notificationService;

  @GetMapping
  public ResponseEntity<?> getAllNotification() {
    try {
      return ResponseEntity.ok(notificationService.getAll());
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getNotificationById(@PathVariable String id) {
    try {
      return ResponseEntity.ok(notificationService.getById(id));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity<?> createNotification(@RequestBody NotificationDTO notificationDTO) {
    try {
      return ResponseEntity.ok(notificationService.create(notificationDTO));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateNotification(@RequestBody NotificationDTO notificationDTO, @PathVariable String id) {
    try {
      return ResponseEntity.ok(notificationService.update(notificationDTO, id));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteNotification(@PathVariable String id) {
    try {
      notificationService.delete(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
