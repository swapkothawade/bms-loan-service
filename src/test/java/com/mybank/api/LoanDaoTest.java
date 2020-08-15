package com.mybank.api;

import com.mongodb.client.MongoClient;
import com.mybank.api.config.MongoDBConfiguration;
import com.mybank.api.dao.LoanDao;
import com.mybank.api.model.Loan;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(classes = {MongoDBConfiguration.class})
@EnableConfigurationProperties
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class LoanDaoTest {

    private LoanDao dao;

    private static String username = "swapnil.kothawade@gmail.com";

    @Autowired
    MongoClient mongoClient;

    @Value("${spring.mongodb.database}")
    String databaseName;

    @Before
    public void setup() {

        this.dao = new LoanDao(mongoClient, databaseName);
    }


    @Test
    public void testSaveLoan(){
        dao.save(getLoanData());
    }

    private Loan getLoanData() {
        Loan loan = new Loan();
        loan.setUsername(username);
        loan.setLoanAmount(450000);
        loan.setLoanType("Car Loan");
        loan.setLoanApplicationDate(LocalDate.now());
        loan.setLoanDurationInMonth(50);
        loan.setRateOfInterest(10.5f);
        return loan;
    }

    @Test
    public void testUserExist(){

        List<Loan> loans = dao.getLoanDetails(username);
        assertNotNull(loans);
        assertEquals(1,loans.size());
        assertEquals("User email should match", username,loans.get(0).getUsername());

    }


}
