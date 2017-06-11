/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Lakshman
 */
public class L1Controler {

    Queues ques;
    L1Victim l1v=new L1Victim();
    WriteBuff wb=new WriteBuff();

    public L1Controler(Queues que) {
        this.ques = que;
    }
    int i = 0, k = 0;
    String[] c;

    public String[][] L1cache = new String[128][2];
    public boolean[][] vb = new boolean[128][2];
    public boolean[][] db = new boolean[128][2];
    public int[][] loc = new int[128][2];
   // List<String> tempList = new ArrayList<String>();
    List<String> tempList_avict = new ArrayList<String>();
    List<String> Instr;
    String s;
    int count = 0;
    public void readfromq() {
        //System.out.println("In K==1 " + ques.getL1CtoL2CQueue()+"L!D"+ques.getL1CtoL1DQueue());
       // System.out.println("In L1Contorller instruction received:"+ques.getCPU2L1ConQueue());
       // L1cache[42][0]="10101";
       //vb[42][0]=true;
     
        Instr = ques.getCPU2L1ConQueue();
        int n = Instr.size();
        s = Instr.get(0);//get the ith item of the list anf store it in a string
        while (!ques.getCPU2L1ConQueue().isEmpty() && count < 1&& (s.contains("Instruction") || s.contains("instruction"))) {
            if (s.contains("Instruction") || s.contains("instruction")) {
                count++;
                // System.out.println("In K==1 " + ques.getL1CtoL2CQueue()+"L!D"+ques.getL1CtoL1DQueue());
                ques.getCPU2L1ConQueue().remove(0);

            }
        }
     //   System.out.println("In K==1 " + ques.getL1CtoL2CQueue()+"L!D"+ques.getL1CtoL1DQueue());
            s = Instr.get(0);
           System.out.println("Checking in L1C for Instruction" +s);
            //tempList.add(s);
          // System.out.println("In K==1 " + ques.getL1CtoL2CQueue()+"L!D"+ques.getL1CtoL1DQueue());
//           if(!ques.getL1CtoL1DQueue()){
//               ques.getL1CtoL1DQueue().remove(0);
//           }
           // System.out.println("i am in L!C checking templist************ "+ tempList.get(0) );
       //    System.out.println(tempList.get(0) + " this is s***************");
            c = s.split(" "); // as the input format is Read " " address i am spliting it into 2 halfs 
            //storing the binary address into an integer
           //System.out.println("i am c of zero" +s);
             // System.out.println("In K==1** " + ques.getL1CtoL2CQueue()+"L!D"+ques.getL1CtoL1DQueue());
            k = B2Iconvert(c);// converting it to inter to get array index.
      //      System.out.println("conversion also done");
             // System.out.println("In K==1 " + ques.getL1CtoL2CQueue()+"L!D"+ques.getL1CtoL1DQueue());
          ques.getCPU2L1ConQueue().remove(0);

    }
    
    public void L2ctol1c() {
        //System.out.println("In K==1 " + ques.getL1CtoL2CQueue()+"L!D"+ques.getL1CtoL1DQueue());
        
        L1cache[42][1]="10101";
        vb[42][1]=true;
        int count = 0;
        Instr = ques.getL2CtoL1CQueue();
        
        int n = Instr.size();
        s = Instr.get(0);//get the ith item of the list anf store it in a string
        System.out.println("in L1C, data received from L2c"+s);
        //System.out.println("In K==1eeee " +Instr);
            //s = Instr.get(0);
  //           System.out.println("In K==1 " + ques.getL1CtoL2CQueue()+"L!D"+ques.getL1CtoL1DQueue());
           // tempList.add(s);
          //  ques.getL2CtoL1CQueue().remove(0);
           
//           if(!ques.getL1CtoL1DQueue()){
//               ques.getL1CtoL1DQueue().remove(0);
//           }
           // System.out.println("i am in L!C checking templist************ "+ tempList.get(0) );
       //    System.out.println(tempList.get(0) + " this is s***************");
            c = s.split(" "); // as the input format is Read " " address i am spliting it into 2 halfs 
            //storing the binary address into an integer
        ///   System.out.println("i am c of zero" + c[1]+s+c.length);
             // System.out.println("In K==1** " + ques.getL1CtoL2CQueue()+"L!D"+ques.getL1CtoL1DQueue());
            k = B2Iconvert(c);// converting it to inter to get array index.
          //  System.out.println("conversion also done");
           // System.out.println("In K==1 " + ques.getL1CtoL2CQueue()+"L!D"+ques.getL2CtoL1CQueue());
              //ques.getL2CtoL1CQueue().remove(0);
             
        

    }

