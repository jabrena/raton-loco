# raton-loco

[![CI Builds](https://github.com/jabrena/raton-loco/actions/workflows/build.yaml/badge.svg)](https://github.com/jabrena/raton-loco/actions/workflows/build.yaml)

🐭 Raton Loco is a Windows utility to avoid the Screensaver

## Usage

```
./mvnw clean package
java -jar target/raton-loco-1.6.0.jar -h

🐭🐭🐭🐭🐭🐭🐭🐭🐭🐭🐭🐭🐭
🐭 R A T O N - L O C O  🐭
🐭🐭🐭🐭🐭🐭🐭🐭🐭🐭🐭🐭🐭
Usage: Raton Loco [-hV] [-p=<pauseParam>] [-u=<untilParam>]
Raton loco is a Windows utility to avoid the Screensaver.
Usecase: you have a computer that you need to be logged but if you don´t
interact with it in a period of time, then the Operating System will show a
Screensaver. This utility avoid this case.
  -h, --help                 Show this help message and exit.
  -p, --pause=<pauseParam>   how many minutes until next mouse movement
  -u, --until=<untilParam>   where x is the number of hours to run
  -V, --version              Print version information and exit.
```

### Jbang support

```
sdk env install
sdk install jbang
jbang cache clear
jbang catalog update
jbang catalog list jabrena
jbang raton-loco@jabrena -h
```

## Other commands

```
./mvnw prettier:write
./mvnw versions:display-dependency-updates
./mvnw versions:display-plugin-updates
```
