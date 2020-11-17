package net.softengine.ssl.api;

import java.util.HashMap;
import java.util.Map;

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

    Map<Integer, String> API_STATUS_CODE_MAP = new HashMap<Integer, String>() {{
        put(4001, "Unauthorized");
        put(4002, "SID/Stakeholder is not permitted");
        put(4003, "IP Blacklisted");
        put(4004, "End point Not Found");
        put(4005, "Invalid request format");
        put(4020, "Invalid CSMS ID");
        put(4022, "Required Parameter Missing");
        put(4023, "Duplicate CMS ID");
        put(4025, "Invalid Cell No.");
        put(4026, "Blocked Cell No.");
        put(4027, "Message length exceeded");
        put(4028, "Invalid message data");
        put(4029, "Too many requests");
        put(4030, "Limit Exceed");
        put(4031, "TPS Exceeded");
        put(5000, "Unknown Error");
    }};
}
