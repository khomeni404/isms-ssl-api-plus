package co.sebd.ssl.ismsplus.api;

/**
 * Copyright &copy; Soft Engine
 * <p>
 * Original author: Ayatullah Khomeni<br/>
 * Date: 24/01/2016
 * Last modification by: ayat $
 * Last modification on 24/01/2016: 12:07 PM
 * Current revision: : 1.1.1.1
 * <p/>
 * Revision History:
 * ------------------
 */
public enum  SMSType {
    ASCII (0), UNICODE(8);
    public int CODE;
    SMSType(int code) {
        this.CODE = code;
    }
}
