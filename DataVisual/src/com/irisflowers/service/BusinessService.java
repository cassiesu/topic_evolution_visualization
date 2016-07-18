package com.irisflowers.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.irisflowers.dao.EvoDao;
import com.irisflowers.domain.EvoGroup;


public class BusinessService {
	private EvoDao eDao = new EvoDao();
	
	public String findEvoGroup(String category, String cid, String path, String pathaddr) throws IOException {
		
		EvoGroup egroup = eDao.findEvoGroup(category, cid, path);
		/**
		 * json实验start
		 */
		JsonConfig cfg = new JsonConfig();	//JSON串过滤
		cfg.setExcludes(new String[]{"evogroup"});	
		//不包含的字段列表，注意一定不能循环包含(evogroup中有evopath, evopath中有evogroup)
		//否则会出net.sf.json.JSONException: There is a cycle in the hierarchy!错误
		JSONObject json = JSONObject.fromObject(egroup, cfg);
		String jsonStr = json.toString().replaceAll("\"", "'");		
		//否则js读el表达式时外围""与里面的"构成一对无法读出json字符串，而js中""与''没差别
		/**
		 * json实验end
		 */
		writeFile(pathaddr, jsonStr);
		return jsonStr;
	}

	public String findWhole(String category, String cid, String path, String addr) throws IOException {
		
		EvoGroup egroup = eDao.findWhole(category, cid, path);
		
		JsonConfig cfg = new JsonConfig();	//JSON串过滤
		cfg.setExcludes(new String[]{"evogroup"});	
		JSONObject json = JSONObject.fromObject(egroup, cfg);
		String jsonStr = json.toString().replaceAll("\"", "'");		
		
		writeFile(addr, jsonStr);
		return jsonStr;
	}
	
	// 读文件，返回字符串
	private String ReadFile(String path){
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
	private void writeFile(String filePath, String str) throws IOException {
		FileWriter fw = new FileWriter(filePath);
		PrintWriter out = new PrintWriter(fw);
		out.write(str);
		out.println();
		fw.close();
		out.close();
	}

	public String findEvoGroup(String category, String path, String pathaddr) throws IOException {
		EvoGroup egroup = eDao.findEvoGroup(category, path);
		
		JsonConfig cfg = new JsonConfig();	//JSON串过滤
		cfg.setExcludes(new String[]{"evogroup"});	
		JSONObject json = JSONObject.fromObject(egroup, cfg);
		String jsonStr = json.toString().replaceAll("\"", "'");		
		
		writeFile(pathaddr, jsonStr);
		return jsonStr;
	}

}
