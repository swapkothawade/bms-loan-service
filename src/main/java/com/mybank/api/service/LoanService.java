package com.mybank.api.service;

import com.mybank.api.dao.LoanDao;
import com.mybank.api.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class LoanService {

    @Autowired
    private LoanDao loanDao;

    public boolean applyLoan(Loan loanRequest) {
        long loanid = generateRandomLoanNumber();
        loanRequest.setLoanid(String.valueOf(loanid));
    	return loanDao.save(loanRequest);

    }

    public List<Loan> getLoanDetails(String username) {
        return loanDao.getLoanDetails(username);

    }
    
    private long generateRandomLoanNumber() {
		Random r = new Random(System.currentTimeMillis());
		return 1000000000 + r.nextInt(2000000000) & Integer.MAX_VALUE;
	}
}
