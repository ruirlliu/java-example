package com.example.framework.netty.v1.pojo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author lr
 * @date 2021/1/28
 */
public class UnixTime {

    private final long value;

    public UnixTime(long value) {
        this.value = value;
    }

    public UnixTime() {
        this(System.currentTimeMillis());
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "UnixTime{" +
                "value=" + LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneId.systemDefault()) +
                '}';
    }
}
