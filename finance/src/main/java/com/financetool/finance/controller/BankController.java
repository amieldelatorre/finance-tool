package com.financetool.finance.controller;

import com.financetool.finance.dto.BankAccountCreateRequest;
import com.financetool.finance.dto.BankAccountOutDto;
import com.financetool.finance.dto.BankTransactionCreateRequest;
import com.financetool.finance.dto.BankTransactionOutDto;
import com.financetool.finance.exception.InternalServerException;
import com.financetool.finance.model.BankAccount;
import com.financetool.finance.model.BankTransaction;
import com.financetool.finance.service.BankService;
import com.financetool.finance.util.InputValidation;
import com.financetool.finance.util.OutputFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping(path = "/bankAccounts", consumes = "application/json", produces = "application/json")
    public @ResponseStatus(HttpStatus.CREATED)
    BankAccountOutDto addNewBankAccount(@RequestBody @Valid BankAccountCreateRequest bankAccountCreateRequest, HttpServletRequest request) {
        boolean validBankAccountCreateRequest = InputValidation.isValidBankAccountCreateRequest(bankAccountCreateRequest, request);

        if (validBankAccountCreateRequest) {
            BankAccount newBankAccount = bankService.createBankAccount(bankAccountCreateRequest);
            return OutputFormatter.bankAccountToBankAccountOutDto(newBankAccount);
        }
        else
            throw new InternalServerException("Sorry something went wrong.", request);
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
        BankAccount bankAccount = bankService.getBankAccountById(bankAccountId);

        return OutputFormatter.bankAccountToBankAccountOutDto(bankAccount);
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
    public BankAccountOutDto updateBankAccountById(@PathVariable(value="bankAccountId") Integer bankAccountId, @RequestBody @Valid BankAccountCreateRequest bankAccountCreateRequest, HttpServletRequest request) {
        boolean validBankAccountCreateRequest = InputValidation.isValidBankAccountCreateRequest(bankAccountCreateRequest, request);

        if (validBankAccountCreateRequest) {
            BankAccount bankAccount = bankService.updateBankAccount(bankAccountId, bankAccountCreateRequest);
            return OutputFormatter.bankAccountToBankAccountOutDto(bankAccount);
        }
        else
            throw new InternalServerException("Sorry something went wrong.", request);
    }

    @DeleteMapping(path = "/bankAccounts/{bankAccountId}")
    public ResponseEntity<?> deleteBankAccountById(@PathVariable(value="bankAccountId") Integer bankAccountId) {
        bankService.deleteBankAccount(bankAccountId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(path = "/bankTransactions", consumes = "application/json", produces = "application/json")
    public @ResponseStatus(HttpStatus.CREATED) BankTransactionOutDto
    addNewBankTransaction(@RequestBody @Valid BankTransactionCreateRequest bankTransactionCreateRequest, HttpServletRequest request) {
        boolean validBankTransactionCreateRequest = InputValidation.isValidBankTransactionCreateRequest(bankTransactionCreateRequest, request);

        if (validBankTransactionCreateRequest) {
            BankTransaction newBankTransaction = bankService.createBankTransaction(bankTransactionCreateRequest);
            return OutputFormatter.bankTransactionToBankTransactionOutDto(newBankTransaction);
        }
        else
            throw new InternalServerException("Sorry something went wrong.", request);
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
        BankTransaction bankTransaction = bankService.getBankTransactionById(bankTransactionId);

        return OutputFormatter.bankTransactionToBankTransactionOutDto(bankTransaction);
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
    public BankTransactionOutDto updateBankTransactionById(@PathVariable(value="bankTransactionId") Integer bankTransactionId, @RequestBody @Valid BankTransactionCreateRequest bankTransactionCreateRequest, HttpServletRequest request) {
        boolean validBankTransactionCreateRequest = InputValidation.isValidBankTransactionCreateRequest(bankTransactionCreateRequest, request);

        if (validBankTransactionCreateRequest) {
            BankTransaction bankTransaction = bankService.updateBankTransaction(bankTransactionId, bankTransactionCreateRequest);
            return OutputFormatter.bankTransactionToBankTransactionOutDto(bankTransaction);
        }
        else
            throw new InternalServerException("Sorry something went wrong.", request);
    }

    @DeleteMapping(path = "/bankTransactions/{bankTransactionId}")
    public ResponseEntity<?> deleteBankTransactionById(@PathVariable(value="bankTransactionId") Integer bankTransactionId) {
        bankService.deleteBankTransaction(bankTransactionId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
