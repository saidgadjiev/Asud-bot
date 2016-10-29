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
import org.telegram.telegrambots.logging.BotLogger;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by said on 27.10.16.
 */
public class BuildCommand extends BotCommand {

    private static final String LOGTAG = "BUILDCOMMAND";
    public static final String COMMAND = "build";
    public static final String DESCRIPTION = "usage area_name job_name key value";
    private final HudsonDAO dao;

    public BuildCommand() throws SQLException, URISyntaxException {
        super(COMMAND, DESCRIPTION);
        this.dao = new HudsonDAO();
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        try {
            String message;

            if (arguments.length > 1) {
                Hudson hudson = dao.getByName(arguments[0]);

                if (hudson != null) {
                    message = "Run job for " + hudson.getName() + " url " + hudson.getHudsonUrl() + " job name " + arguments[1];
                    JenkinsServer jenkinsServer = new JenkinsServer(new URI(hudson.getHudsonUrl()), hudson.getLogin(), hudson.getPassword());
                    Map<String, Job> jobs = jenkinsServer.getJobs();

                    if (jobs.containsKey(arguments[1])) {
                        List<String> paramsList = Arrays.asList(arguments).subList(2, arguments.length);
                        Map<String, String> params = new HashMap<>();

                        for (int i = 0; i < paramsList.size(); i += 2) {
                            params.put(paramsList.get(i), paramsList.get(i + 1));
                        }
                        jobs.get(arguments[1]).build(params);
                    } else {
                        message = MessageDescription.JOB_NOT_FOUND;
                    }
                } else {
                    message = MessageDescription.HUDSON_DOEST_EXIST;
                }
            } else {
                message = MessageDescription.FEW_ARGS;
            }
            MessageSender.sendMessage(absSender, chat.getId(), message);
        } catch (Exception e) {
            BotLogger.error(LOGTAG, e);
        }

    }

    @Override
    public String toString() {
        return "/" + this.getCommandIdentifier() + " " + this.getDescription();
    }
}
