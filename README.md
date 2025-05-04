#Log Analyser 
This is a clean Java-based log parser and analyser that extracts insights from HTTP access logs.

##Features 
1-Count unique IP Address 
2-Show top 3 most visited URLs
3-Show top 3 most active IP addresses

## Technologies Used
java 23
Maven 
JUnit 5

##Example for logs
177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] "GET /intranet-analytics/ HTTP/1.1" 200 3574

## How to run 
1-Clone/download the project 
2-Place your .log file in 'src/main/resources/' and name it sample.log
3-Run the main.Main class 

## Assumptions 
1-Only lines with a standard Get/Post/etc. + HTTP format are parsed 
2-Malformed or incomplete lines are skipped
3-Absolute and relative URLs are both supported 

## OOP Principles 
1-SRP: each class has one responsibility 
2-Encapsulation: Fields in LogEntry are private, accessed through getters
3-Open/Closed: You can add more analytics in LogAnalyser without modifying LogParser
4-Liskov: No inheritance used -> better
5-Dependency Injections: Could improve testability by injecting dependencies into Main.java or using interfaces, but it's overkill for this size