    public int B2Iconvert(String[] d) {
        int tag, index, offset;
        //System.out.println("in method to convert  ***"+d.length+" \t"+d[2]);
        String a, b, c, address;
        if(d.length==3 ||!d[1].equalsIgnoreCase("Store")){
        a = d[1].substring(0, 5);
        b = d[1].substring(5, 12);
        c = d[1].substring(12, 16);

        tag = Integer.parseInt(a, 2);
        index = Integer.parseInt(b, 2);
        //System.out.println("index is " + d[0]+(d.length==3)+a+"a is");
        offset = Integer.parseInt(c, 2);
        }
        else
        {
        a = d[2].substring(0, 5);
        b = d[2].substring(5, 12);
        c = d[2].substring(12, 16);

        tag = Integer.parseInt(a, 2);
        index = Integer.parseInt(b, 2);
        //System.out.println("index is " + d[0]+(d.length==3)+a+"a is");
        offset = Integer.parseInt(c, 2);
        }
        if (d[0].equalsIgnoreCase("Load")) {
            if(d.length==3){
        //   System.out.println("In K==1 " + ques.getL1CtoL2CQueue()+"L!D");
            k = read(a, b, c, tag, index, offset,d);
          //  tempList.remove(0);
//          if(ques.getL1CtoL1DQueue()!=null){
//              ques.getL1CtoL1DQueue().remove(0);
//            }
            
           // System.out.println("checking done");
            
            }
            else{// need to see if i get the data from L2C*************
                
                storeaftermiss(a, b, c, tag, index, offset);
           //tempList.add(d[3]);//sending the string to CPU to print.
           List L1CtoCPU=new ArrayList();
           L1CtoCPU.add(s);
            ques.setL1CtoCPUQueue(L1CtoCPU);
    // System.out.println("this check is to che"+ques.getL2CtoL1CQueue());
            ques.getL2CtoL1CQueue().remove(0);
       // System.out.println("this check is to che"+ques.getL2CtoL1CQueue());     
//System.out.println("this check is to check L1c to CPU"+ques.getL1CtoCPUQueue().get(0)+"after clear");
           // d[0]="Store";//.concat(d[1]);
//            d[0]=d[0].concat(" ");
//            d[0]=d[0].concat(d[2]);
//            d[0]=d[0].concat(" ");
//            d[0]=d[0].concat(d[3]);
           // String st=d[0]+" "+d[1]+" "+d[2]+" "+d[3];
                String st;
            if("Store".equals(d[1]) && "Load".equals(d[0])){
             st=d[1]+" "+d[2]+" "+d[3];
           }
           else if("Store".equals(d[0])){
             st=d[0]+" "+d[1]+" "+d[2]+" "+d[3];
           }
           else{
                st="Store"+" "+d[1]+" "+d[2]+" "+d[3];
           }
            System.out.println("writing data to L1d which got from L2C: ");
             tempList_avict.add(st);
           // System.out.println("Checking st"+st);
            // System.out.println("Cheking templist"+tempList_avict.get(0));
            ques.setL1CtoL1DQueue(tempList_avict);
            //ques.getL2CtoL1CQueue().remove(0);
          //  System.out.println("this check is to check L1c ot CPU"+ques.getL1CtoCPUQueue().get(0)+"after clear");
            //d[0]="Store";//.concat(d[1]);
            }
        } else if (d[0].equalsIgnoreCase("Store")) {
            
              //  System.out.println("this store call is from CPU **********");
                store(a, b, c, tag, index, offset,d);
                //tempList.remove(0);
               // ques.getCPU2L1ConQueue().remove(0);
//                System.out.println("in L1D"+ques.getL1CtoL1DQueue().get(0));
            
        }
        else{
            
        }
        return index;
    }

