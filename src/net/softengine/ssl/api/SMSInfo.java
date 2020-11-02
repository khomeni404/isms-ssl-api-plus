package net.softengine.ssl.api;

/**
 * Copyright &copy; Soft Engine
 * <p/>
 * Original author: Ayatullah Khomeni<br/>
 * Date: 26/01/2016
 * Last modification by: ayat $
 * Last modification on 26/01/2016: 2:40 PM
 * Current revision: : 1.1.1.1
 * <p/>
 * Revision History:
 * ------------------
 */
public class SMSInfo {

    private String  msiSdn;  //Cell No
    private String  smsText;
    private String  csmSid;
    private String  referenceId;
    private String  msiSdnStatus;


    public String getMsiSdn() {
        return msiSdn;
    }

    public void setMsiSdn(String msiSdn) {
        this.msiSdn = msiSdn;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    public String getCsmSid() {
        return csmSid;
    }

    public void setCsmSid(String csmSid) {
        this.csmSid = csmSid;
    }

    public String getMsiSdnStatus() {
        return msiSdnStatus;
    }

    public void setMsiSdnStatus(String msiSdnStatus) {
        this.msiSdnStatus = msiSdnStatus;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
}
