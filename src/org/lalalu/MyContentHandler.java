package org.lalalu;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyContentHandler extends DefaultHandler {
	private static final int BUTTON = 1;
	private static final int TEXT_VIEW = 2;
	private static final int TOGGLE_BUTTON = 3;
	private static final int CHECK_BOX = 4;
	private static final int SPINNER = 5;
	private static final int PROGRESS_BAR = 6;
	private static final int EDIT_TEXT = 7;
	private static final int LIST_WIEW = 8;
	private static final int GRID_VIEW = 9;
	private static final int WEB_VIEW = 10;
	private static final int IMAGE_VIEW = 11;
	private static final int IMAGE_BUTTON = 12;
	private static final int GALLERY = 13;
	private static final int VIDEO_VIEW = 14;
	private static final int TIME_PICKER = 15;
	private static final int DATE_PICKER = 16;
	private static final int RADIO_BUTTON = 17;
	
	
	private static final int LINEARLAYOUT = 18;
	private static final int RELATIVELAYOUT = 19;
	
	private List<WidgetInfo> list;

	// 开始解析XML文档，在这里初始化变量
	@Override
	public void startDocument() throws SAXException {
		list = new ArrayList<WidgetInfo>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		int tag = 0;
		if (qName.equals("Button")) {
			tag = BUTTON;
		} else if (qName.equals("TextView")) {
			tag = TEXT_VIEW;
		} else if (qName.equals("ToggleButton")) {
			tag = TOGGLE_BUTTON;
		} else if (qName.equals("CheckBox")) {
			tag = CHECK_BOX;
		} else if (qName.equals("Spinner")) {
			tag = SPINNER;
		} else if (qName.equals("ProgressBar")) {
			tag = PROGRESS_BAR;
		} else if (qName.equals("EditText")) {
			tag = EDIT_TEXT;
		} else if (qName.equals("ListView")) {
			tag = LIST_WIEW;
		} else if (qName.equals("GridView")) {
			tag = GRID_VIEW;
		} else if (qName.equals("WebView")) {
			tag = WEB_VIEW;
		} else if (qName.equals("ImageView")) {
			tag = IMAGE_VIEW;
		} else if (qName.equals("ImageButton")) {
			tag = IMAGE_BUTTON;
		} else if (qName.equals("Gallery")) {
			tag = GALLERY;
		} else if (qName.equals("VideoView")) {
			tag = VIDEO_VIEW;
		} else if (qName.equals("TimePicker")) {
			tag = TIME_PICKER;
		} else if (qName.equals("DatePicker")) {
			tag = DATE_PICKER;
		} else if (qName.equals("RadioButton")) {
			tag = RADIO_BUTTON;
		} else if (qName.equals("LinearLayout")) {
			tag = LINEARLAYOUT;
		} else if (qName.equals("RelativeLayout")) {
			tag = RELATIVELAYOUT;
		}
		
		if (tag != 0) {
			findWidgetId(tag, attributes);
		}
	}

	private void findWidgetId(int tag, Attributes attributes) {
		WidgetInfo widgetInfo = new WidgetInfo();
		setWidgetInfoType(tag, widgetInfo);
		for (int i = 0, len = attributes.getLength(); i < len; i++) {
			String str = attributes.getValue(i).toString();
			if (attributes.getLocalName(i).equals("android:id")
					&& (attributes.getValue(i).toString().startsWith("@id")||attributes.getValue(i).toString().startsWith("@+id"))) {
				widgetInfo.setIdName(str.substring(str.indexOf('/') + 1,
						str.length()));
				list.add(widgetInfo);
			}
		}
	}

	public List<WidgetInfo> getList() {
		return list;
	}

	private void setWidgetInfoType(int tag, WidgetInfo widgetInfo) {
		switch (tag) {
		case BUTTON:
			widgetInfo.setType("Button");
			break;
		case TEXT_VIEW:
			widgetInfo.setType("TextView");
			break;
		case TOGGLE_BUTTON:
			widgetInfo.setType("ToggleButton");
			break;
		case CHECK_BOX:
			widgetInfo.setType("CheckBox");
			break;
		case SPINNER:
			widgetInfo.setType("Spinner");
			break;
		case PROGRESS_BAR:
			widgetInfo.setType("ProgressBar");
			break;
		case EDIT_TEXT:
			widgetInfo.setType("EditText");
			break;
		case LIST_WIEW:
			widgetInfo.setType("ListView");
			break;
		case GRID_VIEW:
			widgetInfo.setType("GridView");
			break;
		case WEB_VIEW:
			widgetInfo.setType("WebView");
			break;
		case IMAGE_VIEW:
			widgetInfo.setType("ImageView");
			break;
		case IMAGE_BUTTON:
			widgetInfo.setType("ImageButton");
			break;
		case GALLERY:
			widgetInfo.setType("Gallery");
			break;
		case VIDEO_VIEW:
			widgetInfo.setType("VideoView");
			break;
		case TIME_PICKER:
			widgetInfo.setType("TimePicker");
			break;
		case DATE_PICKER:
			widgetInfo.setType("DatePicker");
			break;
		case RADIO_BUTTON:
			widgetInfo.setType("RadioButton");
			break;
		case LINEARLAYOUT:
			widgetInfo.setType("LinearLayout");
			break;
		case RELATIVELAYOUT:
			widgetInfo.setType("RelativeLayout");
			break;
		}
	}
}
