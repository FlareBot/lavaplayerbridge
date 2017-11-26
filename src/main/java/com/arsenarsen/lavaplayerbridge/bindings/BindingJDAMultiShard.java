package com.arsenarsen.lavaplayerbridge.bindings;

import com.arsenarsen.lavaplayerbridge.libraries.Library;
import com.arsenarsen.lavaplayerbridge.player.Provider;
import net.dv8tion.jda.core.audio.AudioSendHandler;
import net.dv8tion.jda.core.entities.Guild;

/**
 * Wrong one mate.
 */
public class BindingJDAMultiShard {
    public static Library createLibrary(Object o) {
        com.arsenarsen.lavaplayerbridge.utils.JDAMultiShard jda = (com.arsenarsen.lavaplayerbridge.utils.JDAMultiShard) o;
        return new Library(o) {
            @Override
            public void setProvider(String guildId, Provider provider) {
                Guild g = jda.getGuild(guildId);
                if(g != null)
                g.getAudioManager().setSendingHandler(new AudioSendHandler() {
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
                return jda.isValid(guildId);
            }
        };
    }
}
