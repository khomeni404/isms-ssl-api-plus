package net.softengine.ssl.api;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 25/01/16
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 25/01/16
 * Version : 1.0
 */

public enum Mask {
    KHOMENI ("Khomeni"),
    ABC_ETC ("Abc Etc"),
    SMS4BD ("sms4bd");
    public String NAME;
    Mask(String name) {
        this.NAME = name;
    }
}
