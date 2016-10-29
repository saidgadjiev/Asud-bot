package common;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by said on 29.10.16.
 */
public class MessageSender {
    public static void sendMessage(AbsSender absSender, Long chatId, String message) throws TelegramApiException {
        SendMessage answer = new SendMessage();

        answer.setChatId(chatId.toString());
        answer.setText(message);
        absSender.sendMessage(answer);
    }
}
