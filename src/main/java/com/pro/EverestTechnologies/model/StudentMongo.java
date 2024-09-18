package com.pro.EverestTechnologies.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "StudentsList")
public class StudentMongo {
    @Id
    private String id;  // MongoDB ID, which is typically a String (ObjectId).
    private String name;
    private String email;
    private String address;

    @Override
    public String toString() {
        return "StudentMongo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
