import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

public class MyTimer {
    public boolean worked = true;

    public void startTimer() {
        Timer timer = new Timer();
        long startTime = System.currentTimeMillis();
        long totalDuration = 3420000;

        Levels level1 = new Level1();
        Levels level2 = new Level2();
        Levels level3 = new Level3();
        Levels level4 = new Level4();
        Levels level5 = new Level5();
        level1.setNext(level2);
        level2.setNext(level3);
        level3.setNext(level4);
        level4.setNext(level5);




        TimerTask checkTimeTask = new TimerTask() {
            @Override
            public void run() {
                long elapsedTime = (System.currentTimeMillis() - startTime);
                long remainingTime = (totalDuration - elapsedTime);
                System.out.println(remainingTime);

                if (remainingTime > 0) {
                    String levels  = level1.nexlevel(remainingTime );
                    if(levels.equals("Level 5")) {
                        System.out.println("Do some exercises");
                    }
                    else if(levels.equals("Level 4")) {
                        System.out.println("It's time to work on your eyesight");
                    }
                    else if(levels.equals("Level 3")) {

                    }
                    else if(levels.equals("Level 2")) {
                        System.out.println("Don't forget to work with your eyesight");
                    }else if (levels.equals("Level 1")) {
                        System.out.println("Do some exercises");
                    }
                } else {
                    System.out.println("You've done enough computer work for today");
                    System.out.println("I suggest you turn off your computer");
                    timer.cancel();
                }
            }
        };

        if(worked) {
            timer.scheduleAtFixedRate(checkTimeTask, 0, 600000);
        }else{

        }

        TimerTask stopTask = new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
            }
        };
        timer.schedule(stopTask, totalDuration);
        
    }

}
