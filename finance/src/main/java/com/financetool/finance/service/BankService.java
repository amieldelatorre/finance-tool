package com.financetool.finance.service;

import com.financetool.finance.dto.BankAccountCreateRequest;
import com.financetool.finance.dto.BankTransactionCreateRequest;
import com.financetool.finance.model.BankAccount;
import com.financetool.finance.model.BankTransaction;
import com.financetool.finance.model.BankTransactionType;
import com.financetool.finance.model.OccurrenceType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public interface BankService {
    BankAccount createBankAccount(BankAccountCreateRequest bankAccountCreateRequest);
    List<BankAccount> getAllBankAccounts();
    BankAccount getBankAccountById(Integer bankAccountId);
    List<BankAccount> getBankAccountByUserId(Integer userId);
    BankAccount updateBankAccount(Integer bankAccountId, BankAccountCreateRequest bankAccountCreateRequest);
    void deleteBankAccount(Integer bankAccountId);

    BankTransaction createBankTransaction(BankTransactionCreateRequest bankTransactionCreateRequest);
    List<BankTransaction> getAllBankTransactions();
    BankTransaction getBankTransactionById(Integer bankTransactionId);
    List<BankTransaction> getBankTransactionByUserid(Integer userId);
    List<BankTransaction> getBankTransactionByAccountId(Integer accountId);
    List<BankTransaction> getBankTransactionByBankTransactionType(BankTransactionType bankTransactionType);
    List<BankTransaction> getBankTransactionByTransactionDate(Date transactionDate);
    List<BankTransaction> getBankTransactionByOccurrenceType(OccurrenceType occurrenceType);
    List<BankTransaction> getBankTransactionByDateOfNextRecurrence(Date dateOfNextRecurrence);
    BankTransaction updateBankTransaction(Integer bankTransactionId, BankTransactionCreateRequest bankTransactionCreateRequest);
    void deleteBankTransaction(Integer bankTransactionId);
}
