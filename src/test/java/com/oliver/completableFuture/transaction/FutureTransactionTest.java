package com.oliver.completableFuture.transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FutureTransactionTest {
    @Autowired
    private FutureTransaction futureTransaction;

    @Test
    public void insertTransaction() throws ExecutionException, InterruptedException {
        futureTransaction.insertTransaction();
    }
}