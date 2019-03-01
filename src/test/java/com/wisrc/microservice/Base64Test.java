package com.wisrc.microservice;

import com.google.common.io.BaseEncoding;
import com.wisrc.batch.utils.JoinCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Base64Test {

    @Test
    public void testJoin(){
        String code = JoinCode.join("123","abc");
        System.out.println(code);
        String first = JoinCode.getFirst(code);
        System.out.println(first);
        String last = JoinCode.getLast(code);
        System.out.println(last);
        byte[] val = BaseEncoding.base64().decode(code);
        System.out.println(new String(val));
        for(byte item: val) {
            System.out.println(item);
        }
    }
}
