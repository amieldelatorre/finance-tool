package com.financetool.finance.service;

import com.financetool.finance.dto.InvestmentCreateRequest;
import com.financetool.finance.exception.ResourceNotFoundException;
import com.financetool.finance.model.Investment;
import com.financetool.finance.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestmentServiceImpl implements InvestmentService{
    @Autowired
    private InvestmentRepository investmentRepository;

    @Override
    public Investment createInvestment(InvestmentCreateRequest investmentCreateRequest) {
        Investment newInvestment = new Investment();

        newInvestment.setUserId(investmentCreateRequest.getUserId());
        newInvestment.setName(investmentCreateRequest.getName());
        newInvestment.setValue(investmentCreateRequest.getValue());
        investmentRepository.save(newInvestment);

        return newInvestment;
    }

    @Override
    public List<Investment> getAllInvestments() {
        return investmentRepository.findAll();
    }

    @Override
    public Optional<Investment> getInvestmentById(Integer investmentId) {
        Optional<Investment> investment = investmentRepository.findById(investmentId);

        if (!investment.isPresent())
            throw new ResourceNotFoundException("Investment id " + investmentId + " cannot be found.");

        return investment;
    }

    @Override
    public List<Investment> getInvestmentByUserId(Integer userId) {
        return investmentRepository.findByUserId(userId);
    }

    @Override
    public Optional<Investment> updateInvestment(Integer investmentId, InvestmentCreateRequest investmentCreateRequest) {
        Optional<Investment> investment = investmentRepository.findById(investmentId);

        if (!investment.isPresent())
            throw new ResourceNotFoundException("Investment id " + investmentId + " cannot be found.");

        System.out.println(investment.get().getId());
        System.out.println(investmentCreateRequest.getUserId());
        System.out.println(investmentCreateRequest.getName());
        System.out.println(investmentCreateRequest.getValue());

        investment.get().setUserId(investmentCreateRequest.getUserId());
        investment.get().setName(investmentCreateRequest.getName());
        investment.get().setValue(investmentCreateRequest.getValue());
        investmentRepository.save(investment.get());

        return investment;
    }

    @Override
    public void deleteInvestment(Integer investmentId) {
        Optional<Investment> investment = investmentRepository.findById(investmentId);

        if (!investment.isPresent())
            throw new ResourceNotFoundException("Investment id " + investmentId + " cannot be found.");

        investmentRepository.delete(investment.get());
    }
}