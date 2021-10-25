package com.financetool.finance.controller;

import com.financetool.finance.dto.InvestmentCreateRequest;
import com.financetool.finance.dto.InvestmentOutDto;
import com.financetool.finance.model.Investment;
import com.financetool.finance.service.InvestmentService;
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
public class InvestmentController {
    @Autowired
    private InvestmentService investmentService;

    @PostMapping(path = "/investments", consumes = "application/json", produces = "application/json")
    public @ResponseStatus(HttpStatus.CREATED)
    InvestmentOutDto addNewInvestment(@RequestBody @Valid InvestmentCreateRequest investmentCreateRequest) {
        Investment investment = investmentService.createInvestment(investmentCreateRequest);

        return OutputFormatter.investmentToInvestmentOutDto(investment);
    }

    @GetMapping(path = "/investments", produces = "application/json")
    public Iterable<InvestmentOutDto> getAllInvestments() {
        List<InvestmentOutDto> investmentOutList = new ArrayList<InvestmentOutDto>();
        List<Investment> investments = investmentService.getAllInvestments();

        for (Investment investment : investments)
            investmentOutList.add(OutputFormatter.investmentToInvestmentOutDto(investment));

        return investmentOutList;
    }

    @GetMapping(path = "/investments/{investmentId}", produces = "application/json")
    public InvestmentOutDto getInvestmentById(@PathVariable(value="investmentId") Integer investmentId) {
        Optional<Investment> investment = investmentService.getInvestmentById(investmentId);

        return OutputFormatter.investmentToInvestmentOutDto(investment.get());
    }

    @GetMapping(path = "/users/{userId}/investments", produces = "application/json")
    public Iterable<InvestmentOutDto> getAllInvestmentByUserId(@PathVariable(value="userId") Integer userId) {
        List<InvestmentOutDto> investmentOutList = new ArrayList<InvestmentOutDto>();
        List<Investment> investments = investmentService.getInvestmentByUserId(userId);

        for (Investment investment : investments)
            investmentOutList.add(OutputFormatter.investmentToInvestmentOutDto(investment));

        return investmentOutList;
    }

    @PutMapping(path = "/investments/{investmentId}", consumes = "application/json", produces = "application/json")
    public InvestmentOutDto updateInvestment(@PathVariable(value="investmentId") Integer investmentId, @RequestBody @Valid InvestmentCreateRequest investmentCreateRequest) {
        Optional<Investment> investment = investmentService.updateInvestment(investmentId, investmentCreateRequest);

        return OutputFormatter.investmentToInvestmentOutDto(investment.get());
    }

    @DeleteMapping(path = "/investments/{investmentId}")
    public ResponseEntity<?> deleteInvestmentById(@PathVariable(value="investmentId") Integer investmentId) {
        investmentService.deleteInvestment(investmentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
