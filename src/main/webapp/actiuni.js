function newToDo() {
    var name = document.getElementById('name').value;
    var action = "NEW";
    $.ajax({
        url: 'addevent?eventname='+name
    }).done(function (response) {
        location.href = "listMyStuff.jsp";
    });
}

function loadToDo() {
    $.ajax({
        url: 'listevent'
    }).done(function (response) {
        printOnDiv(response.listFromBackend);
    });
}
function printOnDiv(listFromBackend) {
    var listHtml = '';

    var list = document.getElementById('listOfToDo');

    for (var i = 0; i < listFromBackend.length; i++) {
        var elemC = listFromBackend[i];
        var el = '<li>'+elemC+'</li>';
        listHtml=listHtml+el;
    }
    list.innerHTML = '<ol>'+listHtml+'</ol>';
}