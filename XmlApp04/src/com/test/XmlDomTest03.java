/*===================================================
	XmlDomTest01.java
	- 콘솔 기반 자바 프로그램
	- XML DOM 활용 → 로컬 (local) XML 읽어내기
	  (memberList.xml)
===================================================*/

// breakfast_menu.xml 파일을 대상으로
/*
	■[Belgian Waffles] $5.95 650 칼로리
	- Two of our famous Belgian Waffles with plenty of real maple syrup
*/
package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest03
{
	public static void main(String[] args)
	{	
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			String url = "VEHICLES.xml";
			Document xmlObj = builder.parse(url);
			
			Element rootElement = xmlObj.getDocumentElement();
			
			NodeList food = rootElement.getElementsByTagName("food");

			
			for (int i = 0; i < food.getLength(); i++)
			{
				Node foodNode = food.item(i);		

				Element foodElement = (Element)foodNode;

				System.out.printf("■[%s] %s %s 칼로리 \n - %s \n --------------------------------------- \n"
						, getText(foodElement, "name")
						, getText(foodElement, "price")
						, getText(foodElement, "calories")
						, getText(foodElement, "description"));
						
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}// end main()		

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
