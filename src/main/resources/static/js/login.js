
function login() {
    const username = $("#username").val();
    const password = $("#password").val();
    const usernameIsnotNull = username != null && username != "";
    const passwordIsnotNull = password != null && password != "";

    if (usernameIsnotNull && passwordIsnotNull) {
        $("#label_input_hidden2").css("display","none");
        $("#label_input_hidden1").css("display","none");
        $("#login_form").submit();
    } else {
        if (!usernameIsnotNull) {
            $("#label_input_hidden1").css("display","block");
            $("#label_input_hidden1").html("username can't be null");
        }
        if(!passwordIsnotNull){
            $("#label_input_hidden2").css("display","block");
            $(" #label_input_hidden2").html("password can't be null");
        }
    }
}



function register() {
    $("#register").submit();
}
