package com.hdev.transaction_service.entity;

import com.hdev.transaction_service.exception.TransactionStatusException;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long payer_id;
    private Long payee_id;
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status = TransactionStatus.PENDING;

    @CreatedDate
    private Instant created_at;

    public Transaction() {
    }

    public Transaction(UUID id, Long payer_id, Long payee_id, BigDecimal amount, TransactionStatus status, Instant created_at) {
        this.id = id;
        this.payer_id = payer_id;
        this.payee_id = payee_id;
        this.amount = amount;
        this.status = status;
        this.created_at = created_at;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getPayer_id() {
        return payer_id;
    }

    public void setPayer_id(Long payer_id) {
        this.payer_id = payer_id;
    }

    public Long getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(Long payee_id) {
        this.payee_id = payee_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatusAuthorized(){
        checkIfTransactionIsPending();
        this.status = TransactionStatus.AUTHORIZED;
    }

    public void setStatusSettled(){
        checkIfTransactionIsAuthorized();
        this.status = TransactionStatus.SETTLED;
    }

    public void setStatusRejected(){
        checkIfTransactionIsAuthorized();
        this.status = TransactionStatus.REJECTED;
    }

    public void setStatusRefunded(){
        checkIfTransactionIsAuthorized();
        this.status = TransactionStatus.REFUNDED;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    private void checkIfTransactionIsPending(){
        if(!status.equals(TransactionStatus.PENDING)){
            throw new TransactionStatusException();
        }
    }

    private void checkIfTransactionIsAuthorized(){
        if(!status.equals(TransactionStatus.AUTHORIZED)){
            throw new TransactionStatusException();
        }
    }
}
