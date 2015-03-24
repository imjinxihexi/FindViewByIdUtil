package org.lalalu;

import java.util.ArrayList;
import java.util.List;

public class FastCreate {
	private List<WidgetInfo> list;
	private List<WidgetInfo> widgetList;
	private String contentInfo;
	private int level;

	public FastCreate(List<WidgetInfo> list) {
		this.list = list;
		widgetList = new ArrayList<WidgetInfo>();
	}

	public void getContent() {
		for (int i = 0, len = list.size(); i < len; i++) {
			if (list.get(i).getType().endsWith("Button")) {
				handWidgets("Button", i);
			} else if (list.get(i).getType().equals("EditText")) {
				handWidgets("EditText", i);
			} else if (list.get(i).getType().equals("TextView")) {
				handWidgets("TextView", i);
			} else if (list.get(i).getType().equals("ImageView")) {
				handWidgets("ImageView", i);
			} else if (list.get(i).getType().equals("CheckBox")) {
				handWidgets("CheckBox", i);
			} else if (list.get(i).getType().equals("Spinner")) {
				handWidgets("Spinner", i);
			} else if (list.get(i).getType().equals("ProgressBar")) {
				handWidgets("ProgressBar", i);
			} else if (list.get(i).getType().equals("EditText")) {
				handWidgets("EditText", i);
			} else if (list.get(i).getType().equals("ListView")) {
				handWidgets("ListView", i);
			} else if (list.get(i).getType().equals("GridView")) {
				handWidgets("GridView", i);
			} else if (list.get(i).getType().equals("WebView")) {
				handWidgets("WebView", i);
			} else if (list.get(i).getType().equals("ImageView")) {
				handWidgets("ImageView", i);
			} else if (list.get(i).getType().equals("Gallery")) {
				handWidgets("Gallery", i);
			} else if (list.get(i).getType().equals("VideoView")) {
				handWidgets("VideoView", i);
			} else if (list.get(i).getType().equals("TimePicker")) {
				handWidgets("TimePicker", i);
			} else if (list.get(i).getType().equals("DatePicker")) {
				handWidgets("DatePicker", i);
			}else if (list.get(i).getType().equals("RadioButton")) {
				handWidgets("RadioButton", i);
			}else if (list.get(i).getType().equals("LinearLayout")) {
				handWidgets("LinearLayout", i);
			}else if (list.get(i).getType().equals("RelativeLayout")) {
				handWidgets("RelativeLayout", i);
			}
			
			
		}

		// 排序，将level按从低向高排序
		for (int i = 0, len = widgetList.size(); i < len; i++) {
			for (int j = i + 1, lenj = widgetList.size(); j < lenj; j++) {
				if (widgetList.get(i).getLevel() > widgetList.get(j).getLevel()) {
					WidgetInfo temp = new WidgetInfo();
					temp = widgetList.get(j);
					widgetList.add(i, temp);

					temp = widgetList.get(i + 1);
					widgetList.remove(i + 1);

					widgetList.add(j, temp);
					widgetList.remove(j + 1);
				}
			}
		}

		StringBuffer sb = new StringBuffer();
		boolean isRun = false;

		int preLevel = 1;
		int nextLevel = 1;
		for (int i = 0, len = widgetList.size(); i < len; i++) {
			nextLevel = widgetList.get(i).getLevel();
			if (preLevel != nextLevel) {
				sb.append("\n\n");
				preLevel = nextLevel;
			}

			if (widgetList.get(i).getLevel() != 4) {
				sb.append(widgetList.get(i).getContent());
				continue;
			}
			if (widgetList.get(i).getLevel() == 4) {
				if (!isRun) {
					sb.append(" OnClickListener mOnClickListener = new OnClickListener() {\n   public void onClick(View v) { \n        switch (v.getId()) {");
					isRun = true;
				}

				sb.append(widgetList.get(i).getContent());
			}
			if (i == len - 1) {
				sb.append("\n    }\n  }\n};");
			}
		}

		System.out.println(sb.toString());
	}

	private void handWidgets(String type, int i) {
		String content = list.get(i).getIdName();
		level = 1;
		contentInfo = getContentInfo(content, type, level);
		createWidgetInfo(contentInfo, type, level, widgetList);

		level = 2;
		contentInfo = getContentInfo(content, type, level);
		createWidgetInfo(contentInfo, type, level, widgetList);

		if (type.equals("Button")
			||type.equals("RelativeLayout")	
			||type.equals("LinearLayout")	
			) {
			contentInfo = " " + content
					+ ".setOnClickListener(mOnClickListener);\n";
			level = 3;
			createWidgetInfo(contentInfo, type, level, widgetList);

			contentInfo = "\n        case R.id." + content
					+ ":\n        break;";
			level = 4;
			createWidgetInfo(contentInfo, type, level, widgetList);
		}
		

	}

	private String getContentInfo(String content, String type, int level) {
		String str = null;
		switch (level) {
		case 1:
			str = " private " + type + " " + content + ";\n";
			break;
		case 2:
			str = " " + content + " = (" + type + ")findViewById(R.id."
					+ content + ");\n";
			break;
		default:
			break;
		}
		return str;
	}

	private void createWidgetInfo(String content, String type, int level,
			List<WidgetInfo> widgetList) {
		WidgetInfo WidgetInfo = new WidgetInfo();
		WidgetInfo.setContent(content);
		WidgetInfo.setType(type);
		WidgetInfo.setLevel(level);
		widgetList.add(WidgetInfo);
	}
}
