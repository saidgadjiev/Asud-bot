package commands;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import common.MessageDescription;
import common.MessageSender;
import db.dao.HudsonDAO;
import db.models.Hudson;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by said on 29.10.16.
 */
public class GetJobsCommand extends BotCommand {

    public static final String COMMAND = "getjobs";
    public static final String DESCRIPTION = "this command getJobs list from hudson";
    private final HudsonDAO dao;

    public GetJobsCommand() throws SQLException {
        super(COMMAND, DESCRIPTION);
        dao = new HudsonDAO();
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        try {
            String message;

            if (strings.length > 0) {
                Hudson hudson = dao.getByName(strings[0]);
                Map<String, Job> jobs = new JenkinsServer(new URI(hudson.getHudsonUrl()), hudson.getLogin(), hudson.getPassword()).getJobs();
                StringBuilder builder = new StringBuilder();

                for (Map.Entry<String, Job> entry : jobs.entrySet()) {
                    builder.append(entry.getKey()).append("\n");
                }
                message = builder.toString();
            } else {
                message = MessageDescription.FEW_ARGS;
            }
            MessageSender.sendMessage(absSender, chat.getId(), message);
        } catch (SQLException | URISyntaxException | IOException | TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "/" + this.getCommandIdentifier() + " " + this.getDescription();
    }
}
