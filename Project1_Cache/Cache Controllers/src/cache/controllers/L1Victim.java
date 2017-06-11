/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.controllers;

/**
 *
 * @author srishailamdasari1
 */
public class L1Victim {
    String victimcache[][]=new String[2][2];
    String data, storeValue,storeTag,action;
    public String victimcache(String[] address){ 
        storeTag=address[1];
            //victimcache[0][1]="10101";
            //victimcache[0][0]="ABCDEF";
        String tag=storeTag.substring(0, 5);
        System.out.println("In L1Victime cache");
        if (tag.equals(victimcache[0][1])) {
            data = victimcache[0][0];
             System.out.println("Data from L1Victim  is : "+address[0]+" "+address[1]+" "+address[2]+" "+data);
            return address[0]+" "+address[1]+" "+address[2]+" "+data;

        } else if (tag.equals(victimcache[1][1])) {
            data = victimcache[0][0];
             System.out.println("Data from L1Victim  is : "+address[0]+" "+address[1]+" "+address[2]+" "+data);
            return address[0]+" "+address[1]+" "+address[2]+" "+data;
        }
//        
        return null;
    }
       public boolean victimcachestore(String tag,int offset,String data){
    
     
        storeValue=data;
            if (victimcache[0][0] == null) {
                victimcache[0][0] = storeValue;
                victimcache[0][1] = storeTag;
                 return true;
            }
           else if (victimcache[1][0] == null) {
                victimcache[1][0] = storeValue;
                victimcache[1][1] = storeTag;
                 return true;
            } else {
                //Simply removing the data.
                victimcache[0][0] = storeValue;
                victimcache[0][1] = storeTag;
                 return true;
            }  
    }
}

