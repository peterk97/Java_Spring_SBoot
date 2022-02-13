package org.example.service;

import java.util.ArrayList;

@lombok.Data
public class BlockChain {

    //== Fields ==
    private ArrayList<Block> chain;
    private ArrayList<Data> pendingTransactions;
    private int difficulty = 2;
    private int miningRewards = 100;

    //== Constructor ==
   public BlockChain(){
       this.chain = new ArrayList<>();
       this.pendingTransactions = new ArrayList<>();
       chain.add(createGenesisBlock());
    }


    //== Methods ==

    private Block createGenesisBlock(){
       Block block = new Block();
       block.setPrevHash("0");
       return block;
    }

    public Block getLatestBlock() {
       return this.chain.get(this.chain.size()-1);
    }

//    public void addNewBlock(Block newBlock){
//       newBlock.setPrevHash(getLatestBlock().getHash());
//      // newBlock.setHash(newBlock.hash256());
//        newBlock.mineBlock(this.difficulty);
//       this.chain.add(newBlock);
//    }

    public void minePendingTransaction(String miningRewardAddress){
       Block newBlock = new Block();
       newBlock.setPrevHash(getLatestBlock().getHash());
       for (Data trans : this.pendingTransactions){
           newBlock.getTransactions().add(trans);
       }

       newBlock.mineBlock(this.difficulty);

        this.chain.add(newBlock);
        System.out.println("Block Mined!");

        this.pendingTransactions.clear();
        pendingTransactions.add(new Data("",miningRewardAddress,this.miningRewards));
    }


    public void createTransaction(Data transaction){
       this.pendingTransactions.add(transaction);
    }

    public int getBalanceOfAddress(String address){
       int balance = 0;

       for(Block block : this.chain){
           for (Data transaction : block.getTransactions()){
               if (transaction.getFrom().equals(address)){
                   balance -= transaction.getAmount();
               }

               if (transaction.getTo().equals(address)){
                   balance+=transaction.getAmount();
               }
           }
       }

       return balance;
    }


    public boolean isChainValid(){
       for (int i = 1; i < this.chain.size(); i++){
           Block block = chain.get(i);
           Block prevBlock = chain.get(i-1);

           if (!block.getHash().equals(block.hash256())){
               System.out.println("Invalid Chain !!!");
               return false;
           }

           if (!block.getPrevHash().equals(prevBlock.getHash())){
               System.out.println("Invalid Chain !!!");
               return false;
           }
       }
        System.out.println("Your Chain is Valid!");
       return true;
    }


    @Override
    public String toString() {
        return "BlockChain{\n" + chain + "}";
    }
}
