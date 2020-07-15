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
        long lastCall = 0;
        while (true) {
            if (System.currentTimeMillis() - lastCall > 60000) {
                lastCall = System.currentTimeMillis();
                String date = new SimpleDateFormat("EEEE", new Locale("en")).format(new Date());
                String time = new SimpleDateFormat("hh:mm").format(new Date());
                boolean isTimeToSend = date.toLowerCase().equals("wednesday") && (time.equals("10:30") || time.equals("12:30"));
                if (isTimeToSend && needToSendWednesday) {
                    bot.sendPhoto(Context.SISKAPISKA_CHAT_ID, Context.WEDNESDAY_PHOTO_ID);
                    needToSendWednesday = false;
                }
            } else needToSendWednesday = true;
        }
    }
}
