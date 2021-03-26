package com.example.rpc;

/**
 * @author liurui
 * @date 2020/12/7
 */
public class ServiceProducerImpl implements ServiceProducer{
    @Override
    public String sendData(String data) {
        return "I am service producer!!!, the data is "+ data;
    }
}

