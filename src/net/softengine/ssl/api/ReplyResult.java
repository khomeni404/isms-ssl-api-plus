package net.softengine.ssl.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright &copy; Soft Engine
 * <p>
 * Original author: Ayatullah Khomeni<br/>
 * Date: 24/01/2016
 * Last modification by: ayat $
 * Last modification on 24/01/2016: 12:05 PM
 * Current revision: : 1.1.1.1
 * <p/>
 * Revision History:
 * ------------------
 */
public class ReplyResult {

    private boolean success;
    private String parameter;
    private String login;
    private String pushApi;
    private String stakeHolderId;
    private String permitted;

    private List<SMSInfo> smsInfoList = new ArrayList<SMSInfo>(0);

    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPushApi() {
        return pushApi;
    }

    public void setPushApi(String pushApi) {
        this.pushApi = pushApi;
    }

    public String getStakeHolderId() {
        return stakeHolderId;
    }

    public void setStakeHolderId(String stakeHolderId) {
        this.stakeHolderId = stakeHolderId;
    }

    public String getPermitted() {
        return permitted;
    }

    public void setPermitted(String permitted) {
        this.permitted = permitted;
    }

    public List<SMSInfo> getSmsInfoList() {
        return smsInfoList;
    }

    public void setSmsInfoList(List<SMSInfo> smsInfoList) {
        this.smsInfoList = smsInfoList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
