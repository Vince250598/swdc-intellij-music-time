package com.softwareco.intellij.plugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.softwareco.intellij.plugin.SoftwareCoSessionManager;
import com.softwareco.intellij.plugin.SoftwareCoUtils;
import com.softwareco.intellij.plugin.music.MusicControlManager;
import org.jetbrains.annotations.NotNull;

public class ConnectSpotifyAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) { MusicControlManager.connectSpotify(); }

    @Override
    public void update(AnActionEvent event) {
        boolean sessionFileExists = SoftwareCoSessionManager.softwareSessionFileExists();
        boolean hasJwt = SoftwareCoSessionManager.jwtExists();
        boolean isLoggedIn = (!sessionFileExists || !hasJwt || !SoftwareCoUtils.isSpotifyConncted())
                ? false : true;
        boolean serverOnline = SoftwareCoSessionManager.isServerOnline();

        event.getPresentation().setVisible(!isLoggedIn && serverOnline);
        event.getPresentation().setEnabled(true);
    }
}
