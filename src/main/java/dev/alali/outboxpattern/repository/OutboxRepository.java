package dev.alali.outboxpattern.repository;

import dev.alali.outboxpattern.model.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxRepository extends JpaRepository<OutboxEvent, Long> {


}
