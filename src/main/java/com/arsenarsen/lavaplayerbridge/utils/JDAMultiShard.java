package com.arsenarsen.lavaplayerbridge.utils;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;

import java.util.Arrays;
import java.util.Objects;

public class JDAMultiShard {
    private JDA[] jdas;

    public JDAMultiShard(JDA[] jdas){
        this.jdas = jdas;
    }

    public boolean isValid(String id){
        return getGuild(id) != null;
    }

    public Guild getGuild(String id){
        return Arrays.stream(jdas).map(jda -> jda.getGuildById(id))
                .filter(Objects::nonNull).findFirst().orElse(null);
    }
}
