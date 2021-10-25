package com.financetool.finance.service;

import com.financetool.finance.dto.InvestmentCreateRequest;
import com.financetool.finance.model.Investment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface InvestmentService {
    Investment createInvestment(InvestmentCreateRequest investmentCreateRequest);
    List<Investment> getAllInvestments();
    Optional<Investment> getInvestmentById(Integer investmentId);
    List<Investment> getInvestmentByUserId(Integer userId);
    Optional<Investment> updateInvestment(Integer investmentId, InvestmentCreateRequest investmentCreateRequest);
    void deleteInvestment(Integer investmentId);
}
