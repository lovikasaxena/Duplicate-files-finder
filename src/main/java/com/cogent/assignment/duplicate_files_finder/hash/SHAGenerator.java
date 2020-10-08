package com.cogent.assignment.duplicate_files_finder.hash;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SHAGenerator {

    public static final String SHA_256 = "SHA-256";

    public String createSHAFor(String content) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest instance = MessageDigest.getInstance(SHA_256);
        byte[] messageDigest = instance.digest(content.getBytes(UTF_8));
        return new BigInteger(1, messageDigest).toString(16);
    }

}
