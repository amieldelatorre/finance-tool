package com.financetool.finance.service;

import com.financetool.finance.dto.BankAccountCreateRequest;
import com.financetool.finance.dto.BankTransactionCreateRequest;
import com.financetool.finance.exception.ResourceNotFoundException;
import com.financetool.finance.model.BankAccount;
import com.financetool.finance.model.BankTransaction;
import com.financetool.finance.model.BankTransactionType;
import com.financetool.finance.model.OccurrenceType;
import com.financetool.finance.repository.BankAccountRepository;
import com.financetool.finance.repository.BankTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    @Override
    public BankAccount createBankAccount(BankAccountCreateRequest bankAccountCreateRequest) {
        BankAccount newBankAccount = new BankAccount();

        newBankAccount.setName(bankAccountCreateRequest.getName());
        newBankAccount.setUserId(bankAccountCreateRequest.getUserId());
        newBankAccount.setValue(bankAccountCreateRequest.getValue());
        newBankAccount.setDateOpened(bankAccountCreateRequest.getDateOpened());
        bankAccountRepository.save(newBankAccount);

        return newBankAccount;
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public Optional<BankAccount> getBankAccountById(Integer bankAccountId) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(bankAccountId);

        if (!bankAccount.isPresent())
            throw new ResourceNotFoundException("Bank Account id " + bankAccountId + " cannot be found");

        return bankAccount;
    }

    @Override
    public List<BankAccount> getBankAccountByUserId(Integer userId) {
        return bankAccountRepository.findByUserId(userId);
    }

    @Override
    public Optional<BankAccount> updateBankAccount(Integer bankAccountId, BankAccountCreateRequest bankAccountCreateRequest) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(bankAccountId);

        if (!bankAccount.isPresent())
            throw new ResourceNotFoundException("Bank Account id " + bankAccountId + " cannot be found");

        bankAccount.get().setName(bankAccountCreateRequest.getName());
        bankAccount.get().setUserId(bankAccountCreateRequest.getUserId());
        bankAccount.get().setValue(bankAccountCreateRequest.getValue());
        bankAccount.get().setDateOpened(bankAccountCreateRequest.getDateOpened());
        bankAccountRepository.save(bankAccount.get());

        return bankAccount;
    }

    @Override
    public void deleteBankAccount(Integer bankAccountId) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(bankAccountId);

        if (!bankAccount.isPresent())
            throw new ResourceNotFoundException("Bank Account id " + bankAccountId + " cannot be found");

        bankAccountRepository.delete(bankAccount.get());
    }


    @Override
    public BankTransaction createBankTransaction(BankTransactionCreateRequest bankTransactionCreateRequest) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(bankTransactionCreateRequest.getAccountId());

        if (!bankAccount.isPresent())
            throw new ResourceNotFoundException("Bank Account id " + bankTransactionCreateRequest.getAccountId() + " cannot be found");

        double newBankAccountValue;
        if (bankTransactionCreateRequest.getBankTransactionType() == BankTransactionType.WITHDRAWAL)
            newBankAccountValue = bankAccount.get().getValue() - bankTransactionCreateRequest.getValue();
        else
            newBankAccountValue = bankAccount.get().getValue() + bankTransactionCreateRequest.getValue();

        bankAccount.get().setValue(newBankAccountValue);
        bankAccountRepository.save(bankAccount.get());

        BankTransaction newBankTransaction = new BankTransaction();

        newBankTransaction.setUserId(bankTransactionCreateRequest.getUserId());
        newBankTransaction.setAccountId(bankTransactionCreateRequest.getAccountId());
        newBankTransaction.setBankTransactionType(bankTransactionCreateRequest.getBankTransactionType());
        newBankTransaction.setTransactionDate(bankTransactionCreateRequest.getTransactionDate());
        newBankTransaction.setValue(bankTransactionCreateRequest.getValue());
        newBankTransaction.setCategory(bankTransactionCreateRequest.getCategory());
        newBankTransaction.setOccurrenceType(bankTransactionCreateRequest.getOccurrenceType());
        newBankTransaction.setDateOfNextRecurrence(bankTransactionCreateRequest.getDateOfNextRecurrence());
        bankTransactionRepository.save(newBankTransaction);

        return newBankTransaction;
    }

    @Override
    public List<BankTransaction> getAllBankTransactions() {
        return bankTransactionRepository.findAll();
    }

    @Override
    public Optional<BankTransaction> getBankTransactionById(Integer bankTransactionId) {
        Optional<BankTransaction> bankTransaction = bankTransactionRepository.findById(bankTransactionId);

        if (!bankTransaction.isPresent())
            throw new ResourceNotFoundException("Bank Transaction id " + bankTransaction + " cannot be found");

        return bankTransaction;
    }

    @Override
    public List<BankTransaction> getBankTransactionByUserid(Integer userId) {
        return bankTransactionRepository.findByUserId(userId);
    }

    @Override
    public List<BankTransaction> getBankTransactionByAccountId(Integer accountId) {
        return bankTransactionRepository.findByAccountId(accountId);
    }

    @Override
    public List<BankTransaction> getBankTransactionByBankTransactionType(BankTransactionType bankTransactionType) {
        return bankTransactionRepository.findByBankTransactionType(bankTransactionType);
    }

    @Override
    public List<BankTransaction> getBankTransactionByTransactionDate(Date transactionDate) {
        return bankTransactionRepository.findByTransactionDate(transactionDate);
    }

    @Override
    public List<BankTransaction> getBankTransactionByOccurrenceType(OccurrenceType occurrenceType) {
        return bankTransactionRepository.findByOccurrenceType(occurrenceType);
    }

    @Override
    public List<BankTransaction> getBankTransactionByDateOfNextRecurrence(Date dateOfNextRecurrence) {
        return bankTransactionRepository.findByDateOfNextRecurrence(dateOfNextRecurrence);
    }

    @Override
    public Optional<BankTransaction> updateBankTransaction(Integer bankTransactionId, BankTransactionCreateRequest bankTransactionCreateRequest) {
        Optional<BankTransaction> bankTransaction = bankTransactionRepository.findById(bankTransactionId);

        if (!bankTransaction.isPresent())
            throw new ResourceNotFoundException("Bank Transaction id " + bankTransaction + " cannot be found");

        BankAccount oldBankAccount = bankAccountRepository.getById(bankTransaction.get().getAccountId());
        double oldBankAccountValue;

        if (bankTransaction.get().getBankTransactionType() == BankTransactionType.WITHDRAWAL)
            oldBankAccountValue = oldBankAccount.getValue() + bankTransaction.get().getValue();
        else
            oldBankAccountValue = oldBankAccount.getValue() - bankTransaction.get().getValue();

        oldBankAccount.setValue(oldBankAccountValue);
        bankAccountRepository.save(oldBankAccount);

        Optional<BankAccount> newBankAccount = bankAccountRepository.findById(bankTransactionCreateRequest.getAccountId());
        if (!newBankAccount.isPresent())
            throw new ResourceNotFoundException("Bank Account id " + bankTransactionCreateRequest.getAccountId() + " cannot be found");

        double newBankAccountValue;
        if (bankTransactionCreateRequest.getBankTransactionType() == BankTransactionType.WITHDRAWAL)
            newBankAccountValue = newBankAccount.get().getValue() - bankTransactionCreateRequest.getValue();
        else
            newBankAccountValue = newBankAccount.get().getValue() + bankTransactionCreateRequest.getValue();

        newBankAccount.get().setValue(newBankAccountValue);
        bankAccountRepository.save(newBankAccount.get());

        bankTransaction.get().setUserId(bankTransactionCreateRequest.getUserId());
        bankTransaction.get().setAccountId(bankTransactionCreateRequest.getAccountId());
        bankTransaction.get().setBankTransactionType(bankTransactionCreateRequest.getBankTransactionType());
        bankTransaction.get().setTransactionDate(bankTransactionCreateRequest.getTransactionDate());
        bankTransaction.get().setValue(bankTransactionCreateRequest.getValue());
        bankTransaction.get().setCategory(bankTransactionCreateRequest.getCategory());
        bankTransaction.get().setOccurrenceType(bankTransactionCreateRequest.getOccurrenceType());
        bankTransaction.get().setDateOfNextRecurrence(bankTransactionCreateRequest.getDateOfNextRecurrence());
        bankTransactionRepository.save(bankTransaction.get());

        return bankTransaction;
    }

    @Override
    public void deleteBankTransaction(Integer bankTransactionId) {
        Optional<BankTransaction> bankTransaction = bankTransactionRepository.findById(bankTransactionId);

        if (!bankTransaction.isPresent())
            throw new ResourceNotFoundException("Bank Transaction id " + bankTransaction + " cannot be found");

        bankTransactionRepository.delete(bankTransaction.get());
    }
}
