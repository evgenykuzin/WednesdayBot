import org.telegram.telegrambots.meta.api.methods.send.SendVideo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Sender extends Thread {
    private Bot bot;
    public Sender(Bot bot) {
        this.bot = bot;
    }
    private boolean needToSendWednesday = Context.needToSendWednesday;
    @Override
    public void run() {
        while (true) {
            String date = new SimpleDateFormat("EEEE", new Locale("en")).format(new Date());
            if (date.toLowerCase().equals("wednesday") && needToSendWednesday) {
                bot.sendPhoto(Context.SISKAPISKA_CHAT_ID, Context.WEDNESDAY_PHOTO_ID);
                needToSendWednesday = false;
            } else needToSendWednesday = true;
        }
    }
}
