package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@AllArgsConstructor
public class BaseEntity {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }
}
