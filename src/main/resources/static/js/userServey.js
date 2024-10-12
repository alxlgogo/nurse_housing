layui.use(['form', 'layedit', 'laydate'], function () {
    var form = layui.form
        , layer = layui.layer
        , layedit = layui.layedit
        , laydate = layui.laydate;

    //date
    laydate.render({
        elem: '#date'
    });
    laydate.render({
        elem: '#date1'
    });

    //create an editer
    var editIndex = layedit.build('LAY_demo_editor');

    //validate
    form.verify({
        title: function (value) {
            if (value.length < 5) {
                return 'At least five characters.';
            }
        }
        , pass: [
            /^[\S]{6,12}$/
            , '6-12 password , cant be null'
        ]
        , content: function (value) {
            layedit.sync(editIndex);
        }
    });

    //监听指定开关
    form.on('switch(switchTest)', function (data) {
        layer.msg('switch checked：' + (this.checked ? 'true' : 'false'), {
            offset: '6px'
        });
        layer.tips('tips', data.othis)
    });

    //listen submit
    form.on('submit(demo1)', function (data) {
        layer.alert(JSON.stringify(data.field), {
            title: 'submit final info'
        })
        return false;
    });

    //set value to form
    layui.$('#LAY-component-form-setval').on('click', function () {
        form.val('example', {
            "username": "tom" // "name": "value"
            , "password": "123456"
            , "interest": 1
            , "like[write]": true //checkbox is selected
            , "close": true //
            , "sex": "male"
            , "desc": ""
        });
    });

    //get value from form
    layui.$('#LAY-component-form-getval').on('click', function () {
        var data = form.val('example');
        alert(JSON.stringify(data));
    });


    //

    form.on('submit(userSurvey)', function (data) {
        //get value of checkbox[name='hobby']
        var arrHobby = new Array();
        $("input:checkbox[name='hobby']:checked").each(function (i) {
            arrHobby[i] = $(this).val();
        });
        data.field.hobby = arrHobby.join(",");//make Array to String


        //get value of checkbox[name='consideration']
        var arrConsideration = new Array();
        $("input:checkbox[name='consideration']:checked").each(function (i) {
            arrConsideration[i] = $(this).val();
        });
        data.field.consideration = arrConsideration.join(",");//make Array to String

        var newStr="{\"gender\": \"male\",\"age\": \"71-75\",\"price\": \"1001-1500\",\"hobby\": \"Gardening,Gaming\",\"consideration\": \"condition,Exercise_equipment\"}";
        console.log(data.field);
        $.post("/userSurvey", data.field, function (res) {
            if (res.code == 1) {
                layer.msg(res.msg, {time: 1800, icon: 1}, function () {
                    location.href = res.url;
                });
            } else {
                layer.msg(res.msg, {time: 1800, icon: 2});
            }

        }, 'json');
        return false;
    });


});