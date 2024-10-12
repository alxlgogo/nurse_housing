/*The average life of male - Table*/
layui.use(['laypage', 'layer'], function () {
    var laypage = layui.laypage
        , layer = layui.layer;
    var jsonStr = $("#users").attr("value");
    var jsonArray = jQuery.parseJSON(jsonStr);

    for (let i = 0, len = jsonArray.length; i < len; i++) {
        let jsonArrayElement = jsonArray[i];
        if (jsonArrayElement != undefined) {
            let gender = jsonArrayElement.gender;
            if (gender != "male") {
                jsonArray.splice(i, 1);
                i--;
                console.log();
            }
            console.log();
        }
    }

    laypage.render({
        elem: 'demo20'
        , count: jsonArray.length
        , prev: '<em>←</em>'
        , next: '<em>→</em>'
        , jump: function (obj) {
            document.getElementById('person_info_list1').innerHTML = function () {
                var arr = []
                    , thisData = jsonArray.slice().splice(obj.curr * obj.limit - obj.limit, obj.limit);
                while (obj.curr < (jsonArray.length / 10 + 1)) {
                    layui.each(thisData, function (index, item) {

                        var checkinTimeYear;
                        if (item.checkinTime != null) {
                            var checkinTimeS = item.checkinTime.time;
                            var checkinTimeDate = new Date(checkinTimeS);
                            checkinTimeYear = checkinTimeDate.getFullYear();
                        }


                        var checkoutTimeYear;
                        if (item.checkoutTime != null) {
                            var checkinTimeS = item.checkoutTime.time;
                            var checkoutTimeDate = new Date(checkinTimeS);
                            checkoutTimeYear = checkoutTimeDate.getFullYear();
                        }

                        var birthdayYear = new Date(item.birthday);
                        var birthday = birthdayYear.getFullYear();
                        let age = checkoutTimeYear - birthday;
                        let gender = item.gender;
                        arr.push('<tr>');
                        arr.push('<td>' + item.id + '</td>');
                        arr.push('<td>' + birthday + '</td>');
                        // arr.push('<td>' + (checkoutTimeYear - checkinTimeYear) + '</td>');
                        arr.push('<td>' + age + '</td>');
                        // arr.push('<td>' + checkinTimeYear + '</td>');
                        arr.push('<td>' + checkoutTimeYear + '</td>');
                        arr.push('<td>' + gender + '</td>');
                        arr.push('</tr>');
                    });
                    return arr.join('');
                }
            }();
        }
    });
});

/*The average life of female - Table*/
layui.use(['laypage', 'layer'], function () {
    var laypage = layui.laypage
        , layer = layui.layer;
    var jsonStr = $("#users").attr("value");
    var jsonArray = jQuery.parseJSON(jsonStr);

    for (let i = 0, len = jsonArray.length; i < len; i++) {
        let jsonArrayElement = jsonArray[i];
        if (jsonArrayElement != undefined) {
            let gender = jsonArrayElement.gender;
            if (gender != "female") {
                jsonArray.splice(i, 1);
                i--;
                console.log();
            }
            console.log();
        }
    }

    laypage.render({
        elem: 'demo202'
        , count: jsonArray.length
        , prev: '<em>←</em>'
        , next: '<em>→</em>'
        , jump: function (obj) {
            document.getElementById('person_info_list2').innerHTML = function () {
                var arr = []
                    , thisData = jsonArray.slice().splice(obj.curr * obj.limit - obj.limit, obj.limit);
                while (obj.curr < (jsonArray.length / 10 + 1)) {
                    layui.each(thisData, function (index, item) {

                        var checkinTimeYear;
                        if (item.checkinTime != null) {
                            var checkinTimeS = item.checkinTime.time;
                            var checkinTimeDate = new Date(checkinTimeS);
                            checkinTimeYear = checkinTimeDate.getFullYear();
                        }


                        var checkoutTimeYear;
                        if (item.checkoutTime != null) {
                            var checkinTimeS = item.checkoutTime.time;
                            var checkoutTimeDate = new Date(checkinTimeS);
                            checkoutTimeYear = checkoutTimeDate.getFullYear();
                        }

                        var birthdayYear = new Date(item.birthday);
                        var birthday = birthdayYear.getFullYear();
                        let age = checkoutTimeYear - birthday;
                        let gender = item.gender;
                        arr.push('<tr>');
                        arr.push('<td>' + item.id + '</td>');
                        arr.push('<td>' + birthday + '</td>');
                        // arr.push('<td>' + (checkoutTimeYear - checkinTimeYear) + '</td>');
                        arr.push('<td>' + age + '</td>');
                        // arr.push('<td>' + checkinTimeYear + '</td>');
                        arr.push('<td>' + checkoutTimeYear + '</td>');
                        arr.push('<td>' + gender + '</td>');
                        arr.push('</tr>');
                    });
                    return arr.join('');
                }
            }();
        }
    });
});


