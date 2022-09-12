package io.github.mcchampions.DodoOpenJava.Command;

import io.github.mcchampions.DodoOpenJava.Event.EventHandler;
import io.github.mcchampions.DodoOpenJava.Event.Listener;
import io.github.mcchampions.DodoOpenJava.Event.events.MessageEvent;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static io.github.mcchampions.DodoOpenJava.Command.Command.commands;

/**
 * 命令触发
 */
public class CommandTrigger implements Listener {
    @EventHandler
    public void event(MessageEvent e) {
        JSONObject jsontext = new JSONObject(e.jsonString);
        if (jsontext.getJSONObject("eventBody").getInt("messageType") != 1) return;
        if (jsontext.getJSONObject("eventBody").getJSONObject("messageBody").getString("content").indexOf("/") != 0) return;
        String command = jsontext.getJSONObject("eventBody").getJSONObject("messageBody").getString("content").replaceFirst("/","");
        CommandSender sender = new CommandSender();
        sender.InitSender(jsontext);
        List<String> Command = new java.util.ArrayList<>(List.of(command.split(" ")));
        String MainCommand = Command.get(0);
        Command.remove(0);
        String[] args = Command.toArray(new String[Command.size()]);
        io.github.mcchampions.DodoOpenJava.Command.Command.Trigger(sender, MainCommand, args);
    }

    public static void listenerConsole() {
        ConsoleListener cs = new ConsoleListener(new Scanner(System.in), new ConsoleListener.Action() {

            public void act(String msg) {
                List<String> Command = new java.util.ArrayList<>(List.of(msg.split(" ")));
                String MainCommand = Command.get(0);
                Command.remove(0);
                String[] args = Command.toArray(new String[Command.size()]);
                for (CommandExecutor command :commands) {
                    if (Objects.equals(command.MainCommand(), MainCommand)) {
                        command.onCommand(new ConsoleSender(), args);
                        break;
                    }
                }
            }
        });
        cs.listenInNewThread();

        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
