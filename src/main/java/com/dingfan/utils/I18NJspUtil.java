package com.dingfan.utils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.lang.StringUtils;

/**
 * The <code>I18NUtil</code>	
 * 
 * @author Johnny
 * @version 1.0, Created at 2008/11/10
 */
public class I18NJspUtil {
	//输出路径，保存生成好的i18n文件，该路径下需要一个config.properties，
	//里面写入你需要国际化的jsp目录路径
	public static final String configPath = "D:\\i18n\\";
	//项目的国际化资源文件的路径
	public static final String resourcesFile = "E:\\workspace\\MAYW06\\code\\maywcpfr\\web\\WEB-INF\\classes\\ApplicationResources_zh_TW.properties";
	
	@SuppressWarnings("unchecked")
	public void initI18NJsp(String key) {
		try {
			LineIterator configFleIterator = FileUtils.lineIterator(new File(I18NJspUtil.configPath + "config.properties"), "UTF-8");
			StringBuffer i18nBuffer = new StringBuffer();
			while (configFleIterator.hasNext()) {
				String configPath = configFleIterator.nextLine();
				if (StringUtils.isEmpty(configPath)) {
					continue;
				}
				Collection<File> fileSet = FileUtils.listFiles(new File(configPath), FileFilterUtils.suffixFileFilter(".jsp"), null);
				
				for (File sourceJspFile : fileSet) {
					createI18NSystem(key, i18nBuffer, sourceJspFile, getTargetJspFile(sourceJspFile));
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private File getTargetJspFile(File sourceJspFile) {
		String sourceJspPath = sourceJspFile.getPath();
		String targetJspPath = I18NJspUtil.configPath + sourceJspPath.substring(2, sourceJspPath.length());
		return new File(targetJspPath);
	}
	
	private void createI18NSystem(String key, StringBuffer i18nBuffer, File sourceJspFile, File targetJspFile) {
		try {
			StringBuffer jspBuffer = new StringBuffer();
			
			LineIterator lineIterator = FileUtils.lineIterator(sourceJspFile, "UTF-8");
			long lineNo = 1;
			String lineStr = null;
			
			i18nBuffer.append("------" + sourceJspFile.getName() + "------" + "\r\n");
			String jspFileName = sourceJspFile.getName().substring(0, sourceJspFile.getName().length() - 4);
			
			while (lineIterator.hasNext()) {
				lineStr = lineIterator.nextLine();
				
				if (StringUtils.trim(lineStr).startsWith("//")) {
					jspBuffer.append(lineStr + "\r\n");
					continue;
				}
				
				jspBuffer.append(processLineString(key, jspFileName, lineStr, i18nBuffer, lineNo) + "\r\n");
				lineNo++;
			}
			
			FileUtils.writeStringToFile(targetJspFile, jspBuffer.toString(), "UTF-8");
			FileUtils.writeStringToFile(new File(configPath + "i18n.properties"), i18nBuffer.toString(), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String processLineString(String key, String jspFileName, String lineStr, StringBuffer i18nBuffer, Long lineNo) {
		List<List<Integer>> lineList = new ArrayList<List<Integer>>();
		List<Integer> locaterList = null;
		for (int i = 0; i < lineStr.length(); i++) {
			if (lineStr.substring(i, i + 1).matches("[\\u4e00-\\u9fa5]+")) {
				locaterList = new ArrayList<Integer>();
				for (int j = i; j < lineStr.length(); j++) {
					if (!lineStr.substring(j, j + 1).matches("[\\u4e00-\\u9fa5]+")) {
						break;
					} else {
						locaterList.add(j);
						i = j;
					}
				}
				lineList.add(locaterList);
			} else {
				//System.out.println(lineStr);
			}
			
		}
		
		List<Integer> tmpList1 = null;
		List<Integer> tmpList2 = null;
		List<Integer> tmpList3 = null;
		if (lineList.size() == 1) {
			tmpList1 = lineList.get(0);
			String chineseStr = lineStr.substring(tmpList1.get(0), tmpList1.get(tmpList1.size() - 1) + 1);
			
			String unicodeStr = chinaToUnicode(chineseStr);
			String keyFromFile = getKeyFromI18NFile(unicodeStr);
			if (keyFromFile == null) {
				key += "." + jspFileName + "." + lineNo;
				i18nBuffer.append(key + "=" + unicodeStr + "\r\n");
			} else {
				key = keyFromFile;
			}
			String jspStr = lineStr.substring(0, tmpList1.get(0)) + "<fmt:message key=\"" + key + "\" />" + lineStr.substring(tmpList1.get(tmpList1.size() - 1) + 1, lineStr.length());
			return jspStr;
		} else if (lineList.size() == 2) {
			tmpList1 = lineList.get(0);
			tmpList2 = lineList.get(1);
			String chineseStr1 = lineStr.substring(tmpList1.get(0), tmpList1.get(tmpList1.size() - 1) + 1);
			String chineseStr2 = lineStr.substring(tmpList2.get(0), tmpList2.get(tmpList2.size() - 1) + 1);
			
			String unicodeStr1 = chinaToUnicode(chineseStr1);
			String unicodeStr2 = chinaToUnicode(chineseStr2);
			
			String key1 = null;
			String key2 = null;
			String keyFromFile1 = getKeyFromI18NFile(unicodeStr1);
			String keyFromFile2 = getKeyFromI18NFile(unicodeStr2);
			if (keyFromFile1 == null) {
				i18nBuffer.append(key + "." + jspFileName + ".first." + lineNo + "=" + unicodeStr1 + "\r\n");
				key1 = key + "." + jspFileName + ".first." + lineNo;
			} else {
				key1 = keyFromFile1;
			}
			if (keyFromFile2 == null) {
				i18nBuffer.append(key + "." + jspFileName + ".second." + lineNo + "=" + unicodeStr2 + "\r\n");
				key2 = key + "." + jspFileName + ".second." + lineNo;
			} else {
				key2 = keyFromFile2;
			}
			String jspStr = lineStr.substring(0, tmpList1.get(0))
					+ "<fmt:message key=\"" + key1 + "\" />"
					+ lineStr.substring(tmpList1.get(tmpList1.size() - 1) + 1, tmpList2.get(0))
					+ "<fmt:message key=\"" + key2 + "\" />"
					+ lineStr.substring(tmpList2.get(tmpList2.size() - 1) + 1, lineStr.length());
			return jspStr;
		} else if (lineList.size() == 3) {
			tmpList1 = lineList.get(0);
			tmpList2 = lineList.get(1);
			tmpList3 = lineList.get(2);
			String chineseStr1 = lineStr.substring(tmpList1.get(0), tmpList1.get(tmpList1.size() - 1) + 1);
			String chineseStr2 = lineStr.substring(tmpList2.get(0), tmpList2.get(tmpList2.size() - 1) + 1);
			String chineseStr3 = lineStr.substring(tmpList3.get(0), tmpList3.get(tmpList3.size() - 1) + 1);
			
			String unicodeStr1 = chinaToUnicode(chineseStr1);
			String unicodeStr2 = chinaToUnicode(chineseStr2);
			String unicodeStr3 = chinaToUnicode(chineseStr3);
			
			String key1 = null;
			String key2 = null;
			String key3 = null;
			String keyFromFile1 = getKeyFromI18NFile(unicodeStr1);
			String keyFromFile2 = getKeyFromI18NFile(unicodeStr2);
			String keyFromFile3 = getKeyFromI18NFile(unicodeStr3);
			if (keyFromFile1 == null) {
				i18nBuffer.append(key + "." + jspFileName + ".first." + lineNo + "=" + unicodeStr1 + "\r\n");
				key1 = key + "." + jspFileName + ".first." + lineNo;
			} else {
				key1 = keyFromFile1;
			}
			if (keyFromFile2 == null) {
				i18nBuffer.append(key + "." + jspFileName + ".second." + lineNo + "=" + unicodeStr2 + "\r\n");
				key2 = key + "." + jspFileName + ".second." + lineNo;
			} else {
				key2 = keyFromFile2;
			}
			if (keyFromFile3 == null) {
				i18nBuffer.append(key + "." + jspFileName + ".third." + lineNo + "=" + unicodeStr3 + "\r\n");
				key3 = key + "." + jspFileName + ".third." + lineNo;
			} else {
				key3 = keyFromFile3;
			}
			
			String jspStr = lineStr.substring(0, tmpList1.get(0))
					+ "<fmt:message key=\"" + key1 + "\" />"
					+ lineStr.substring(tmpList1.get(tmpList1.size() - 1) + 1, tmpList2.get(0))
					+ "<fmt:message key=\"" + key2 + "\" />"
					+ lineStr.substring(tmpList2.get(tmpList2.size() - 1) + 1, tmpList3.get(0)) + "<fmt:message key=\"" + key3 + "\" />"
					+ lineStr.substring(tmpList3.get(tmpList3.size() - 1) + 1, lineStr.length());
			return jspStr;
		}
		return lineStr;
	}
	
	private String getKeyFromI18NFile(String unicodeStr) {
		try {
			LineIterator lineIterator = FileUtils.lineIterator(new File(I18NJspUtil.resourcesFile), "UTF-8");
			String lineStr = null;
			String[] lineArray = null;
			while (lineIterator.hasNext()) {
				lineStr = lineIterator.nextLine();
				lineArray = lineStr.split("=");
				if (lineArray.length != 2)
					continue;
				if (lineArray != null && lineArray.length > 1) {
					if (lineArray[1].trim().equals(unicodeStr))
						return lineArray[0].trim();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String chinaToUnicode(String str) {
		String result = new String();
		for (int i = 0; i < str.length(); i++) {
			int chr1 = (char) str.charAt(i);
			result += "\\u" + Integer.toHexString(chr1);
		}
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		I18NJspUtil jsp = new I18NJspUtil();
		jsp.initI18NJsp("member");
		System.out.println("ok");
	}
	
}
