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
public class WriteBuffer {
    String Buffercache[][]=new String[2][2];
    String data;
    String storeValue;
    String storeTag;
    public String bufferload(String tag,int offset){   
        if (tag.equals(Buffercache[0][1])) {
            data = Buffercache[0][0];
            return data;

        } else if (tag.equals(Buffercache[1][1])) {
            data = Buffercache[1][0];
            return data;
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
