package br.homedeveloper.config;

import br.homedeveloper.game.model.Game;
import br.homedeveloper.game.service.FileService;
import br.homedeveloper.game.service.GameService;
import br.homedeveloper.game.service.impl.GameServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@RequiredArgsConstructor
public class SpringAsyncConfig implements AsyncConfigurer {

    private boolean debug = false;
    private int runEveryNSeconds = 2000;
    private long lastKnownPosition = 0;
    private File logFile = null;

    @Value("${quake.logFile}")
    private String fileLocation;

    private final GameService gameService;

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    private void processLine(String message) {
        System.out.println(message);
    }

    @Async("threadPoolTaskExecutor")
    public void processTailFile() {
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
                        }
                        this.processLine(line);
                    }
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
