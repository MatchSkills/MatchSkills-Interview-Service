package com.matchskills.interview.service.entitys;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "jobposts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JobPostingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long companyId;
    private String title;
    private String description;
    private String local;

    @CreationTimestamp
    @Column(name = "create_at", updatable = false)
    private LocalDate createAt;

    @Column(columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Integer> targetSoftskills;

    private List<String> targetHardskills;


}
