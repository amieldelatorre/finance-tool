package com.financetool.finance.repository;

import com.financetool.finance.model.BankTransaction;
import com.financetool.finance.model.BankTransactionType;
import com.financetool.finance.model.OccurrenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction, Integer> {
    List<BankTransaction> findByUserId(Integer userId);
    List<BankTransaction> findByAccountId(Integer accountId);
    List<BankTransaction> findByBankTransactionType(BankTransactionType bankTransactionType);
    List<BankTransaction> findByTransactionDate(Date transactionDate);
    List<BankTransaction> findByCategory(String category);
    List<BankTransaction> findByOccurrenceType(OccurrenceType occurrenceType);
    List<BankTransaction> findByDateOfNextRecurrence(Date dateOfNextRecurrence);
}
