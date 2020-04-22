package org.kortunov.vtbtasks.date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DateParser {

    public static List<Date> getData(String string, SimpleDateFormat dateFormat) {
        return string.lines()
                .flatMap(line -> Stream.of(line.split("\\s")))
                .map(part -> {
                    try {
                        return dateFormat.parse(part);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
