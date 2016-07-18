$('.drag').draggable({ 
    appendTo: 'body',
    zIndex: 2700,
     snap: true,
    helper: 'clone',
    stop:function(event, ui){
        alert("123");
        $('#timeline').data('timeline')._drawItems();
    }
});
            $('.drag').draggable({
                appendTo: 'body',
                zIndex: 2700,
                 snap: true,
                helper: 'clone',
                stop:function(event, ui){
                    $('#timeline').timeline('drawItems',event ,ui);
                }
            });
