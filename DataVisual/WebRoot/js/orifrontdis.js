var ofresultJsonArray=[];  
if("whole"!=gdata.id) {
	for(var i=0; i<pathnum; i++){
		var path = gdata.list[i];
		//alert(gdata.list[i].tag);
		ofresultJsonArray.push({ "name":path.tag , "data": path.weightperyear});
	}
}else{
	for(var i=0; i<gdatapath.list.length; i++){
		var path = gdatapath.list[i];
		//alert(gdata.list[i].tag);
		ofresultJsonArray.push({ "name":path.tag , "data": path.weightperyear});			//但是全局path这里还没有tag暂时
	}
}

//alert(resultJsonArray);
//alert(tag2idx.法律);

  $(function () {
        $('#orifrontdis').highcharts({
            chart: {
                type: 'areaspline'
            },
            title: {
                text: 'Normal Evolution Graph'
            },
            subtitle: {
                text: 'Source: Dealt with at Frontground'
            },
            credits: {
                enabled: false
            },
            xAxis: {
                categories: disdate,
                tickmarkPlacement: 'on',
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
            tooltip: {
                shared: true,
                valueSuffix: ''
            },
            legend : {
            	layout: 'vertical',
            	backgroundColor: '#FFFFFF',
           	 	align: 'right',
           	 	verticalAlign: 'middle'
            },
            plotOptions: {
                areaspline: {
                    stacking: 'normal',
                    fillOpacity: 0.7,
                    lineColor: '#FFFFFF',
                    lineWidth: 0,
                    marker: {                       
                        enabled: false
                    },
                    events: {
	                    click: function(event) {		//whole的时候不好用???
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
            series: ofresultJsonArray
        });
    });