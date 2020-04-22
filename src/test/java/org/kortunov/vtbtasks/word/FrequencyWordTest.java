package org.kortunov.vtbtasks.word;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.kortunov.vtbtasks.role.model.Privilege.READ;

public class FrequencyWordTest {

    @Test
    public void testGetWord(){
        Assertions.assertEquals( "round", FrequencyWord.getWord("Right round like a record, baby Right round round round"));
    }

}
