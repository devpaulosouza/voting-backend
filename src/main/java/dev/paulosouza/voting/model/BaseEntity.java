package dev.paulosouza.voting.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class BaseEntity extends AuditedEntity {

	@Id
	private UUID id;

	@PrePersist
	private void perPersist() {
		if (Objects.isNull(this.id)) {
			this.id = UUID.randomUUID();
		}
	}

}
