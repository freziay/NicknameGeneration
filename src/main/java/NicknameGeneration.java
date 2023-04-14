import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class NicknameGeneration {
    static int counThree = 0;
    static int countFour = 0;
    static int countFive = 0;
    static String word;
    private static AtomicInteger atomic3 = new AtomicInteger(0);
    private static AtomicInteger atomic4 = new AtomicInteger(0);
    private static AtomicInteger atomic5 = new AtomicInteger(0);


    public static void main(String args[]) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[200];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
            word = texts[i];
            System.out.println(word);
        }
        Thread thread3 = new Thread(() -> {
            for (String text : texts) {
                if (text.equals(new StringBuffer().append(text).reverse().toString())) {
                   System.out.println(text + " polindrom");
                   if(text.length()==3){
                    atomic3.getAndIncrement();}
                   if (text.length()==4){
                    atomic4.getAndIncrement();}
                   if(text.length()==5){
                    atomic5.getAndIncrement();}
                }
            }
        });
        thread3.start();
        Thread thread4 = new Thread(() -> {
            for (int k = 0; k < texts.length; k++) {
                String s = texts[k];
                String[] words = s.split("");
                //   String  d=words[i];
                boolean isPovtor = true;
                String one = words[0];
                for (int j = 1; j < words.length; j++) {
                    if (!one.equals(words[j])) {
                        isPovtor = false;
                        break;
                    } else {
                        one = words[j];
                    }
                }
                if (isPovtor) {
                    System.out.println(s + " kras ");

                    if(s.length()==3){
                        atomic3.getAndIncrement();}
                    if (s.length()==4){
                        atomic4.getAndIncrement();}
                    if(s.length()==5){
                        atomic5.getAndIncrement();}
                }
            }
        });
        thread4.start();
        Thread thread5 = new Thread(() -> {
            for (int r = 0; r < texts.length; r++) {
                char[] charArray = texts[r].toCharArray();
                Arrays.sort(charArray);
                String sortedString = new String(charArray);
                for (int j = 0; j < texts.length; j++) {
                    if (!sortedString.equals(texts[j])) {
                    } else {
                        System.out.println(texts[j]+" povtor");
                        if(sortedString.length()==3){
                            atomic3.getAndIncrement();}
                        if (sortedString.length()==4){
                            atomic4.getAndIncrement();}
                        if(sortedString.length()==5){
                            atomic5.getAndIncrement();}
                    }
                }
            }
        });
        thread5.start();

        thread3.join();
        thread4.join();
        thread5.join();
        System.out.println("Красивых слов с длиной 3:" + atomic3.get() + " шт");
        System.out.println("Красивых слов с длиной 4:" + atomic4.get() + " шт");
        System.out.println("Красивых слов с длиной 5:" + atomic5.get() + " шт");
    }
//    public void run() {
//        if
//        System.out.println(Thread.currentThread().getName() + "# " +
//                atomic.getAndIncrement());
//    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
