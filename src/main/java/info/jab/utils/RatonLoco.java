package info.jab.utils;

import java.awt.Robot;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class RatonLoco {

    private static final int DEFAULT_UNTIL = 5 * 60;
    private static final int DEFAULT_PAUSE = 5000;
    private static final int MAX_Y = 400;
    private static final int MAX_X = 400;

    private static final String MOUSE_EMOJI = "\uD83D\uDC2D";
    private static final String CHEESE_EMOJI = "\uD83E\uDDC0";

    public static void main(String... args) throws Exception {

        //Application Begin
        LocalDateTime start = LocalDateTime.now();

        Robot robot = new Robot();
        Random random = new Random();

        //Header
        System.out.println(MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI);
        System.out.println(MOUSE_EMOJI + " R A T O N - L O C O  " + MOUSE_EMOJI);
        System.out.println(MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI + MOUSE_EMOJI);

        //Configuration
        Integer until = DEFAULT_UNTIL;
        String[] rawParams = args[0].split("=");
        if(rawParams[0].equals("UNTIL")) {

            try {
                until = Integer.parseInt(rawParams[1]);
                if(until == 1) {
                    System.out.println("The " + MOUSE_EMOJI + " will work for " + until + " minute.");
                } else {
                    System.out.println("The " + MOUSE_EMOJI + " will work for " + until + " minutes.");
                }
            } catch (NumberFormatException ex) {
                System.err.println("Parameter UNTIL only accepts integers");
                System.exit(1);
            }

        }
        System.out.println();

        while (true) {
            robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));
            Thread.sleep(DEFAULT_PAUSE);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            Duration duration = Duration.between(start, now);
            if (duration.toMinutes() == until) {
                System.out.println(dtf.format(now) + " " +  MOUSE_EMOJI + " escaped with the " + CHEESE_EMOJI);
                break;
            } else {
                System.out.println(dtf.format(now) + " " +  MOUSE_EMOJI);
            }
        }
    }
}