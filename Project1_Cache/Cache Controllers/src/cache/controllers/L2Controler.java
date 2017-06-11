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
 * @author Lakshman
 */
class L2Controler {
    Queues ques;
     L1Victim l1v=new L1Victim();
    WriteBuff wb=new WriteBuff();
    L1Controler l1c;
            
    L2Controler(Queues ques) {
        this.ques=ques;
    }
    int i = 0, k = 0;
    String[] c;

    public String[][] L2cache = new String[256][2];

    public boolean[][] vb = new boolean[256][2];
    public boolean[][] db = new boolean[256][2];
    public int[][] loc = new int[256][2];
   // List<String> tempList = new ArrayList<String>();
    List<String> tempList_avict = new ArrayList<String>();
    List<String> Instr_l2c;
    L2Buffer l2b=new L2Buffer(ques);
     String s;
    public void readfroml1c() {
       // L2cache[170][1]="1010";
       // vb[170][1]=true;
        
        Instr_l2c = ques.getL1CtoL2CQueue();
        int n = Instr_l2c.size();
       //get the ith item of the list anf store it in a string
         
        s =Instr_l2c.get(0);
        System.out.println("in L2C, instruction received from L1c is: " +s);
       // System.out.println("in L2C from L1c"+ques.getL1CtoL2CQueue());
   //     System.out.println("this is S in L2" + s);
       // tempList.add(s);

    //    System.out.println("i am in L2C checking templist************ " + tempList.get(0));
    //    System.out.println(s + " this is s***************");
        c = s.split(" "); // as the input format is Read " " address i am spliting it into 3parts 
        //storing the binary address into an integer
     //   System.out.println("i am c of zero" + c[0]);
        k = B2Iconvert(c);// converting it to inter to get array index.
       // System.out.println("conversion in L2 done"+ques.getL1CtoL2CQueue().get(0));
        ques.getL1CtoL2CQueue().remove(0);
        //System.out.println("conversion in L2 done"+ques.getL1CtoL2CQueue().get(0));
        

    }
      public void readfrommemory() {
        
        Instr_l2c = ques.getMemorytoL2CQueue();
        int n = Instr_l2c.size();
        String s;//get the ith item of the list anf store it in a string
        s = ques.getMemorytoL2CQueue().get(0);
        System.out.println("Data read from Memory for instruction is: "+ s );
     //   System.out.println("this is S in L2" + s);
      //  tempList.add(s);

        //System.out.println("i am in L2C checking templist************ " + s);
    //    System.out.println(s + " this is s***************");
        c = s.split(" "); // as the input format is Read " " address i am spliting it into 3parts 
        //storing the binary address into an integer
       // System.out.println("i am c of zero" + c[0]);
       // System.out.println("i am in L2C checking templist************ " + c[3]);
        k = B2Iconvert1(c);// converting it to inter to get array index.
       // System.out.println("conversion in L2 done");
       ques.getMemorytoL2CQueue().remove(0);

    }
          public int B2Iconvert(String[] d) {
        int tag, index, offset;
     //   System.out.println("in method to convert  ***"+d.length);
        String a, b, c, address;
        //System.out.println(d[1]);
        if(d[1].equalsIgnoreCase("Store")){
        a = d[2].substring(0, 4);
        b = d[2].substring(4, 12);
        c = d[2].substring(12, 16);
        tag = Integer.parseInt(a, 2);
        index = Integer.parseInt(b, 2);
       // System.out.println("index is is L2c store containing " + index);
        offset = Integer.parseInt(c, 2);
        }
        else{
        a = d[1].substring(0, 4);
        b = d[1].substring(4, 12);
        c = d[1].substring(12, 16);
        tag = Integer.parseInt(a, 2);
        index = Integer.parseInt(b, 2);
        //System.out.println("index is " + index);
        offset = Integer.parseInt(c, 2);
        }
        
        if (d[0].equalsIgnoreCase("Load")) {
           // k = read(a, b, c, tag, index, offset,d);
            if(d.length<=4){
                
                k = read(a, b, c, tag, index, offset,d);
           
            //System.out.println("i just removed"+ques.getL2CtoL2DQueue());
                       
            }
            else if(ques.getMemorytoL2CQueue()!=null){// need to see if i get the data from memory*************
                
            storeaftermiss(a, b, c, tag, index, offset);
            String received_from_memory=ques.getMemorytoL2CQueue().get(0);
            List L2CtoL1C=new ArrayList();
            L2CtoL1C.add(received_from_memory);
            System.out.println("Data received from Memory, Loading into L2"+received_from_memory);
            //tempList.add(received_from_memory);
            ques.setL2CtoL1CQueue(L2CtoL1C);
//            d[0]="Store ".concat(d[1]);
//            d[0]=d[0].concat(" ");
//            d[0]=d[0].concat(d[2]);
//            d[0]=d[0].concat(" ");
//            d[0]=d[0].concat(d[3]);
           // tempList.remove(0);
            String st=d[1]+" "+d[2]+" "+d[3];
            System.out.println("writing data into L2Data cache: "+st);
            List L2CtoL2D=new ArrayList();
            L2CtoL2D.add(st);
            //tempList.add(d[0]); //to send data);
            ques.setL2CtoL2DQueue(L2CtoL2D);
            
//            ques.getL1CtoL2CQueue().remove(0);
            
            }
        } else if (d[0].equalsIgnoreCase("Store")) {// i think its not necessary????????????????
            
              //  System.out.println("this call is from CPU in L2D**********");
            //    store(a, b, c, tag, index, offset);
                //ques.getCPU2L1ConQueue().remove(0);
            
        }
     
        return index;
    }
   
