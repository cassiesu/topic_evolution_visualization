<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>

<div id="page-wrapper">
	<div id="page" class="pagecontainer">
		<div id="content">
			<div class="title">
				<h2>StreamGraph && TagCloud</h2>
			</div>
            
			<p>
				<div id="all">
	              <div id="allcontent">
	                  <div id="maincontent">
	                        <div class="portfolio">
								<div id="backdis">
	                            	<!--<img src="http://theeggs.biz/imgs/illu/illu01.jpg" alt="Portfolio img"><br>-->
	                            </div>
	                            <span class="txt">BackStage Display(Percent)&nbsp;&nbsp;&nbsp;
		                            <ul id="threechoice">
									  <li>
									   <a href="" title="BackDis">@Three Interpolations at background.</a>
									 <div>
									   Test1:&nbsp; <a href="javascript:changeSplitMethod(1);">Lagrange</a><br/>
									   Test2:&nbsp; <a href="javascript:changeSplitMethod(2);">Newton</a><br/>
									   Test3:&nbsp; <a href="javascript:changeSplitMethod(3);">ThreeSpline</a><br/>
									    </div>
									  </li>
									</ul>
	                            </span>
	                            <div class="ombra"></div>
	                        </div>
	                        <div class="portfolio">
	                        	<div id="frontdis">
	                            	<!--<img src="http://theeggs.biz/imgs/web/web01.jpg" alt="Portfolio img"><br>-->
	                            </div>
	                            <span class="txt">FrontStage Display(Percent)&nbsp;&nbsp;&nbsp;
	                            	<ul>
									  <li>
									   <a href="" title="FrontDis">@Areaspline displayed at foreground.</a>
									  </li>
									</ul>
								</span>
	                            <div class="ombra"></div>
	                        </div>
	                        <div class="portfolio">
								<div id="oribackdis">
	                            	<!--<img src="http://theeggs.biz/imgs/illu/illu01.jpg" alt="Portfolio img"><br>-->
	                            </div>
	                            <span class="txt">BackStage Display(Original)&nbsp;&nbsp;&nbsp;
		                            <ul id="threechoice">
									  <li>
									   <a href="" title="BackDis">@Three Interpolations at background.</a>
									 <div>
									   Test1:&nbsp; <a href="javascript:changeOriSplitMethod(1);">Lagrange</a><br/>
									   Test2:&nbsp; <a href="javascript:changeOriSplitMethod(2);">Newton</a><br/>
									   Test3:&nbsp; <a href="javascript:changeOriSplitMethod(3);">ThreeSpline</a><br/>
									    </div>
									  </li>
									</ul>
	                            </span>
	                            <div class="ombra"></div>
	                        </div>
	                        <div class="portfolio">
	                        	<div id="orifrontdis">
	                            	<!--<img src="http://theeggs.biz/imgs/web/web01.jpg" alt="Portfolio img"><br>-->
	                            </div>
	                            <span class="txt">FrontStage Display(Original)&nbsp;&nbsp;&nbsp;
	                            	<ul>
									  <li>
									   <a href="" title="FrontDis">@Areaspline displayed at foreground.</a>
									  </li>
									</ul>
								</span>
	                            <div class="ombra"></div>
	                        </div>
	                    </div>
	                    <div id="navi"></div>
	                </div>
	            </div>
			</p>
		</div>
		
		<div id="sidebar">
			<div class="title">
				<h2>DocLists/Year</h2>
			</div>
			<nav id="docnav">
			  <ul>
			    <li><a href="#">2007</a>
			    </li><li><a href="#">2008</a>
			    </li><li><a href="#">2009</a>
			    </li><li><a href="#">2010</a>
			    </li><li><a href="#">2011</a>
			    </li><li><a href="#">2012</a>
			    </li><li><a href="#">2013</a>
			    </li><li><a href="#">2014</a>
			  </ul>
			</nav>
			<div class="section">
				<ul id="doclist" class="blur">
					<li><article class="post">
						  <h1><a href="#">Article title</a></h1>
						  <time datetime="2014-01-01">01/01/2014</time>
						  <p>This is my article.</p>  
						</article>
					</li>
					<li>Two</li>
					<li>Three</li><li>Four</li><li>Five</li><li>Six</li><li>Seven</li><li>Eight</li><li>Nine</li><li>Ten</li>
				</ul>
			</div>
		</div>
	</div>
</div>

