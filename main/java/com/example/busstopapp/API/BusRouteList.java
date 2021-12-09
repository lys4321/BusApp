package com.example.busstopapp.API;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class BusRouteList {
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    public ArrayList<Double> busStopLati(String ctc, String busCode) {
        int page = 1;	// 페이지 초기값
        ArrayList<Double> glati = new ArrayList<Double>();

        try{
            while(true){
                // parsing할 url 지정(API 키 포함해서)
                String url = "http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteAcctoThrghSttnList?serviceKey=S%2BUtXhAIhzDAPraTZURRpd63mngQxRxCFl4tqOU09y4CQmVwgya4PDI42xeoZODg2r8KzGb%2B5V8Fk0bfHo2Upg%3D%3D&numOfRows=128&pageNo=1&cityCode="+ctc+"&routeId="+busCode;

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
                        glati.add(Double.valueOf(getTagValue("gpslati", eElement)));
                    }	// for end
                }	// if end
                if(page <= 2){
                    break;
                }
            }	// while end

        } catch (Exception e){
            e.printStackTrace();
        }	// try~catch end
        return glati;
    }

    public ArrayList<Double> busStopLong(String ctc, String busCode) {
        int page = 1;	// 페이지 초기값
        ArrayList<Double> glong = new ArrayList<Double>();

        try{
            while(true){
                // parsing할 url 지정(API 키 포함해서)
                String url = "http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteAcctoThrghSttnList?serviceKey=S%2BUtXhAIhzDAPraTZURRpd63mngQxRxCFl4tqOU09y4CQmVwgya4PDI42xeoZODg2r8KzGb%2B5V8Fk0bfHo2Upg%3D%3D&numOfRows=128&pageNo=1&cityCode="+ctc+"&routeId="+busCode;

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
                        glong.add(Double.valueOf(getTagValue("gpslong", eElement)));
                    }	// for end
                }	// if end
                if(page <= 2){
                    break;
                }
            }	// while end

        } catch (Exception e){
            e.printStackTrace();
        }	// try~catch end
        return glong;
    }

    public ArrayList<String> stopCode(String ctc, String busCode) {
        int page = 1;	// 페이지 초기값
        ArrayList<String> stopCD = new ArrayList<String>();

        try{
            while(true){
                // parsing할 url 지정(API 키 포함해서)
                String url = "http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteAcctoThrghSttnList?serviceKey=S%2BUtXhAIhzDAPraTZURRpd63mngQxRxCFl4tqOU09y4CQmVwgya4PDI42xeoZODg2r8KzGb%2B5V8Fk0bfHo2Upg%3D%3D&numOfRows=128&pageNo=1&cityCode="+ctc+"&routeId="+busCode;

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
                        stopCD.add(getTagValue("nodeid", eElement));
                    }	// for end
                }	// if end
                if(page <= 2){
                    break;
                }
            }	// while end

        } catch (Exception e){
            e.printStackTrace();
        }	// try~catch end
        return stopCD;
    }

    public ArrayList<String> stopName(String ctc, String busCode) {
        int page = 1;	// 페이지 초기값
        ArrayList<String> stopNM = new ArrayList<String>();

        try{
            while(true){
                // parsing할 url 지정(API 키 포함해서)
                String url = "http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteAcctoThrghSttnList?serviceKey=S%2BUtXhAIhzDAPraTZURRpd63mngQxRxCFl4tqOU09y4CQmVwgya4PDI42xeoZODg2r8KzGb%2B5V8Fk0bfHo2Upg%3D%3D&numOfRows=128&pageNo=1&cityCode="+ctc+"&routeId="+busCode;

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
                        stopNM.add(getTagValue("nodenm", eElement));
                    }	// for end
                }	// if end
                if(page <= 2){
                    break;
                }
            }	// while end

        } catch (Exception e){
            e.printStackTrace();
        }	// try~catch end
        return stopNM;
    }

    public static void main(String argv[])
    {
        BusRouteList b = new BusRouteList();
        System.out.println(b.busStopLati("33010","CJB270002000"));
        System.out.println(b.busStopLong("33010","CJB270002000"));
        System.out.println(b.stopCode("33010","CJB270002000"));
        System.out.println(b.stopName("33010","CJB270002000"));
    }





}
