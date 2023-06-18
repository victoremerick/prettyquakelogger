package br.homedeveloper.config;

import br.homedeveloper.game.model.Game;
import br.homedeveloper.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

@Component("QuakeLogMonitorThread")
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class QuakeLogMonitorConfig{

    private boolean debug = false;
    private int runEveryNSeconds = 2000;
    private long lastKnownPosition = 0;
    private File logFile = null;

    @Value("${quake.logFile}")
    private String fileLocation;

    private final GameService gameService;

    @Scheduled(initialDelay = 1000, fixedDelay = Long.MAX_VALUE)
    public void startMonitoring() {
        logFile = new File(fileLocation);
        Game currentGame = gameService.getLastGame();

        while (true) {
            try {
                Thread.sleep(runEveryNSeconds);
                long fileLength = logFile.length();
                if (fileLength > lastKnownPosition) {
                    RandomAccessFile readWriteFileAccess = new RandomAccessFile(logFile, "rw");
                    readWriteFileAccess.seek(lastKnownPosition);
                    String line = null;
                    List<String> events = new ArrayList<>();
                    while ((line = readWriteFileAccess.readLine()) != null) {
                        if(line.contains("InitGame")){
                            currentGame = gameService.processLog(currentGame, events);
                            events = new ArrayList<>();
                            currentGame = gameService.processLog(events);
                        }else if(line.contains("Kill")){
                            events.add(line);
                        }
                    }
                    currentGame = gameService.processLog(currentGame, events);
                    lastKnownPosition = readWriteFileAccess.getFilePointer();
                    readWriteFileAccess.close();
                }

            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
