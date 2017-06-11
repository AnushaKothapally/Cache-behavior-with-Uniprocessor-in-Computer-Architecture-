/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.controllers;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author srishailamdasari1
 */
public class Queues {

    /**
     * @return the L1DtoL1CQueue
     */
    public List<String> getL1DtoL1CQueue() {
        return L1DtoL1CQueue;
    }

    /**
     * @param L1DtoL1CQueue the L1DtoL1CQueue to set
     */
    public void setL1DtoL1CQueue(List<String> L1DtoL1CQueue) {
        this.L1DtoL1CQueue = L1DtoL1CQueue;
    }
    public  List<String> CPUQueue;
    public List<String> CPU2L1ConQueue;
    public List<String> L1DtoL1CQueue;
    public List<String> L1CtoL1DQueue;
    public List<String> L1CtoL2CQueue;
    public List<String> L2CtoL1CQueue;
    public List<String> L2CtoL2DQueue;
    public List<String> L2DtoL2CQueue;
    public List<String> L2CtoMemoryQueue;
    public List<String> MemorytoL2CQueue;
    public List<String> L1CtoCPUQueue;
    /**
     * @return the CPUQueue
     */
    public List<String> getCPUQueue() {
        return CPUQueue;
    }

    /**
     * @param CPUQueue the CPUQueue to set
     */
    public void setCPUQueue(List<String> CPUQueue) {
        this.CPUQueue = CPUQueue;
    }

    /**
     * @return the CPU2L1ConQueue
     */
    public List<String> getCPU2L1ConQueue() {
        return CPU2L1ConQueue;
    }

    /**
     * @param CPU2L1ConQueue the CPU2L1ConQueue to set
     */
    public void setCPU2L1ConQueue(List<String> CPU2L1ConQueue) {
        this.CPU2L1ConQueue = CPU2L1ConQueue;
    }
    /**
     * @return the L1CtoL1DQueue
     */
    public List<String> getL1CtoL1DQueue() {
        return L1CtoL1DQueue;
    }

    /**
     * @param L1CtoL1DQueue the L1CtoL1DQueue to set
     */
    public void setL1CtoL1DQueue(List<String> L1CtoL1DQueue) {
        this.L1CtoL1DQueue = L1CtoL1DQueue;
    }

    /**
     * @return the L1CtoL2CQueue
     */
    public List<String> getL1CtoL2CQueue() {
        return L1CtoL2CQueue;
    }

    /**
     * @param L1CtoL2CQueue the L1CtoL2CQueue to set
     */
    public void setL1CtoL2CQueue(List<String> L1CtoL2CQueue) {
        this.L1CtoL2CQueue = L1CtoL2CQueue;
    }

    /**
     * @return the L2CtoL1CQueue
     */
    public List<String> getL2CtoL1CQueue() {
        return L2CtoL1CQueue;
    }

    /**
     * @param L2CtoL1CQueue the L2CtoL1CQueue to set
     */
    public void setL2CtoL1CQueue(List<String> L2CtoL1CQueue) {
        this.L2CtoL1CQueue = L2CtoL1CQueue;
    }

    /**
     * @return the L2CtoL2DQueue
     */
    public List<String> getL2CtoL2DQueue() {
        return L2CtoL2DQueue;
    }

    /**
     * @param L2CtoL2DQueue the L2CtoL2DQueue to set
     */
    public void setL2CtoL2DQueue(List<String> L2CtoL2DQueue) {
        this.L2CtoL2DQueue = L2CtoL2DQueue;
    }

    /**
     * @return the L2DtoL2CQueue
     */
    public List<String> getL2DtoL2CQueue() {
        return L2DtoL2CQueue;
    }

    /**
     * @param L2DtoL2CQueue the L2DtoL2CQueue to set
     */
    public void setL2DtoL2CQueue(List<String> L2DtoL2CQueue) {
        this.L2DtoL2CQueue = L2DtoL2CQueue;
    }

    /**
     * @return the L2CtoMemoryQueue
     */
    public List<String> getL2CtoMemoryQueue() {
        return L2CtoMemoryQueue;
    }

    /**
     * @param L2CtoMemoryQueue the L2CtoMemoryQueue to set
     */
    public void setL2CtoMemoryQueue(List<String> L2CtoMemoryQueue) {
        this.L2CtoMemoryQueue = L2CtoMemoryQueue;
    }

    /**
     * @return the MemorytoL2CQueue
     */
    public List<String> getMemorytoL2CQueue() {
        return MemorytoL2CQueue;
    }

    /**
     * @param MemorytoL2CQueue the MemorytoL2CQueue to set
     */
    public void setMemorytoL2CQueue(List<String> MemorytoL2CQueue) {
        this.MemorytoL2CQueue = MemorytoL2CQueue;
    }

    /**
     * @return the L1CtoCPUQueue
     */
    public List<String> getL1CtoCPUQueue() {
        return L1CtoCPUQueue;
    }

    /**
     * @param L1CtoCPUQueue the L1CtoCPUQueue to set
     */
    public void setL1CtoCPUQueue(List<String> L1CtoCPUQueue) {
        this.L1CtoCPUQueue = L1CtoCPUQueue;
    }

  
}
