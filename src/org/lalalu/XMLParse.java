package org.lalalu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.lalalu.util.FileCharsetConverter;
import org.xml.sax.SAXException;

public class XMLParse {
	// ����XML�ĵ���·����������Ҫ�Լ��޸�
	private static final String filePath = Config.FilePath; 

	public static void main(String[] args) {
		// �õ�SAX�������Ĺ���ʵ��
		SAXParserFactory saxfa = SAXParserFactory.newInstance();
		MyContentHandler myContentHandler = new MyContentHandler();
		try {
			// ��SAX����ʵ���л��SAX������
			SAXParser saxparser = saxfa.newSAXParser();
			// ���ĵ�ת������
			
			
			
//			try {
//				FileCharsetConverter.convert(new File(filePath), "utf-8", "gbk");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			InputStream it = new FileInputStream(filePath);
			InputStreamReader read = new InputStreamReader(new FileInputStream(filePath), "gb2312");
			
			 
			saxparser.parse(it, myContentHandler);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FastCreate fastCreate = new FastCreate(myContentHandler.getList());
		fastCreate.getContent();
	}
}
