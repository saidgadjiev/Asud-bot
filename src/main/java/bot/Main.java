package bot;

import com.j256.ormlite.table.TableUtils;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import db.dao.HudsonDAO;
import db.models.Hudson;
import handler.CommandsHandler;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by said on 26.10.16.
 */
public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException, SQLException {
        TelegramBotsApi api = new TelegramBotsApi();

        try {
            api.registerBot(new CommandsHandler());
        } catch (TelegramApiException ex) {
            BotLogger.error("TAG", ex.getMessage());
        }
    }
}
