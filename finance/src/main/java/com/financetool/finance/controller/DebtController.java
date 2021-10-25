package com.financetool.finance.controller;

import com.financetool.finance.dto.DebtCreateRequest;
import com.financetool.finance.dto.DebtOutDto;
import com.financetool.finance.dto.DebtRepaymentCreateRequest;
import com.financetool.finance.dto.DebtRepaymentOutDto;
import com.financetool.finance.model.Debt;
import com.financetool.finance.model.DebtRepayment;
import com.financetool.finance.service.DebtService;
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
@RequestMapping("/api/v1")
public class DebtController {
    @Autowired
    private DebtService debtService;

    @PostMapping(path = "/debts", consumes = "application/json", produces = "application/json")
    public @ResponseStatus(HttpStatus.CREATED)
    DebtOutDto addNewDebt(@RequestBody @Valid DebtCreateRequest debt) {
        Debt newDebt = debtService.createDebt(debt);

        return OutputFormatter.debtToDebtOutDto(newDebt);
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
        Optional<Debt> debt = debtService.getDebtById(debtId);

        return OutputFormatter.debtToDebtOutDto(debt.get());
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
    public DebtOutDto updateDebt(@PathVariable(value="debtId") Integer debtId, @RequestBody @Valid DebtCreateRequest debtRequest) {
        Optional<Debt> debt = debtService.updateDebt(debtId, debtRequest);

        return OutputFormatter.debtToDebtOutDto(debt.get());
    }

    @DeleteMapping(path = "/debts/{debtId}", produces = "application/json")
    public ResponseEntity<?> deleteDebtById(@PathVariable(value="debtId") Integer debtId) {
        debtService.deleteDebt(debtId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/debts/{debtId}/repayments", produces = "application/json")
    public @ResponseStatus(HttpStatus.CREATED)
    DebtRepaymentOutDto addNewDebtRepayment(@RequestBody @Valid DebtRepaymentCreateRequest debtRepaymentRequest) {
        DebtRepayment debtRepayment = debtService.createDebtRepayment(debtRepaymentRequest);

        return OutputFormatter.debtRepaymentToDebtRepaymentOutDto(debtRepayment);
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
        Optional<DebtRepayment> debtRepayment = debtService.getDebtRepaymentById(debtRepaymentId);

        return OutputFormatter.debtRepaymentToDebtRepaymentOutDto(debtRepayment.get());
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
    updateDebtRepaymentById(@PathVariable(value="debtRepaymentId") Integer debtRepaymentId, @RequestBody @Valid DebtRepaymentCreateRequest debtRepaymentCreateRequest) {
        Optional<DebtRepayment> debtRepayment = debtService.updateDebtRepayment(debtRepaymentId, debtRepaymentCreateRequest);

        return OutputFormatter.debtRepaymentToDebtRepaymentOutDto(debtRepayment.get());
    }
}
