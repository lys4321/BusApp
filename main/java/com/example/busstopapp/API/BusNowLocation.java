package com.example.busstopapp.API;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class BusNowLocation {
    int page = 1;
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    public ArrayList<Double> NowBusLocationLati(String citycode, String routeID) {
        ArrayList<Double> NBLL = new ArrayList<Double>();

        try {
            while (true) {
                // parsing할 url 지정(API 키 포함해서)
                String url = "http://openapi.tago.go.kr/openapi/service/BusLcInfoInqireService/getRouteAcctoBusLcList?serviceKey=S%2BUtXhAIhzDAPraTZURRpd63mngQxRxCFl4tqOU09y4CQmVwgya4PDI42xeoZODg2r8KzGb%2B5V8Fk0bfHo2Upg%3D%3D&cityCode="+citycode+"&routeId="+routeID;
                DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
                Document doc = dBuilder.parse(url);

                // root tag
                doc.getDocumentElement().normalize();

                // 파싱할 tag
                NodeList nList = doc.getElementsByTagName("item");
                //System.out.println("파싱할 리스트 수 : "+ nList.getLength());

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;
                        //System.out.println(eElement.getTextContent());
                        NBLL.add(Double.valueOf(getTagValue("gpslati", eElement)));//
                    }    // for end
                }    // if end
                if (page <= 2) {
                    break;
                }
            }    // while end

        } catch (Exception e) {
            e.printStackTrace();
        }    // try~catch end
        return NBLL;
    }

    public ArrayList<Double> NowBusLocationLong(String citycode, String routeID) {
        ArrayList<Double> NBLLong = new ArrayList<Double>();

        try {
            while (true) {
                // parsing할 url 지정(API 키 포함해서)
                String url = "http://openapi.tago.go.kr/openapi/service/BusLcInfoInqireService/getRouteAcctoBusLcList?serviceKey=S%2BUtXhAIhzDAPraTZURRpd63mngQxRxCFl4tqOU09y4CQmVwgya4PDI42xeoZODg2r8KzGb%2B5V8Fk0bfHo2Upg%3D%3D&cityCode="+citycode+"&routeId="+routeID;

                DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
                Document doc = dBuilder.parse(url);

                // root tag
                doc.getDocumentElement().normalize();

                // 파싱할 tag
                NodeList nList = doc.getElementsByTagName("item");
                //System.out.println("파싱할 리스트 수 : "+ nList.getLength());

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;
                        //System.out.println(eElement.getTextContent());
                        NBLLong.add(Double.valueOf(getTagValue("gpslong", eElement)));//
                    }    // for end
                }    // if end
                if (page <= 2) {
                    break;
                }
            }    // while end

        } catch (Exception e) {
            e.printStackTrace();
        }    // try~catch end
        return NBLLong;
    }


}
