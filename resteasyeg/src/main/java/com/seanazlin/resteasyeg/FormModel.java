package com.seanazlin.resteasyeg;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;

public class FormModel {
    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    @FormParam("file")
    @PartType("application/octet-stream")
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}
