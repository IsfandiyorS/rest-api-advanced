package com.epam.esm.entity;

import com.epam.esm.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Abstract class {@code AbstractDomain} represents to identify objects.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseAbstractDomain {

    @Id
    @GeneratedValue
    protected Long id;

    @CreatedDate
    protected LocalDateTime createDate = LocalDateTime.now();

    @LastModifiedDate
    protected LocalDateTime lastUpdateDate;

    @Column(columnDefinition = "NUMERIC default 0")
    protected State state = State.CREATED;
}

