package org.example.service;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

@lombok.Data
public class Block {

        //== Fields ==
       // private final int index;
        private final Timestamp timestamp;
        private ArrayList<Data> transactions;
        private String prevHash;
        private String hash;
        private int nonce;

        //== Constructor ==
        public Block() {
          //  this.index = index;
            this.timestamp = new Timestamp(System.currentTimeMillis());
            this.transactions = new ArrayList<>();
            this.hash = hash256();
            this.nonce = 0;
        }

        //== Methods ==

    public String hash256(){
                String convert = this.timestamp + this.prevHash + this.transactions + String.valueOf(this.nonce);
               return Hashing.sha256().hashString(convert,StandardCharsets.UTF_8).toString();
        }


        public void mineBlock(int difficulty){

            StringBuilder sb = new StringBuilder("");

            for (int i =0; i<difficulty;  i++){
                sb.append("0");
            }

            while (!this.hash.substring(0,difficulty).equals(sb.toString())){
                this.nonce ++;
                this.hash = hash256();
            }
            System.out.println("Blocked Mined: " + this.hash);
        }



    @Override
    public String toString() {
        return "[   INDEX: " +   " \n      HASH: "
                + this.hash + " \n TIMESTAMP: "
                + this.timestamp + " \n      DATA: "
                + this.transactions + " \n  PREVHASH: "
                + this.prevHash + " ]\n\n"  ;
    }
}
