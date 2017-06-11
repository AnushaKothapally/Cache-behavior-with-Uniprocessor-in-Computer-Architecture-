/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.controllers;

import java.util.TimerTask;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;

/**
 *
 * @author srishailamdasari1
 */
public class Scheduling {

    Date now;
    Queues queueline;
    Caches cache;

    Scheduling(Queues CPUQueue) {
        this.queueline = CPUQueue;
        this.cache = cache;
    }
    // @Override

    public void run() {
        CPUController controller1 = new CPUController(queueline);
        L1DataController L1Data = new L1DataController(queueline);
        L1Controler L1C = new L1Controler(queueline);
        L2DataController L2D = new L2DataController(queueline);
        L2Controler L2c = new L2Controler(queueline);
        Memory mem = new Memory(queueline);
        Caches cache = new Caches();
        int i = 1;
        while (true) {
            System.out.println("-----------------------------");
            System.out.println("Cycle" + i);
            if (queueline.getL1CtoCPUQueue() != null && queueline.getL1CtoCPUQueue().size() != 0 && queueline.getL1CtoCPUQueue().get(0) != null && !queueline.getL1CtoCPUQueue().isEmpty()) {
                controller1.displayData();
               // System.out.println(queueline.getL1CtoCPUQueue() + " !!!!!");
                
            }
            if (queueline.getCPUQueue() != null && queueline.getCPUQueue().size() != 0 && queueline.getCPUQueue().get(0) != null && !queueline.getCPUQueue().isEmpty()) {
                controller1.CPU2L1Move();
            }
            // if(i%2==1){
            if (queueline.getCPU2L1ConQueue() != null && queueline.getCPU2L1ConQueue().size() != 0 && queueline.getCPU2L1ConQueue().get(0) != null && !queueline.getCPU2L1ConQueue().isEmpty()) {
                L1C.readfromq();
            }//}
            if (queueline.getL1CtoL1DQueue() != null && queueline.getL1CtoL1DQueue().size() != 0 && queueline.getL1CtoL1DQueue().get(0) != null && !queueline.getL1CtoL1DQueue().isEmpty()) {
                //  System.out.println("going to data L1D");
                L1Data.L1DataToL1Controller();
                // System.out.println(queueline.getL2CtoL2DQueue()+" i "+i);
            }

            if (queueline.getL1DtoL1CQueue() != null && queueline.getL1DtoL1CQueue().size() != 0 && queueline.getL1DtoL1CQueue().get(0) != null && !queueline.getL1DtoL1CQueue().isEmpty()) {
                //  System.out.println("Am excuting*********");
                L1C.notempty();
            }
            if (queueline.getL1CtoL2CQueue() != null && queueline.getL1CtoL2CQueue().size() != 0 && queueline.getL1CtoL2CQueue().get(0) != null && !queueline.L1CtoL2CQueue.isEmpty()) {
                L2c.readfroml1c();
            }
//        System.out.println(queueline.getL2CtoL2DQueue().size()+" i "+i);

            if (queueline.getL2CtoL1CQueue() != null && queueline.getL2CtoL1CQueue().size() != 0 && queueline.getL2CtoL1CQueue().get(0) != null && !queueline.getL2CtoL1CQueue().isEmpty()) {
                L1C.L2ctol1c();
            }
            if (queueline.getL2DtoL2CQueue() != null && queueline.getL2DtoL2CQueue().size() != 0 && queueline.getL2DtoL2CQueue().get(0) != null && !queueline.getL2DtoL2CQueue().isEmpty()) {
                L2c.notempty();
            }
            if (queueline.getL2CtoMemoryQueue() != null && queueline.getL2CtoMemoryQueue().size() != 0 && queueline.getL2CtoMemoryQueue().get(0) != null && !queueline.getL2CtoMemoryQueue().isEmpty()) {
                // System.out.println("Calling Memory in it"+queueline.getL2CtoMemoryQueue().get(0));
                mem.memory();

            }
            if (queueline.getMemorytoL2CQueue() != null && queueline.getMemorytoL2CQueue().size() != 0 && queueline.getMemorytoL2CQueue().get(0) != null && !queueline.getMemorytoL2CQueue().isEmpty()) {
                L2c.readfrommemory();
            }
            if (queueline.getL2CtoL2DQueue() != null && queueline.getL2CtoL2DQueue().size() != 0 && queueline.getL2CtoL2DQueue().get(0) != null && !queueline.getL2CtoL2DQueue().isEmpty()) {
                // System.out.println("****");
                L2D.L2Data();
            }
            // System.out.println("value is been read" + queueline.getL1CtoCPUQueue());
            //  System.out.println("Am excuting*********"+queueline.getL1CtoCPUQueue());
            if (queueline.getL1CtoCPUQueue() != null && queueline.getL1CtoCPUQueue().size() != 0 && queueline.getL1CtoCPUQueue().get(0) != null && !queueline.getL1CtoCPUQueue().isEmpty()) {
                //   System.out.println(queueline.getL1CtoCPUQueue()+" !!!!!");
                controller1.displayData();
            }
            if (queueline.getL1CtoL1DQueue() != null && queueline.getL1CtoL1DQueue().size() != 0 && queueline.getL1CtoL1DQueue().get(0) != null && !queueline.getL1CtoL1DQueue().isEmpty()) {
                //   System.out.println("going to data L1D");
                L1Data.L1DataToL1Controller();
                // System.out.println(queueline.getL2CtoL2DQueue()+" i "+i);
            }
            if(i==3){
            controller1.checkMutualInclusion();
            }
            
            i++;
            //Scheduling.sleep(1000);
        }

    }

}
