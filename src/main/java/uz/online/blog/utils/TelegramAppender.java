package uz.online.blog.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class TelegramAppender extends AppenderBase<LoggingEvent> {

    private static final String BOT_TOKEN = "7474179192:AAFHZjuFrL-eb2mVRO-nImOZXIxwlPRxlXA";
    private static final String CHAT_ID = "1808922480";
    private static final TelegramBot bot = new TelegramBot(BOT_TOKEN);

    public TelegramAppender() {
        addFilter(new Filter<>() {
            @Override
            public FilterReply decide(LoggingEvent loggingEvent) {
                return loggingEvent.getLevel().equals(Level.ERROR) ? FilterReply.ACCEPT : FilterReply.DENY;
            }
        });
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        try {
            String logMessage = loggingEvent.getFormattedMessage();
            SendMessage sendMessage = new SendMessage(CHAT_ID, logMessage);
            bot.execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