layui.use('form', function () {
    let form = layui.form;
    form.render();
    form.on('submit(maleExpectancyAge)', function (data) {
        let expectancyAge = $("#maleExpectancyAge").val();
        let postData = {
            gender: "male",
            expectancyAge: expectancyAge
        }
        if (expectancyAge !== null && expectancyAge !== "") {
            $.ajax({
                url: "/expectancyAge"
                , data: postData
                , type: "post"
                , success: function (data) {
                    console.log(data);
                    $("#targetYear").val(data.year);
                }
            })
        } else {
            layer.open({
                title: 'Notice'
                , content: "Please Input An Expectancy Age"
            });
            return false;
        }
        return false;
    })
    return false;
});


layui.use('form', function () {
    let form = layui.form;
    form.render();
    form.on('submit(femaleExpectancyAge)', function (data) {
        let expectancyAge = $("#femaleExpectancyAge").val();
        let postData = {
            gender: "female",
            expectancyAge: expectancyAge
        }
        if (expectancyAge !== null && expectancyAge !== "") {
            $.ajax({
                url: "/expectancyAge"
                , data: postData
                , type: "post"
                , success: function (data) {
                    console.log(data);
                    $("#targetYear2").val(data.year);
                }
            })
        } else {
            layer.open({
                title: 'Notice'
                , content: "Please Input An Expectancy Age"
            });
            return false;
        }
        return false;
    })
    return false;
});


$(document).ready(function () {
    // $("#showChart1").click();
    // <button type="button" onclick="showChart1()" class="layui-btn" lay-submit="" lay-filter="showChart1">Show AvgLife_Year(male)</button>
});

function showChart1() {
    let postData = {
        gender: "male"
    }
    $.ajax({
        type: 'post',
        url: '/generateAvgLifeYear',
        data: {},
        cache: false,
        dataType: 'json',
        data: postData,
        success: function (data) {
            var fileName = data.fileName;
            $("#AvgLife_Year_male").attr("src", "http://localhost:8000/" + fileName);
            console.log(data);
        },
        error: function () {
            console.log();
        }
    });
}


function showChart2() {
    var postData = {
        gender: "female"
    }

    $.ajax({
        type: 'post',
        url: '/generateAvgLifeYear',
        data: {},
        cache: false,
        dataType: 'json',
        data: postData,
        success: function (data) {
            var fileName = data.fileName;
            $("#AvgLife_Year_female").attr("src", "http://localhost:8000/" + fileName);
            console.log(data);
        },
        error: function () {
            console.log();
        }
    });
}


layui.use(['layer', 'jquery', 'form'], function () {
    let layer = layui.layer,
        $ = layui.jquery,
        form = layui.form;
    form.on('select(MaleLifeExpectancy)', function (data) {
        let year = data.value;
        let postData = {
            year: data.value,
            gender: "male"
        }
        if (year !== null && year !== "") {
            $.ajax({
                url: "/generateLifeExpectancy"
                , dataType: "json"
                , data: postData
                , type: "post"
                , success: function (data) {
                    var fileName = data.fileName;
                    $("#MaleLifeExpectancyIMG").attr("src", "http://localhost:8000/" + fileName);
                    console.log(fileName);
                }
            })
        } else {
            layer.open({
                title: 'Notice'
                , content: "Please select an year range"
            });
            return false;
        }
        return false;
    });

    form.on('select(FemaleLifeExpectancy)', function (data) {
        let year = data.value;
        let postData = {
            year: data.value,
            gender: "female"
        }
        if (year !== null && year !== "") {
            $.ajax({
                url: "/generateLifeExpectancy"
                , dataType: "json"
                , data: postData
                , type: "post"
                , success: function (data) {
                    let fileName = data.fileName;
                    $("#FemaleLifeExpectancyIMG").attr("src", "http://localhost:8000/" + fileName);
                    console.log(fileName);
                }
            })
        } else {
            layer.open({
                title: 'Notice'
                , content: "Please select an year range"
            });
            return false;
        }
        return false;
    });
});

function increaseMaleAge() {
    $.ajax({
        type: 'get',
        url: '/updateMaleCheckoutTime?gender=male&action=increase',
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function () {
            console.log();
        }
    });
}

function decreaseMaleAge() {
    $.ajax({
        type: 'get',
        url: '/updateMaleCheckoutTime?gender=male&action=rest',
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function () {
            console.log();
        }
    });
}

function increaseFemaleAge() {
    $.ajax({
        type: 'get',
        url: '/updateFemaleCheckoutTime?gender=female&action=increase',
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function () {
            console.log();
        }
    });
}

function decreaseFemaleAge() {
    $.ajax({
        type: 'get',
        url: '/updateFemaleCheckoutTime?gender=female&action=rest',
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function () {
            console.log();
        }
    });
}