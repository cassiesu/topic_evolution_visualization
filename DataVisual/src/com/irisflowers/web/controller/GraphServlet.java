package com.irisflowers.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.irisflowers.service.BusinessService;

public class GraphServlet extends HttpServlet {

	BusinessService bs = new BusinessService();
	
	public GraphServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String display = request.getParameter("display");
		String choice[] = display.split("-");
		if("LH".equals(choice[0]) ){
			display(request, response, choice[0], choice[1]);
		} else if(  "MHSL".equals(choice[0])) {
			display(request, response, choice[0]);
		} else if( "NIPS".equals(choice[0]) ) {
			display(request, response, choice[0]);
		}
		
	}
	
	private void display(HttpServletRequest request,
			HttpServletResponse response, String category) throws IOException, ServletException {
		
		String path = getServletContext().getRealPath("/data/"+category+"/XML/"+category+".xml");
		String pathaddr = getServletContext().getRealPath("/data/"+category+"/Json/"+"data.json");
		String str = bs.findEvoGroup(category, path, pathaddr);			//NIPS, MHSL
		//System.out.println(str);
		request.setAttribute("data", str);
		request.getRequestDispatcher("/content.jsp").forward(request,response);
		
	}

	private void display(HttpServletRequest request,
			HttpServletResponse response, String category, String id) throws ServletException, IOException {
		
		if("whole".equals(id)){
			String path1 = getServletContext().getRealPath("/data/LH/XML/Taxonomy");
			String addr = getServletContext().getRealPath("/data/LH/Json/Whole/whole.json");
			String str = bs.findWhole(category, id, path1, addr);
			
			String path2 = getServletContext().getRealPath("/data/LH/XML/Lianghui-Context-Evo.xml");
			String pathaddr = getServletContext().getRealPath("/data/LH/Json/Whole/wholepath.json");
			String strpath = bs.findEvoGroup(category, id+"path", path2, pathaddr);
			//System.out.println(str);
			//System.out.println(strpath);
			request.setAttribute("data", str);
			request.setAttribute("datapath", strpath);
			request.getRequestDispatcher("/content.jsp").forward(request,response);
		} else{
			String path = getServletContext().getRealPath("/data/LH/XML/Detail/"+category+"-"+id+".xml");
			String pathaddr = getServletContext().getRealPath("/data/LH/Json/Detail/"+id+".json");
			String str = bs.findEvoGroup(category, id, path, pathaddr);
			//System.out.println(str);
			request.setAttribute("data", str);
			request.getRequestDispatcher("/content.jsp").forward(request,response);
		}
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
