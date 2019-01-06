package sample;

public class CustomThread extends Thread {
    String name;
    Controller myController;

    CustomThread(String s, Controller myc){
        this.name = s;
        this.myController = myc;
    }

    public void run(){
        System.out.println("Starte Thread " + name);

        try {
            Thread.sleep(100);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Beende Thread " + name);
    }
}
