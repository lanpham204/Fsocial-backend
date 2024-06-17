package com.fsocial.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("majors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Major {
    @Id
    private String id;
    private String name;

}
