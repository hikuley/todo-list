/**
 * Created by hikuley on 30/08/16.
 */
$(document).ready(function () {

    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };


    $.toogleLogin = function () {
        var name = $(".toggle span").text() == "Login" ? "Register" : "Login";
        $(".toggle span").text(name);
        // Switches the forms
        $('.form').animate({
            height: "toggle",
            'padding-top': 'toggle',
            'padding-bottom': 'toggle',
            opacity: "toggle"
        }, "slow");
    }

    $.login = function () {
        var request = $(".loginForm").serializeObject();

        var form = $(".loginForm");
        var validator = form.validate({
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                }
            }
        });
        if (form.valid()) {
            var settings = {
                "url": "/user/login",
                "method": "POST",
                "headers": {
                    "content-type": "application/json"
                },
                "data": JSON.stringify(request)
            }
            $.ajax(settings).done(function (response) {
                if (response.status == "OK") {
                    window.location = "/";
                }
                else if (response.status == "FOUND") {
                    $("#login-error").show().text(response.message);
                }
                else if (response.status == "BAD_REQUEST") {
                    var errors = response.fieldErrors;
                    var errorArray = {};
                    for (var i = 0; i < errors.length; i++) {
                        var field = errors[i]["field"];
                        var defaultMessage = errors[i]["defaultMessage"];
                        errorArray[field] = defaultMessage;
                    }
                    validator.showErrors(errorArray)
                }
            });
        }


    }

    $.register = function () {
        var request = $(".registerForm").serializeObject();

        var form = $(".registerForm");
        form.validate({
            rules: {
                name: {
                    required: true
                },
                surname: {
                    required: true
                },
                email: {
                    required: true
                },
                username: {
                    required: true
                },
                password: {
                    required: true
                }
            }
        });
        if (form.valid()) {
            var settings = {
                "url": "/user/create",
                "method": "POST",
                "headers": {
                    "content-type": "application/json"
                },
                "data": JSON.stringify(request)
            }

            $.ajax(settings).done(function (response) {
                console.log(response);
                if (response.status == "OK") {
                    $("#register-info").show().text(response.message);
                    $.toogleLogin();
                }
                else if (response.status == "FOUND") {
                    $("#register-error").show().text(response.message);
                }
                else if (response.status == "BAD_REQUEST") {
                    var errors = response.fieldErrors;
                    var errorArray = {};
                    for (var i = 0; i < errors.length; i++) {
                        var field = errors[i]["field"];
                        var defaultMessage = errors[i]["defaultMessage"];
                        errorArray[field] = defaultMessage;
                    }
                    validator.showErrors(errorArray)
                }
            });
        }
    }

    $.logout = function () {
        var settings = {
            "url": "/user/logout",
            "method": "GET",
            "headers": {
                "content-type": "application/json",
            }
        }

        $.ajax(settings).done(function (response) {
            if (response.status == "OK") {
                window.location = "/login";
            }
        });
    }

    $.todoFormPost = function () {

        var request = $("#todoForm").serializeObject();

        var form = $("#todoForm");
        var validator = form.validate({
            rules: {
                name: {
                    required: true
                },
                content: {
                    required: true
                },
                deadline: {
                    required: true
                }
            }
        });
        if (form.valid()) {
            var settings = {
                "url": "/todo/create",
                "method": "POST",
                "headers": {
                    "content-type": "application/json"
                },
                "data": JSON.stringify(request)
            }
            $.ajax(settings).done(function (response) {
                if (response.status == "OK") {
                    //append to todo box
                    var item = '<li class="ui-state-default">' + response.data.name + '</li>'
                    $("#TODO").append(item);
                    newTodoModal.close();
                    form[0].reset();
                    window.location = "/";
                }
                else if (response.status == "BAD_REQUEST") {
                    var errors = response.fieldErrors;
                    var errorArray = {};
                    for (var i = 0; i < errors.length; i++) {
                        var field = errors[i]["field"];
                        var defaultMessage = errors[i]["defaultMessage"];
                        errorArray[field] = defaultMessage;
                    }
                    validator.showErrors(errorArray)
                }
            });
        }

    }

    $(".delete-item").click(function () {
        var id = $(this).attr("data-todo-id");
        var item = $(this).parent("li");
        var settings = {
            "url": "/todo/delete/" + id,
            "method": "DELETE",
            "headers": {
                "content-type": "application/json"
            }
        }
        $.ajax(settings).done(function (response) {
            if (response.status == "OK") {
                item.hide();
            }
        });

    });

    $(".update-item").click(function () {
        var form = $("#todoForm");


        newTodoModal.open();
    });


});