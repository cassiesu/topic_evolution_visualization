var obresultJsonArray=[];  
if("whole"!=gdata.id) {
	for(var i=0; i<pathnum; i++){
		var path = gdata.list[i];
		//alert(gdata.list[i].tag);
		obresultJsonArray.push({ "name":path.tag , "data": path[wf]});
	}
}else{
	for(var i=0; i<gdatapath.list.length; i++){
		var path = gdatapath.list[i];
		//alert(gdata.list[i].tag);
		obresultJsonArray.push({ "name":path.tag , "data": path[wf]});			//但是全局path这里还没有tag暂时
	}
}

function changeOriSplitMethod(number){
	wf = "weightfull"+number;
	if("whole"!=gdata.id) {
		obresultJsonArray = obresultJsonArray.slice(pathnum);
		for(var i=0; i<pathnum; i++){
			var path = gdata.list[i];
			//alert(gdata.list[i].tag);
			obresultJsonArray.push({ "name":path.tag , "data": path[wf]});
		}
	}else{
		obresultJsonArray = obresultJsonArray.slice(gdatapath.list.length);
		for(var i=0; i<gdatapath.list.length; i++){
			var path = gdatapath.list[i];
			//alert(gdata.list[i].tag);
			obresultJsonArray.push({ "name":path.tag , "data": path[wf]});			//但是全局path这里还没有tag暂时
		}
	}

	oribackgraphEliminator();
	oribackgraphGenerator();	
}

function oribackgraphEliminator(){
	$('#oribackdis').empty()
			.removeAttr("data-highcharts-chart");
	$('#oribackdis').parent().addClass("opened").show();
	$('#orifrontdis').parent().hide();
	$('#frontdis').parent().hide();
	$('#backdis').parent().hide();
}

function oribackgraphGenerator(){
	//alert('backdis ok');
	$('#oribackdis').highcharts({
        chart: {
            type: 'area'
        },
        title: {
            text: 'Normal Evolution StreamGraph'
        },
        subtitle: {
            text: 'Source: Computed at Background'
        },
        credits: {
            enabled: false
        },
        xAxis: {
            categories: backdisdate,
            tickmarkPlacement: 'on',
            labels: { 
     			step:10	
            },
            title: {
                text: 'year'
            }
        },
        yAxis: {
            title: {
                text: 'weights'
            },
            labels: {
                formatter: function() {
                    return this.value;
                }
            }
        },
        legend : {
        	layout: 'vertical',
        	backgroundColor: '#FFFFFF',
       	 	align: 'right',
           	verticalAlign: 'middle'
        },
        tooltip: {
            shared: true,
            valueSuffix: ''
        },
        plotOptions: {
            area: {
                stacking: 'normal',
                fillOpacity: 0.7,
                lineColor: '#FFFFFF',
                lineWidth: 0,
                marker: {
                    enabled: false
                },
                events: {
                    click: function(event) {			//whole的时候不好用???
                    	var tag = this.name;
                    	$("#alertbox .Subtitle").html("Detailed analysis changed to <strong style=\"font-color:#A500CC\">"+tag+"</strong> Area...");
	                    $("#alertbox").fadeIn();
                        //alert(tag +' clicked\nChange TagClouds!');
                        //alert(tag2idx[tag]);		//json对象key如果是变量（动态）可以通过下标取到
                    	if("whole" == gdata.id){
                    		tc_idx = tag2idx1[tag];
                    		tagcloudEliminator();
                    		tagcloudGenerator2();
                    		kwComparitorEliminator();
                  			kwComparitorGenerator2();
                    	}else{
                    		tc_idx = tag2idx[tag];
                    		tagcloudEliminator();
                    		tagcloudGenerator();
                    		kwComparitorEliminator();
                  			kwComparitorGenerator();
                    	}		
                    },
                    legendItemClick: function(event) {
                        var visibility = this.visible ? 'visible' : 'hidden';
                        if (!confirm('The series is currently '+ 
                                     visibility +'. Do you want to change that?')) {
                            return false;
                        }
                    }
                }
            }
        },
        series: obresultJsonArray
    });
}


$(function () {
    oribackgraphGenerator();
});