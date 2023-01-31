///usr/bin/env jbang "$0" "$@" ; exit $?

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

    private static final String PARAMETER_UNTIL = "UNTIL";
    private static final int DEFAULT_UNTIL_VALUE = 9 * 60;
    private static final int DEFAULT_PAUSE = 60 * 1000;
    private static final String PARAMETER_HELP = "HELP";

    private static final String MOUSE_EMOJI = "\uD83D\uDC2D";
    private static final String CHEESE_EMOJI = "\uD83E\uDDC0";
    private static final String OS = System.getProperty("os.name");

    public static void main(String... args) throws Exception {
        //Header
        showHeader();

        //Configuration
        Map<String, Integer> configuration = processConfiguration(args);

        //Process
        process(configuration);
    }

    private static void showHeader() {
        String symbol = OS.startsWith("Win") ? "::" : MOUSE_EMOJI;
        String mouseLine = IntStream.rangeClosed(1, 13).boxed().map(i -> symbol).reduce("", String::concat);
        System.out.println(mouseLine);
        System.out.println(symbol + " R A T O N - L O C O  " + symbol);
        System.out.println(mouseLine);
    }

    private static Map<String, Integer> processConfiguration(String[] args) {
        Map<String, Integer> configuration = new HashMap<>();

        if (args.length > 0) {
            if (args.length > 1) {
                System.out.println("The utility only accepts an unique parameter: HELP | UNTIL=x");
                System.out.println();
                System.exit(1);
            }
            String[] rawParams = args[0].split("=");
            if (rawParams[0].equals(PARAMETER_UNTIL)) {
                try {
                    configuration.put(PARAMETER_UNTIL, Integer.parseInt(rawParams[1]));
                } catch (NumberFormatException ex) {
                    System.err.println("Parameter " + PARAMETER_UNTIL + " only accepts a non decimal value");
                    System.out.println();
                    System.exit(1);
                }
            }
            if (rawParams[0].equals(PARAMETER_HELP)) {
                System.out.println("'Raton loco' is a Windows utility to avoid the Screensaver.");
                System.out.println();
                System.out.println("Usecase: you have a computer that you need to be logged");
                System.out.println("         but in few minutes if you don´t interact with it, ");
                System.out.println("         it enter the Screensaver. This utility avoid this case");
                System.out.println();
                System.out.println("Usage:   The utility accepts a parameter UNTIL=x ");
                System.out.println("         where x is the number of minutes to run");
                System.out.println();
                System.exit(0);
            }
        }

        //Defaults
        if (!configuration.containsKey(PARAMETER_UNTIL)) {
            configuration.put(PARAMETER_UNTIL, DEFAULT_UNTIL_VALUE);
        }

        //Show values
        String mouse = OS.startsWith("Win") ? "mouse" : MOUSE_EMOJI;
        Integer until = configuration.get(PARAMETER_UNTIL);
        if (until == 1) {
            System.out.println("The " + mouse + " will work for " + until + " minute.");
        } else {
            System.out.println("The " + mouse + " will work for " + until + " minutes.");
        }
        System.out.println();

        return configuration;
    }

    private static void process(Map<String, Integer> configuration) throws AWTException, InterruptedException {
        LocalDateTime start = LocalDateTime.now();
        Robot robot = new Robot();
        Integer maxX = 400;
        Integer maxY = 400;
        Random random = new Random();
        Integer until = configuration.get(PARAMETER_UNTIL);
        Integer defaultPause = DEFAULT_PAUSE;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now) + " " + (OS.startsWith("Win") ? "" : MOUSE_EMOJI));

        while (true) {
            robot.mouseMove(random.nextInt(maxX), random.nextInt(maxY));
            Thread.sleep(defaultPause);

            now = LocalDateTime.now();
            Duration duration = Duration.between(start, now);
            if (duration.toMinutes() == until) {
                if (OS.startsWith("Win")) {
                    System.out.println(dtf.format(now) + " The mouse escaped with the cheese");
                } else {
                    System.out.println(dtf.format(now) + " " + MOUSE_EMOJI + " escaped with the " + CHEESE_EMOJI);
                }
                break;
            } else {
                System.out.println(dtf.format(now) + " " + (OS.startsWith("Win") ? "" : MOUSE_EMOJI));
            }
        }
    }
}
