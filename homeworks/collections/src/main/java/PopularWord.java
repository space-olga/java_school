import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Поиск наиболее часто встречающегося слова в тексте

// Пример входных данных
// a aa aaa aaaa a aa aaa bbbb
public class PopularWord {
    public static String getPopularWord(String[] wordsArray) {
        Map<String, Integer> wordsCountMap = new TreeMap<String, Integer>();
        Arrays.stream(wordsArray).forEach(word -> {
            if (wordsCountMap.containsKey(word.toLowerCase()))
                wordsCountMap.put(word.toLowerCase(), wordsCountMap.get(word.toLowerCase()) + 1);
            else wordsCountMap.put(word, 1);
        });

        Integer maxValue = Collections.max(wordsCountMap.entrySet(), Map.Entry.comparingByValue()).getValue();
        // перебор элементов
        Optional<String> sentenceWords = wordsCountMap.entrySet().stream()
                                        .filter(item -> item.getValue() == maxValue).map(item -> item.getKey())
                                        .reduce((itemlast, itemnext)-> String.format("%s\n%s", itemlast, itemnext));
        return sentenceWords.get();
    }

    public static void main(String[] argv) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] wordsArray = (in.readLine()).split("\\s|\\n");

        System.out.println(getPopularWord(wordsArray));

       /* Map<String, Integer> wordsCountMap = new TreeMap<String, Integer>();
        Arrays.stream(wordsArray).forEach(word -> {
            if (wordsCountMap.containsKey(word.toLowerCase()))
                wordsCountMap.put(word.toLowerCase(), wordsCountMap.get(word.toLowerCase()) + 1);
            else wordsCountMap.put(word, 1);
        });

        Integer maxValue = Collections.max(wordsCountMap.entrySet(), Map.Entry.comparingByValue()).getValue();
        // перебор элементов
        wordsCountMap.entrySet().stream().filter(item -> item.getValue() == maxValue).map(item -> item.getKey()).forEach(System.out::println);
    */
    }
}
