package org.kortunov.vtbtasks.word;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FrequencyWord {

    public static String getWord(String string){
        return Optional.ofNullable(string)
                .map(String::toLowerCase)
                .map(s -> s.split("\\s"))
                .stream()
                .flatMap(Stream::of)
                .collect(groupingBy(identity(), counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

}
