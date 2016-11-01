$(function(){
    var block=$('.block').eq(0);

    function changeColor(classsName,path){
        $(classsName).find('.content_img img').attr("src",path);
        $(classsName).find('.content_title').addClass('blue');
        $(classsName).find('.content_content').addClass('blue');
        $(classsName).find('.content_button').addClass('blue_back white');
    }
    
    $('.hover-effect').mouseover(function(){        
        if($(this).is('.content-1')){
            block.css({'transition':'all 1s ease 0s','left':'15%'})        
            changeColor(".content-1","img/home/20160923_13_a.png")       
        }
        else if($(this).is('.content-2')){
            block.css({'transition':'all 1s ease 0s','left':'48%'})
            changeColor(".content-2","img/home/20160923_16.png")
        }
        else if($(this).is('.content-3')){
            block.css({'transition':'all 1s ease 0s','left':'82%'})
            changeColor(".content-3","img/home/20160923_18_a.png")
        }        
    })
    
    $('.hover-effect').mouseout(function(){       
        block.css({'transition':'all 1s ease 0s','left':'48%'})             
        $('.content_title').removeClass('blue');
        $('.content_content').removeClass('blue');
        $('.content_button').removeClass('blue_back white');
        $('.content_img img').eq(0).attr("src","img/home/20160923_13.png");
        $('.content_img img').eq(1).attr("src","img/home/20160923_16_a.png");
        $('.content_img img').eq(2).attr("src","img/home/20160923_18.png");
    })

    $('.div2_content').mouseleave(function(){
        $('.content-2').find('.content_img img').attr("src","img/home/20160923_16.png");  
        $('.content-2').find('.content_title').addClass('blue');
        $('.content-2').find('.content_content').addClass('blue');
        $('.content-2').find('.content_button').addClass('blue_back white');
    })
})