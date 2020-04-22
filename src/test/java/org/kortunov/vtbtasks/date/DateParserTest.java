package org.kortunov.vtbtasks.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kortunov.vtbtasks.sum.SumAllNumbers;

import java.text.SimpleDateFormat;

public class DateParserTest {

    @Test
    public void testGetData(){
        String pattern = "yyyyMMddHHmm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        var a = DateParser.getData(" fsfss 201603031111 fsdfs 201403031211 sffsfs 223603031111", simpleDateFormat);
        Assertions.assertEquals(DateParser.getData(" fsfss 201603031111 sffsfs 223603031111", simpleDateFormat).toString(),
                "[Thu Mar 03 11:11:00 MSK 2016, Thu Mar 03 11:11:00 MSK 2236]");
    }
}
