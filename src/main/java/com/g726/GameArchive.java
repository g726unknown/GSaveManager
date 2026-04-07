package com.g726;

import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameArchive {
    private String gameName;
    private String saveName;
    private Path savePath;
    private Path backupPath;
    private LocalDateTime timeStamp;
    private String customName = "";

    public GameArchive(String gamename, String savename, String pathname) {
        this.gameName = gamename;
        this.saveName = savename;
        this.savePath = Paths.get(pathname);
        this.timeStamp = LocalDateTime.now();
        this.backupPath = Paths.get("Backup").resolve(gameName).resolve(saveName).resolve(this.getTimeStamp());
    }

    public GameArchive(GameArchive other) {
        this(other.getGameName(), other.getSaveName(), other.savePath.toString());
    }

    public GameArchive(String gamename, String savename, String pathname, String customTimeStamp) {
        this.gameName = gamename;
        this.saveName = savename;
        this.savePath = Paths.get(pathname);
        DateTimeFormatter stampFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        this.timeStamp = LocalDateTime.parse(customTimeStamp, stampFormatter);
        this.backupPath = Paths.get("Backup").resolve(gameName).resolve(saveName).resolve(customTimeStamp);
    }

    public boolean checkPath() {
        return Files.exists(savePath);
    }

    public String getGameName() {
        return gameName;
    }

    public String getSaveName() {
        return saveName;
    }

    public Path getSavePath() {
        if (this.checkPath()) {
            return savePath;
        } else {
            return null;
        }
    }

    public String getTimeStamp() {
        DateTimeFormatter stamp = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String stampString = this.timeStamp.format(stamp);
        return stampString;
    }

    public Path getBackupPath() {
        return backupPath;
    }

    public String getAbsRawSavePathString() {
        return this.savePath.toAbsolutePath().toString();
    }

    public String getCustomName() {
        return customName == null ? "" : customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
}
