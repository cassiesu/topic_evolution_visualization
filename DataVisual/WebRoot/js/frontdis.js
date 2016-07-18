
var fresultJsonArray=[];  
for(var i=0; i<pathnum; i++){
	var path = gdata.list[i];
	//alert(gdata.list[i].tag);
	fresultJsonArray.push({ "name":path.tag , "data": path.weightperyear});
}
//alert(resultJsonArray);
//alert(tag2idx.法律);

  $(function () {
        $('#frontdis').highcharts({
            chart: {
                type: 'areaspline'
            },
            title: {
                text: 'Percent Evolution Graph'
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
                    stacking: 'percent',
                    fillOpacity: 0.7,
                    lineColor: '#FFFFFF',
                    lineWidth: 0,
                    marker: {                       
                        enabled: false
                    },
                    events: {
	                    click: function(event) {
	                    	var tag = this.name;
	                    	$("#alertbox .Subtitle").html("Detailed analysis changed to <strong style=\"font-color:#A500CC\">"+tag+"</strong> Area...");
	                    	$("#alertbox").fadeIn();
	                        //alert(tag +' clicked\nChange TagClouds!');
	                        //alert(tag2idx[tag]);		//json对象key如果是变量（动态）可以通过下标取到
	                    	tc_idx = tag2idx[tag];
	                    	tagcloudEliminator();
	                    	tagcloudGenerator();
	                    	kwComparitorEliminator();
                  			kwComparitorGenerator();		
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
            series: fresultJsonArray
        });
    });