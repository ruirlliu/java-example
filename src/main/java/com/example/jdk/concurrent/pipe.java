package com.example.jdk.concurrent;

import lombok.Cleanup;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author lr
 * @date 2019/7/12 16:10
 */
public class pipe {
    public static void main(String[] args) {
        try {
            @Cleanup PipedReader in = new PipedReader();
            @Cleanup PipedWriter out = new PipedWriter(in);
            Thread printThread = new Thread(new Print(in), "PrintThread");
            printThread.start();
            int receive;
            while ((receive = System.in.read()) != -1) {
                out.write(receive);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Print implements Runnable {
        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }
        @Override
        public void run() {
            int receive;
            try {
                while ((receive = in.read()) != -1){
                    System.out.print((char) receive);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
