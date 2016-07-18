  //！！！因为是静态包含，所以如果我们把这一坨放在最后所有的元素都能看到了
  
  //jQuery也找不到content中的标签？？网页有错误——已解决
  //$("#page").html("jQuery Test");

//残留问题：为什么重新点回以前点过的标签云，js出错，TypeError: sprite is undefined
	
function tagcloudEliminator(){
	for(var year=2007; year<2015; year++){
		$("#tcy"+year+" p").empty();
		//alert("Empty success!");
	}
	$("#tcfull p").empty();
}

function tagcloudGenerator(){
	
	  var fill = d3.scale.category20();
	  var oname = "";
	  //var gdata值可以直接在这里得到哦
	  for(var name in gdata.list[tc_idx].map){
		  	//alert("#tc"+name+" p");
	  	if(gdata.category=="MHSL"){
	  		if(name == "y308-312") {			//转换id
	  			oname = "y2007";
	  		} else if(name == "y313-317") {
	  			oname = "y2008";
	  		} else if(name == "y318-322") {
	  			oname = "y2009";
	  		} else if(name == "y323-327") {
	  			oname = "y2010";
	  		} else if(name == "y328-401") {
	  			oname = "y2011";
	  		} else if(name == "y402-406") {
	  			oname = "y2012";
	  		} else if(name == "y407-411") {
	  			oname = "y2013";
	  		} else if(name == "y412-416") {
	  			oname = "y2014";
	  		}
			//alert(oname);
	  	}
	  		
  		if(gdata.category=="NIPS"){
  			if(name == "2003") {			//转换id
	  			oname = "y2007";
	  		} else if(name == "2004") {
	  			oname = "y2008";
	  		} else if(name == "2005") {
	  			oname = "y2009";
	  		} else if(name == "2006") {
	  			oname = "y2010";
	  		} else if(name == "2007") {
	  			oname = "y2011";
	  		} else if(name == "2008") {
	  			oname = "y2012";
	  		} else if(name == "2009") {
	  			oname = "y2013";
	  		} else if(name == "2010") {
	  			oname = "y2014";
	  		}
			//alert(oname);
  		}
			
		  	if(name != "full"){
		  	  function draw(words) {
			    d3.select("#tc"+(oname==""?name:oname)+" p").append("svg")
			        .attr("width", 250)
			        .attr("height", 250)
			      .append("g")
			        .attr("transform", "translate(125,125)")
			      .selectAll("text")
			        .data(words)
			      .enter().append("text")
			        .style("font-size", function(d) { return d.size + "px"; })
			        .style("font-family", "Microsoft YaHei")
			        .style("font-weight", "bold")
			        .style("fill", function(d, i) { return fill(i); })
			        .attr("text-anchor", "middle")
			        .attr("onclick", "javascript:searchKeyword(evt);")
			        .attr("onmouseover", "this.style.cursor='hand'")
			        .attr("transform", function(d) {
			          return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
			        })
			        .text(function(d) { return d.text; });
			  }
			  d3.layout.cloud().size([250, 250])
			  .words(gdata.list[tc_idx].map[name].list)
			      .padding(5)
			      .rotate(function() { return ~~(Math.random() * 2) * 90; })
			      .font("Impact")
			      .fontSize(function(d) { 
			      				if(gdata.id=="whole") {
			      					return d.size*3500;
			      					alert(d.size*3500);
			      				} else{
			      					return d.size*1000;
			      				}
			      			})
			      .on("end", draw)
			      .start();
			} else {
			  function draw(words) {
				d3.select("#tc"+name+" p").append("svg")
			        .attr("width", 370)
			        .attr("height", 370)
			      .append("g")
			        .attr("transform", "translate(185,185)")
			      .selectAll("text")
			        .data(words)
			      .enter().append("text")
			        .style("font-size", function(d) { return d.size + "px"; })
			        .style("font-family", "Microsoft YaHei")
			        .style("font-weight", "bold")
			        .style("fill", function(d, i) { return fill(i); })
			        .attr("text-anchor", "middle")
			        .attr("onclick", "javascript:searchKeyword(evt);")
			        .attr("onmouseover", "this.style.cursor='hand'")
			        .attr("transform", function(d) {
			          return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
			        })
			        .text(function(d) { return d.text; });
			  }
			  d3.layout.cloud().size([370, 370])
			  .words(gdata.list[tc_idx].map[name].list)
			      .padding(5)
			      .rotate(function() { return ~~(Math.random() * 2) * 90; })
			      .font("Impact")
			      .fontSize(function(d){ 
			      				if(gdata.category=="MHSL") {
			      					return d.size*1600;
			      				}
			      				if(gdata.id=="whole") {
			      					return d.size*800; 
			      				} else{
			      					return d.size*800;					//这里怎么搞一个自适应的算法？？？
			      				}
			      			})
			      .on("end", draw)
			      .start();
				
			}
		}
		
		//$("svg g text").wrap("<a xlink:href='http://www.w3schools.com/svg/' target='_blank'></a>");		//这样写显示不出
		
	    /*$("#svg g text").click(function(){			//这样也不行？
			alert("ok");
	    });*/
}