<div id="alertbox" class="Modal-Background toggle-Modal" style="display:none">
  <div class="Center-Block Absolute-Center is-Fixed is-Variable Modal" id="Fixed-Modal">
  <div class="Center-Content">
    <h4 class="Title">Change Tagclouds.</h4>
    <span class="close">X</span>
    <p class="Subtitle">Detailed analysis changed to ... area.</p>      
    <p><a href="javascript:offbox();" class="Shaw-Button toggle-Modal">Got it & Jump!</a></p>
  </div>
  </div>
</div>

<nav id = "nav">
  <div id = "bookmark">&#9776;<div id = "horizontal-shadow"></div><div id = "vertical-shadow"></div></div>
  <div id = "vertical-shadow-nav"></div>
  <ul>
    <li><a href = "javascript:toggle(1)">Details</a></li>
    <li><a href = "javascript:toggle(2)">Whole</a></li>
    <li><a href = "javascript:toggle(3)">ShowAll</a></li>
    <li><a href = "javascript:toggle(4)">ShowNone</a></li>
    <li><div id = "sep"></div></li>
  </ul>
</nav>

<div class="wrapper" id="details" style="DISPLAY: none">
	<div id="four-column" class="container">
		<div id="tcy2007" class="tbox1">
			<div align="left"><h3>2007</h3></div>
			<p></p>
		</div>
		<div id="tcy2008" class="tbox2">
			<div align="left"><h3>2008</h3></div>
			<p></p>
		</div>
		<div id="tcy2009" class="tbox1">
			<div align="left"><h3>2009</h3></div>
			<p></p>
		</div>
		<div id="tcy2010" class="tbox2">
			<div align="left"><h3>2010</h3></div>
			<p></p>
		</div>
	</div>

	<div id="four-column" class="container">
		<div id="tcy2011" class="tbox2">
			<div align="left"><h3>2011</h3></div>
			<p></p>
		</div>
		<div id="tcy2012" class="tbox1">
			<div align="left"><h3>2012</h3></div>
			<p></p>
		</div>
		<div id="tcy2013" class="tbox2">
			<div align="left"><h3>2013</h3></div>
			<p></p>
		</div>
		<div id="tcy2014" class="tbox1">
			<div align="left"><h3>2014</h3></div>
			<p></p>
		</div>
	</div>	
</div>

<div class="wrapper" id="whole" style="DISPLAY: none">
	<div id="three-column">
		<div id="tcfull" class="box1">
			<div align="left"><h3>FullTagClouds</h3></div>
			<p></p>
		</div>
		<div id="whole1" class="box2"></div>
		<div id="whole2" class="box3"></div>
	</div>
</div>

<div id="page-wrapper">
	<div id="analysis" class="pagecontainer">
		<div class="title">
			<h2>Topic Relation Net</h2>
		</div>
		<div id="graphHolder"></div>
		<div id="toolbox">
			<p id="titlep">
				<div id="title">TopicNet</div><br/>
			</p>
			<div align="center" id="chartSelector">
				<input type="radio" value="network" id="networkOption" name="chartOption" checked="checked" /><label for="networkOption">network</label>
				<input type="radio" value="chord" id="chordOption" name="chartOption" /><label for="chordOption">chord</label>
			</div>
			<br/>
			<p><input id="hide_checkbox" type="checkbox" onClick="hide()" />Hide unrelated topics</p>
			<div id="sliderContainer">
				Filter by similarity level: <span id="similarity"></span>
				<div id="slider"></div>
			</div>
			<p id="hint"></p>
			<div id="language_information"></div>
		</div>
	</div>
</div>

<%@ include file="footer.jsp" %>





<script type="text/javascript">
function toggle(num){
	if(1==num) {	
		/*document.getElementById("details").style.display="block";
		document.getElementById("whole").style.display="none";*/
		$('#whole').slideUp(1000);
		$('#details').slideDown(1000);
		
	}
	else if(2==num) {
		/*document.getElementById("details").style.display="none";
		document.getElementById("whole").style.display="block";*/
		$('#details').slideUp(1000);
		$('#whole').slideDown(1000);
	}
	else if(3==num) {
		//document.getElementById("details").style.display="block";
		//document.getElementById("whole").style.display="block";
		$('#whole').fadeIn("slow");
		$('#details').fadeIn("slow");
	}
	else {
		//document.getElementById("details").style.display="none";
		//document.getElementById("whole").style.display="none";
		$('#whole').fadeOut("slow");
		$('#details').fadeOut("slow");
	}
}
</script>

