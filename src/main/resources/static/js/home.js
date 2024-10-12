layui.use('element', function(){
    var $ = layui.jquery
        ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

    //触发事件
    var active = {
        tabAdd: function(){
            //新增一个Tab项
            element.tabAdd('demo', {
                title: '新选项'+ (Math.random()*1000|0) //用于演示
                ,content: '内容'+ (Math.random()*1000|0)
                ,id: new Date().getTime() //实际使用一般是规定好的id，这里以时间戳模拟下
            })
        }
        ,tabDelete: function(othis){
            //删除指定Tab项
            element.tabDelete('demo', '44'); //删除：“商品管理”


            othis.addClass('layui-btn-disabled');
        }
        ,tabChange: function(){
            element.tabChange('demo', '22'); //切换到：用户管理
        }
    };

    $('.site-demo-active').on('click', function(){
        var othis = $(this), type = othis.data('type');
        active[type] ? active[type].call(this, othis) : '';
    });

    //Hash地址的定位
    var layid = location.hash.replace(/^#test=/, '');
    element.tabChange('test', layid);

    element.on('tab(test)', function(elem){
        location.hash = 'test='+ $(this).attr('lay-id');
    });

});

layui.use(['carousel', 'form'], function () {
    var carousel = layui.carousel
        , form = layui.form;

    //Normal Image Carousel.
    carousel.render({
        elem: '#test1'
        , arrow: 'always'
    });

    //Set interval time, animation type,height
    carousel.render({
        elem: '#test2'
        , interval: 1800
        , anim: 'fade'
        , height: '120px'
    });

    //set parameter
    var ins3 = carousel.render({
        elem: '#test3'
    });
    //Image Carousel.
    carousel.render({
        elem: '#test10'
        , width: '90%'
        , height: '440px'
        , interval: 5000
    });

    //event
    carousel.on('change(test4)', function (res) {
        console.log(res)
    });

    var $ = layui.$, active = {
        set: function (othis) {
            var THIS = 'layui-bg-normal'
                , key = othis.data('key')
                , options = {};

            othis.css('background-color', '#5FB878').siblings().removeAttr('style');
            options[key] = othis.data('value');
            ins3.reload(options);
        }
    };

    //switch check
    form.on('switch(autoplay)', function () {
        ins3.reload({
            autoplay: this.checked
        });
    });

    $('.demoSet').on('keyup', function () {
        var value = this.value
            , options = {};
        if (!/^\d+$/.test(value)) return;

        options[this.name] = value;
        ins3.reload(options);
    });

    //other demo
    $('.demoTest .layui-btn').on('click', function () {
        var othis = $(this), type = othis.data('type');
        active[type] ? active[type].call(this, othis) : '';
    });
});


function HouseInfo(id, name, star) {
    this.id = id;
    this.name = name;
    this.star = star;
}

var house1 = new HouseInfo(1, 'house1', 4.5);
var house2 = new HouseInfo(2, 'house2', 4);
var house3 = new HouseInfo(1, 'house3', 3.5);
var house4 = new HouseInfo(2, 'house4', 3);

var houses = new Array()
houses[0] = house1;
houses[1] = house2;
houses[2] = house3;
houses[3] = house4;

//Rank
layui.use(['rate'], function () {
    var rate = layui.rate;
    for (var item in houses) {
        var houseName = houses[item].name;
        var HouseStart = houses[item].star;
        rate.render({
            elem: "#" + houseName
            , value: HouseStart
            , half: true
            , text: true
            , readonly: true
            , setText: function (value) {
                this.span.text(value);
            }
        })
    }
});