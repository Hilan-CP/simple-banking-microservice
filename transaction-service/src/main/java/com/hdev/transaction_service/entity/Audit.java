package com.hdev.transaction_service.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_audit")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID transaction_id;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status_from;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status_to;

    @CreatedDate
    private Instant change_timestamp;

    public Audit() {
    }

    public Audit(Long id, UUID transaction_id, TransactionStatus status_from, TransactionStatus status_to, Instant change_timestamp) {
        this.id = id;
        this.transaction_id = transaction_id;
        this.status_from = status_from;
        this.status_to = status_to;
        this.change_timestamp = change_timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(UUID transaction_id) {
        this.transaction_id = transaction_id;
    }

    public TransactionStatus getStatus_from() {
        return status_from;
    }

    public void setStatus_from(TransactionStatus status_from) {
        this.status_from = status_from;
    }

    public TransactionStatus getStatus_to() {
        return status_to;
    }

    public void setStatus_to(TransactionStatus status_to) {
        this.status_to = status_to;
    }

    public Instant getChange_timestamp() {
        return change_timestamp;
    }

    public void setChange_timestamp(Instant change_timestamp) {
        this.change_timestamp = change_timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Audit audit = (Audit) o;
        return Objects.equals(id, audit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
