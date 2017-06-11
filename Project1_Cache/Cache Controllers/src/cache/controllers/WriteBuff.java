/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.controllers;

/**
 *
 * @author Lakshman
 */
public class WriteBuff {
      String Buffercache[][]=new String[2][2];
    String data;
    String storeValue;
    String storeTag;
    public String bufferload(String []address){
        String tag=address[1];
      //  Buffercache[0][1]="10101";
        //Buffercache[0][0]="ABCDEF";
     System.out.println("In L1 write buffer:");
         tag=tag.substring(0, 5);
        if (tag.equals(Buffercache[0][1])) {
            data = Buffercache[0][0];
             System.out.println("Data from L1 WriteBuffer  is : "+address[0]+" "+address[1]+" "+address[2]+" "+data);
            return address[0]+" "+address[1]+" "+address[2]+" "+data;

        } else if (tag.equals(Buffercache[1][1])) {
            data = Buffercache[1][0];
             System.out.println("Data from L1 WriteBuffer  is : "+address[0]+" "+address[1]+" "+address[2]+" "+data);
            return address[0]+" "+address[1]+" "+address[2]+" "+data;
        }

        return null;
    }
 public boolean storebuffer(String tag,int offset,String data){
     storeValue=data;
     if (Buffercache[0][0] == null) {
         Buffercache[0][0] = storeValue;
         Buffercache[0][1] = storeTag;
         return true;
     } else if (Buffercache[1][0] == null) {
         Buffercache[1][0] = storeValue;
         Buffercache[1][1] = storeTag;
         return true;
     } else if(Buffercache[0][0] != null){
         

     }
 
     return false;
 }  
}
