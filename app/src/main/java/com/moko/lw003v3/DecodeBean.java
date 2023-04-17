package com.moko.lw003v3;

/**
 * @author: jun.liu
 * @date: 2023/4/17 10:08
 * @des:
 */
public class DecodeBean {
    public String byteStr;
    public int port;

    public DecodeBean(String byteStr, int port) {
        this.byteStr = byteStr;
        this.port = port;
    }

    public DecodeBean() {
    }
}
