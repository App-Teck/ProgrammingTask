package service;

import model.LogEntry;

import java.util.*;

public class LogAnalyser {

    //Param List<LogEntry> cause the parser returns List<LogEntry> and we operate raw data List<LogEntry>
    public int countUniqueIPs(List<LogEntry> logEntries){
        //Set<String> to de-duplicate
        Set<String> uniqueIPs = new HashSet<>(); //Fast and efficient impl of Set
        for (LogEntry logEntry : logEntries) {
            uniqueIPs.add(logEntry.getIpAddress());
        }
        return uniqueIPs.size();
    }

    public List<String> getTop3Urls(List<LogEntry> logEntries){
        Map<String, Integer> urlCount = new HashMap<>();

        //Loop through each LogEntry, and count each URL in a map(url -> count)
        for (LogEntry logEntry : logEntries) {
            String url = logEntry.getUrl().toLowerCase();
            urlCount.put(url, urlCount.getOrDefault(url, 0) + 1);
        }

        //sort the map entries by value and return the top 3 URLs
        return urlCount.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))//Descending
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<String> getTop3ActiveIPs(List<LogEntry> logEntries){
        Map<String, Integer> ipCount = new HashMap<>();

        for (LogEntry logEntry : logEntries) {
            String ip = logEntry.getIpAddress();
            ipCount.put(ip, ipCount.getOrDefault(ip, 0) + 1);
        }

        return ipCount.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))//Descendign
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
    }
}
