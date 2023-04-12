/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany._ex_09_5_1;

import java.io.*;
import java.lang.Math;

/**
 *
 * @author lesdabit
 */
class JOB extends Thread {
    private String jobName;
    private int loopLmt;
    
    public JOB(int loopLmt, String jobName) {
        this.loopLmt = loopLmt;
        this.jobName = jobName;
    }

    private void PAUSE(double sec) {
        try {
            Thread.sleep(Math.round(1000.0*sec));
        } catch (InterruptedException e) {

        }
    }

    public void RUN() {
        for(int i = 0; i < loopLmt; i++) {
            System.out.println(jobName + ":work" + i);
            PAUSE(Math.random());
        }
    }
}

public class App {

    public static void main(String[] args) {
        JOB job1 = new JOB(4,"job1");
        JOB job2 = new JOB(8,"job2");
        job1.start();
        job2.start();
        job2.suspend();
        System.out.println("job2 is suspend");
        System.out.println("job1 is alive:" + job1.isAlive());
        try {
            job1.join();
        } catch (InterruptedException e){
            System.out.println("False join");
        }
        System.out.println("job1 is alive:" + job1.isAlive());
        job2.resume();
        System.out.println("job2 is resume");
    }
}
