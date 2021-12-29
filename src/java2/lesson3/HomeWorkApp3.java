package java2.lesson3;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class HomeWorkApp3 {

    private static final String[] WORDS  = {
            "word0",
            "word1",
            "word2",
            "word3", "word3", "word3",
            "word4",
            "word5",
            "word6",
            "word7", "word7",
            "word8",
            "word9",
            "word10"
    };

    public static void main(String[] args){

        System.out.println("----ЗАДАНИЕ 1----");
        Map<String, Integer> wordCount = new TreeMap<>();
        for (String word : WORDS) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        for (Map.Entry<String,Integer> entry : wordCount.entrySet()) {
            System.out.printf("Фраза %s присутствует %d раз %n", entry.getKey(), entry.getValue());
        }
        System.out.println("----ЗАДАНИЕ 2----");
        PhonesBook phonesBook = new PhonesBook();
        phonesBook.add("Иванов","111");
        phonesBook.add("Петров","222");
        phonesBook.add("Сидоров","333");
        phonesBook.add("Сидоров","444");

        Set<String>  names = phonesBook.getNames();
        for (String name : names) {
            System.out.printf("Абонент %s имеет номер %s %n", name, phonesBook.get(name));

        }


    }
    
}
