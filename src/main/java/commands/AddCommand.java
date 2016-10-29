package commands;

import common.MessageDescription;
import common.MessageSender;
import db.dao.HudsonDAO;
import db.models.Hudson;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by said on 27.10.16.
 */
public class AddCommand extends BotCommand {

    private static final String LOGTAG = "ADDCOMMAND";
    public static final String COMMAND = "add";
    public static final String DESCRIPTION = "usage area_name hadson_url login password";
    private final HudsonDAO dao;

    public AddCommand() throws SQLException {
        super(COMMAND, DESCRIPTION);
        dao = new HudsonDAO();
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        try {
            String message;

            if (strings.length > 1) {
                if (strings.length > 2 && strings.length < 4) {
                    MessageSender.sendMessage(absSender, chat.getId(), MessageDescription.FEW_ARGS);

                    return;
                }
                dao.createOrUpdate(new Hudson(
                        strings[0],
                        strings[1],
                        strings.length > 2 ? strings[2] : "",
                        strings.length > 3 ? strings[3] : "",
                        strings.length > 4 ? strings[4] : ""));
                BotLogger.info(LOGTAG, "hudson created " + Arrays.toString(strings));
                message = MessageDescription.HUDSON_CREATED;
            } else {
                message = MessageDescription.FEW_ARGS;
            }
            MessageSender.sendMessage(absSender, chat.getId(), message);
        } catch (SQLException | TelegramApiException e) {
            BotLogger.error(LOGTAG, e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "/" + this.getCommandIdentifier() + " " + this.getDescription();
    }
}
