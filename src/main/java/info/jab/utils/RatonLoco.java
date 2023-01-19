package info.jab.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public final class RatonLoco {

    private static final int DEFAULT_UNTIL = 5 * 60;
    private static final int DEFAULT_PAUSE = 5000;

    private static final String MOUSE_EMOJI = "\uD83D\uDC2D";
    private static final String CHEESE_EMOJI = "\uD83E\uDDC0";

    public static void main(String... args) throws Exception {
        //Header
        showHeader();

        //Configuration
        Map<String, Integer> configuration = processConfiguration(args);

        //Process
        process(configuration);
    }

    private static void showHeader() {
        String os = System.getProperty("os.name");
        if (os.startsWith("Win")) {
            String mouseLine = IntStream.rangeClosed(1, 13).boxed().map(i -> "::").reduce("", String::concat);
            System.out.println(mouseLine);
            System.out.println("::" + " R A T O N - L O C O  " + "::");
            System.out.println(mouseLine);
        } else {
            String mouseLine = IntStream.rangeClosed(1, 13).boxed().map(i -> MOUSE_EMOJI).reduce("", String::concat);
            System.out.println(mouseLine);
            System.out.println(MOUSE_EMOJI + " R A T O N - L O C O  " + MOUSE_EMOJI);
            System.out.println(mouseLine);
        }
    }

    private static Map<String, Integer> processConfiguration(String[] args) {
        Integer until = DEFAULT_UNTIL;

        Map<String, Integer> configuration = new HashMap<>();

        if (args.length > 0) {

            //First parameter
            String[] rawParams = args[0].split("=");
            if (rawParams[0].equals("UNTIL")) {
                try {
                    until = Integer.parseInt(rawParams[1]);
                    if (until == 1) {
                        System.out.println("The " + MOUSE_EMOJI + " will work for " + until + " minute.");
                    } else {
                        System.out.println("The " + MOUSE_EMOJI + " will work for " + until + " minutes.");
                    }
                    configuration.put("UNTIL", until);
                } catch (NumberFormatException ex) {
                    System.err.println("Parameter UNTIL only accepts integers");
                    System.exit(1);
                }
            }
        }
        System.out.println();

        //Defaults
        if (configuration.get("UNTIL") == null) {
            configuration.put("UNTIL", DEFAULT_UNTIL);
        }

        return configuration;
    }

    private static void process(Map<String, Integer> configuration) throws AWTException, InterruptedException {
        LocalDateTime start = LocalDateTime.now();
        Robot robot = new Robot();
        Integer maxX = 400;
        Integer maxY = 400;
        Random random = new Random();
        Integer until = configuration.get("UNTIL");

        while (true) {
            robot.mouseMove(random.nextInt(maxX), random.nextInt(maxY));
            Thread.sleep(DEFAULT_PAUSE);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(start, now);
            String os = System.getProperty("os.name");
            if (duration.toMinutes() == until) {
                if (os.startsWith("Win")) {
                    System.out.println(dtf.format(now) + "The mouse escaped with the cheese");
                } else {
                    System.out.println(dtf.format(now) + " " + MOUSE_EMOJI + " escaped with the " + CHEESE_EMOJI);
                }
                break;
            } else {
                System.out.println(dtf.format(now) + " " + (os.startsWith("Win") ? "" : MOUSE_EMOJI));
            }
        }
    }
}
