package com.arsenarsen.lavaplayerbridge.bindings;

import com.arsenarsen.lavaplayerbridge.libraries.Library;
import com.arsenarsen.lavaplayerbridge.player.Provider;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.audio.AudioSendHandler;

public class BindingJDAImpl {
    public static Library createLibrary(Object o) {
        JDA jda = (JDA) o;
        return new Library(o) {
            @Override
            public void setProvider(String guildId, Provider provider) {
                jda.getGuildById(guildId).getAudioManager().setSendingHandler(new AudioSendHandler() {
                    @Override
                    public boolean canProvide() {
                        return provider.isReady();
                    }

                    @Override
                    public byte[] provide20MsAudio() {
                        return provider.provide();
                    }

                    @Override
                    public boolean isOpus() {
                        return true;
                    }
                });
            }

            @Override
            public boolean isValidGuild(String guildId) {
                return jda.getGuildById(guildId) != null;
            }
        };
    }
}