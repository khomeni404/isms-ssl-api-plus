package net.softengine.ssl.util;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 25/01/16
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 25/01/16
 * Version : 1.0
 */

public class Utility {
    private static final int PAD_LIMIT = 8192;
    public static String leftPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            if (size == str.length()) {
                return str; // returns original String when possible
            } else {
                str = str.substring(0, size);
                return str;
            }

        }
        if (pads > PAD_LIMIT) {
            return leftPad(str, size, padChar);
        }
        return padding(pads, padChar).concat(str);
    }

    public static String rightPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            if (size == str.length()) {
                return str; // returns original String when possible
            }// returns original String when possible
            else {
                str = str.substring(0, size);
                return str;
            }
        }
        if (pads > PAD_LIMIT) {
            return rightPad(str, size, padChar);
        }
        return str.concat(padding(pads, padChar));
    }

    private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException {
        if (repeat < 0) {
            throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
        }
        final char[] buf = new char[repeat];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = padChar;
        }
        return new String(buf);
    }
}
