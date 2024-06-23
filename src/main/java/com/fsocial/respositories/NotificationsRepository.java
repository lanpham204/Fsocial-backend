package com.fsocial.respositories;

import com.fsocial.models.Notifications;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationsRepository extends MongoRepository<Notifications,String> {
}
