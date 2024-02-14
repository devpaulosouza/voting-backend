package dev.paulosouza.voting.repository;

import dev.paulosouza.voting.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, String> {
}
