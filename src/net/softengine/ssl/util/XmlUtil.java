package net.softengine.ssl.util;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.softengine.ssl.api.ReplyResult;
import net.softengine.ssl.api.SMSInfo;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 25/01/16
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 25/01/16
 * Version : 1.0
 */

public class XmlUtil {


    public static ReplyResult generateActionResult(String xmlRecords) throws Exception {
        ReplyResult result = new ReplyResult();

        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xmlRecords));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("REPLY");

        for (int ii = 0; ii < nodes.getLength(); ii++) {
            Element element = (Element) nodes.item(ii);
            Element line;

            NodeList parameter = element.getElementsByTagName("PARAMETER");
            int i;
            for (i = 0; i < parameter.getLength(); i++) {
                line = (Element) parameter.item(i);
                if (line != null)
                    result.setParameter(getCharacterDataFromElement(line));
            }

            NodeList login = element.getElementsByTagName("LOGIN");
            for (i = 0; i < login.getLength(); i++) {
                line = (Element) login.item(i);
                if (line != null)
                    result.setLogin(getCharacterDataFromElement(line));
            }

            NodeList pushApi = element.getElementsByTagName("PUSHAPI");
            for (i = 0; i < pushApi.getLength(); i++) {
                line = (Element) pushApi.item(i);
                if (line != null)
                    result.setPushApi(getCharacterDataFromElement(line));
            }

            NodeList stakeHolderId = element.getElementsByTagName("STAKEHOLDERID");
            for (i = 0; i < stakeHolderId.getLength(); i++) {
                line = (Element) stakeHolderId.item(i);
                if (line != null)
                    result.setStakeHolderId(getCharacterDataFromElement(line));
            }

            NodeList permitted = element.getElementsByTagName("PERMITTED");
            for (i = 0; i < permitted.getLength(); i++) {
                line = (Element) permitted.item(i);
                if (line != null)
                    result.setPermitted(getCharacterDataFromElement(line));
            }

            //===============================================================
            NodeList smsInfoList = element.getElementsByTagName("SMSINFO");
            SMSInfo smsInfo;
            for (i = 0; i < smsInfoList.getLength(); i++) {
                smsInfo = new SMSInfo();
                System.out.println("SMS INFO : ");
                NodeList msiSdn = element.getElementsByTagName("MSISDN");
                line = (Element) msiSdn.item(i);
                if (line != null)
                    smsInfo.setMsiSdn(getCharacterDataFromElement(line));

                NodeList smsText = element.getElementsByTagName("SMSTEXT");
                line = (Element) smsText.item(i);
                if (line != null)
                    smsInfo.setSmsText(getCharacterDataFromElement(line));

                NodeList msiSdnStatus = element.getElementsByTagName("MSISDNSTATUS");
                line = (Element) msiSdnStatus.item(i);
                if (line != null)
                    smsInfo.setMsiSdnStatus(getCharacterDataFromElement(line));
                else
                    smsInfo.setMsiSdnStatus("OK");

                NodeList csmSid = element.getElementsByTagName("CSMSID");
                line = (Element) csmSid.item(i);
                if (line != null)
                    smsInfo.setCsmSid(getCharacterDataFromElement(line));

                NodeList referenceId = element.getElementsByTagName("REFERENCEID");
                line = (Element) referenceId.item(i);
                if (line != null)
                    smsInfo.setReferenceId(getCharacterDataFromElement(line));
                result.getSmsInfoList().add(smsInfo);
            }
        }
        return result;

    }

    public static void readStringXML(String xmlRecords) throws Exception {
       /* String xmlRecords = "<data><employee><name>A</name>"
                + "<title>Manager</title></employee></data>";*/

        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xmlRecords));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("REPLY");

        for (int ii = 0; ii < nodes.getLength(); ii++) {
            Element element = (Element) nodes.item(ii);
            Element line;

            NodeList parameter = element.getElementsByTagName("PARAMETER");
            int i;
            for (i = 0; i < parameter.getLength(); i++) {
                line = (Element) parameter.item(i);
                System.out.println("PARAMETER: " + getCharacterDataFromElement(line));
            }

            NodeList login = element.getElementsByTagName("LOGIN");
            for (i = 0; i < login.getLength(); i++) {
                line = (Element) login.item(i);
                System.out.println("LOGIN: " + getCharacterDataFromElement(line));
            }

            NodeList pushApi = element.getElementsByTagName("PUSHAPI");
            for (i = 0; i < pushApi.getLength(); i++) {
                line = (Element) pushApi.item(i);
                System.out.println("PUSHAPI: " + getCharacterDataFromElement(line));
            }

            NodeList stakeHolderId = element.getElementsByTagName("STAKEHOLDERID");
            for (i = 0; i < stakeHolderId.getLength(); i++) {
                line = (Element) stakeHolderId.item(i);
                System.out.println("STAKEHOLDERID: " + getCharacterDataFromElement(line));
            }
            NodeList smsInfo = element.getElementsByTagName("SMSINFO");
            for (i = 0; i < smsInfo.getLength(); i++) {
                System.out.println("SMS INFO : ");
                NodeList msiSdn = element.getElementsByTagName("MSISDN");
                line = (Element) msiSdn.item(i);
                System.out.println("\tMSISDN: " + getCharacterDataFromElement(line));

                NodeList smsText = element.getElementsByTagName("SMSTEXT");
                line = (Element) smsText.item(i);
                System.out.println("\tSMSTEXT: " + getCharacterDataFromElement(line));

                NodeList csmSid = element.getElementsByTagName("CSMSID");
                line = (Element) csmSid.item(i);
                System.out.println("\tCSMSID: " + getCharacterDataFromElement(line));

                NodeList referenceId = element.getElementsByTagName("REFERENCEID");
                line = (Element) referenceId.item(i);
                System.out.println("\tREFERENCEID: " + getCharacterDataFromElement(line));
            }
        }

    }

    public static void main(String[] args) {
        try {
            readStringXML(xml2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

    static String xml2 = "<?xml version='1.0' encoding='ISO-8859-1' ?> " +
            "<REPLY> " +
            "<PARAMETER>OK</PARAMETER> " +
            "<LOGIN>SUCESSFULL</LOGIN> " +
            "<PUSHAPI>ACTIVE</PUSHAPI> " +
            "<STAKEHOLDERID>OK</STAKEHOLDERID> " +
            "<PERMITTED>OK</PERMITTED> " +
            "<SMSINFO> " +
            "<MSISDN>8801913900620</MSISDN> " +
            "<SMSTEXT>Test SMS 1</SMSTEXT> " +
            "<CSMSID>123456789</CSMSID> " +
            "<REFERENCEID>2013051107040462930900620</REFERENCEID> " +
            "</SMSINFO> " +
            "<SMSINFO> " +
            "<MSISDN>8801913900620</MSISDN> " +
            "<SMSTEXT>Test SMS 2</SMSTEXT> " +
            "<CSMSID>123456790</CSMSID> " +
            "<REFERENCEID>2013051107040454068900620</REFERENCEID> " +
            "</SMSINFO> " +
            "</REPLY>";
}
