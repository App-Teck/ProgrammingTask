package parser;

import model.LogEntry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

    private static final Pattern LOG_PATTERN = Pattern.compile(
            "^(\\S+)\\s+-\\s+-\\s+\\[[^\\]]+\\]\\s+\"[A-Z]+\\s+([^\\s\"]+)\\s+HTTP/\\d\\.\\d\".*$"
    );

    public List<LogEntry> parse(Path logFilePath) throws IOException {
        List<LogEntry> logEntries = new ArrayList<>();

        List<String> lines = Files.readAllLines(logFilePath);
        for (String line : lines) {
            Matcher matcher = LOG_PATTERN.matcher(line);
            if (matcher.matches()) {
                String ipAddress = matcher.group(1);
                String url = matcher.group(2);
                logEntries.add(new LogEntry(ipAddress, url));
            } else {
                //We log it into a logger system
                System.out.println("Skipping malformed line: " + line);
            }
        }
        return logEntries;
    }
}
