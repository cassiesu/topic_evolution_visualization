package com.irisflowers.domain;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.irisflowers.util.XMLProcessor;

public class EvoAll {
	private Map<Integer, EvoGroup> map;
	
	public EvoAll(){
		map = new HashMap<Integer, EvoGroup>();
	}
	
	public void addEvoGroup(EvoGroup group){
		int hcode = group.hashCode();
		map.put(hcode, group);
	}
	
	public EvoGroup findEvoGroup(String category, String id){
		EvoGroup group = null;
		
		//hashcode()
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		if(map.containsKey(result)){
			group = map.get(result);
			return group;
		}
		
		return null;	
	}
	
	public int countGroupNum(){
		return map.size();
	}
	
	public static void main(String[] args){
		//测试包
		try {
			EvoAll ea = new EvoAll();
			XMLProcessor xml = new XMLProcessor();
			Pattern p = Pattern.compile("([^\\s/]+)/([A-Za-z]+)");
			String[] years = new String[]{"2008","2009","2010","2011","2012","2013"};
			
			Document document = xml.getXmlDocument("src\\lh-evo.xml");
//			Node root = document.getFirstChild();
//			NodeList evolist = xml.getDescendantElements(root, "evolution");			//这里不能写"/evolution"

			//换种写法
			NodeList evolist = xml.getDescendantElements(document, "/evolutions/evolution");
			EvoGroup egroup = new EvoGroup("LH", "ms");
			
			for(int i = 0; i < evolist.getLength(); i++) {
				Element evo = (Element)evolist.item(i);
				NodeList topiclist = xml.getDescendantElements(evo, "Topic");
				
				EvoPath epath = new EvoPath(i, egroup);
				
				String topicweight = "";
				String words = "";
				String wordweight = "";
				String year = "";
				String id = "";
				
				double wpy[] = new double[years.length];			//6个年份
				String idpy[] = new String[years.length];
				int count = 0;			//年份比较计数器
				
				for(int j =0; j<topiclist.getLength(); j++) {
					Element topic = (Element)topiclist.item(j);
					topicweight = topic.getAttribute("topicweight");
					words = topic.getAttribute("words");
					wordweight = topic.getAttribute("wordweight");
					year = topic.getAttribute("year");
					id = topic.getAttribute("id");
					
					String[] ww = wordweight.split(" ");
					Matcher m = p.matcher(words);
					
					KeywordList kwlist = new KeywordList(year);
					for(int k=0; k<ww.length; k++){
						if(m.find()) {
							String word = m.group(1);
							double weight = Double.parseDouble(ww[k]);		//下标一定注意不能用错，否则后果很严重
							Keyword kw = new Keyword(word, weight);
							kwlist.addKeyword(kw);
						}
					}
					Collections.sort(kwlist.getList());
//					System.out.println(kwlist);
//					JSONObject json = JSONObject.fromObject(kwlist); 
//					System.out.println(json.toString());
					
					epath.addMap(kwlist);
					
					for(; count<years.length; count++){
						if(year.equals(years[count])){
							wpy[count] = Double.parseDouble(topicweight);
							idpy[count] = id;
							count++;				//debug出来的，否则break不会自动++
							break;
						} else {
							wpy[count] = 0;
						}
					}
				}
				
				epath.setTopicidperyear(idpy);
				epath.setWeightperyear(wpy);
				//System.out.println(epath.getMap().keySet());
				
				/**
				 * json实验start
				 */
//				JsonConfig cfg = new JsonConfig();	//JSON串过滤
//				cfg.setExcludes(new String[]{"evogroup"});	
//				//不包含的字段列表，注意一定不能循环包含evogroup中有evopath, evopath中有evogroup
//				//否则会出net.sf.json.JSONException: There is a cycle in the hierarchy!错误
//				JSONObject json = JSONObject.fromObject(epath, cfg); 
//				System.out.println(json.toString());
				/**
				 * json实验end
				 */

				egroup.addEvoPath(epath);
	
			}	
			
			JsonConfig cfg = new JsonConfig();	//JSON串过滤
			cfg.setExcludes(new String[]{"evogroup"});	
			//不包含的字段列表，注意一定不能循环包含(evogroup中有evopath, evopath中有evogroup)
			//否则会出net.sf.json.JSONException: There is a cycle in the hierarchy!错误
			JSONObject json = JSONObject.fromObject(egroup, cfg); 
			System.out.println(json.toString());
			
			//System.out.println(egroup.getPathNum());		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
