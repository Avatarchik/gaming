function paylineToPositions(payline) {
    var positions = [];
    $.each(payline, function(index, value) {
        positions.push({x: index+1, y: value});
    });
    return positions;
}
function getAllPositions(row, col) {
    var positions = []
    for (i=0; i<row; i++) {
        for (j=0; j<col; j++) {
            positions.push({x: j+1, y: i+1});
        }
    }
    return positions;
}
function areSamePosition(p1, p2) {
    if (p1.x == p2.x && p1.y == p2.y) {
        return true;
    } else {
        return false;
    }
}
function isPositionInArray(item, array) {
    for (i=0; i<array.length; i++){
        if (areSamePosition(item, array[i])) {
            return true;
        }
    }
    return false;
}
function difference(array1, array2) {
    var _array = new Array();
    _array = jQuery.grep(array1, function (item) {
        return isPositionInArray(item, array2) == false;
    });
    return _array;
}
function selectAllButPositions(positions) {
    pos = difference(getAllPositions(4,5), positions);
    return pos;
}
function convertStringToPosition(positions) {
    var posArray = positions.split(";");
    var objArray = [];

    $.each(posArray, function(index, value) {
        var point = value.split(',');
        var p = {};
        p.x = point[0];
        p.y = point[1];
        objArray.push(p);
    });

    return objArray;
}

/**
 *	Will return the digit section at the end of a string
 *   Example: return "1" from 'line_1', return '20' from 'winning_line_20'
 */
function parseId(idStr) {
    array = idStr.split("_");
    return array[array.length-1];
}

function loadCss(path) {
    var link = document.createElement('link');
    link.rel = 'stylesheet';
    link.type = 'text/css';
    link.href = path;
    $('head')[0].appendChild(link);
}