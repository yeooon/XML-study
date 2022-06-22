/*===================================================
	XmlDomTest07.java 
	- 콘솔 기반 자바 프로그램
	- XML DOM 활용 → 원격 (remote) XML 읽어내기
	(http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=109)
===================================================*/

/*
title > JTBC News
link > https://fs.jtbc.joins.com//RSS/newsflash.xml
description> 속보 RSS
copyright > Copyright(C) JTBC All rights reserved.

주요 기사 ------------------------------------------
title : [날씨] 전국 흐리다가 오후부터 차차 갬... 서울 낮 기온 27도
description : 목요일인 오늘...
link : 
pubDate : 

구성할 수 있을까...
*/



package com.test;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;



public class XmlDomTest07
{
	public static void main(String[] args)
	{	
		try
		{	
			// 1. XML 파일 메모리에 로드
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;	
			
			// Local XML 파일인 경우 ...
			// String ulr = "rss.xml";
			// xmlObj = builder.parse(url);
			
			// Remot XMl 파일인 경우
			
			String str = "https://fs.jtbc.joins.com/RSS/newsflash.xml";
			URL url = new URL(str);
			InputSource is = new InputSource(url.openStream());
			xmlObj = builder.parse(is);
						
			// 루트 엘리먼트 접근
			Element root = xmlObj.getDocumentElement(); // root 접근

			NodeList channelNodes = root.getElementsByTagName("channel");
			Node channelNode = channelNodes.item(0);
			Element channelElement = (Element)channelNode;

			System.out.println("--------------------------------------------------");
			System.out.printf("title> %s %n", getText(channelElement, "title"));
			System.out.printf("link> %s %n", getText(channelElement, "link"));
			System.out.printf("description> %s %n%n",getText(channelElement, "description"));
			System.out.printf("copyright > %s\n",getText(channelElement, "copyright"));
			System.out.printf("category > %s\n",getText(channelElement, "category"));

			System.out.println("주요 기사 ------------------------");
			NodeList itemNodes = channelElement.getElementsByTagName("item");
			for (int i=0; i<itemNodes.getLength(); i++) 
			{
				Node itemNode = itemNodes.item(i);
				Element itemElement = (Element)itemNode;
				
				System.out.printf("title> %s\n", getText(itemElement, "title"));
				System.out.printf("link> %s\n", getText(itemElement, "link"));
				System.out.printf("description> %s \n", getText(itemElement, "description"));
				System.out.printf("pubDate> %s\n", getText(itemElement, "pubDate"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	private static String getText(Element parent, String tagName) 
	{
		String result = "";		// 특정 태그 이름을 가진 객체의 첫 번째 자식 노드를 얻는 과정		
		Node node = parent.getElementsByTagName(tagName).item(0);		
		Element element = (Element) node;		
		// 특정 엘리먼트의 자식 노드(Text Node)의 값(nodeValue)를 얻는 과정		
		result = element.getChildNodes().item(0).getNodeValue();		
		return result;	
	}
}

