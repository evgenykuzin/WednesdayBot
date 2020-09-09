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
            int limit = new Random().nextInt(1000) * 1000 + 60000;
            if (System.currentTimeMillis() - lastCall > limit) {
                lastCall = System.currentTimeMillis();
                String date = new SimpleDateFormat("EEEE", new Locale("ru")).format(new Date());
                int houres = Integer.parseInt(new SimpleDateFormat("HH").format(new Date()));
                int minutes = Integer.parseInt(new SimpleDateFormat("mm").format(new Date()));
                int seconds = Integer.parseInt(new SimpleDateFormat("ss").format(new Date()));
                boolean isTimeToSend = date.toLowerCase().equals("wednesday") && houres == 10 && minutes == 30 && seconds == 30;
                if (isTimeToSend && needToSendWednesday) {
                    bot.sendPhoto(Context.SISKAPISKA_CHAT_ID, Context.WEDNESDAY_PHOTO_ID);
                    needToSendWednesday = false;
                } else needToSendWednesday = true;
            }
        }
    }
}
