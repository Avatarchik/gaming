function getPayline(paylineNumber) {
    var paylines =
        [[1, 1, 1,  1,  1],
         [2, 2, 2,  2,  2],
         [3, 3, 3,  3,  3],
         [4, 4, 4,  4,  4],
         [1, 2, 3,  2,  1],
         [4, 3, 2,  3,  4],
         [2, 3, 4,  3,  2],
         [3, 2, 1,  2,  3],
         [1, 2, 1,  2,  1],
         [2, 3, 2,  3,  2],
         [3, 4, 3,  4,  3],
         [2, 1, 2,  1,  2],
         [3, 2, 3,  2,  3],
         [4, 3, 4,  3,  4],
         [1, 1, 2,  3,  3],
         [4, 4, 3,  2,  2],
         [2, 2, 3,  4,  4],
         [3, 3, 2,  1,  1],
         [1, 4, 1,  4,  1],
         [4, 4, 4,  1,  4],
         [1, 3, 1,  3,  1],
         [4, 2, 4,  2,  4],
         [2, 4, 2,  4,  2],
         [3, 1, 3,  1,  3],
         [1, 1, 2,  1,  1],
         [4, 4, 3,  4,  4],
         [2, 2, 3,  2,  2],
         [3, 3, 2,  3,  3],
         [3, 3, 4,  3,  3],
         [2, 2, 1,  2,  2],
         [1, 3, 4,  3,  1],
         [4, 2, 1,  2,  4],
         [1, 2, 4,  2,  1],
         [4, 3, 1,  3,  4],
         [1, 1, 3,  1,  1],
         [4, 4, 2,  4,  4],
         [2, 2, 4,  2,  2],
         [3, 3, 1,  3,  3],
         [1, 1, 4,  1,  1],
         [4, 4, 1,  4,  4]];

    return paylines[paylineNumber-1];
}

function selectPayline(spinId, paylineNumber) {
    var positions = paylineToPositions(getPayline(paylineNumber));
    return generateSelector(spinId, positions);
}

function selectAllButPayline(spinId, paylineNumber) {
    var positions = selectAllButPositions(paylineToPositions(getPayline(paylineNumber)));
    return generateSelector(spinId, positions);
}

function selectScatter(spinId, positionsStr) {
    var positions = convertStringToPosition(positionsStr);
    return generateSelector(spinId, positions);
}

function selectAllButPosition(spinId, positionsStr) {
    var positions = selectAllButPositions(convertStringToPosition(positionsStr));
    return generateSelector(spinId, positions);
}

function generateSelector(spinId, positions) {
    selectors = $();
    $.each(positions, function(index, value) {
        selectors = selectors.add("#spin_" + spinId + " .row" + value.y + ".col" + value.x);
    });
    return selectors;
}

function applyEffect(selector) {
    $(selector).fadeTo(0, 0.2, function(){});
    $(selector).addClass("selected");
}

function reverseEffect(selector) {
    $(selector).fadeTo(0, 1, function(){});
    $(selector).removeClass("selected");
}

function reverseEffectAll() {
    reverseEffect(".selected");
}

function showCoinswon(spinId, credits) {
    var canvasId = "#canvas_coins_won_spin_" + spinId;
    $(canvasId).html(credits);
    $(canvasId).show();
}

function hideCoinswon(spinId) {
    var canvasId = "#canvas_coins_won_spin_" + spinId;
    $(canvasId).hide();
}

function setSameHeight() {

    /*$(".spin_div").each(function(){
        var heightest = 0;
        $(this).find(".winning_line_button").each(function() {
            if (heightest < $(this).height()) {
                heightest = $(this).height();
            }
        });
        $(this).find(".winning_line_button").each(function() {
            $(this).height(heightest);
        });
    });*/

}

$(function() {
    $(".payline_button").mouseenter(
        function(){
            $(this).css("background-color", "#FCFC64");
            reverseEffectAll();
            paylineId = parseId($(this).attr("id"));
            spinId = parseId($(this).parents("div.spin_div").attr("id"));

            selector = selectAllButPayline(spinId, paylineId);

            applyEffect(selector);

        }).mouseleave(
        function(){
            $(this).css("background-color", "#CCCCFF");
            reverseEffectAll();
        });

    $(".winning_line_button").mouseenter(
        function() {
            $(this).css("background-color", "#FCFC64");
            reverseEffectAll();
            spinId = parseId($(this).parents("div.spin_div").attr("id"));

            positionStr = $(this).find("input").val()

            selector = selectAllButPosition(spinId, positionStr);
            applyEffect(selector);
            
            creditsWon = $(this).find("span").text();
            showCoinswon(spinId, creditsWon);

        }).mouseleave(
        function(){
            $(this).css("background-color", "#C1C6C4");
            spinId = parseId($(this).parents("div.spin_div").attr("id"));
            hideCoinswon(spinId);
            reverseEffectAll();
        });
    
    $(".winning_line_button_fs").mouseenter(
            function() {
                $(this).css("background-color", "#FCFC64");
                reverseEffectAll();
                spinId = parseId($(this).parents("div.spin_div").attr("id"));

                positionStr = $(this).find("input").val()

                selector = selectAllButPosition(spinId, positionStr);
                applyEffect(selector);

            }).mouseleave(
            function(){
                $(this).css("background-color", "#C1C6C4");
                reverseEffectAll();
            });

});

$(function(){
    cssPath = $('input[name=css_path]').val();
    loadCss(cssPath);
    setTimeout("setSameHeight()", 100);
});