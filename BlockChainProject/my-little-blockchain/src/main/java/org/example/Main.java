package org.example;

import com.google.common.hash.Hashing;
import org.example.service.Block;
import org.example.service.BlockChain;
import org.example.service.Data;

import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {


        BlockChain peterCoin = new BlockChain();


        peterCoin.createTransaction(new Data("address1","address2",100));
        peterCoin.createTransaction(new Data("address2","address1",50));

        peterCoin.minePendingTransaction("peter");

        //System.out.println(peterCoin.getChain());

        System.out.println("Balance of peter is " + peterCoin.getBalanceOfAddress("peter"));

        peterCoin.minePendingTransaction("peter");
        System.out.println("Balance of peter is " + peterCoin.getBalanceOfAddress("peter"));

        System.out.println(peterCoin.getChain());



    }
}
