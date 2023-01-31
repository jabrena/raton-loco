# raton-loco
🐭 Raton Loco is a Windows utility to avoid the Screensaver

## Usage

```
./mvnw package
java -jar target/raton-loco-1.3.0.jar UNTIL=1

🐭🐭🐭🐭🐭🐭🐭🐭🐭🐭🐭🐭🐭
🐭 R A T O N - L O C O  🐭
🐭🐭🐭🐭🐭🐭🐭🐭🐭🐭🐭🐭🐭
The 🐭 will work for 1 minute.

2023/01/31 08:36:37 🐭
2023/01/31 08:37:37 🐭 escaped with the 🧀
```

### Jbang support

```
sdk install jbang
jbang cache clear
jbang catalog update
jbang catalog list jabrena
jbang raton-loco@jabrena HELP
jbang raton-loco@jabrena UNTIL=1
```

## Other commands

```
mvn prettier:write
mvn versions:display-dependency-updates
mvn versions:display-plugin-updates
```
