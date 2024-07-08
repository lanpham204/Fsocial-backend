package com.fsocial.services.interfaces;

import com.fsocial.dtos.NotificationDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getAll();
    Notification getById(String id) throws DataNotFoundException;
    Notification create(NotificationDTO notificationsDTO);
    Notification update(NotificationDTO notificationsDTO, String id) throws DataNotFoundException ;
    void  delete(String id) throws DataNotFoundException;

}
