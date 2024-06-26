package com.fsocial.services.interfaces;

import com.fsocial.dtos.NotificationDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.responses.NotificationResponse;

import java.util.List;

public interface NotificationService {
    List<NotificationResponse> getAll();
    NotificationResponse getById(String id) throws DataNotFoundException;
    NotificationResponse create(NotificationDTO notificationsDTO) throws DataNotFoundException;
    NotificationResponse update(NotificationDTO notificationsDTO, String id) throws DataNotFoundException ;
    void  delete(String id) throws DataNotFoundException;
}