function tagcloudGenerator2(){
	
	  var fill = d3.scale.category20();
	  //var gdata值可以直接在这里得到哦
	  for(var name in gdatapath.list[tc_idx].map){
		  	if(name != "full"){
		  	  function draw(words) {
			    d3.select("#tc"+name+" p").append("svg")
			        .attr("width", 250)
			        .attr("height", 250)
			      .append("g")
			        .attr("transform", "translate(125,125)")
			      .selectAll("text")
			        .data(words)
			      .enter().append("text")
			        .style("font-size", function(d) { return d.size + "px"; })
			        .style("font-family", "Microsoft YaHei")
			        .style("font-weight", "bold")
			        .style("fill", function(d, i) { return fill(i); })
			        .attr("text-anchor", "middle")
			        .attr("onclick", "javascript:searchKeyword(evt);")
			        .attr("onmouseover", "this.style.cursor='hand'")
			        .attr("transform", function(d) {
			          return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
			        })
			        .text(function(d) { return d.text; });
			  }
			  d3.layout.cloud().size([250, 250])
			  .words(gdatapath.list[tc_idx].map[name].list)
			      .padding(5)
			      .rotate(function() { return ~~(Math.random() * 2) * 90; })
			      .font("Impact")
			      .fontSize(function(d) {
			      		return d.size*800;
			      	})
			      .on("end", draw)
			      .start();
			} else {
			  function draw(words) {
				d3.select("#tc"+name+" p").append("svg")
			        .attr("width", 370)
			        .attr("height", 370)
			      .append("g")
			        .attr("transform", "translate(185,185)")
			      .selectAll("text")
			        .data(words)
			      .enter().append("text")
			        .style("font-size", function(d) { return d.size + "px"; })
			        .style("font-family", "Microsoft YaHei")
			        .style("font-weight", "bold")
			        .style("fill", function(d, i) { return fill(i); })
			        .attr("text-anchor", "middle")
			        .attr("onclick", "javascript:searchKeyword(evt);")
			        .attr("onmouseover", "this.style.cursor='hand'")
			        .attr("transform", function(d) {
			          return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
			        })
			        .text(function(d) { return d.text; });
			  }
			  d3.layout.cloud().size([370, 370])
			  .words(gdatapath.list[tc_idx].map[name].list)
			      .padding(5)
			      .rotate(function() { return ~~(Math.random() * 2) * 90; })
			      .font("Impact")
			      .fontSize(function(d){ 
			      		return d.size*8000;					//这里怎么搞一个自适应的算法？？？
			      	})
			      .on("end", draw)
			      .start();
				
			}
		}
		
		//$("svg g text").wrap("<a xlink:href='http://www.w3schools.com/svg/' target='_blank'></a>");		//这样写显示不出
		
	    /*$("#svg g text").click(function(){			//这样也不行？
			alert("ok");
	    });*/
}


function searchKeyword(evt){
	alert('search');
	obj = evt.target;			//终于好使了！svg自己的获取调用click函数的对象
	keyword = obj.textContent;		//对象的文本属性
	alert(keyword);
	window.location.href="servlet?id="+keyword;
}



/******************************************************************************
 ****** keywordComparitor*********
 ******************************************************************************/

function kwComparitorEliminator(){
	$('#whole1').empty()
			.removeAttr("data-highcharts-chart");
}

