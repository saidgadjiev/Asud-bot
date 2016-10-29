package commands;

import common.MessageSender;
import db.dao.HudsonDAO;
import db.models.Hudson;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by said on 27.10.16.
 */
public class InfoCommand extends BotCommand {

    private static final String LOGTAG = "INFOCOMMAND";
    public static final String COMMAND = "info";
    public static final String DESCRIPTION = "list of hudsons";
    private final HudsonDAO dao;

    public InfoCommand() throws SQLException {
        super(COMMAND, DESCRIPTION);
        this.dao = new HudsonDAO();
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        try {
            List<Hudson> hudsons = dao.getAll();
            SendMessage answer = new SendMessage();
            StringBuilder messageBuilder = new StringBuilder();

            for (Hudson hudson: hudsons) {
                messageBuilder.append(hudson.toString()).append("\n");
            }
            MessageSender.sendMessage(absSender, chat.getId(), messageBuilder.toString());
        } catch (SQLException | TelegramApiException e) {
            BotLogger.error(LOGTAG, e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "/" + this.getCommandIdentifier() + " " + this.getDescription();
    }
}