<script type="text/javascript">
	var data = "${data}"; 				//JSON数据，服务端是普通字符串返回的,在js中使用el表达式一定要使用双引号;
	var gdata = eval("("+data+")");		//把普通的JSON字符串文本变成真正的JSON数据
	
	//alert(gdata);
	//alert(gdata.list[5].firstyear);
	//alert(gdata.list[0].map.y2008);
	//alert(gdata.list[0].map.y2008.list);
	var datapath = "";					//JS变量记得要初始化
	var gdatapath = {};
	if("whole" == gdata.id){
		datapath = "${datapath}";	
		gdatapath =eval("("+datapath+")");
	}
		
	var tc_idx;						//点击变换对应路径tagclouds的索引
	var tag2idx = {};					//pathtag与pathlist的pathindex一一对应
	var pathnum = gdata.list.length;
	for(var i=0; i<pathnum; i++){
		tag2idx[gdata.list[i].tag] = i;
	}
	
	var tag2idx1 = {};
	var pathnum1;
	if("whole" == gdata.id){
		pathnum1 = gdatapath.list.length;
		for(var i=0; i<pathnum1; i++){
			tag2idx1[gdatapath.list[i].tag] = i;
		} 
	}
		
	
	var disdate;				//存放highchart显示的时间
	var backdisdate;
	
	if(gdata.category == "MHSL") {						//改写标签title内容
		$("#tcy2007>div>h3").html("3月8日~3月12日");
		$("#tcy2008>div>h3").html("3月13日~3月17日");
		$("#tcy2009>div>h3").html("3月18日~3月22日");
		$("#tcy2010>div>h3").html("3月23日~3月27日");
		$("#tcy2011>div>h3").html("3月28日~4月1日");
		$("#tcy2012>div>h3").html("4月2日~4月6日");
		$("#tcy2013>div>h3").html("4月7日~4月11日");
		$("#tcy2014>div>h3").html("4月12日~4月16日");
		disdate = ['3.8~3.12','3.13~3.17','3.18~3.22','3.23~3.27','3.28~4.1','4.2~4.6','4.7~4.11','4.12~4.16'];	
		backdisdate = ['3.8~3.12','','','','','','','','','',
					'3.13~3.17','','','','','','','','','',
					'3.18~3.22','','','','','','','','','',
					'3.23~3.27','','','','','','','','','',
					'3.28~4.1','','','','','','','','','',
					'4.2~4.6','','','','','','','','','',
					'4.7~4.11','','','','','','','','','','4.12~4.16'];
	} if(gdata.category == "NIPS") {						//改写标签title内容
		$("#tcy2007>div>h3").html("2003");
		$("#tcy2008>div>h3").html("2004");
		$("#tcy2009>div>h3").html("2005");
		$("#tcy2010>div>h3").html("2006");
		$("#tcy2011>div>h3").html("2007");
		$("#tcy2012>div>h3").html("2008");
		$("#tcy2013>div>h3").html("2009");
		$("#tcy2014>div>h3").html("2010");
		disdate = ['2003','2004','2005','2006','2007','2008','2009','2010'];	
		backdisdate = ['2003','','','','','','','','','',
					'2004','','','','','','','','','',
					'2005','','','','','','','','','',
					'2006','','','','','','','','','',
					'2007','','','','','','','','','',
					'2008','','','','','','','','','',
					'2009','','','','','','','','','','2010'];
	} else {
		disdate = ['2007', '2008', '2009', '2010', '2011', '2012', '2013', '2014'];
		backdisdate = ['2007','','','','','','','','','',
					'2008','','','','','','','','','',
					 '2009','','','','','','','','','',
					  '2010','','','','','','','','','',
					   '2011','','','','','','','','','',
					    '2012','','','','','','','','','',
					     '2013','','','','','','','','','', '2014'];
	}
</script>
<script type="text/javascript">
	$(function() {
		$("#chartSelector").buttonset();
		$("#chartSelector").change(function(event){
			chartChange($("input[type=radio]:checked").val());
		});
		restart();
		$("#slider").slider({ change: filterChange, min: similarityThresholdMin, max: similarityThresholdMax, value: similarityThreshold });
	});
</script>
<script type="text/javascript" src="js/tr/<%=request.getParameter("display")%>.js"></script>
<script type="text/javascript" src="js/langnet.js"></script>
<script type="text/javascript" src="js/slides.js"></script>		
<script type="text/javascript" src="js/tagcloud.js"></script>	
<script type="text/javascript" src="js/frontdis.js"></script>	
<script type="text/javascript" src="js/backdis.js"></script>
<script type="text/javascript" src="js/orifrontdis.js"></script>	
<script type="text/javascript" src="js/oribackdis.js"></script>		
<script type="text/javascript" src="js/doclist.js"></script>	
<script type="text/javascript" src="js/zoomcircle.js"></script>	

	