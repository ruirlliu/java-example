package com.harbor.test;

import com.harbor.client.HarborClientBuilder;
import com.harbor.client.v1.HarborClientV1;
import com.harbor.client.v1.HarborResponse;
import com.harbor.client.v1.flag.ResponseConfigure;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author lr
 * @date 2021/2/4
 */
public class ClientTest {

    public static void main(String[] args) throws IOException {

        testV1();

    }


    public static void testV1() {
        HarborClientV1 clientV1 = new HarborClientBuilder()
                .setUrl("http://192.168.1.72:30000/")
                .setUsername("admin")
                .setPassword("Harbor123456")
                .setConnectionTimeout(30, TimeUnit.SECONDS)
                .disable(ResponseConfigure.FAILED_THROW)
                .buildV1();

        clientV1.projects().withName("test").withExactName("test").get();


        HarborResponse<String> delete = clientV1.projects().withExactName("test022221").delete();

//        System.out.println(create);
        System.out.println(delete);
    }



}
