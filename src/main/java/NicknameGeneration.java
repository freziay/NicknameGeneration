import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class NicknameGeneration {
    static int counThree = 0;
    static int countFour = 0;
    static int countFive = 0;
    static String word;
    public static ArrayList<String> arrayList = new ArrayList<>();

    private static AtomicInteger atomic3 = new AtomicInteger(counThree);
    private static AtomicInteger atomic4 = new AtomicInteger(countFour);
    private static AtomicInteger atomic5 = new AtomicInteger(countFive);

    public static void main(String args[]) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[10_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
            word = texts[i];
        }
        Thread thread1 = new Thread(() -> {
            polindrom(arrayList, texts);

        });
        Thread thread2 = new Thread(() -> {
            repeatedLetters(arrayList,texts);

        });
        Thread thread3 = new Thread(() -> {
            repeatInAlphabeticalOrder(arrayList,texts);

        });
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
        for (int k = 0; k < arrayList.size(); k++) {
            String s = arrayList.get(k);
            String[] words = s.split("");

            if (words.length == 3) {
                atomic3.getAndIncrement();
            }

            if (words.length == 4) {
                atomic4.getAndIncrement();
            }
            if (words.length == 5) {
                atomic5.getAndIncrement();
            }
        }
        System.out.println("Красивых слов с длиной 3: " + atomic3.get() + " шт" +
                "\r\n" + "Красивых слов с длиной 4: " + atomic4.get() + " шт" + "\r\n" +
                "Красивых слов с длиной 5: " + atomic5.get() + " шт");
    }
    public static void polindrom(ArrayList<String> arrayList, String[] texts) {
        for (int i = 0; i < texts.length; i++) {
            if (texts[i].equals(new StringBuffer().append(texts[i]).reverse().toString())) {
                String s = texts[i];
                String[] words = s.split("");
                String one = words[1];
                for (int j = 2; j < words.length; j++) {
                    if (!one.equals(words[j])) {
                        addList(arrayList, texts[i]);
                        break;
                    } else {
                        break;
                    }
                }
            }
        }
    }
    public static void repeatedLetters(ArrayList<String> arrayList, String[] texts){
        for (int k = 0; k < texts.length; k++) {
            String s = texts[k];
            String[] words = s.split("");
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
                addList(arrayList, s);
            }
        }
    }
    public static void repeatInAlphabeticalOrder(ArrayList<String> arrayList, String[] texts){
        for (int r = 0; r < texts.length; r++) {
            char[] charArray = texts[r].toCharArray();
            Arrays.sort(charArray);
            String sortedString = new String(charArray);
            for (int j = 0; j < sortedString.length(); j++) {
                if (sortedString.equals(texts[j])) {
                    String s = texts[j];
                    String[] words = s.split("");
                    String one = words[0];
                    for (int i = 1; i < words.length; i++) {
                        if (one.equals(words[i])) {
                        } else {
                            addList(arrayList, s);
                            break;
                        }
                    }
                }
            }
        }

    }
    public static synchronized void addList(ArrayList<String> arrayList, String string) {
        arrayList.add(string);
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
