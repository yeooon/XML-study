package com.test;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLDOM
{
	public static String getText(Element parent, String tagName)
	{
		String result = "";
		
		// 대상 태그(tagName) 객체의 첫 번째 자식 노드 얻어오기 
		Node node = parent.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;

		// 특정 엘리먼트의 자식 노드(Text Node)의 값(nodeValue)을 얻어올 수 있도록 처리
		result = element.getChildNodes().item(0).getNodeValue();
		
		// 결과값 반환
		return result;
	}
}
