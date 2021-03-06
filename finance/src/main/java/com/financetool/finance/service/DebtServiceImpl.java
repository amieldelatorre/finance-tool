package com.financetool.finance.service;

import com.financetool.finance.dto.DebtCreateRequest;
import com.financetool.finance.dto.DebtRepaymentCreateRequest;
import com.financetool.finance.exception.ResourceNotFoundException;
import com.financetool.finance.model.Debt;
import com.financetool.finance.model.DebtRepayment;
import com.financetool.finance.repository.DebtRepaymentRepository;
import com.financetool.finance.repository.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class DebtServiceImpl implements DebtService{
    @Autowired
    private DebtRepository debtRepository;
    @Autowired
    private DebtRepaymentRepository debtRepaymentRepository;

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
    public Debt getDebtById(Integer debtId) {
        Optional<Debt> debt = debtRepository.findById(debtId);

        if (!debt.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Debt id " + debtId + " cannot be found.", request);
        }

        return debt.get();
    }

    @Override
    public List<Debt> getDebtByUserId(Integer userId) {
        List<Debt> debts = debtRepository.findByUserId(userId);
        return debts;
    }

    @Override
    public Debt updateDebt(Integer debtId, DebtCreateRequest debtRequest) {
        Optional<Debt> debt = debtRepository.findById(debtId);

        if (!debt.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Debt id " + debtId + " cannot be found.", request);
        }
        else {
            debt.get().setUserId(debtRequest.getUserId());
            debt.get().setValue(debtRequest.getValue());
            debt.get().setDescription(debtRequest.getDescription());
            debtRepository.save(debt.get());
        }
        return debt.get();
    }

    @Override
    public void deleteDebt(Integer debtId) {
        Optional<Debt> debt = debtRepository.findById(debtId);

        if (!debt.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Debt id " + debtId + " cannot be found.", request);
        }
        else {
            debtRepository.delete(debt.get());
        }
    }

    @Override
    public DebtRepayment createDebtRepayment(DebtRepaymentCreateRequest debtRepayment) {
        Optional<Debt> debt = debtRepository.findById(debtRepayment.getDebtId());

        if (!debt.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Debt id " + debtRepayment.getDebtId() + " cannot be found.", request);
        }
        else {
            DebtRepayment newDebtRepayment = new DebtRepayment();

            newDebtRepayment.setDebtId(debtRepayment.getDebtId());
            newDebtRepayment.setValue(debtRepayment.getValue());
            newDebtRepayment.setOccurrenceType(debtRepayment.getOccurrenceType());

            debt.get().setValue(debt.get().getValue() - newDebtRepayment.getValue());

            debtRepository.save(debt.get());
            debtRepaymentRepository.save(newDebtRepayment);

            return newDebtRepayment;
        }
    }

    @Override
    public List<DebtRepayment> getAllDebtRepayment() {
        return debtRepaymentRepository.findAll();
    }

    @Override
    public DebtRepayment getDebtRepaymentById(Integer debtRepaymentId) {
        Optional<DebtRepayment> debtRepayment = debtRepaymentRepository.findById(debtRepaymentId);

        if (!debtRepayment.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Debt Repayment id " + debtRepaymentId + " cannot be found.", request);
        }

        return debtRepayment.get();
    }

    @Override
    public List<DebtRepayment> getDebtRepaymentsByDebtId(Integer debtId) {
        Optional<Debt> debt = debtRepository.findById(debtId);

        if (!debt.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Debt id " + debtId + " cannot be found.", request);
        }
        else {
            List<DebtRepayment> debtRepayments = debtRepaymentRepository.findByDebtId(debtId);
            return debtRepayments;
        }
    }

    @Override
    public void deleteDebtRepayment(Integer debtRepaymentId) {
        Optional<DebtRepayment> debtRepayment = debtRepaymentRepository.findById(debtRepaymentId);

        if (!debtRepayment.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Debt Repayment id " + debtRepaymentId + " cannot be found.", request);
        }
        else {
            Optional<Debt> debt = debtRepository.findById(debtRepayment.get().getDebtId());
            debt.get().setValue(debt.get().getValue() + debtRepayment.get().getValue());
            debtRepository.save(debt.get());
            debtRepaymentRepository.delete(debtRepayment.get());
        }
    }

    @Override
    public DebtRepayment updateDebtRepayment(Integer debtRepaymentId, DebtRepaymentCreateRequest debtRepaymentCreateRequest) {
        Optional<DebtRepayment> debtRepayment = debtRepaymentRepository.findById(debtRepaymentId);

        if (!debtRepayment.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("Debt Repayment id " + debtRepaymentId + " cannot be found.", request);
        }
        else {
            Optional<Debt> updatedDebt = debtRepository.findById(debtRepaymentCreateRequest.getDebtId());

            if (!updatedDebt.isPresent()) {
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpServletRequest request = requestAttributes.getRequest();

                throw new ResourceNotFoundException("Debt Repayment id " + debtRepaymentId + " cannot be found.", request);
            }

            Optional<Debt> debt = debtRepository.findById(debtRepayment.get().getDebtId());
            Double oldDebtValue = debt.get().getValue() + debtRepayment.get().getValue();
            debt.get().setValue(oldDebtValue);
            debtRepository.save(debt.get());

            debtRepayment.get().setValue(debtRepaymentCreateRequest.getValue());
            debtRepayment.get().setDebtId(debtRepaymentCreateRequest.getDebtId());
            debtRepayment.get().setOccurrenceType((debtRepaymentCreateRequest.getOccurrenceType()));
            debtRepaymentRepository.save(debtRepayment.get());

            updatedDebt.get().setValue(updatedDebt.get().getValue() - debtRepaymentCreateRequest.getValue());
            debtRepository.save(updatedDebt.get());

            return debtRepayment.get();
        }
    }
}
