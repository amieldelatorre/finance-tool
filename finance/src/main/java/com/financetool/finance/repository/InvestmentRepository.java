package com.financetool.finance.repository;

import com.financetool.finance.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Integer> {
    List<Investment> findByUserId(Integer userId);
}