function kwComparitorGenerator(){
	var yeardata=[];  
	var words=[];
	
	var topwordlist = gdata.list[tc_idx].map.full.list;
	
	for(var i=0; i<8; i++) {
		var word = topwordlist[i].text;
		words.push(word);
	}
	//alert(words);
	
	
	var date = [];
	if(gdata.category == "MHSL") {
		date.push("308-312"); date.push("313-317"); date.push("318-322"); date.push("323-327");
		date.push("328-401"); date.push("402-406"); date.push("407-411"); date.push("412-416");
	}
	if(gdata.category == "LH") {
		date.push("2007"); date.push("2008"); date.push("2009"); date.push("2010");
		date.push("2011"); date.push("2012"); date.push("2013"); date.push("2014");
	}
	if(gdata.category == "NIPS") {
		date.push("2003"); date.push("2004"); date.push("2005"); date.push("2006");
		date.push("2007"); date.push("2008"); date.push("2009"); date.push("2010");
	}
	
	for(var i=0; i<8; i++) {
		var flag = false;
		var datename = "y"+date[i];
		for(var name in gdata.list[tc_idx].map) {
			if(datename == name) {
				var wordlist = gdata.list[tc_idx].map[datename];
				var d0=0, d1=0, d2=0, d3=0, d4=0, d5=0, d6=0, d7=0;
				for(var j=0;j<wordlist.num; j++){
					if(wordlist.list[j].text==words[0]) {
						d0 = wordlist.list[j].size;
					}else if(wordlist.list[j].text==words[1]) {
						d1 = wordlist.list[j].size;
					}else if(wordlist.list[j].text==words[2]) {
						d2 = wordlist.list[j].size;
					}else if(wordlist.list[j].text==words[3]) {
						d3 = wordlist.list[j].size;
					}else if(wordlist.list[j].text==words[4]) {
						d4 = wordlist.list[j].size;
					}else if(wordlist.list[j].text==words[5]) {
						d5 = wordlist.list[j].size;
					}else if(wordlist.list[j].text==words[6]) {
						d6 = wordlist.list[j].size;
					}else if(wordlist.list[j].text==words[7]) {
						d7 = wordlist.list[j].size;
					}
				}
				var data = [];
				data.push(d0);data.push(d1);data.push(d2);data.push(d3);data.push(d4);data.push(d5);data.push(d6);data.push(d7);
				//alert(data);
				yeardata.push({ "name":date[i] , "data": data});
				flag = true;
				break;
			}
			if(flag) break;
		}
	}
	
	$('#whole1').highcharts({
	            
	    chart: {
	        polar: true,
	        type: 'line',
	        width: 370,
	        backgroundColor: '#FFF0F5'
	    },   
	    title: {
	        text: 'KeyWords Comparison'
	    },
	    credits: {
	        enabled: false
	    },           
	    xAxis: {
	        categories: words,
	        tickmarkPlacement: 'on',
	        lineWidth: 0,
	        labels: {
	        	style: {
					color: '#6D869F',
					fontWeight: 'bold'
				}
	        }
	    },      
	    yAxis: {
	        gridLineInterpolation: 'polygon',
	        lineWidth: 0,
	        min: 0
	    },
	    tooltip: {
	    	shared: true,
	        pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y:,.0f}</b><br/>'
	    },
	    legend: {
	        align: 'center',
	        layout: 'horizontal'
	    },
	    series: yeardata
	});
};

function kwComparitorGenerator2(){
	var yeardata=[];  
	var words=[];
	
	var topwordlist = gdatapath.list[tc_idx].map.full.list;
	
	for(var i=0; i<8; i++) {
		var word = topwordlist[i].text;
		words.push(word);
	}
	
	var date = [];
	date.push("2007"); date.push("2008"); date.push("2009"); date.push("2010");
	date.push("2011"); date.push("2012"); date.push("2013"); date.push("2014");

	for(var i=0; i<8; i++) {
		var flag = false;
		var datename = "y"+date[i];
		for(var name in gdatapath.list[tc_idx].map) {
			if(datename == name) {
				var wordlist = gdatapath.list[tc_idx].map[datename];
				var d0=0, d1=0, d2=0, d3=0, d4=0, d5=0, d6=0, d7=0;
				for(var j=0;j<wordlist.num; j++){
					if(wordlist.list[j].text==words[0]) {
						d0 = wordlist.list[j].size;
					}else if(wordlist.list[j].text==words[1]) {
						d1 = wordlist.list[j].size;
					}else if(wordlist.list[j].text==words[2]) {
						d2 = wordlist.list[j].size;
					}else if(wordlist.list[j].text==words[3]) {
						d3 = wordlist.list[j].size;
					}else if(wordlist.list[j].text==words[4]) {
						d4 = wordlist.list[j].size;
					}else if(wordlist.list[j].text==words[5]) {
						d5 = wordlist.list[j].size;
					}else if(wordlist.list[j].text==words[6]) {
						d6 = wordlist.list[j].size;
					}else if(wordlist.list[j].text==words[7]) {
						d7 = wordlist.list[j].size;
					}
				}
				var data = [];
				data.push(d0);data.push(d1);data.push(d2);data.push(d3);data.push(d4);data.push(d5);data.push(d6);data.push(d7);
				//alert(data);
				yeardata.push({ "name":date[i] , "data": data});
				flag = true;
				break;
			}
			if(flag) break;
		}
	}
	
	$('#whole1').highcharts({
	            
	    chart: {
	        polar: true,
	        type: 'line',
	        width: 370,
	        backgroundColor: '#FFF0F5'
	    },   
	    title: {
	        text: 'KeyWords Comparison'
	    },
	    credits: {
	        enabled: false
	    },           
	    xAxis: {
	        categories: words,
	        tickmarkPlacement: 'on',
	        lineWidth: 0,
	        labels: {
	        	style: {
					color: '#6D869F',
					fontWeight: 'bold'
				}
	        }
	    },      
	    yAxis: {
	        gridLineInterpolation: 'polygon',
	        lineWidth: 0,
	        min: 0
	    },
	    tooltip: {
	    	shared: true,
	        pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y:,.0f}</b><br/>'
	    },
	    legend: {
	        align: 'center',
	        layout: 'horizontal'
	    },
	    series: yeardata
	});
};
