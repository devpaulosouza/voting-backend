package dev.paulosouza.voting.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "options")
public class Option extends AuditedEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251209L;

    @Id
    private String username;

    private String name;

}
