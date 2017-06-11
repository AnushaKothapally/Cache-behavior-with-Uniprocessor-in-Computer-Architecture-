/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.controllers;
import java.util.Timer;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author srishailamdasari1
 */
public class CacheControllers {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here

        Scanner inputFile = new Scanner(new File("sample.txt"));
        Queues queueline = new Queues();
        Caches cache=new Caches();
        //queueline.setCPU2L1ConQueue(null);
        List<String> tempCPUq = new ArrayList<String>();
        while (inputFile.hasNextLine()) {
            tempCPUq.add(inputFile.nextLine());
        }
        queueline.setCPUQueue(tempCPUq);
         for(Iterator it= queueline.getCPUQueue().iterator();it.hasNext();){
          System.out.println(it.next());
   }
        Timer time = new Timer();
        Scheduling st = new Scheduling(queueline);
        st.run();
        //time.schedule(st, 0, 1000);

    }
    
}
