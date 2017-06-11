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
public class L2DataController {
    Queues queueline;
    String data;
     String cachesize[][]=new String[256][2];
     String datacache[][]=new String[256][2];
     
    L2DataController( Queues que){
        this.queueline=que;
    }
    public void L2Data(){
        //System.out.println("**************");
      //  datacache[170][0] = "CDghyuio";
      //  cachesize[170][0] = "1010";
        cachesize[127][0] = "01011";
        datacache[127][0] = "AB";
        String st = queueline.getL2CtoL2DQueue().get(0);
       // System.out.println("In L2D to save"+st);
        String instruction[] = st.split(" ", 0);
        List tempList = new ArrayList();
         System.out.println("Current instruction in L2d is:"+ st);
        switch (instruction[0]) {
            case "Load":
               
                String tag=null;
                int offset=0,location=0;
                if(!instruction[1].equalsIgnoreCase("store")){
                     ///System.out.println("Current instruction in L2d is:^^^%%%$$$$$$&&&&***  " + st);
                 tag = instruction[1].substring(0, 4);
                 offset = Integer.parseInt(instruction[1].substring(12, 17));
                 location = Integer.parseInt(instruction[1].substring(4, 12), 2);
                data=null;
                //System.out.println(location+"  ");
                // System.out.println("Current instruction in L2d is:^^^%%%$$$$$$&&&&***  " + st+cachesize[location][0]);
                if (tag.equalsIgnoreCase(cachesize[location][0])) {
                    data = datacache[location][0];

                } else if (tag.equalsIgnoreCase(cachesize[location][1])) {
                    data = datacache[location][1];
                }
                // System.out.println("Hello"+data);
               // data = !(data.equals(null)) ? data.substring(offset, offset + Integer.parseInt(instruction[2])) : " ";
               if(data!=null){
                System.out.println("Data read from L2D is: " + data + " for instrction" + st);
                data=st+" "+data;        
                tempList.add(data);
                queueline.setL2DtoL2CQueue(tempList);
               }
                // System.out.println("Data read is"+queueline.getL2CtoL2DQueue().get(0));
                queueline.getL2CtoL2DQueue().remove(0);
//                for(String st1: queueline.getL2DtoL2CQueue()){
//                System.out.println(" Data in L2DtoL2CQueue Queue :"+st1);
//                }

                for (String st1 : queueline.getL2CtoL2DQueue()) {
                    System.out.println("Remaining instructions in L2CtoL2DQueue are: " + st1);
                }
                
                }else{
                    tag = instruction[2].substring(0, 4);
                 offset = Integer.parseInt(instruction[2].substring(12, 17));
                 location = Integer.parseInt(instruction[2].substring(4, 12), 2);
                data=null;
                //System.out.println(location+"  ");
                if (tag.equalsIgnoreCase(cachesize[location][0])) {
                    data = datacache[location][0];

                } else if (tag.equalsIgnoreCase(cachesize[location][1])) {
                    data = datacache[location][1];
                }
                // System.out.println("Hello"+data);
               // data = !(data.equals(null)) ? data.substring(offset, offset + Integer.parseInt(instruction[2])) : " ";
               if(data!=null){
                System.out.println("Data read from L2D is: " + data + " for instrction" + st);
                data=instruction[1]+" "+instruction[2]+" "+instruction[3]+" "+data;        
                tempList.add(data);
                queueline.setL2DtoL2CQueue(tempList);
               }
                // System.out.println("Data read is"+queueline.getL2CtoL2DQueue().get(0));
                queueline.getL2CtoL2DQueue().remove(0);
//                for(String st1: queueline.getL2DtoL2CQueue()){
//                System.out.println(" Data in L2DtoL2CQueue Queue :"+st1);
//                }

//                for (String st1 : queueline.getL2CtoL2DQueue()) {
//                    System.out.println("Remaining instructions in L2CtoL2DQueue are: " + st1);
//                }
                
                }
                break;
            case "Store":
                
                String instr=queueline.getL2CtoL2DQueue().get(0);
                
                //System.out.println("L2D "+queueline.getL2CtoL2DQueue().get(0)+instruction.length);
                String tag1 = instruction[1].substring(0, 4);
                int offset1 = Integer.parseInt(instruction[1].substring(12, 17));
                int location1 = Integer.parseInt(instruction[1].substring(4, 12), 2);
                if(!instruction[1].equalsIgnoreCase("Store")){
                    
                String value = instruction[2];
                if(instruction.length==4){
                value=instruction[3];
                 //System.out.println("Value"+value);
                }
                if (null == datacache[location1][0]) {
                    cachesize[location1][0]=tag1;
                    datacache[location1][0] = value;
                } else if (null == datacache[location1][0]) {
                    cachesize[location1][1]=tag1;
                    datacache[location1][1] = value;
                }
                System.out.println("Value has been stored in L2DataCache: Data is - " + value + " for instrction" + st);
                queueline.getL2CtoL2DQueue().remove(0);
                }else{
                String value = instruction[2];
                if (null == datacache[location1][0]) {
                    cachesize[location1][0]=tag1;
                    datacache[location1][0] = value;
                } else if (null == datacache[location1][0]) {
                    cachesize[location1][1]=tag1;
                    datacache[location1][1] = value;
                }
                System.out.println("Value has been stored in L2DataCache: Data is - " + value + " for instrction" + st);
                queueline.getL2CtoL2DQueue().remove(0);
                }
                break;
             case "Evict":
            String tag2=instruction[1].substring(0, 4);
            int offset2= Integer.parseInt(instruction[1].substring(12,17),2);
            int location2=Integer.parseInt(instruction[1].substring(4, 12),2);
            int dirtybit=Integer.parseInt(instruction[2]);
             if(dirtybit==1) {
                if (tag2.equalsIgnoreCase(cachesize[location2][0])) {
                    data = datacache[location2][0];

                } else if (tag2.equalsIgnoreCase(cachesize[location2][1])) {
                    data = datacache[location2][1];
                }
                // data=!(data.equals(null))?data.substring(offset2,offset2+Integer.parseInt(instruction[2])):" ";
                L2Buffer v = new L2Buffer();
                Boolean result = v.storebuffer(tag2, offset2, data,instruction[1].substring(4, 12));
                if (result) {
                    System.out.println("Data has been stored into L2 Write Buffer cache for instruction: " + st);
                }
            }
          break;
        }

    }
    
}
