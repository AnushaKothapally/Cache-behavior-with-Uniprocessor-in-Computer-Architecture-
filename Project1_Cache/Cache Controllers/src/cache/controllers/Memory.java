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
 * @author srishailamdasari1`
 */
public class Memory {
    Queues queueline;
    Memory(){
    }
    Memory(Queues que) {
        this.queueline = que;
    }
    String datacache[] = new String[4096];
    String data;
  

    public void memory() {
        datacache[2729]="GoogleFacebook";
        datacache[2730]="ABCDEFGHIJKLMNOP";
        datacache[2731]="QWERTQWERTY";
        datacache[2735]="UNIVERSITYNEBRASKA";
    String st = queueline.getL2CtoMemoryQueue()!=null?queueline.getL2CtoMemoryQueue().get(0):null;
    String instruction[] = st!=null?st.split(" ", 0):null;
    List tempList = new ArrayList();
    System.out.println("Current instruction in memory is"+st);
        switch (instruction[0]) {
            case "Load":
                if(instruction[1].equalsIgnoreCase("Store")){
                //System.out.println("Current instruction is:  " + st);
                int offset = Integer.parseInt(instruction[2].substring(12, 17));
                int location = Integer.parseInt(instruction[2].substring(0, 12), 2);
               // System.out.println("Location"+location);
                data = datacache[location];
                // System.out.println("Hello"+data);
                  //  data = !(data.equals(null)) ? data.substring(offset, offset + Integer.parseInt(instruction[2])) : " ";
                System.out.println("Data read from Memory is: " + data + " for instrction" + st);
               if(data!=null){
                   data=st+" "+data;
                tempList.add(data);
                queueline.setMemorytoL2CQueue(tempList);
                // System.out.println("Data read is"+queueline.getL2CtoL2DQueue().get(0));
               }
                queueline.getL2CtoMemoryQueue().remove(0);
                //System.out.println("Removed"+ queueline.getL2CtoMemoryQueue());
//                for(String st1: queueline.getMemorytoL2CQueue()){
//                System.out.println(" Data in L2DtoL2CQueue Queue :"+st1);
//                }

                for (String st1 : queueline.getL2CtoMemoryQueue()) {
                    System.out.println("Remaining instructions in L2CtoMemoryQueue are: " + st1);
                }
                }
                else{
               // System.out.println("Current instruction is:  " + instruction[1]);
                int offset = Integer.parseInt(instruction[1].substring(12, 17));
                int location = Integer.parseInt(instruction[1].substring(0, 12), 2);
              //  System.out.println("Location"+location);
                data = datacache[location];
                // System.out.println("Hello"+data);
                  //  data = !(data.equals(null)) ? data.substring(offset, offset + Integer.parseInt(instruction[2])) : " ";
               // System.out.println("Data read from Memory is: " + data + " for instrction" + st);
               if(data!=null){
                   data=st+" "+data;
                tempList.add(data);
                queueline.setMemorytoL2CQueue(tempList);
                // System.out.println("Data read is"+queueline.getL2CtoL2DQueue().get(0));
               }
                queueline.getL2CtoMemoryQueue().remove(0);
                
             //   System.out.println("Removed"+ queueline.getL2CtoMemoryQueue());
//                for(String st1: queueline.getMemorytoL2CQueue()){
//                System.out.println(" Data in L2DtoL2CQueue Queue :"+st1);
//                }

                for (String st1 : queueline.getL2CtoMemoryQueue()) {
                    System.out.println("Remaining instructions in L2CtoMemoryQueue are: " + st1);
                }
                        }
                break;
            case "Store":
                //System.out.println(queueline.getL2CtoMemoryQueue().get(0));
                int offset1 = Integer.parseInt(instruction[1].substring(12, 17));
                int location1 = Integer.parseInt(instruction[1].substring(0, 12), 2);
                String value = instruction[2];
                datacache[location1] = value;
                System.out.println("Value has been stored in Memory : Data is - " + datacache[location1] + " for instrction" + instruction[0]);
                queueline.getL2CtoMemoryQueue().remove(0);
                break;

        }

    }
    public void StoreDatatoMemory(String Writebackdata,String WBindex,String WBTag){
        WBTag=WBTag+WBindex;
        int location=Integer.parseInt(WBTag,2);
    datacache[location]=Writebackdata;
    }
}
