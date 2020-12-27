package com.mybank.api.controller;

import com.mybank.api.model.Loan;
import com.mybank.api.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    LoanService loanService;

    /**
     *
     * @param token
     * @param loanid
     * @return
     */
    @GetMapping("/restricted/loan/{loanid}")
    public ResponseEntity<?> loanDetails(@RequestHeader("authorization") String token,@PathVariable("loanid") String loanid){
        String username = "abc@gmail.com"; // As stateless service, user information needs to be derived using token.
        if(loanid!=null && !loanid.isEmpty()){
            // retrieve loan details based on loan id.. for now
            List<Loan> loans = loanService.getLoanDetails(username);
            return ResponseEntity.ok().body(loans);
        }else{
            // otherwise get loan details based on username.
            List<Loan> loans = loanService.getLoanDetails(username);
            return ResponseEntity.ok().body(loans);
        }

    }

    @PostMapping("/restricted/loan")
    @CrossOrigin("*")
    @ResponseBody
    public ResponseEntity<?> applyLoan(@RequestHeader("authorization") String token, @RequestBody Loan loanRequest){
        // get User details here using token
        try {
            String username = "swapnil.kothawade@gmaill.com";
            loanRequest.setUsername(username);
            loanService.applyLoan(loanRequest);
            return ResponseEntity.ok("Loan Applied Successfully");
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Loan Applied Successfully");
        }
    }
}
