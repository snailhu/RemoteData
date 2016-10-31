$(function(){
    $('.hover-effect').mouseover(function(){      
        if($(this).is('.content-1')){
            $('.block').eq(0).css({'transition':'all 1s ease 0s',
                'left':'15%',})
        }
        else if($(this).is('.content-2')){
            $('.block').eq(0).css({'transition':'all 1s ease 0s',
                'left':'48%',})
        }
        else if($(this).is('.content-3')){
            $('.block').eq(0).css({'transition':'all 1s ease 0s',
                'left':'82%',})
        }        
    })
    
    $('.hover-effect').mouseout(function(){
        $('.block').eq(0).css({'transition':'all 1s ease 0s',
                'left':'48%',})
    })
})