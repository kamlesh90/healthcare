$(document).ready(function() {
    alert("xcbmzcbzm");
    //1. hide error section
        $("#pwd1Error").hide();
        $("#pwd2Error").hide();

    //2. define error varibles
        var pwd1Error=false;
        var pwd2Error=false;

    //3. define validate function
        function pwd1_validate() {
            //read pwd1 val
            var val = $("#pwd1").val();
            //define expression
            var exp = /^[A-Za-z0-9\s\.\-\_\@]{6,20}$/;
            //check if the pwd1 empty
            if(val==''){
                //show error
                $("#pwd1Error").show();
                //show in bold and red
                $("#pwd1Error").html("<b>*Password</b> must not be empty !!");
                $("#pwd1Error").css("color","red");

                pwd1Error=false;
            }else if(!exp.test(val)){
                $("#pwd1Error").show();
                $("#pwd1Error").html("Password must be 6-20 including [A-Za-z0-9\s\.\-\_\@]");
                $("#pwd1Error").css("color","red");

                pwd1Error=false;
            }else{
                $("#pwd1Error").hide();
                pwd1Error=true;
            }

            return pwd1Error;
        }

        function pwd2_validate() {
            //read pwd1 & pwd2 val
            var p1 = $("#pwd1").val();
            var p2 = $("#pwd2").val();

            //check if the pwd1 empty
            if(p1 != p2){
                //show error
                $("#pwd2Error").show();
                //show in bold and red
                $("#pwd2Error").html("<b>*Password not matching</b>!!");
                $("#pwd2Error").css("color","red");

                pwd2Error=false;
            }else{
                $("#pwd2Error").hide();
                pwd2Error=true;
            }

            return pwd2Error;
        }

    //4. link validate function with action event 
        $("#pwd1").keyup(function (){
           
           pwd1_validate();
        });

        $("#pwd2").keyup(function (){
            pwd2_validate();
        });

    //5. on submit
        $("#userPwdUpdateId").submit(function() {
            pwd1_validate();
            pwd2_validate();

            if(pwd1Error && pwd2Error) 
                return true;
            else
                return false;

        });
});