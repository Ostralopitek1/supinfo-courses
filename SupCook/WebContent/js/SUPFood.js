// Fonction pour le register

function validate_mdp1(){
    var passMsg1 = $("#pass-msg-1");
    passMsg1.removeClass("alert-warning").removeClass("alert-danger").removeClass("alert-success").empty();
    var submit = $("#submit");
    submit.prop("disabled",true);

    var pwd = $("#pwd");
    if (pwd.val().length >= 8) {
        passMsg1.addClass("alert-success").append(" Votre mot de passe semble correcte");
    }

    if (pwd.val().length < 8) {
        passMsg1.addClass("alert-warning").append("<strong>Attention!</strong> Votre mot de passe est trop court");
        submit.prop("disabled",true);
    }
}

function img_height(){
    var ref = $('#ref_height').height();
    $('.recipe_index_img').height(ref);

}

function validate_mdp2(){
    var passMsg2 = $("#pass-msg-2");
    passMsg2.removeClass("alert-warning").removeClass("alert-danger").removeClass("alert-success").empty();
    var submit = $("#submit");
    submit.prop("disabled",true);

    var pwd = $("#pwd");
    var pwd2 = $("#pwd2");
    if (pwd.val() === pwd2.val()) {
        passMsg2.addClass("alert-success").append(" Vos mots de passes correspondent");
        submit.prop("disabled",false);
    }
    if (pwd.val() !== pwd2.val()) {
        passMsg2.addClass("alert-danger").append("<strong>Attention!</strong> Vos mots de passes ne correspondent pas");
    }
}

function Highlight(){
    var home = true;
    var loc = window.location.href; // returns the full URL
    if(/recipes/.test(loc) || /recipe/.test(loc)) {
        $('#header_recipes').addClass('active');
        home = false;
    }
    if(/register/.test(loc)) {
        $('#header_register').addClass('active');
        home = false;
    }
    if(/contact/.test(loc)) {
        $('#header_contact').addClass('active');
        home = false;
    }
    if(/login/.test(loc)) {
        $('#header_connect').addClass('active');
        home = false;
    }
    if(/profile/.test(loc)) {
        $('#header_account').addClass('active');
        home = false;
    }
    if (home === true){
        $('#header_home').addClass('active');
    }
}

function Marks(recipeAvg, recipeId){

    if(recipeAvg === 0){return;}
    if(recipeAvg === 1){
        $("#star1" + recipeId).addClass('rating_active');
    }
    if(recipeAvg === 2){
        $("#star1" + recipeId).addClass('rating_active');
        $("#star2" + recipeId).addClass('rating_active');
    }
    if(recipeAvg === 3){
        $("#star1" + recipeId).addClass('rating_active');
        $("#star2" + recipeId).addClass('rating_active');
        $("#star3" + recipeId).addClass('rating_active');
    }
    if(recipeAvg === 4){
        $("#star1" + recipeId).addClass('rating_active');
        $("#star2" + recipeId).addClass('rating_active');
        $("#star3" + recipeId).addClass('rating_active');
        $("#star4" + recipeId).addClass('rating_active');
    }
    if(recipeAvg === 5){
        $("#star1" + recipeId).addClass('rating_active');
        $("#star2" + recipeId).addClass('rating_active');
        $("#star3" + recipeId).addClass('rating_active');
        $("#star4" + recipeId).addClass('rating_active');
        $("#star5" + recipeId).addClass('rating_active');
    }
}
