package project;

import java.util.List;
import java.util.Date;

public class DeadlineChecker extends Thread {
    List<Assignment> assignments;
    private volatile boolean running = true;  // Thread-safe flag

    public DeadlineChecker(List<Assignment> assignments) {
        this.assignments = assignments;
    }
    
    public void stopChecker() {
        running = false;
        this.interrupt();  // Interrupts sleep
    }

    public void run() {
        while (running) {
            for (Assignment a : assignments) {
                if (a.deadline != null && new Date().after(a.deadline)) {
                    System.out.println("Deadline passed for: " + a.title);
                }
            }
            try {
                Thread.sleep(60000); // check every 1 minute
            } catch (InterruptedException e) {
            	if(!running) {
            		System.out.print("BYE.");
            		break;
            	}
            }
        }
    }
}
