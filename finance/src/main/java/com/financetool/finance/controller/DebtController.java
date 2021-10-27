package com.financetool.finance.controller;

import com.financetool.finance.dto.DebtCreateRequest;
import com.financetool.finance.dto.DebtOutDto;
import com.financetool.finance.dto.DebtRepaymentCreateRequest;
import com.financetool.finance.dto.DebtRepaymentOutDto;
import com.financetool.finance.exception.InternalServerException;
import com.financetool.finance.model.Debt;
import com.financetool.finance.model.DebtRepayment;
import com.financetool.finance.service.DebtService;
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
@RequestMapping("/api/v1")
public class DebtController {
    @Autowired
    private DebtService debtService;

    @PostMapping(path = "/debts", consumes = "application/json", produces = "application/json")
    public @ResponseStatus(HttpStatus.CREATED)
    DebtOutDto addNewDebt(@RequestBody @Valid DebtCreateRequest debt, HttpServletRequest request) {
        boolean validDebtCreateRequest = InputValidation.isValidDebtCreateRequest(debt, request);

        if (validDebtCreateRequest) {
            Debt newDebt = debtService.createDebt(debt);
            return OutputFormatter.debtToDebtOutDto(newDebt);
        }
        else
            throw new InternalServerException("Sorry something went wrong.", request);

    }

    @GetMapping(path = "/debts", produces = "application/json")
    public Iterable<DebtOutDto> getAllDebts() {
        List<DebtOutDto> debtOutList = new ArrayList<DebtOutDto>();
        List<Debt> debts = debtService.getAllDebt();

        for (Debt debt : debts)
            debtOutList.add(OutputFormatter.debtToDebtOutDto(debt));

        return debtOutList;
    }

    @GetMapping(path = "/debts/{debtId}", produces = "application/json")
    public DebtOutDto getDebtById(@PathVariable(value="debtId") Integer debtId) {
        Debt debt = debtService.getDebtById(debtId);

        return OutputFormatter.debtToDebtOutDto(debt);
    }

    @GetMapping(path = "/users/{userId}/debts", produces = "application/json")
    public Iterable<DebtOutDto> getDebtByUserId(@PathVariable(value="userId") Integer userId) {
        List<DebtOutDto> debtOutList = new ArrayList<DebtOutDto>();
        List<Debt> debts = debtService.getDebtByUserId(userId);

        for (Debt debt : debts)
            debtOutList.add(OutputFormatter.debtToDebtOutDto(debt));

        return debtOutList;
    }

    @PutMapping(path = "/debts/{debtId}", produces = "application/json")
    public DebtOutDto updateDebt(@PathVariable(value="debtId") Integer debtId, @RequestBody @Valid DebtCreateRequest debtRequest, HttpServletRequest request) {
        boolean validDebtCreateRequest = InputValidation.isValidDebtCreateRequest(debtRequest, request);

        if (validDebtCreateRequest) {
            Debt debt = debtService.updateDebt(debtId, debtRequest);
            return OutputFormatter.debtToDebtOutDto(debt);
        }
        else
            throw new InternalServerException("Sorry something went wrong.", request);
    }

    @DeleteMapping(path = "/debts/{debtId}", produces = "application/json")
    public ResponseEntity<?> deleteDebtById(@PathVariable(value="debtId") Integer debtId) {
        debtService.deleteDebt(debtId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/debts/{debtId}/repayments", produces = "application/json")
    public @ResponseStatus(HttpStatus.CREATED)
    DebtRepaymentOutDto addNewDebtRepayment(@RequestBody @Valid DebtRepaymentCreateRequest debtRepaymentRequest, HttpServletRequest request) {
        boolean validDebtRepaymentCreateRequest = InputValidation.isValidDebtRepaymentCreateRequest(debtRepaymentRequest, request);

        if (validDebtRepaymentCreateRequest) {
            DebtRepayment debtRepayment = debtService.createDebtRepayment(debtRepaymentRequest);

            return OutputFormatter.debtRepaymentToDebtRepaymentOutDto(debtRepayment);
        }
        else
            throw new InternalServerException("Sorry something went wrong.", request);
    }

    @GetMapping(path = "/debtRepayments", produces = "application/json")
    public Iterable<DebtRepaymentOutDto> getAllDebtRepayments() {
        List<DebtRepaymentOutDto> debtRepaymentOutDtos = new ArrayList<DebtRepaymentOutDto>();
        List<DebtRepayment> debtRepayments = debtService.getAllDebtRepayment();

        for (DebtRepayment debtRepayment : debtRepayments)
            debtRepaymentOutDtos.add(OutputFormatter.debtRepaymentToDebtRepaymentOutDto(debtRepayment));

        return debtRepaymentOutDtos;
    }

    @GetMapping(path = "/debtRepayments/{debtRepaymentId}", produces = "application/json")
    public DebtRepaymentOutDto getDebtRepaymentById(@PathVariable(value="debtRepaymentId") Integer debtRepaymentId) {
        DebtRepayment debtRepayment = debtService.getDebtRepaymentById(debtRepaymentId);

        return OutputFormatter.debtRepaymentToDebtRepaymentOutDto(debtRepayment);
    }

    @GetMapping(path = "/debts/{debtId}/repayments", produces = "application/json")
    public Iterable<DebtRepaymentOutDto> getDebtRepaymentByDebtId(@PathVariable(value="debtId") Integer debtId) {
        List<DebtRepaymentOutDto> debtRepaymentOutDtos = new ArrayList<DebtRepaymentOutDto>();
        List<DebtRepayment> debtRepayments = debtService.getDebtRepaymentsByDebtId(debtId);

        for (DebtRepayment debtRepayment : debtRepayments)
            debtRepaymentOutDtos.add(OutputFormatter.debtRepaymentToDebtRepaymentOutDto(debtRepayment));

        return debtRepaymentOutDtos;
    }

    @DeleteMapping(path = "/debtRepayments/{debtRepaymentId}")
    public ResponseEntity<?> deleteDebtRepaymentById(@PathVariable(value="debtRepaymentId") Integer debtRepaymentId) {
        debtService.deleteDebtRepayment(debtRepaymentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/debtRepayments/{debtRepaymentId}")
    public DebtRepaymentOutDto
    updateDebtRepaymentById(@PathVariable(value="debtRepaymentId") Integer debtRepaymentId, @RequestBody @Valid DebtRepaymentCreateRequest debtRepaymentCreateRequest, HttpServletRequest request) {
        boolean validDebtRepaymentCreateRequest = InputValidation.isValidDebtRepaymentCreateRequest(debtRepaymentCreateRequest, request);

        if (validDebtRepaymentCreateRequest) {
            DebtRepayment debtRepayment = debtService.updateDebtRepayment(debtRepaymentId, debtRepaymentCreateRequest);
            return OutputFormatter.debtRepaymentToDebtRepaymentOutDto(debtRepayment);
        }
        else
            throw new InternalServerException("Sorry something went wrong.", request);
    }
}
