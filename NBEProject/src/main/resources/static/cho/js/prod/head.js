$(function(){
    $('#recent').click(function (){
        location.href = 'http://localhost:8080/nbe/recent'
    })

    $('.home').click(function(){
        location.href = 'http://localhost:8080/nbe/home';
    })

});

function recent(){
    location.href = 'http://localhost:8080/nbe/recent';
}

function goHome(){
    location.href = 'http://localhost:8080/nbe/home';
}

function category(){
    $('.category1').toggleClass('hidden');
}