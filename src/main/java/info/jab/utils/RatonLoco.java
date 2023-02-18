///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.7.1

package info.jab.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.stream.IntStream;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

// @formatter:off
@Command(
    name = "Raton Loco",
    version = "Raton Loco 1.4",
    description = "Raton loco is a Windows utility to avoid the Screensaver.\n"
            + "Usecase: you have a computer that you need to be logged "
            + "but if you don´t interact with it in a period of time, "
            + "then the Operating System will show a Screensaver. "
            + "This utility avoid this case.",
    mixinStandardHelpOptions = true
)
// @formatter:on
public final class RatonLoco implements Runnable {

    @Option(names = { "-u", "--until" }, description = "where x is the number of minutes to run")
    private Integer untilParam = 9 * 60;

    @Option(names = { "-p", "--pause" }, description = "how many minutes until next mouse movement")
    private Integer pauseParam = 60 * 1000;

    private static final String MOUSE_EMOJI = "\uD83D\uDC2D";
    private static final String CHEESE_EMOJI = "\uD83E\uDDC0";
    private static final String OS = System.getProperty("os.name");

    public static void main(String... args) throws Exception {
        //Header
        showHeader();

        int exitCode = new CommandLine(new RatonLoco()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        try {
            LocalDateTime start = LocalDateTime.now();
            Robot robot = new Robot();
            Integer maxX = 400;
            Integer maxY = 400;
            Random random = new Random();
            Integer until = untilParam;
            Integer pause = pauseParam;

            String mouse = OS.startsWith("Win") ? "mouse" : MOUSE_EMOJI;
            if (until == 1) {
                System.out.println("The " + mouse + " will work for " + until + " minute.");
            } else {
                System.out.println("The " + mouse + " will work for " + until + " minutes.");
            }
            System.out.println();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now) + " " + (OS.startsWith("Win") ? "" : MOUSE_EMOJI));

            while (true) {
                robot.mouseMove(random.nextInt(maxX), random.nextInt(maxY));
                Thread.sleep(pause);

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
        } catch (AWTException | InterruptedException ex) {
            //Empty
        }
    }

    private static void showHeader() {
        String symbol = OS.startsWith("Win") ? "::" : MOUSE_EMOJI;
        String mouseLine = IntStream.rangeClosed(1, 13).boxed().map(i -> symbol).reduce("", String::concat);
        System.out.println(mouseLine);
        System.out.println(symbol + " R A T O N - L O C O  " + symbol);
        System.out.println(mouseLine);
    }
}
