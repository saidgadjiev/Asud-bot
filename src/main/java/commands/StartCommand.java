package commands;

import common.MessageSender;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

/**
 * Created by said on 27.10.16.
 */
public class StartCommand extends BotCommand {

    public static final String COMMAND = "start";
    public static final String DESCRIPTION = "start command";
    private static final String TAG = "STARTCOMMAND";

    public StartCommand() {
        super(COMMAND, DESCRIPTION);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = chat.getUserName();

        if (userName == null || userName.isEmpty()) {
            userName = user.getFirstName() + " " + user.getLastName();
        }
        try {
            MessageSender.sendMessage(absSender, chat.getId(), "hello " + userName);
        } catch (TelegramApiException e) {
            BotLogger.error("StartCommand", e);
        }
    }

    @Override
    public String toString() {
        return "/" + this.getCommandIdentifier() + " " + this.getDescription();
    }
}
