package com.matchskills.interview.service.entitys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "softskills")
@NoArgsConstructor
@Getter
@Setter
public class SoftskillEntity {

    @Id
    private String id;
    private String name;
    private String description;
    private List<AnchorEntity> anchots;
    private List<String> questions;
}