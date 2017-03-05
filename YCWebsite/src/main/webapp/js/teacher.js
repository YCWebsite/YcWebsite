$(function() {
    $("img").lazyload({
        effect: "fadeIn",
        threshold: 200
    });
    if (window.innerWidth < 768) {
        $('<span id="shownav"></span>').appendTo($('body'));
        $('#shownav').click(function(e) {
            $('#nav').toggle()
        });
        $('#nav li').click(function() {
            $('#nav').hide();
            $('#shownav').show()
        })
    } else {
        $('#shownav').remove()
    };
    window.onscroll = function() {
        var t = document.documentElement.scrollTop || document.body.scrollTop;
        if (t > 500) {
            $('#arrow_up').css("visibility","visible");

        } else {
            $('#arrow_up').css("visibility","hidden");

        }
    };


    function foo(){
        var h1 = document.getElementsByClassName("h1")[0];

        var as = document.getElementById("nav").getElementsByTagName("li");
        for(var i=0;i<as.length;i++){
            as[i].onmouseover = function(){

                if(this.className == "li1"){
                    h1.style.marginLeft = "0%";
                }else if(this.className == "li2"){
                    h1.style.marginLeft = "12%";
                }else if(this.className == "li3"){
                    h1.style.marginLeft = "24%";
                }else if(this.className == "li4"){
                    h1.style.marginLeft = "36%";
                }else if(this.className == "li5"){
                    h1.style.marginLeft = "48%";
                }else if(this.className == "li6"){
                    h1.style.marginLeft = "60%";
                }else if(this.className == "li7"){
                    h1.style.marginLeft = "72%";
                }

            }

            as[i].onmouseout = function(){
                h1.style.marginLeft = "0px";
                h1.style.transition = "0.4s all";
            }

        }
    }
    foo();
});