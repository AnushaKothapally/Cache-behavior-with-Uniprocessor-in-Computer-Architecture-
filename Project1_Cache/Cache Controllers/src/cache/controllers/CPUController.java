/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * @author srishailamdasari1
 */
public class CPUController {
    Queues queueline;
    CPUController(Queues cpuq){
    this.queueline=cpuq;
    }
    public void CPU2L1Move(){       
           System.out.println("Reading instructions from file to CPU Queue: ");
            List<String> tempList=new ArrayList<String>();
            int i=0;
            int count=0;
            
        while (!queueline.getCPUQueue().isEmpty()&&count<=1) {
            if ((i < queueline.getCPUQueue().size())&&(!(queueline.getCPUQueue().isEmpty()))) {
            //    System.out.println("******"+queueline.CPUQueue.get(i));
                if(queueline.getCPUQueue().get(i).contains("Instruction")||queueline.getCPUQueue().get(i).contains("instruction")){
                count++;
                }
                if(count!=2){
                String st = queueline.CPUQueue.get(i);
                tempList.add(st);
                queueline.CPUQueue.remove(i);
                }
              
            }
         }
            queueline.setCPU2L1ConQueue(tempList);
            // System.out.println("CPUQ size: "+queueline.getCPUQueue().size()+" 5 is "+queueline.getCPU2L1ConQueue().get(2));
//            System.out.println(queueline.CPUQueue.size()+"CPU2L1QUEUE");
//            for (Iterator it = queueline.getCPU2L1ConQueue().iterator(); it.hasNext();) {
//                System.out.println(it.next());
//            }
          
            
       
        
    }
    
public void displayData(){
   //System.out.println(queueline.getL1CtoCPUQueue()+"*****************");
    String st = queueline.getL1CtoCPUQueue().get(0);
    // String st="Load 10101010101000000 5 ABCDE";
    String[] data = st.split(" ");
    int no_of_bytes = 0;
    //System.out.println("Data in CPU ****"+st);
    if(data.length==4){
    String off = data[1].substring(12, 17);
    int offset = Integer.parseInt(off, 2);
    if (data[0] != null && data[0].equals("Load")) {
        no_of_bytes = Integer.parseInt(data[2]);
        String key=null;
       
        System.out.println("Data read for instruction: " + data[0] + " " + data[1] + " " + data[2] + " is: ");
        if(data.length==4)
        key = data[3];
        for (int i = offset; i < key.length();) {
            for (int j = i; j < i + no_of_bytes;) {
                // System.out.print(j+""+key.length());
                if (j < key.length()) {
                    System.out.print(key.charAt(j));
                }
                j++;
            }
            i = i + no_of_bytes;
            System.out.println("\n");
        }
    queueline.getL1CtoCPUQueue().remove(0);
    }
    }    
}
public void checkMutualInclusion(){
     System.out.println("");
    System.out.println("Checking mutual Inclusion Properity: ");
    L1Controler L1C=new L1Controler(queueline);
    L1DataController L1D=new L1DataController(queueline);
    L2Controler L2C=new L2Controler(queueline);
    L2Buffer L2B= new L2Buffer();
    Boolean istrue=true;
    L2DataController L2Data=new L2DataController(queueline);
    for(int i=0;i<128;i++){
        if(L1C.L1cache[i][0]!=null){ 
        istrue=L1C.L1cache[i][0].contains(L2C.L2cache[i][0])||(L1C.L1cache[i][0].contains(L2C.L2cache[i][1]))||(L1C.L1cache[i][0].contains(L2C.L2cache[i+128][0]))||(L1C.L1cache[i][0].contains(L2C.L2cache[i+128][1]))||L1C.L1cache[i][0].contains(L2B.Buffercache[0][1])?true:false;
        }
    
    }
    if(istrue){
    System.out.println("Mutual Inclusion is satisfied");
    }
    else{
    System.out.println("Mutual Inclusion is not satisfied");
    }
    
}
//public static void main(String args[]){
//    Queues ueq=null;
//    CPUController cpu=new CPUController(ueq);
//    cpu.displayData();
//
//}
    
}
