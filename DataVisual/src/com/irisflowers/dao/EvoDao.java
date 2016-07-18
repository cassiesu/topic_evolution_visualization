package com.irisflowers.dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.irisflowers.domain.EvoGroup;
import com.irisflowers.domain.EvoPath;
import com.irisflowers.domain.Keyword;
import com.irisflowers.domain.KeywordList;
import com.irisflowers.interpolation.MyInterpolation;
import com.irisflowers.math.Convergence;
import com.irisflowers.math.SD;
import com.irisflowers.util.XMLProcessor;

public class EvoDao {

	public EvoGroup findEvoGroup(String category, String cid, String path) {
		try {
			XMLProcessor xml = new XMLProcessor();
			Pattern p = Pattern.compile("([^\\s/]+)/([A-Za-z]+)");
			String[] years = new String[]{"2007","2008","2009","2010","2011","2012","2013","2014"};
			DecimalFormat f1 = new DecimalFormat("0.0000");
			
			Document document = xml.getXmlDocument(path);
//			Node root = document.getFirstChild();
//			NodeList evolist = xml.getDescendantElements(root, "evolution");			//这里不能写"/evolution"

			//换种写法
			NodeList evolist = xml.getDescendantElements(document, "/evolutions/evolution");
			EvoGroup egroup = new EvoGroup(category, cid);
			
			for(int i = 0; i < evolist.getLength(); i++) {
				Element evo = (Element)evolist.item(i);
				
				NodeList topiclist = xml.getDescendantElements(evo, "Topic");
				EvoPath epath = new EvoPath(i, egroup);
				if(evo.hasAttribute("tag")){
					String tag = evo.getAttribute("tag");
					epath.setTag(tag);
				}	
				
				KeywordList kwlistfull = new KeywordList("full");			//重计算所有年份的kwlistfull
				
				String topicweight = "";
				String words = "";
				String wordweight = "";
				String year = "";
				String id = "";
				
				double wpy[] = new double[years.length];			//8个年份
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
					
					KeywordList kwlist = new KeywordList("y"+year);		//纯数字json里面不能作key用.取
					for(int k=0; k<ww.length; k++){
						if(m.find()) {
							String word = m.group(1);
							double weight = Double.parseDouble(ww[k]);		//下标一定注意不能用错，否则后果很严重
							Keyword kw = new Keyword(word, weight);
							kwlist.addKeyword(kw);
							
							//重计算overall关键词权重
							double recount = weight*Double.parseDouble(topicweight);
							recount = Double.parseDouble(f1.format(recount));
							kwlistfull.changeKwWeight(word, recount);
						}
					}
					Collections.sort(kwlist.getList());
					epath.addMap(kwlist);
//					System.out.println(kwlist);
//					JSONObject json = JSONObject.fromObject(kwlist); 
//					System.out.println(json.toString());
					
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
				/**
				 * kwlistfull放入epath-map start
				 */
				Collections.sort(kwlistfull.getList());
				epath.addMap(kwlistfull);
				/**
				 * kwlistfull放入epath-map end
				 */
				
				/**
				 * 插值start
				 */
				double[] x0 = new double[]{2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014};
				double[] y0 = wpy;
				double[] x = new double[71];			//每年之间插10个点
				for(int j=0; j<x.length; j++)
			    {
					x[j] = 2007 + 0.1*j;
			    }
				//插值这里应该自己制定规则：两个weight为0之间，查的所有值均为0，否则容易出问题
				double[] y1 = MyInterpolation.lagrange(x0, y0, x0.length, x);
				double[] y2 = MyInterpolation.newton(x0, y0, x0.length, x);
				double[] y3 = MyInterpolation.ThreeSpline(x0, y0, x0.length, x);
				
				System.out.println();
				epath.setYearfull(x);
				epath.setWeightfull1(y1);
				epath.setWeightfull2(y2);
				epath.setWeightfull3(y3);
				
				/**
				 * 插值end
				 */			
				egroup.addEvoPath(epath);
	
			}	
			/**
			 * path重排序start
			 */
			for(int w=0; w<egroup.getList().size(); w++){
				System.out.print(egroup.getList().get(w).getId()+" ");
				System.out.print(SD.getSD(egroup.getList().get(w).getWeightperyear())+" ");
			}
			System.out.println();
			Collections.sort(egroup.getList());		//重排序以后path的id顺序也变了呦
			for(int w=0; w<egroup.getList().size(); w++){
				System.out.print(egroup.getList().get(w).getId()+" ");
			}
			System.out.println();
			/**
			 * path重排序end
			 */
			return egroup;
			
			//System.out.println(egroup.getPathNum());		
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public EvoGroup findWhole(String category, String cid, String path) {
		try {
			XMLProcessor xml = new XMLProcessor();
			String[] years = new String[]{"2007","2008","2009","2010","2011","2012","2013","2014"};
			DecimalFormat f1 = new DecimalFormat("0.0000");
			//Document document = xml.getXmlDocument(path + "\\" + y + "-LH-Evolution.xml");
			//System.out.println(path + "\\"+ y + "-LH-Evolution.xml");
			Document document = xml.getXmlDocument(path + "\\" + "LH-Taxonomy.xml");
			NodeList threadlist = xml.getDescendantElements(document, "/topic/thread");
			
			EvoGroup egroup = new EvoGroup(category, cid);
			List<double[]> numbergroup = new ArrayList<double[]>();			//存放所有path的weightperyear三次样条插值得到的数据，用于演化趋势的判断
			
			for(int i=0; i<threadlist.getLength(); i++){
				EvoPath epath = new EvoPath(i, egroup);
				KeywordList kwlistfull = new KeywordList("full");			//重计算所有年份的kwlistfull
				
				Element thread = (Element)threadlist.item(i);
				String tag = thread.getAttribute("text");
				epath.setTag(tag);
				
				NodeList themeslist = xml.getDescendantElements(thread, "themes");
				double[] wpy = new double[years.length];
				for(int j=0; j<themeslist.getLength(); j++){
					Element themes = (Element)themeslist.item(j);
					wpy[j] = Double.parseDouble(f1.format(Double.parseDouble(themes.getAttribute("weight"))));
					
					String weight = "";
					String text = "";
					String textweights = "";
					
					NodeList themelist = xml.getDescendantElements(themes, "theme");
					KeywordList kwlist = new KeywordList("y"+years[j]);		//纯数字json里面不能作key用.取
					
					for(int k=0;k<themelist.getLength();k++){
						Element theme = (Element)themelist.item(k);
						text = theme.getAttribute("text");
						textweights = theme.getAttribute("textweights");
						weight = theme.getAttribute("weight");
						
						String[] words = text.split(" ");
						String[] wordweights = textweights.split(" ");
						double[] weights = new double[words.length];
						for(int n=0; n<words.length; n++) {
							weights[n] = Double.parseDouble(weight)*Double.parseDouble(wordweights[n]);
							weights[n] = Double.parseDouble(f1.format(weights[n]));
							kwlist.changeKwWeight(words[n], weights[n]);
							kwlistfull.changeKwWeight(words[n], weights[n]);
						}	
					}
					
					Collections.sort(kwlist.getList());
					epath.addMap(kwlist);
					
				}
				
				epath.setWeightperyear(wpy);
				
				/**
				 * kwlistfull放入epath-map start
				 */
				Collections.sort(kwlistfull.getList());
				epath.addMap(kwlistfull);
				/**
				 * kwlistfull放入epath-map end
				 */
				
				/**
				 * 插值start
				 */
				double[] x0 = new double[]{2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014};
				double[] y0 = wpy;
				double[] x = new double[71];			//每年之间插10个点
				for(int j=0; j<x.length; j++)
			    {
					x[j] = 2007 + 0.1*j;
			    }
				//比较证明，三次样条效果最好，不会出现负值且圆滑
				double[] y1 = MyInterpolation.lagrange(x0, y0, x0.length, x);
				double[] y2 = MyInterpolation.newton(x0, y0, x0.length, x);
				double[] y3 = MyInterpolation.ThreeSpline(x0, y0, x0.length, x);

//				System.out.println();
//				for(int j=0; j<y3.length; j++)
//			    {
//					System.out.print(y3[j]+" ");
//			    }
				
				epath.setYearfull(x);
				epath.setWeightfull1(y1);
				epath.setWeightfull2(y2);
				epath.setWeightfull3(y3);
				
				/**
				 * 插值end
				 */	
				numbergroup.add(epath.getWeightfull3());
				
				egroup.addEvoPath(epath);
			
			}
			//判断路径的发散收敛性
			Convergence.judge(numbergroup, egroup);
			
			return egroup;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public EvoGroup findEvoGroup(String category, String path) {
		try {
			XMLProcessor xml = new XMLProcessor();
			Pattern p = Pattern.compile("([^\\s/]+)/([A-Za-z]+)");
			String[] years;
			if("MHSL".equals(category)) {
				years = new String[]{"308-312","313-317","318-322","323-327","328-401","402-406","407-411","412-416"};
			} else {
				years = new String[]{"2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010"};
			}
			DecimalFormat f1 = new DecimalFormat("0.0000");
			
			Document document = xml.getXmlDocument(path);

			NodeList evolist = xml.getDescendantElements(document, "/evolutions/evolution");
			EvoGroup egroup = new EvoGroup(category, null);
			
			for(int i = 0; i < evolist.getLength(); i++) {
				Element evo = (Element)evolist.item(i);
				
				NodeList topiclist = xml.getDescendantElements(evo, "Topic");
				EvoPath epath = new EvoPath(i, egroup);
				
				if(evo.hasAttribute("tag")){
					String tag = evo.getAttribute("tag");
					epath.setTag(tag);
				}	
				
				KeywordList kwlistfull = new KeywordList("full");			//重计算所有年份的kwlistfull
				
				String topicweight = "";
				String words = "";
				String wordweight = "";
				String year = "";
				String id = "";
				
				double wpy[] = new double[years.length];			//8个年份
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
					
					KeywordList kwlist = new KeywordList("y"+year);		//纯数字json里面不能作key用.取
					for(int k=0; k<ww.length; k++){
						if(m.find()) {
							String word = m.group(1);
							double weight = Double.parseDouble(ww[k]);		//下标一定注意不能用错，否则后果很严重
							Keyword kw = new Keyword(word, weight);
							kwlist.addKeyword(kw);
							
							//重计算overall关键词权重
							double recount = weight*Double.parseDouble(topicweight);
							recount = Double.parseDouble(f1.format(recount));
							kwlistfull.changeKwWeight(word, recount);
						}
					}
					Collections.sort(kwlist.getList());
					epath.addMap(kwlist);
//					System.out.println(kwlist);
//					JSONObject json = JSONObject.fromObject(kwlist); 
//					System.out.println(json.toString());
					
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
				/**
				 * kwlistfull放入epath-map start
				 */
				Collections.sort(kwlistfull.getList());
				epath.addMap(kwlistfull);
				/**
				 * kwlistfull放入epath-map end
				 */
							
				/**
				 * 插值start
				 */
				double[] x0 = new double[]{2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014};
				double[] y0 = wpy;
				double[] x = new double[71];			//每年之间插10个点
				for(int j=0; j<x.length; j++)
			    {
					x[j] = 2007 + 0.1*j;
			    }
				//插值这里应该自己制定规则：两个weight为0之间，查的所有值均为0，否则容易出问题
				double[] y1 = MyInterpolation.lagrange(x0, y0, x0.length, x);
				double[] y2 = MyInterpolation.newton(x0, y0, x0.length, x);
				double[] y3 = MyInterpolation.ThreeSpline(x0, y0, x0.length, x);
				
				System.out.println();
				epath.setYearfull(x);
				epath.setWeightfull1(y1);
				epath.setWeightfull2(y2);
				epath.setWeightfull3(y3);
				
				/**
				 * 插值end
				 */			
				egroup.addEvoPath(epath);
	
			}	
			/**
			 * path重排序start
			 */
			for(int w=0; w<egroup.getList().size(); w++){
				System.out.print(egroup.getList().get(w).getId()+" ");
				System.out.print(SD.getSD(egroup.getList().get(w).getWeightperyear())+" ");
			}
			System.out.println();
			Collections.sort(egroup.getList());		//重排序以后path的id顺序也变了呦
			for(int w=0; w<egroup.getList().size(); w++){
				System.out.print(egroup.getList().get(w).getId()+" ");
			}
			System.out.println();
			/**
			 * path重排序end
			 */
			return egroup;	
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
