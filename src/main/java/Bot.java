import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

public class Bot extends TelegramLongPollingBot {

    private String botName;
    private String botToken;

    public Bot() throws IOException {
        loadProps();
        System.out.println("bot '" + botName + "' run!");
    }

    private void loadProps() throws IOException {
        String rootPath = new File("").getAbsolutePath() + "/src/main/resources";
        String appConfigPath = rootPath + "/props/bot.properties";
        Properties botProps = new Properties();
        botProps.load(new FileInputStream(appConfigPath));
        botName = botProps.getProperty("bot.name");
        botToken = botProps.getProperty("bot.token");

    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message.hasText()) {
            String text = message.getText().toLowerCase();
            if (text.contains("сред") || text.contains("wednesday")) {
                String date = new SimpleDateFormat("EEEE", new Locale("en")).format(new Date());
                sendPhoto(message.getChatId(), Context.WEDNESDAY_PHOTO_ID);
                //sendText(message.getChatId(), "it's " + date + ", my dudes.");
            }
        }
    }

    public synchronized void sendText(long chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            //log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    public synchronized void sendSticker(long chatId, String sticker) {
        SendSticker sendSticker = new SendSticker();
        sendSticker.setChatId(chatId);
        sendSticker.setSticker(sticker);
        try {
            execute(sendSticker);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            //log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    public void sendPhoto(long chatId, String photo) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(photo);
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            //log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    public synchronized void sendVideo(long chatId, String video) {
        SendVideo sendVideo = new SendVideo();
        sendVideo.setChatId(chatId);
        sendVideo.setVideo(video);
        try {
            execute(sendVideo);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            //log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

}
