package concurrency.waitnotify;

import java.util.function.Function;

import static concurrency.threadlesson.ColorScheme.BLUE;
import static concurrency.threadlesson.ColorScheme.RED;

public class WaitNotifyExample {

    public static void main(String[] args) {
        Message message = new Message();
        new Thread(new Producer(message)).start();
        new Thread(new Consumer(message)).start();


    }


    private static class Producer implements Runnable {
        private final Message message;

        Producer(Message message) {
            this.message = message;
        }

        String[] messages = {
                "Через час отсюда в чистый переулок",
                "вытечет по человеку ваш обрюзгший жир,",
                "а я вам открыл столько стихов шкатулок,",
                "я — бесценных слов мот и транжир.",

                "Вот вы, мужчина, у вас в усах капуста",
                "Где-то недокушанных, недоеденных щей;",
                "вот вы, женщина, на вас белила густо,",
                "вы смотрите устрицей из раковин вещей.",

                "Все вы на бабочку поэтиного сердца",
                "взгромоздитесь, грязные, в калошах и без калош.",
                "Толпа озвереет, будет тереться,",
                "ощетинит ножки стоглавая вошь.",

                "А если сегодня мне, грубому гунну,",
                "кривляться перед вами не захочется — и вот",
                "я захохочу и радостно плюну,",
                "плюну в лицо вам",
                "я — бесценных слов транжир и мот.",
                "DONE."
        };

        @Override
        public void run() {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void produce() throws InterruptedException {
            for(String s : messages) {
                synchronized (message) {
                    System.out.println(BLUE + "Producing message: " + s);
                    message.setMessage(s);
                    message.notify();
                    if (!"DONE.".equals(s)) {
                        message.wait();
                    }
                    Thread.sleep(400);
                }
            }
        }
    }

    private static class Consumer implements Runnable {
        private final Message message;

        Consumer(Message message) {
            this.message = message;
        }

        @Override
        public void run() {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void consume() throws InterruptedException {
            while (true) {
                Thread.sleep(400);
                synchronized (message) {
                    System.out.println(RED + "Consuming message: " + updateStringWithFunc(message.getMessage(), e -> ">>> "+e+"!!!"));
                    if (!"DONE.".equals(message.getMessage())) {
                        message.notify();
                        message.wait();
                    } else return;
                }
            }
        }

        private static String updateStringWithFunc(String message, Function<String, String> function) {
            return function.apply(message);
        }
    }

}