    public int read(String a, String b, String c, int tag, int index, int offset, String[] add) {
        //System.out.println("tag is "+a+" "+L1cache[index][0]);
        
        if ((a.equals(L1cache[index][0])) && (vb[index][0] == true)) {
            System.out.println("Data found in L1C, waiting to load from L1D"+s);
//            tempList.add(s);
       List L1CtoL1D=new ArrayList();
       L1CtoL1D.add(s);
            ques.setL1CtoL1DQueue(L1CtoL1D);
            //  System.out.println("hi"+ques.getL1CtoL1DQueue());
            return 0;
        } 
        else if ((a.equals(L1cache[index][1])) && (vb[index][1] == true)) 
        {
            System.out.println("Data found in L1C, waiting to load from L1D"+s);
            List L1CtoL1D=new ArrayList();
                L1CtoL1D.add(s);
            //tempList.add(s);
            ques.setL1CtoL1DQueue(L1CtoL1D);
         //   System.out.println("hi"+ques.getL1CtoL1DQueue());
            return 0;
        } 
        else 
        {
            System.out.println("Data not present in L1C, checking in L1_victim cache: "+s);
            String t = l1v.victimcache(add);
           // System.out.println(t);
            if (t!=null)
            {
                List L1CtoCPU=new ArrayList();
                L1CtoCPU.add(t);
                
                //tempList.add(s);
                ques.setL1CtoCPUQueue(L1CtoCPU);
                return 0;
            }
            else
            {
                System.out.println("Data not present in L1C,checking in L1_Data buffer: "+s);
                t = wb.bufferload(add);
                //System.out.println(t);
                if (t!=null) {
                     List L1CtoCPU=new ArrayList();
                      L1CtoCPU.add(t);
                   // tempList.add(s);
                    ques.setL1CtoCPUQueue(L1CtoCPU);
                    return 0;
                } 
                else
                {
                    System.out.println("Data not present in L1C, checking in L2cache"+s);
                    List<String> L1ctoL2c=new ArrayList<String>();
                    L1ctoL2c.add(s);
                    //tempList.add(s);
                   // System.out.println("checking in L2cache"+ques.getL1CtoL1DQueue());
                    ques.setL1CtoL2CQueue(L1ctoL2c);
                    //System.out.println("this is in templist"+ques.getL1CtoL2CQueue().get(0));                  
                }
            }

        }
   return 0;}
    public int store(String a, String b, String c, int tag, int index, int offset,String[] ins) {
        
       if(ins.length<=3){
      //   System.out.println("in store from CPU"+a+L1cache[index][0]+index+a.equals(L1cache[index][0]+vb[index][0]));
           if(a.equals(L1cache[index][0]) && (vb[index][0]==true))
            {
            vb[index][0]=true;
            L1cache[index][0]=a;
            db[index][0]=false;
            List L1CtoL1D=new ArrayList();
            L1CtoL1D.add(s);
            ques.setL1CtoL1DQueue(L1CtoL1D);
            System.out.println("Tag found, sending data to Store in L1 cache");
            return 0;
        } 
        else if(a.equals(L1cache[index][1]) && (vb[index][1]==true))
            {
            vb[index][1]=true;
            L1cache[index][1]=a;
            db[index][1]=false;
            List L1CtoL1D=new ArrayList();
            L1CtoL1D.add(s);
            System.out.println("Tag found, sending data to Store in L1 Data cache");
            ques.setL1CtoL1DQueue(L1CtoL1D);
            return 0;
        }
        else{
            System.out.println("Data not present in L1, sending to check in L2 cache");
            if((vb[index][1]==false) ||(vb[index][0]==false)){
                String l1c2l2c="Load ".concat(s);
                //tempList.remove(0);
                List L1CtoL2C=new ArrayList();
                L1CtoL2C.add(l1c2l2c);
                ques.setL1CtoL2CQueue(L1CtoL2C);
                return 0;
            }
            else if((vb[index][1]==true) ||(vb[index][0]==true)){
                if((db[index][1]==false) && (db[index][0]==false)){
                    
                }
            }
            
            
        }
       }
       else{
          // System.out.println("in store from L2c after L1c store miss"+vb[index][0]+vb[index][1]+index);
           if(vb[index][0]==false){
           //    System.out.println("Here I am");
           L1cache[index][0]=a;
           vb[index][0]=true;
           List L1CtoL1D=new ArrayList();
           L1CtoL1D.add(s);
            ques.setL1CtoL1DQueue(L1CtoL1D);
            ques.getL2CtoL1CQueue().remove(0);
           //db[index][0]=true;
           return 0;
           }
           else if(vb[index][1]==false){
            //   System.out.println("Here I am");
           L1cache[index][1]=a;
           vb[index][1]=true;
           List L1CtoL1D=new ArrayList();
           L1CtoL1D.add(s);
            ques.setL1CtoL1DQueue(L1CtoL1D);
            ques.getL2CtoL1CQueue().remove(0);
           //db[index][1]=true;
           return 0;
           }else if(a.equals(L1cache[index][0]) && (vb[index][0]==true))
            {
               //  System.out.println("in store from L2c after L1c store miss1");
            vb[index][1]=true;
            L1cache[index][1]=a;
            db[index][1]=false;
            List L1CtoL1D=new ArrayList();
            s="Store"+" "+s;
            L1CtoL1D.add(s);
            ques.setL1CtoL1DQueue(L1CtoL1D);
             ques.getL2CtoL1CQueue().remove(0);
            return 0;
        }
           else if(a.equals(L1cache[index][1]) && (vb[index][1]==true))
            {
               //  System.out.println("in store from L2c after L1c store miss2");
            vb[index][1]=true;
            L1cache[index][1]=a;
            db[index][1]=false;
            List L1CtoL1D=new ArrayList();
            s="Store"+" "+s;
            L1CtoL1D.add(s);
            ques.setL1CtoL1DQueue(L1CtoL1D);
             ques.getL2CtoL1CQueue().remove(0);
            return 0;
        }
           else{
              // System.out.println("i am in store avict case");
            String tag1,send_data;
            tag1=L1cache[index][0];
            send_data="Evict ".concat(tag1);
            send_data=send_data.concat(b);
            send_data=send_data.concat("00000 ");
            if(!db[index][0]){
                send_data=send_data.concat("0");
            }else{
                send_data=send_data.concat("1");
            }
            db[index][0]=true;
            L1cache[index][0]=a;
            tempList_avict.add(send_data);
            System.out.println("tempList_avict"+tempList_avict);
            ques.setL1CtoL1DQueue(tempList_avict);
            tempList_avict.remove(0);
            ques.getL2CtoL1CQueue().remove(0);
            System.out.println("tempList_avict value is"+ques.getL2CtoL1CQueue());
       }
       }
    return 0;
    }
    
