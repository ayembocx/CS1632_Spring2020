md bin

javac -d bin -cp "CommandLineJunit\*" src\*.java

java -XX:+UseCountedLoopSafepoints -XX:-Inline -cp bin GameOfLife %1
