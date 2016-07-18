// JavaScript Document
// Claudio Gomboli . the EGGS LAB
// Responsive animated gallery

//Test!
//alert("ok");			//如果不放到最后，则这句可以正常执行
//$('.title h2').html("ok");	//但是这句不可以，因为涉及到查找元素

$('.portfolio').each(function(index)
{
    $(this).attr('id', 'img' + (index + 1));
});
    
$('.portfolio').each(function(){
  $('#navi').append('<div class="circle"></div>');
});
  
$('.circle').each(function(index)
{
    $(this).attr('id', 'circle' + (index + 1));
});   
   
$('.portfolio').click(function(){
	//alert('click');
if($(this).hasClass('opened')){
    $(this).removeClass('opened');
    $(".portfolio").fadeIn("fast");
    $(this).find('.ombra').fadeOut();
    $("#navi div").removeClass("activenav");
}
else{
	var indexi = $("#maincontent .portfolio").index(this) + 1;
    $(this).addClass('opened'); 
    $(".portfolio").not(this).fadeOut("fast");
    $(this).find('.ombra').fadeIn();
    $("#circle" + indexi).addClass('activenav'); 
}
});	

//navi buttons
$("#navi div").click(function() {
if($(this).hasClass("activenav")){
	return false;
}
		
	$("#navi div").removeClass("activenav");
	$(".portfolio").removeClass('opened');
	$(".portfolio").show();
        $('.ombra').hide();
		
	var index = $("#navi div").index(this) + 1;
	$("#img" + index).addClass('opened'); 
    $(".portfolio").not("#img" + index).fadeOut("fast");
    $("#img" + index).find('.ombra').fadeIn();
        
    $(this).addClass("activenav");
});



/*********************************************************************************/
/* Alertbox                                                              */
/*********************************************************************************/

// Simple show/hide modal

function offbox(){
	$("#alertbox").fadeOut();
	//return false;
}


/*********************************************************************************/
/* ThreeChoice                                                              */
/*********************************************************************************/
$('#threechoice li').mouseenter(function(){
	var pos = $(this).position();
	$(this).find('div').css('top', (pos.top)+50 + 'px').fadeIn();
}).mouseleave(function(){
	$(this).find('div').fadeOut();
});

