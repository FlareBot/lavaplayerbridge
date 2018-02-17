package com.arsenarsen.lavaplayerbridge.utils;

import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.bot.utils.cache.ShardCacheView;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;

import java.util.Arrays;
import java.util.Objects;

public class JDAMultiShard {
    private ShardManager manager;

    public JDAMultiShard(ShardManager manager){
        this.manager = manager;
    }

    public boolean isValid(String id){
        return manager.getGuildById(id) != null;
    }

    public Guild getGuild(String id) {
        return manager.getGuildById(id);
    }
}
