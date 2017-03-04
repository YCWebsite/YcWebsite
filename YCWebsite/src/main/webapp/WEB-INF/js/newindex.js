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
    $('#arrow_up').click(function() {
        $('html,body').animate({
            'scrollTop': 0
        }, 300)
    });

    $('#toggle_team span').mouseover(function() {
        if ($(this).index() == 0) {
            $(this).addClass('choice_team').siblings().removeClass('choice_team');
            $('#teacher_team').show();
            $('#manage_team').hide()
        } else {
            $(this).addClass('choice_team').siblings().removeClass('choice_team');
            $('#teacher_team').hide();
            $('#manage_team').show()
        }
    });
    $('#teacher_content ul li').hover(function(e) {
        var thisid = $(this).attr('id');
        var parentoffset = $('#teacher_content').offset();
        $(this).find('#' + thisid + '_intro').css({
            'left': (e.pageX - parentoffset.left) - 120,
            'top': (e.pageY - parentoffset.top) + 30
        }).fadeIn('normal')
    }, function(e) {
        var thisid = $(this).attr('id');
        $(this).find('#' + thisid + '_intro').hide()
    });
    $('#teacher_content ul li').mousemove(function(e) {
        var thisid = $(this).attr('id');
        var parentoffset = $('#teacher_content').offset();
        $(this).find('#' + thisid + '_intro').css({
            'left': (e.pageX - parentoffset.left) - 120,
            'top': (e.pageY - parentoffset.top) + 30
        })
    });
    $('#course_img').on('contextmenu', function() {
        return false
    });
    $('#project ul li').hover(function(e) {
        $('body').append($('<div id="showBigimg"></div>'));
        var imgname = $(this).find('img').attr('src').replace('.', '_b.');
        var getleft = (e.pageX - $(this).offset().left) / $(this).width();
        var gettop = (e.pageY - $(this).offset().top) / $(this).height();
        if (e.pageX < $(document).width() * 0.6) {
            $('#showBigimg').css({
                'left': (e.pageX + 10) + 'px',
                'top': (e.pageY + 10) + 'px',
                'background': 'url("' + imgname + '") no-repeat',
                'background-position': (getleft * 100) + '% ' + (gettop * 100) + '%'
            }).show('normal')
        } else {
            $('#showBigimg').css({
                'left': (e.pageX - 260) + 'px',
                'top': (e.pageY + 10) + 'px',
                'background': 'url("' + imgname + '") no-repeat',
                'background-position': (getleft * 100) + '% ' + (gettop * 100) + '%'
            }).show('normal')
        }
    }, function(e) {
        $('#showBigimg').remove()
    });
    $('#project ul li').mousemove(function(e) {
        var imgname = $(this).find('img').attr('src').replace('.', '_b.');
        var getleft = (e.pageX - $(this).offset().left) / $(this).width();
        var gettop = (e.pageY - $(this).offset().top) / $(this).height();
        if (e.pageX < $(document).width() * 0.6) {
            $('#showBigimg').css({
                'left': (e.pageX + 10) + 'px',
                'top': (e.pageY + 10) + 'px',
                'background': 'url("' + imgname + '") no-repeat',
                'background-position': (getleft * 100) + '% ' + (gettop * 100) + '%'
            })
        } else {
            $('#showBigimg').css({
                'left': (e.pageX - 260) + 'px',
                'top': (e.pageY + 10) + 'px',
                'background': 'url("' + imgname + '") no-repeat',
                'background-position': (getleft * 100) + '% ' + (gettop * 100) + '%'
            })
        }
    });
    $('#blog_content ul li').click(function() {
        var showname = $(this).attr('id') + '_show';
        $(this).addClass('current_article').siblings().removeClass('current_article');
        $('#blog_content div').hide();
        $('#' + showname).show()
    });
    var picTimer;
    var actwidth = $('#act_content ul li').length * $('#act_content ul li').innerWidth();
    $('#act_content ul')[0].innerHTML += $('#act_content ul').html();
    $('#act_content ul')[0].style.width = actwidth * 2 + 'px';
    $('#act_content ul').hover(function() {
        clearInterval(picTimer)
    }, function() {
        picTimer = setInterval(function() {
            $('#act_content ul')[0].style.left = $('#act_content ul')[0].offsetLeft - 5 + 'px';
            if ($('#act_content ul')[0].offsetLeft <= -actwidth) {
                $('#act_content ul')[0].style.left = 0
            }
        }, 50)
    }).trigger('mouseout');
    $('#course_content ul li').mouseover(function() {
        var imgsrc = $(this).find('span').text();
        $(this).addClass('course_current').siblings().removeClass('course_current');
        $('#course_img img')[0].src = 'images/' + imgsrc + '.png';
        $('#course_img img')[0].alt = imgsrc
    });
	
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