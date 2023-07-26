package me.gabryosas.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
                List <String> completions = new ArrayList<>();
                completions.add("borsa");
                completions.add("assumi");
                completions.add("deposita");
                completions.add("preleva");
                completions.add("imposta");
                completions.add("licenzia");
                completions.add("give");
                completions.add("crea");
                completions.add("rimuovi");
                completions.add("reload");
                for (String s : completions) {
                    if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                        commands.add(s);
                    }
                }
            return commands;
            }
        return null;
        }
    }
