import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

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
            int limit = new Random().nextInt(1000) * 1000 + 10000;
            if (System.currentTimeMillis() - lastCall > limit) {
                lastCall = System.currentTimeMillis();
                String date = new SimpleDateFormat("EEEE", new Locale("en")).format(new Date());
                String time = new SimpleDateFormat("hh").format(new Date());
                boolean isTimeToSend = date.toLowerCase().equals("wednesday") && (time.equals("10") || time.equals("12"));
                if (isTimeToSend && needToSendWednesday) {
                    bot.sendPhoto(Context.SISKAPISKA_CHAT_ID, Context.WEDNESDAY_PHOTO_ID);
                    needToSendWednesday = false;
                } else needToSendWednesday = true;
            }
        }
    }
}
