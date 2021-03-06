/*===================================================
	XmlDomTest01.java
	- 콘솔 기반 자바 프로그램
	- XML DOM 활용 → 로컬 (local) XML 읽어내기
	  (memberList.xml)
===================================================*/

package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest04
{
	public static void main(String[] args)
	{
		/*
		----------------------------------------------------------------
		No		MAKE		MODEL		YEAR		STYLE		PRICE
		----------------------------------------------------------------
		1		Dodge		Durango		1998		Sport Utility	18000
		Options ----------------------------------------------------------------
		
			Power_Lock : Yes
			Power_Windoe : Yes
			Stereo : Radio/Cassette/Cd
			Airr_Conditioning : Yes
			Automatic : Yes
			Four_Wheel_Drive : Full/Partial 
			Note : Very clean
		-------------------------------------------------------------------
		2		Honda		Civic		1997		Sedan			800
		Options ----------------------------------------------------------------
		
			Power_Lock : Yes
			Power_Windoe : Yes
			Stereo : Radio/Cassette/Cd
			Airr_Conditioning : Yes
			Automatic : Yes
			Four_Wheel_Drive : Full/Partial 
			Note : Very clean		
		
		
		*/
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			String url = "VEHICLES.xml";
			Document xmlObj = builder.parse(url);
			
			Element rootElement = xmlObj.getDocumentElement();
			
			NodeList vehicleNodeList = rootElement.getElementsByTagName("VEHICLE");

	
			System.out.println("---------------------------------------------------------------- \n");
			System.out.println("		No		MAKE		MODEL		YEAR		STYLE		PRICE  \n");
			System.out.println("---------------------------------------------------------------- \n");
			
			
			for (int i = 0; i < vehicleNodeList.getLength(); i++)
			{
				Node vehicleNode = vehicleNodeList.item(i);		

				Element vehicleElement = (Element)vehicleNode;

				System.out.printf("%2s %7s %10s %5s %13s %7s \n --------------------------------------- \n"
						, vehicleElement.getElementsByTagName("INVENTORY_NUMBER").item(0).getTextContent()
						, vehicleElement.getElementsByTagName("MAKE").item(0).getTextContent()
						, vehicleElement.getElementsByTagName("MODEL").item(0).getTextContent()
						, vehicleElement.getElementsByTagName("YEAR").item(0).getTextContent()
						, vehicleElement.getElementsByTagName("STYLE").item(0).getTextContent()
						, vehicleElement.getElementsByTagName("PRICE").item(0).getTextContent());
			
			// Option 추가
			System.out.println("---------------------------------------------------------------- \n");
			System.out.println("2		Honda		Civic		1997		Sedan			800\n");

			NodeList options = vehicleElement.getElementsByTagName("OPTIONS");
			Node option = options.item(0);		
			Element optionElement = (Element)option;
			
			NodeList childNodes = optionElement.getChildNodes();

			for (int k = 0; k < childNodes.getLength(); k++)
			{
				Node childNode = childNodes.item(k);		
				if (childNode.getNodeType() == 1)
				{
					System.out.printf("%2s %7s \n"
							, childNode.getNodeName()
							, childNode.getTextContent());
				}					
			}
			System.out.println("---------------------------------------------------------------- \n");

			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}		
	}
	private static String getText(Element parent, String tagName)
	{
		// 반환할 결과값
		String result = "";
		
		// 특정 태그 이름을 가진 객체의 첫 번째 자식 노드를 얻어온 다음
		Node node = parent.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;
		
		// 특정 엘리먼트의 자식 노드(Text Node)의 값(nodeValue)를 얻어올 수 있도록 처리한 다음
		result = element.getChildNodes().item(0).getNodeValue();
		
		// 결과값 반환
		return result;
	}	
	
	
}
