package com.financetool.finance.controller;

import com.financetool.finance.dto.BankAccountCreateRequest;
import com.financetool.finance.dto.BankAccountOutDto;
import com.financetool.finance.dto.BankTransactionCreateRequest;
import com.financetool.finance.dto.BankTransactionOutDto;
import com.financetool.finance.model.BankAccount;
import com.financetool.finance.model.BankTransaction;
import com.financetool.finance.service.BankService;
import com.financetool.finance.util.OutputFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping(path = "/bankAccounts", consumes = "application/json", produces = "application/json")
    public @ResponseStatus(HttpStatus.CREATED)
    BankAccountOutDto addNewBankAccount(@RequestBody @Valid BankAccountCreateRequest bankAccountCreateRequest) {
        BankAccount newBankAccount = bankService.createBankAccount(bankAccountCreateRequest);

        return OutputFormatter.bankAccountToBankAccountOutDto(newBankAccount);
    }

    @GetMapping(path = "/bankAccounts", produces = "application/json")
    public Iterable<BankAccountOutDto> getAllBankAccounts() {
        List<BankAccountOutDto> bankAccountOutList = new ArrayList<BankAccountOutDto>();
        List<BankAccount> bankAccounts = bankService.getAllBankAccounts();

        for (BankAccount bankAccount : bankAccounts)
            bankAccountOutList.add(OutputFormatter.bankAccountToBankAccountOutDto(bankAccount));

        return bankAccountOutList;
    }

    @GetMapping(path = "/bankAccounts/{bankAccountId}", produces = "application/json")
    public BankAccountOutDto getBankAccountById(@PathVariable(value="bankAccountId") Integer bankAccountId) {
        Optional<BankAccount> bankAccount = bankService.getBankAccountById(bankAccountId);

        return OutputFormatter.bankAccountToBankAccountOutDto(bankAccount.get());
    }

    @GetMapping(path = "/users/{userId}/bankAccounts", produces = "application/json")
    public Iterable<BankAccountOutDto> getBankAccountByUserId(@PathVariable(value="userId") Integer userId) {
        List<BankAccountOutDto> bankAccountOutDtoList = new ArrayList<BankAccountOutDto>();
        List<BankAccount> bankAccounts = bankService.getBankAccountByUserId(userId);

        for (BankAccount bankAccount : bankAccounts)
            bankAccountOutDtoList.add(OutputFormatter.bankAccountToBankAccountOutDto(bankAccount));

        return bankAccountOutDtoList;
    }

    @PutMapping(path = "/bankAccounts/{bankAccountId}", consumes = "application/json", produces = "application/json")
    public BankAccountOutDto updateBankAccountById(@PathVariable(value="bankAccountId") Integer bankAccountId, @RequestBody @Valid BankAccountCreateRequest bankAccountCreateRequest) {
        Optional<BankAccount> bankAccount = bankService.updateBankAccount(bankAccountId, bankAccountCreateRequest);

        return OutputFormatter.bankAccountToBankAccountOutDto(bankAccount.get());
    }

    @DeleteMapping(path = "/bankAccounts/{bankAccountId}")
    public ResponseEntity<?> deleteBankAccountById(@PathVariable(value="bankAccountId") Integer bankAccountId) {
        bankService.deleteBankAccount(bankAccountId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(path = "/bankTransactions", consumes = "application/json", produces = "application/json")
    public @ResponseStatus(HttpStatus.CREATED) BankTransactionOutDto
    addNewBankTransaction(@RequestBody @Valid BankTransactionCreateRequest bankTransactionCreateRequest) {
        BankTransaction newBankTransaction = bankService.createBankTransaction(bankTransactionCreateRequest);

        return OutputFormatter.bankTransactionToBankTransactionOutDto(newBankTransaction);
    }

    @GetMapping(path = "/bankTransactions", produces = "application/json")
    public Iterable<BankTransactionOutDto> getAllBankTransactions() {
        List<BankTransactionOutDto> bankTransactionOutDtoList = new ArrayList<BankTransactionOutDto>();
        List<BankTransaction> bankTransactions = bankService.getAllBankTransactions();

        for (BankTransaction bankTransaction : bankTransactions)
            bankTransactionOutDtoList.add(OutputFormatter.bankTransactionToBankTransactionOutDto(bankTransaction));

        return bankTransactionOutDtoList;
    }

    @GetMapping(path = "/bankTransactions/{bankTransactionId}", produces = "application/json")
    public BankTransactionOutDto getBankTransactionById(@PathVariable(value="bankTransactionId") Integer bankTransactionId) {
        Optional<BankTransaction> bankTransaction = bankService.getBankTransactionById(bankTransactionId);

        return OutputFormatter.bankTransactionToBankTransactionOutDto(bankTransaction.get());
    }

    @GetMapping(path = "/users/{userId}/bankTransactions", produces = "application/json")
    public Iterable<BankTransactionOutDto> getBankTransactionByUserId(@PathVariable(value="userId") Integer userId) {
        List<BankTransactionOutDto> bankTransactionOutDtoList = new ArrayList<BankTransactionOutDto>();
        List<BankTransaction> bankTransactions = bankService.getBankTransactionByUserid(userId);

        for (BankTransaction bankTransaction : bankTransactions)
            bankTransactionOutDtoList.add(OutputFormatter.bankTransactionToBankTransactionOutDto(bankTransaction));

        return bankTransactionOutDtoList;
    }

    @GetMapping(path = "/bankAccounts/{bankAccountId}/bankTransactions", produces = "application/json")
    public Iterable<BankTransactionOutDto> getBankTransactionByBankAccountId(@PathVariable(value="bankAccountId") Integer bankAccountId) {
        List<BankTransactionOutDto> bankTransactionOutDtoList = new ArrayList<BankTransactionOutDto>();
        List<BankTransaction> bankTransactions = bankService.getBankTransactionByAccountId(bankAccountId);

        for (BankTransaction bankTransaction : bankTransactions)
            bankTransactionOutDtoList.add(OutputFormatter.bankTransactionToBankTransactionOutDto(bankTransaction));

        return bankTransactionOutDtoList;
    }

    @PutMapping(path = "/bankTransactions/{bankTransactionId}", consumes = "application/json", produces = "application/json")
    public BankTransactionOutDto updateBankTransactionById(@PathVariable(value="bankTransactionId") Integer bankTransactionId, @RequestBody @Valid BankTransactionCreateRequest bankTransactionCreateRequest) {
        Optional<BankTransaction> bankTransaction = bankService.updateBankTransaction(bankTransactionId, bankTransactionCreateRequest);

        return OutputFormatter.bankTransactionToBankTransactionOutDto(bankTransaction.get());
    }

    @DeleteMapping(path = "/bankTransactions/{bankTransactionId}")
    public ResponseEntity<?> deleteBankTransactionById(@PathVariable(value="bankTransactionId") Integer bankTransactionId) {
        bankService.deleteBankTransaction(bankTransactionId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