    public int B2Iconvert1(String[] d) {
        int tag, index, offset;
       // System.out.println("in method to convert 111  ***"+d.length+d[3]);
        String a, b, c, address;
        //System.out.println(d[1]);
        if(d[1].equalsIgnoreCase("Store")){
        a = d[2].substring(0, 4);
        b = d[2].substring(4, 12);
        c = d[2].substring(12, 16);
        tag = Integer.parseInt(a, 2);
        index = Integer.parseInt(b, 2);
     //   System.out.println("index is is L2c store containing " + index);
        offset = Integer.parseInt(c, 2);
        }
        else{
        a = d[1].substring(0, 4);
        b = d[1].substring(4, 12);
        c = d[1].substring(12, 16);
        tag = Integer.parseInt(a, 2);
        index = Integer.parseInt(b, 2);
       // System.out.println("index is " + index+"iiii");
        offset = Integer.parseInt(c, 2);
        }
        
        if (d[0].equalsIgnoreCase("Load")) {
           // k = read(a, b, c, tag, index, offset,d);
            if(d.length<4){
                
                k = read(a, b, c, tag, index, offset,d);
           
           // System.out.println("i just removed"+ques.getL2CtoL2DQueue());
                       
            }
            else if(ques.getMemorytoL2CQueue()!=null){// need to see if i get the data from memory*************
                
            storeaftermiss(a, b, c, tag, index, offset);
            String received_from_memory=ques.getMemorytoL2CQueue().get(0);
            List L2CtoL1C=new ArrayList();
            L2CtoL1C.add(received_from_memory);
            System.out.println("writing data to L2d which got from memory"+received_from_memory);
            //tempList.add(received_from_memory);
            ques.setL2CtoL1CQueue(L2CtoL1C);
//            d[0]="Store ".concat(d[1]);
//            d[0]=d[0].concat(" ");
//            d[0]=d[0].concat(d[2]);
//            d[0]=d[0].concat(" ");
//            d[0]=d[0].concat(d[3]);
           // tempList.remove(0);
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
            //System.out.println("writing data to L2d which got from memory"+st);
            List L2CtoL2D=new ArrayList();
            L2CtoL2D.add(st);
            //tempList.add(d[0]); //to send data);
            ques.setL2CtoL2DQueue(L2CtoL2D);
            
//            ques.getL1CtoL2CQueue().remove(0);
            
            }
        } else if (d[0].equalsIgnoreCase("Store")) {// i think its not necessary????????????????
            
                //System.out.println("this call is from CPU in L2D**********");
            //    store(a, b, c, tag, index, offset);
                //ques.getCPU2L1ConQueue().remove(0);
            
        }
     
        return index;
    }
    
    public int read(String a, String b, String c, int tag, int index, int offset, String[] add) {
        if ((a.equals(L2cache[index][0])) && (vb[index][0] == true)) {
            System.out.println("Data found in L2 cache,  so waiting to Load from L2D"+s);
            List L2toL2D=new ArrayList();
            L2toL2D.add(s);
            ques.setL2CtoL2DQueue(L2toL2D);
              //System.out.println("hi"+ques.getL2CtoL2DQueue());
            return 0;
        } 
        else if ((a.equals(L2cache[index][1])) && (vb[index][1] == true)) 
        {
            System.out.println("Data found in L2 cache,  so waiting to Load from L2D"+s);
           List L2CtoL2D=new ArrayList();
           L2CtoL2D.add(s);
            ques.setL2CtoL2DQueue(L2CtoL2D);
            //System.out.println("hi"+ques.getL2CtoL2DQueue());
            return 0;
        } 
        else 
        {
            System.out.println("Data not found in L2 cache,checking in L2 write buffer"+s);
            String t = l2b.bufferload(add);
           // System.out.println("From L2c"+t);
               if (t!=null) {
                   List L2CtoL1C=new ArrayList();
                   L2CtoL1C.add(t);
                    ques.setL2CtoL1CQueue(L2CtoL1C);
                    return 0;
                } 
                else
                {
                    System.out.println("Data not found in L2 cache,going to Memory");
                    List L2CtoMemory=new ArrayList();
                   L2CtoMemory.add(s);
                    ques.setL2CtoMemoryQueue(L2CtoMemory);
                   // System.out.println("going to Memorty ");
                    return 1;
                }
            }

        }
    public void storeaftermiss(String a, String b, String c, int tag, int index, int offset){
    //System.out.println("in store from memory**********");
        if(vb[index][0]==false){
            vb[index][0]=true;
            L2cache[index][0]=a;
            db[index][0]=false;
                       
        }
        else if(vb[index][1]==false){
            vb[index][1]=true;
            L2cache[index][1]=a;
            db[index][1]=false;
            
        }
        
    
   }
    public void notempty(){
        String received=ques.getL2DtoL2CQueue().get(0);
       // received=received;
       List L2CtoL1C=new ArrayList();
        L2CtoL1C.add(received);
        ques.setL2CtoL1CQueue(L2CtoL1C);
        System.out.println("Data is sending to L1 cache from L2"+received);
        ques.getL2DtoL2CQueue().remove(0);
        }
}

