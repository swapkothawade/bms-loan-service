package com.mybank.api.dao;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mybank.api.exception.CustomException;
import com.mybank.api.model.Loan;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


@Component
public class LoanDao extends AbstractBMSDao{
    private final MongoCollection<Loan> loanCollection;

    private final Logger log;

    public LoanDao(MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
        super(mongoClient, databaseName);
        CodecRegistry pojoCodecRegistry =
                fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        loanCollection = db.getCollection("user_loan", Loan.class).withCodecRegistry(pojoCodecRegistry);
        log = LoggerFactory.getLogger(this.getClass());
    }

    public boolean save(Loan loanRequest) {

        try {
            loanCollection.insertOne(loanRequest);
            return true;
        }catch(MongoWriteException exception){
            throw new CustomException("Error occured while saving loan request.", HttpStatus.NOT_MODIFIED);
        }

    }

    public List<Loan> getLoanDetails(String username) {
        Bson filter = eq("username",username);
        Iterator<Loan> itr = loanCollection.find(filter).iterator();
        List<Loan> loans = new ArrayList<>();
        while(itr.hasNext()){
            loans.add(itr.next());
        }
        return loans;

    }
}
