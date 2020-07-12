import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            Bot bot = new Bot();
            Sender sender = new Sender(bot);
            sender.start();
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException | IOException tae) {
            tae.printStackTrace();
        }

    }
}
