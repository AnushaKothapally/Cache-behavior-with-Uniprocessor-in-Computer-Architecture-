/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.controllers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author srishailamdasari1
 */
public class L2Buffer {
    Queues ques;
    L2Buffer(){
    }
    L2Buffer(Queues que){
        this.ques=que;
    }
    String Buffercache[][]=new String[2][2];
    String data;
    String storeValue;
    String storeTag;
    String indexValue[]=new String[2];
    Memory memory=new Memory();
    public String bufferload(String []address){
       Buffercache[0][1]="1010";
        Buffercache[0][0]="L2WriteBuffercache";
System.out.println("In L2 WriteBuffer: ");
        String tag=address[1];
        tag=tag.substring(0,4);
        if (tag.equals(Buffercache[0][1])) {
            data = Buffercache[0][0];
             System.out.println("Data from L2 WriteBuffer  is : "+address[0]+" "+address[1]+" "+address[2]+" "+data);
            return address[0]+" "+address[1]+" "+address[2]+" "+data;
           

        } else if (tag.equals(Buffercache[1][1])) {
            data = Buffercache[1][0];
             System.out.println("Data from L2 WriteBuffer  is : "+address[0]+" "+address[1]+" "+address[2]+" "+data);
            return address[0]+" "+address[1]+" "+address[2]+" "+data;
        }

        return null;
    }
 public boolean storebuffer(String tag,int offset,String data,String index){
     storeValue=data;
     if (Buffercache[0][0] == null) {
         Buffercache[0][0] = storeValue;
         Buffercache[0][1] = storeTag;
         indexValue[0]=index;
         return true;
     } else if (Buffercache[1][0] == null) {
         Buffercache[1][0] = storeValue;
         Buffercache[1][1] = storeTag;
         indexValue[0]=index;
         return true;
     } else if (Buffercache[0][0] != null) {
         
         String Writebackdata=Buffercache[0][0];
         String WBindex=indexValue[0];
         String WBTag=Buffercache[0][1];
         Memory m=new Memory();
         m.StoreDatatoMemory(Writebackdata,WBindex,WBTag);/*Cause for L2 and L1 Mutual inclusion*/
        // RemoveinL1(WBTag);
        String instr="Store"+" "+WBTag+WBindex+"00000"+" "+"NULL";
        List L2BuffertoL1=new ArrayList();
        L2BuffertoL1.add(instr);
        ques.setL2CtoL1CQueue(L2BuffertoL1); /*Fix for Mutual inclusion*/
         Buffercache[0][0] = storeValue;
         Buffercache[0][1] = storeTag;

     }
 
     return false;
 }
    
}
