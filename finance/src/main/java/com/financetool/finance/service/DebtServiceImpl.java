package com.financetool.finance.service;

import com.financetool.finance.dto.DebtCreateRequest;
import com.financetool.finance.exception.ResourceNotFoundException;
import com.financetool.finance.model.Debt;
import com.financetool.finance.repository.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DebtServiceImpl implements DebtService{
    @Autowired
    private DebtRepository debtRepository;

    @Override
    public Debt createDebt(DebtCreateRequest debt) {
        Debt newDebt = new Debt();

        newDebt.setUserId(debt.getUserId());
        newDebt.setValue(debt.getValue());
        newDebt.setDescription(debt.getDescription());
        debtRepository.save(newDebt);

        return newDebt;
    }

    @Override
    public List<Debt> getAllDebt() {
        List<Debt> debts = debtRepository.findAll();

        return debts;
    }

    @Override
    public Optional<Debt> getDebtById(Integer debtId) {
        Optional<Debt> debt = debtRepository.findById(debtId);

        if (!debt.isPresent()) {
            throw new ResourceNotFoundException("Debt id " + debtId + " cannot be found.");
        }

        return debt;
    }

    @Override
    public List<Debt> getDebtByUserId(Integer userId) {
        List<Debt> debts = debtRepository.findByUserId(userId);
        return debts;
    }

    @Override
    public Optional<Debt> updateDebt(Integer debtId, DebtCreateRequest debtRequest) {
        Optional<Debt> debt = debtRepository.findById(debtId);

        if (!debt.isPresent()) {
            throw new ResourceNotFoundException("Debt id " + debtId + " cannot be found.");
        }
        else {
            debt.get().setUserId(debtRequest.getUserId());
            debt.get().setValue(debtRequest.getValue());
            debt.get().setDescription(debtRequest.getDescription());
            debtRepository.save(debt.get());
        }
        return debt;
    }

    @Override
    public void deleteDebt(Integer debtId) {
        Optional<Debt> debt = debtRepository.findById(debtId);

        if (!debt.isPresent()) {
            throw new ResourceNotFoundException("Debt id " + debtId + " cannot be found.");
        }
        else {
            debtRepository.delete(debt.get());
        }
    }
}