    public void storeaftermiss(String a, String b, String c, int tag, int index, int offset){
       System.out.println("Receved data from L2 for instruction : "+s);
        if(vb[index][0]==false){
           // System.out.println("Here I am in after miss");
            vb[index][0]=true;
            L1cache[index][0]=a;
            db[index][0]=false;
            List L1CtoL1D=new ArrayList();
            L1CtoL1D.add(s);
            // System.out.println("Here I am in after miss"+s);
          //  ques.setL1CtoL1DQueue(L1CtoL1D);
            
        }
        else if(vb[index][1]==false){
          //  System.out.println("Here I am in after miss1");
            vb[index][1]=true;
            L1cache[index][1]=a;
            db[index][1]=false;
            List L1CtoL1D=new ArrayList();
            L1CtoL1D.add(s);
          //  ques.setL1CtoL1DQueue(L1CtoL1D);
           // ques.setL1CtoL1DQueue(tempList);
           
        }
        else{
            String tag1,send_data;
            tag1=L1cache[index][0];
            send_data="Evict ".concat(tag1);
            send_data=send_data.concat(b);
            send_data=send_data.concat("00000 ");
            if(!db[index][0]){
                send_data=send_data.concat("0");
            }else{
                send_data=send_data.concat("1");
            }
         
            L1cache[index][0]=a;
            tempList_avict.add(send_data);
            ques.setL1CtoL1DQueue(tempList_avict);
            tempList_avict.remove(0);
            List L1CtoL1D=new ArrayList();
            L1CtoL1D.add(s);
            ques.setL1CtoL1DQueue(L1CtoL1D);
           // ques.setL1CtoL1DQueue(tempList);
            
        }
        
    }
    public void notempty(){
        String received=ques.getL1DtoL1CQueue().get(0);
        System.out.println("Data received from L1D, and sending to CPU"+received);
        List L1CtoCPU=new ArrayList();
        L1CtoCPU.add(received);
        ques.setL1CtoCPUQueue(L1CtoCPU);
       System.out.println("Data received from L1D, and sending to CPU"+L1CtoCPU+"size is"+ques.getL1CtoCPUQueue());

       // System.out.println("Print please"+ques.getL1CtoCPUQueue());
        ques.getL1DtoL1CQueue().remove(0);
    }
}
