var wf = "weightfull1";
var bresultJsonArray=[];  
for(var i=0; i<pathnum; i++){
	var path = gdata.list[i];
	//alert(gdata.list[i].tag);
	bresultJsonArray.push({ "name":path.tag , "data": path[wf]});
}

function changeSplitMethod(number){
	wf = "weightfull"+number;
	bresultJsonArray = bresultJsonArray.slice(pathnum);
	//alert(wf);
	for(var i=0; i<pathnum; i++){
		var path = gdata.list[i];
		//alert(gdata.list[i].tag);
		bresultJsonArray.push({ "name":path.tag , "data": path[wf]});
	}
	
	backgraphEliminator();
	backgraphGenerator();
	
}

function backgraphEliminator(){
	$('#backdis').empty()
			.removeAttr("data-highcharts-chart");
	$('#backdis').parent().addClass("opened").show();
	$('#frontdis').parent().hide();
	$('#orifrontdis').parent().hide();
	$('#oribackdis').parent().hide();
}

function backgraphGenerator(){
	//alert('backdis ok');
	$('#backdis').highcharts({
        chart: {
            type: 'area'
        },
        title: {
            text: 'Percent Evolution StreamGraph'
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
            area: {
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
        series: bresultJsonArray
    });
}


$(function () {
    backgraphGenerator();
});