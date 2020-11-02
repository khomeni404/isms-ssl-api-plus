package net.softengine.ssl.api;

/**
 * Copyright &copy; Soft Engine
 * <p>
 * Original author: Ayatullah Khomeni<br/>
 * Date: 24/01/2016
 * Last modification by: ayat $
 * Last modification on 24/01/2016: 12:20 PM
 * Current revision: : 1.1.1.1
 * <p/>
 * Revision History:
 * ------------------
 */
public interface StatusCode {

    int NONE = 0;
    int ENROUTE = 1;
    int DELEVERED = 2;
    int EXPIRED = 3;
    int DELETED = 4;
    int UNDELIVERABLE = 5;
    int ACCEPTED = 6;
    int UNKNOWN = 7;
    int REJECTED = 8;
    int EXCEPTION = 9;



}
