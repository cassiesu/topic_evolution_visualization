package com.irisflowers.relation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.irisflowers.domain.Keyword;
import com.irisflowers.util.XMLProcessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class RelationTestLH {
	
	//事实证明好像每年一种颜色比较现实好看，更容易看出转化关系（07-14年需要8种）
	private static Map<String, String> colormap = new HashMap<String, String>();
	private static DecimalFormat f1 = new DecimalFormat("00.0000");

	public static void findTopics(String path) {
		List<MTopic> mtopics = new ArrayList<MTopic>();

		String topics = ReadFile(path);// 获得json文件的内容
		JSONArray ja = JSONArray.fromObject(topics);
		//JSONObject jo = JSONObject.fromObject(topics);// 格式化成json对象
		
		for(int i=0; i<ja.size(); i++) {
			JSONObject object = (JSONObject) ja.get(i);
			MTopic mtopic = (MTopic)JSONObject.toBean(object, MTopic.class);
			mtopics.add(mtopic);
			if(!colormap.containsKey(mtopic.getColor())) {
				colormap.put(mtopic.getColor(), mtopic.getTextcolor());
			}
		}
		//System.out.println(mtopics.get(1).getColor());
		//System.out.println(mtopics.size());
		System.out.println(colormap);
		System.out.println(colormap.size());

	}


	// 读文件，返回字符串
	public static String ReadFile(String path){
		BufferedReader buffRed = null;
		String filepath = path;
		String tempStr;
		StringBuffer xmlStrBuff = new StringBuffer();
		try {
			buffRed = new BufferedReader(new FileReader(filepath));
			while ((tempStr = buffRed.readLine()) != null) {
				xmlStrBuff.append(tempStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffRed != null)
					buffRed.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return xmlStrBuff.toString();
	}

	// 把json格式的字符串写到文件
	public static void writeFile(String filePath, String str) throws IOException {
		FileWriter fw = new FileWriter(filePath);
		PrintWriter out = new PrintWriter(fw);
		out.write(str);
		out.println();
		fw.close();
		out.close();
	}

	public static void main(String[] args) {
		
//		String fileName = "src/topics.json";
//		findTopics(fileName);
//			
//		//随机取colormap的一种颜色
//		Object[] lightkeys = colormap.keySet().toArray();
//		String[] yearkeys = new String[8];
//		for(int i=0; i<8; i++) {
//			yearkeys[i] = (String) lightkeys[i*10];
//		}
	
		colormap.put("#b7c7f7", "#7787b7");	//浅蓝色
		colormap.put("#a28eee", "#624eae");	//蓝色
		colormap.put("#99cb8a", "#598b4a");	//绿色
		colormap.put("#cfeb8a", "#8fab4a");	//草色
		colormap.put("#e6de95", "#a69e55");	//黄色
		colormap.put("#d1b897", "#917857");	//棕色
		colormap.put("#ec8e97", "#ac4e57");	//红色
		colormap.put("#e481ee", "#a441ae");	//粉色
		
		String[] yearkeys = new String[8];
		yearkeys[0] = "#b7c7f7";
		yearkeys[1] = "#a28eee";
		yearkeys[2] = "#99cb8a";
		yearkeys[3] = "#cfeb8a";
		yearkeys[4] = "#e6de95";
		yearkeys[5] = "#d1b897";
		yearkeys[6] = "#ec8e97";
		yearkeys[7] = "#e481ee";
		
		
		String fileName = "src/RelationTest.xml";
		XMLProcessor xml = new XMLProcessor();
		Map<String, Integer> topic2id = new HashMap<String, Integer>();
		int mapsize = 0;
		
		List<MTopic> topics = new ArrayList<MTopic>();
		List<MTopicRelation> relations = new ArrayList<MTopicRelation>();
		List<StringBuffer> descs = new ArrayList<StringBuffer>();		//记录每个话题的desc（与其他话题相关度）
		//Language: <a href='' target='_blank'>Afrikaans</a><br/>Similar to:<ul><li><a href='' target='_blank'>Dutch</a> at 69%</li><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 45%</li></ul>
		
		try {
			Document document = xml.getXmlDocument(fileName);
			NodeList evolist = xml.getDescendantElements(document, "/evolutions/evolution");
		
			//Topic1
			String label1 = "";
			int id1 = 0;
			double size1 = 0;
			String color1 = "#adab86";
			String textcolor1="#6d6b46";
			String tag1 = "";
			String str1 = "";
			
			//Topic2
			String label2 = "";
			int id2 = 0;
			double size2 = 0;
			String color2 = "#adab86";
			String textcolor2="#6d6b46";
			String tag2 = "";
			String str2 = "";
			
			String words = "";
			Pattern p = Pattern.compile("([^\\s/]+)/([A-Za-z]+)");
			Matcher m;
			
			String rdesc;			//relation description

			for(int i = 0; i < evolist.getLength(); i++) {
				Element path = (Element)evolist.item(i);
				NodeList topiclist = xml.getDescendantElements(path, "Topic");
				double distance = Double.parseDouble(xml.getDescendantElement(path, "dis").getTextContent());
				distance = Double.parseDouble(f1.format(distance));
				
				//TOPIC1
				Element topic1 = (Element) topiclist.item(0);
				words = topic1.getAttribute("words");
				m = p.matcher(words);
				int num = 0;
				tag1 = "";			//每次循环后必须清空
				str1 = "";
				while(m.find()) {
					String word = m.group(1);
					if(num < 3){
						tag1 += (word+" ");
					}
					if(num<10){
						str1 += (word+" ");
					}
					num++;
				}
				size1 = Double.parseDouble(topic1.getAttribute("topicweight"))*100;
				label1 = topic1.getAttribute("year") + ": " + tag1;
				color1 = yearkeys[Integer.parseInt(topic1.getAttribute("year"))-2007];
				textcolor1 = colormap.get(color1);
				String yearid1 = topic1.getAttribute("year") + topic1.getAttribute("id");
				if(!topic2id.containsKey(yearid1)) {
					topic2id.put(yearid1, mapsize);
					id1 = mapsize;
					topics.add(new MTopic(label1,id1,color1,textcolor1,size1,""));
					
					StringBuffer desc = new StringBuffer();
					desc.append("<strong>Topic: </strong><a href='' target='_blank'>"+topic1.getAttribute("year") + ": " + str1+"</a><br/><br/><strong>Similar to:</strong><br/><ul>");
					descs.add(desc);
					
					mapsize++;
				}else {
					id1 = topic2id.get(yearid1);
				}
				
				
				//TOPIC2
				Element topic2 = (Element) topiclist.item(1);
				words = topic2.getAttribute("words");
				m = p.matcher(words);
				num = 0;
				tag2 = "";
				str2 = "";
				while(m.find()) {
					String word = m.group(1);
					if(num < 3){
						tag2 += (word+" ");
					}
					if(num<10){
						str2 += (word+" ");
					}
					num++;
				}
				size2 = Double.parseDouble(topic2.getAttribute("topicweight"))*100;
				label2 = topic2.getAttribute("year") + ": " + tag2;
				color2 = yearkeys[Integer.parseInt(topic2.getAttribute("year"))-2007];
				textcolor2 = colormap.get(color2);
				String yearid2 = topic2.getAttribute("year") + topic2.getAttribute("id");
				if(!topic2id.containsKey(yearid2)) {
					topic2id.put(yearid2, mapsize);
					id2 = mapsize;
					topics.add(new MTopic(label2,id2,color2,textcolor2,size2,""));
					
					StringBuffer desc = new StringBuffer();
					desc.append("<strong>Topic: </strong><a href='' target='_blank'>"+topic2.getAttribute("year") + ": " + str2+"</a><br/><br/><strong>Similar to:</strong><br/><ul><li><a href='' target='_blank'>"+ topic1.getAttribute("year") + ": " + str1+"</a><br/> distance: "+ distance +"</li>");
					descs.add(desc);
					(descs.get(id1)).append("<li><a href='' target='_blank'>"+ topic2.getAttribute("year") + ": " + str2+"</a><br/> distance: "+ distance +"</li>");
					
					mapsize++;
				}else {
					id2 = topic2id.get(yearid2);
					(descs.get(id1)).append("<li><a href='' target='_blank'>"+ topic2.getAttribute("year") + ": " + str2+"</a><br/> distance: "+ distance +"</li>");
					(descs.get(id2)).append("<li><a href='' target='_blank'>"+ topic1.getAttribute("year") + ": " + str1+"</a><br/> distance: "+ distance +"</li>");
				}
				
				
				//TOPIC1 && TOPIC2 Relation
				rdesc = label1 + "--" + label2;
				//距离越大weight越小，所以需要取反
				MTopicRelation relation = new MTopicRelation(rdesc, id1, id2, 1/distance, textcolor1);
				relations.add(relation);
			}
			
			//第一部分
			//System.out.println(descs.size());
			for(int i=0; i<descs.size(); i++) {
				(topics.get(i)).setDesc(((descs.get(i)).append("</ul>")).toString());
			}
			
			Collections.sort(topics);				//用于弦图顺序显示，时间从小到大排序
			int[] oldtonew = new int[topics.size()];		//重新标记TopicId值
			for(int i=0; i<topics.size(); i++) {
				MTopic topic = topics.get(i);
				topic.setId(i);
				oldtonew[topic.getOldid()] = i;
			}
			JsonConfig cfg = new JsonConfig();	//JSON串过滤
			cfg.setExcludes(new String[]{"oldid"});
			JSONArray topicArr = JSONArray.fromObject(topics,cfg);
			//System.out.println(topicArr.toString());
			writeFile("src/1-topicresult.txt", topicArr.toString());
			
			//第二部分
			StringBuffer strb = new StringBuffer();
			for(int i=0; i<topics.size(); i++) {
				strb.append("nodesHash[\"" + topics.get(i).getLabel() + "\"] = " + topics.get(i).getId()+";\n");
			}
			writeFile("src/2-labelid.txt", strb.toString());
			
			//第三部分
			//System.out.println(relations.size());
			for(int i=0; i<relations.size(); i++) {
				MTopicRelation relation = relations.get(i);
				relation.setSource(oldtonew[relation.getSource()]);
				relation.setTarget(oldtonew[relation.getTarget()]);
			}
			JSONArray relationArr = JSONArray.fromObject(relations);
			writeFile("src/3-relationresult.txt", relationArr.toString());
			
//			System.out.println(topic2id.size());
//			System.out.println(topic2id);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
