package com.mybank.api.service;

import com.mybank.api.dao.LoanDao;
import com.mybank.api.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanDao loanDao;

    public boolean applyLoan(Loan loanRequest) {
            return loanDao.save(loanRequest);

    }

    public List<Loan> getLoanDetails(String username) {
        return loanDao.getLoanDetails(username);

    }
}
