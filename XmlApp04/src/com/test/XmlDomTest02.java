package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest02
{
	public static void main(String[] args)
	{
		// 이연주 010-1111-1111
		// 홍길동 010-1111-1111 이름 출력
		
		// 1. XML 파일을 메모리에 로드 → XML DOM 형성
		// 2. 루트 엘리먼트 접근
		// 3. 특정 하위 엘리먼트 접근 → 위치, 이름 등을 기준으로 접근
		// 4. 텍스트 노드(속성 노드) 접근 → 데이터 획득
		// 5. 결과 출력
		
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			String url = "memberList.xml";
			xmlObj = builder.parse(url);
			
			Element root = xmlObj.getDocumentElement();
			
			// 얻어낸 루트 엘리먼트를 활용하여 특정 하위 엘리먼트에 접근
			NodeList memberInfo = root.getElementsByTagName("memberInfo");
			//-- 이 때 getElementsByTagName 메소드는
			// 태그의 이름을 가지고 자식이나 자손 노드에 접근을 수행하는 메소드
			//						
			
			// ※ check~!!!
			// XML 의 모든 노드는 루트 엘리먼트 하위에 존재~!!!!
			
			// 이렇게 얻어낸 NodeList 객체에 포함되어 있는 Node 의 갯수ㅡㄹ
			// getLength() 메소드를 통해 확인할 수 있다.
			
			// 테스트
			// System.out.println(memberInfoNodeList.getLength());
			//--==>> 2
			
			for (int i = 0; i < memberInfo.getLength(); i++)
			{
				Node memberNode = memberInfo.item(i);		

				Element memberElement = (Element)memberNode;

				System.out.printf("%s %s"
						, getText(memberElement, "name")
						, getText(memberElement, "telephone"));
				//--==>>
				/*
				김상기 010-1213-4546
				김민성 010-5678-6789
				*/
						
				// 커리큘럼에 대한 처리 추가 -----------------------------------
				
				// memberInfoElement 로 부터 curriculumn ModeList 얻어오기
				NodeList curriculumnNodeList = memberElement.getElementsByTagName("curriculumn");
				
				// check~!!!
				/*
				if (curriculumnNodeList.getLength() > 0)
				{
					Node curriculumnNode = curriculumnNodeList.item(0);
					Element curriculuElement = (Element)curriculumnNode;
					
					// 방법 1
					NodeList subNodeList = curriculuElement.getElementsByTagName("sub");
					for (int m = 0; m < subNodeList.getLength(); m++)
					{
						Node subNode = subNodeList.item(m);
						Element subElement = (Element)subNode;
						System.out.printf("%s", subElement.getTextContent());
					}
					System.out.println();
				}
				*/
				
				
				if (curriculumnNodeList.getLength() > 0)
				{
					Node curriculumnNode = curriculumnNodeList.item(0);
					Element curriculuElement = (Element)curriculumnNode;
					
					// 방법 2.
					/*
					----------------------------------------------------------------
					Node Type				Name Constant 
					----------------------------------------------------------------
						1					ELEMENT_NODE
						2					ATTRIBUTE_NODE
						3					TEXT_NODE
						4					CDATA_SELECTION_NODE
						5					ENTITY_REFERENCE_NODE
						6					ENTITY_NODE
						7					PROCESSING_INSTRUCTION_NODE
						8					COMMENT_NODE
						9					DOCUMENT_NODE
						10					DOCUMENT_TYPE_NODE
						11					DOCUMENT_FRAGMENT_NODE
						12					NOTATION_NODE
					----------------------------------------------------------------
					*/
					// ----------------------------------- 커리큘럼에 대한 처리 추가
					
					NodeList subNodeList = curriculuElement.getChildNodes();
					for (int m = 0; m < subNodeList.getLength(); m++)
					{
						Node subNode = subNodeList.item(m);
						if (subNode.getNodeType() == 1)
						{
							Element subElement = (Element)subNode;
							System.out.printf("%s", subElement.getTextContent());
						}
					}
					System.out.println();
				}
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
