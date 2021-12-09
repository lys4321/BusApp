package com.example.busstopapp.API;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class getRouteInfo {
    // tag값의 정보를 가져오는 메소드
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }


    public ArrayList<String> getBusNo(String ctc) {//
        int page = 1;	// 페이지 초기값
        ArrayList<String> busNo = new ArrayList<String>();

        try{
            while(true){
                // parsing할 url 지정(API 키 포함해서)
                String url = "http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteNoList?serviceKey=S%2BUtXhAIhzDAPraTZURRpd63mngQxRxCFl4tqOU09y4CQmVwgya4PDI42xeoZODg2r8KzGb%2B5V8Fk0bfHo2Upg%3D%3D&cityCode="+ctc+"&pageNo="+page;

                DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
                Document doc = dBuilder.parse(url);

                // root tag
                doc.getDocumentElement().normalize();

                // 파싱할 tag
                NodeList nList = doc.getElementsByTagName("item");
                //System.out.println("파싱할 리스트 수 : "+ nList.getLength());

                for(int temp = 0; temp < nList.getLength(); temp++){
                    Node nNode = nList.item(temp);
                    if(nNode.getNodeType() == Node.ELEMENT_NODE){

                        Element eElement = (Element) nNode;
                        //System.out.println(eElement.getTextContent());
                        busNo.add(getTagValue("routeno", eElement));
                    }	// for end
                }	// if end
                page += 1;
                if(page > 19){
                    break;
                }
            }	// while end
        } catch (Exception e){
            e.printStackTrace();
        }	// try~catch end
        return busNo;
    }	// main end

    public ArrayList<String> getBusCode(String ctc) {
        int page = 1;	// 페이지 초기값
        ArrayList<String> busCode = new ArrayList<String>();

        try{
            while(true){
                // parsing할 url 지정(API 키 포함해서)
                String url = "http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteNoList?serviceKey=S%2BUtXhAIhzDAPraTZURRpd63mngQxRxCFl4tqOU09y4CQmVwgya4PDI42xeoZODg2r8KzGb%2B5V8Fk0bfHo2Upg%3D%3D&cityCode="+ctc+"&pageNo="+page;

                DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
                Document doc = dBuilder.parse(url);

                // root tag
                doc.getDocumentElement().normalize();

                // 파싱할 tag
                NodeList nList = doc.getElementsByTagName("item");
                //System.out.println("파싱할 리스트 수 : "+ nList.getLength());

                for(int temp = 0; temp < nList.getLength(); temp++){
                    Node nNode = nList.item(temp);
                    if(nNode.getNodeType() == Node.ELEMENT_NODE){

                        Element eElement = (Element) nNode;
                        //System.out.println(eElement.getTextContent());
                        busCode.add(getTagValue("routeid", eElement));
                    }	// for end
                }	// if end
                page += 1;
                if(page > 19){
                    break;
                }
            }	// while end
        } catch (Exception e){
            e.printStackTrace();
        }	// try~catch end
        return busCode;
    }	// main end

    public static void main(String argv[])
    {
        getRouteInfo b = new getRouteInfo();
        ArrayList<String> a = b.getBusNo("33010");
        System.out.println(a);
        System.out.println(a.size());
    }
}
