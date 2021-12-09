package com.example.busstopapp.API;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class NowCloseStop {
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    public ArrayList<Double> closeStopLA(double la, double lo) {
        int page = 1;	// 페이지 초기값
        ArrayList<Double> CstopLA= new ArrayList<Double>();

        try{
            while(true){
                // parsing할 url 지정(API 키 포함해서)
                String url = "http://openapi.tago.go.kr/openapi/service/BusSttnInfoInqireService/getCrdntPrxmtSttnList?serviceKey=S%2BUtXhAIhzDAPraTZURRpd63mngQxRxCFl4tqOU09y4CQmVwgya4PDI42xeoZODg2r8KzGb%2B5V8Fk0bfHo2Upg%3D%3D&gpsLati="+la+"&gpsLong="+lo;

                DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
                Document doc = dBuilder.parse(url);

                // root tag
                doc.getDocumentElement().normalize();

                // 파싱할 tag
                NodeList nList = doc.getElementsByTagName("item");
                //System.out.println("파싱할 리스트 수 : "+ nList.getLength());

                for(int temp = 0; temp < 5; temp++){
                    Node nNode = nList.item(temp);
                    if(nNode.getNodeType() == Node.ELEMENT_NODE){

                        Element eElement = (Element) nNode;
                        //System.out.println(eElement.getTextContent());
                        CstopLA.add(Double.valueOf(getTagValue("gpslati", eElement)));
                    }	// for end
                }	// if end
                if(page <= 2){
                    break;
                }
            }	// while end

        } catch (Exception e){
            e.printStackTrace();
        }	// try~catch end
        return CstopLA;
    }

    public ArrayList<Double> closeStopLO(double la, double lo) {
        int page = 1;	// 페이지 초기값
        ArrayList<Double> CstopLO= new ArrayList<Double>();

        try{
            while(true){
                // parsing할 url 지정(API 키 포함해서)
                String url = "http://openapi.tago.go.kr/openapi/service/BusSttnInfoInqireService/getCrdntPrxmtSttnList?serviceKey=S%2BUtXhAIhzDAPraTZURRpd63mngQxRxCFl4tqOU09y4CQmVwgya4PDI42xeoZODg2r8KzGb%2B5V8Fk0bfHo2Upg%3D%3D&gpsLati="+la+"&gpsLong="+lo;

                DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
                Document doc = dBuilder.parse(url);

                // root tag
                doc.getDocumentElement().normalize();

                // 파싱할 tag
                NodeList nList = doc.getElementsByTagName("item");
                //System.out.println("파싱할 리스트 수 : "+ nList.getLength());

                for(int temp = 0; temp < 5; temp++){
                    Node nNode = nList.item(temp);
                    if(nNode.getNodeType() == Node.ELEMENT_NODE){

                        Element eElement = (Element) nNode;
                        //System.out.println(eElement.getTextContent());
                        CstopLO.add(Double.valueOf(getTagValue("gpslong", eElement)));
                    }	// for end
                }	// if end
                if(page <= 2){
                    break;
                }
            }	// while end

        } catch (Exception e){
            e.printStackTrace();
        }	// try~catch end
        return CstopLO;
    }

    public ArrayList<String> closeStopNM(double la, double lo) {
        int page = 1;	// 페이지 초기값
        ArrayList<String> CstopNM= new ArrayList<String>();

        try{
            while(true){
                // parsing할 url 지정(API 키 포함해서)
                String url = "http://openapi.tago.go.kr/openapi/service/BusSttnInfoInqireService/getCrdntPrxmtSttnList?serviceKey=S%2BUtXhAIhzDAPraTZURRpd63mngQxRxCFl4tqOU09y4CQmVwgya4PDI42xeoZODg2r8KzGb%2B5V8Fk0bfHo2Upg%3D%3D&gpsLati="+la+"&gpsLong="+lo;

                DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
                Document doc = dBuilder.parse(url);

                // root tag
                doc.getDocumentElement().normalize();

                // 파싱할 tag
                NodeList nList = doc.getElementsByTagName("item");
                //System.out.println("파싱할 리스트 수 : "+ nList.getLength());

                for(int temp = 0; temp < 5; temp++){
                    Node nNode = nList.item(temp);
                    if(nNode.getNodeType() == Node.ELEMENT_NODE){

                        Element eElement = (Element) nNode;
                        //System.out.println(eElement.getTextContent());
                        CstopNM.add(getTagValue("nodenm", eElement));
                    }	// for end
                }	// if end
                if(page <= 2){
                    break;
                }
            }	// while end

        } catch (Exception e){
            e.printStackTrace();
        }	// try~catch end
        return CstopNM;
    }
}
