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
public class L1DataController {
    Queues queueline;
    String data;
    String cachesize[][]=new String[128][2];
     String datacache[][]=new String[128][2];
     //Constructor to L1DATA
    L1DataController(Queues que){
       this.queueline=que;
    }
    //Method that implement LOAD,STORE,EVICT from L1D.
    public void L1DataToL1Controller(){
    
      // datacache[42][0]="CD123456789";
      //  cachesize[42][0]="10101";
         cachesize[127][0]="01011";
         datacache[127][0]="AB";
   //    System.out.println("in L1D"+queueline.getL1CtoL1DQueue().get(0));  
       String st=queueline.getL1CtoL1DQueue().get(0);
       System.out.println("In L1D, Current instruction is:  "+st);
       String instruction[]=st.split(" ",0);
       List tempList=new ArrayList();
       //  System.out.println("i am in L1D now "+instruction[0]);
       //System.out.println("Instruction about to excuted is "+queueline.getL1CtoL1DQueue().get(0));
       switch(instruction[0]){
           case "Load":
               
                String tag=null;
                int offset;
                int location;
                data=null;
                tag=instruction[1].substring(0, 5);
                offset= Integer.parseInt(instruction[1].substring(12,17));
                location=Integer.parseInt(instruction[1].substring(5, 12),2); 
               //  System.out.println("Current instruction is:  "+st+location);
               if(tag.equalsIgnoreCase(cachesize[location][0])){
                   data=datacache[location][0];
                  
               }
               else if(tag.equalsIgnoreCase(cachesize[location][1])){
                   data=datacache[location][1];
               }
                if(data!=null){
                data=st+" "+data;
               
                tempList.add(data);         
                queueline.setL1DtoL1CQueue(tempList);
                }
                queueline.getL1CtoL1DQueue().remove(0);
                System.out.println("Data read is: "+data+queueline.getL1CtoL1DQueue());
                for(String st1: queueline.getL1CtoL1DQueue()){
                System.out.println("Remaining instructions in L1C2L1DQueue are: "+st1);
                }   
             break;
           case "Store":
               if(!instruction[1].equalsIgnoreCase("Store")){
               String tag1=instruction[1].substring(0, 5);
               int offset1= Integer.parseInt(instruction[1].substring(12,17));
               int location1=Integer.parseInt(instruction[1].substring(5, 12),2);
               String value=instruction[2];
               if(instruction.length==4&& !"Store".equalsIgnoreCase(instruction[0])){
                 value=instruction[3];  
                 
               }
               else if(instruction.length==4&&"Store".equalsIgnoreCase(instruction[0])){
               value=instruction[3];  
               }
               //System.out.println("i am store of L1D"+value+"\t"+queueline.getL1CtoL1DQueue().get(0));    
               if(tag1.equals(cachesize[location1][0])){
               datacache[location1][0]=value;
               }
               else if(tag1.equals(cachesize[location1][1])){
               datacache[location1][0]=value;
               }
               else{
               if(datacache[location1][0]==null){
               datacache[location1][0]=value;
               cachesize[location1][0]=tag1;
               }
               else{
               datacache[location1][1]=value;
               cachesize[location1][1]=tag1;
               }
               
               }
              System.out.println("Value has been stored in L1DataCache: Data is - "+ datacache[location1][0]+" for instrction"+st); 
              queueline.getL1CtoL1DQueue().remove(0);
               }
               else{
                  // System.out.println("++++++"+instruction[2]);
                   String tag1=instruction[2].substring(0, 5);
               int offset1= Integer.parseInt(instruction[2].substring(12,17));
               int location1=Integer.parseInt(instruction[2].substring(5, 12),2);
               String value=instruction[3];
              // System.out.println("i am store of L1D"+value+"\t"+queueline.getL1CtoL1DQueue().get(0));    
               if(tag1.equals(cachesize[location1][0])){
               datacache[location1][0]=value;
               }
               else if(tag1.equals(cachesize[location1][1])){
               datacache[location1][1]=value;
               }
               else{
               if(datacache[location1][0]==null){
               datacache[location1][0]=value;
               cachesize[location1][0]=tag1;}
               else{
               datacache[location1][1]=value;
               cachesize[location1][1]=tag1;
               }}
              System.out.println("Value has been stored in L1DataCache: Data is - "+ datacache[location1][0]+" for instrction"+instruction[0]+location1); 
              queueline.getL1CtoL1DQueue().remove(0); 
               }
              break;
       case "Evict":
            String tag2=instruction[1].substring(0, 5);
            int offset2= Integer.parseInt(instruction[1].substring(12,17),2);
            int location2=Integer.parseInt(instruction[1].substring(5, 12),2);
            int dirtybit=Integer.parseInt(instruction[2]);
            if(dirtybit==0){
                  if (tag2.equalsIgnoreCase(cachesize[location2][0])) {
                    data = datacache[location2][0];

                } else if (tag2.equalsIgnoreCase(cachesize[location2][1])) {
                    data = datacache[location2][1];
                }
                // data=!(data.equals(null))?data.substring(offset2,offset2+Integer.parseInt(instruction[2])):" ";
                L1Victim v = new L1Victim();
                Boolean result = v.victimcachestore(tag2, offset2, data);
                if (result) {
                    System.out.println("Data has been stored into victim cache for instruction" + st);
                }
          }
            else if(dirtybit==1) {
                if (tag2.equalsIgnoreCase(cachesize[location2][0])) {
                    data = datacache[location2][0];

                } else if (tag2.equalsIgnoreCase(cachesize[location2][1])) {
                    data = datacache[location2][1];
                }
                // data=!(data.equals(null))?data.substring(offset2,offset2+Integer.parseInt(instruction[2])):" ";
                WriteBuff v = new WriteBuff();
                Boolean result = v.storebuffer(tag2, offset2, data);
                if (result) {
                    System.out.println("Data has been stored into Write Buffer cache for instruction" + st);
                }
            }
          break;
       } 
    }
}
