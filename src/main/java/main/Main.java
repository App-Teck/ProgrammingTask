package main;

import model.LogEntry;
import parser.LogParser;
import service.LogAnalyser;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String logFilePath = "src/main/resources/programming-task-example-data.log";

        LogParser logParser = new LogParser();
        LogAnalyser analyser = new LogAnalyser();

        try{
            List<LogEntry> logEntries = logParser.parse(Paths.get(logFilePath));
            System.out.println("log file parsed successfully.");
            System.out.println("Preview of first 5 parsed entries:");
            logEntries.stream().limit(5).forEach(System.out::println);

            System.out.println("\nAnalytics Summary");

            //1-Unique IPs
            int uniqueIpsCount = analyser.countUniqueIPs(logEntries);
            System.out.println("\nNumber of unique IPs: " + uniqueIpsCount);

            //2-Top 3 most visited URLs
            List<String> top3Urls = analyser.getTop3Urls(logEntries);
            System.out.println("\nTop 3 most visited URLs:");
            for (int i = 0; i < top3Urls.size(); i++) {
                System.out.printf("   %d. %s%n", i + 1, top3Urls.get(i));
            }

            //3-Top 3 most active IPs
            List<String> top3ActiveIPs = analyser.getTop3ActiveIPs(logEntries);
            System.out.println("\nTop 3 most active IPs:");
            for (int i = 0; i < top3ActiveIPs.size(); i++) {
                System.out.printf("   %d. %s%n", i + 1, top3ActiveIPs.get(i));
            }

        } catch (IOException e){
            System.err.println("Failed to read log file: " + e.getMessage());
        }
    }
}
