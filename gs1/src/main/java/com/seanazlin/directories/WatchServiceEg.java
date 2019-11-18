package com.seanazlin.directories;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;


// Good for "file watcher tasks", such as when a new file is created as an input for the system
public class WatchServiceEg {
    public static void main(String[] args) {
        Path path = Paths.get(".");
        try {
            WatchService watchService = path.getFileSystem().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey key = null;
            while(true){
                key = watchService.poll(10, TimeUnit.SECONDS);
                if (key != null){
                    key.pollEvents().stream().forEach(event->System.out.println(event.context()));
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
