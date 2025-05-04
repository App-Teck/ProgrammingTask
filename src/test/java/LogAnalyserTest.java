import model.LogEntry;
import org.junit.jupiter.api.Test;
import service.LogAnalyser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogAnalyserTest {

    private final LogAnalyser analyser = new LogAnalyser();

    @Test
    void testsUniqueIPCount(){
        List<LogEntry> logEntries = List.of(
                new LogEntry("1.1.1.1", "/home"),
                new LogEntry("1.1.1.1", "/about"),
                new LogEntry("2.2.2.2", "/home")
        );

        int result = analyser.countUniqueIPs(logEntries);
        assertEquals(2, result);
    }

    @Test
    void testTop3Urls(){
        List<LogEntry> logEntries = List.of(
                new LogEntry("1.1.1.1", "/a"),
                new LogEntry("2.2.2.2", "/a"),
                new LogEntry("3.3.3.3", "/b"),
                new LogEntry("4.4.4.4", "/a"),
                new LogEntry("5.5.5.5", "/b"),
                new LogEntry("6.6.6.6", "/c")
        );
        List<String> top = analyser.getTop3Urls(logEntries);
        assertEquals(List.of("/a", "/b", "/c"), top);
    }

    @Test
    void testTop3ActiveIPs(){
        List<LogEntry> logEntries = List.of(
                new LogEntry("1.1.1.1", "/x"),
                new LogEntry("2.2.2.2", "/x"),
                new LogEntry("2.2.2.2", "/y"),
                new LogEntry("1.1.1.1", "/z"),
                new LogEntry("3.3.3.3", "/y"),
                new LogEntry("1.1.1.1", "/y")
        );
        List<String> top = analyser.getTop3ActiveIPs(logEntries);
        assertEquals(List.of("1.1.1.1", "2.2.2.2", "3.3.3.3"), top);
    }
}
