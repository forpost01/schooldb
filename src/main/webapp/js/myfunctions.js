function sendAjax() {

    // get inputs
    var school = new Object();
    school.school_id = $('#school_id').val();
    school.fioDirector = $('#fioDirector').val();
    school.address = $('#address').val();
    school.accountNumber = $('#accountNumber').val();

    $.ajax({
        url: "/rest/schools/post",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(school),
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function (data) {
            alert("Школа добавлена: " + data);
        },
        error: function (data, status, er) {
            alert("Школа не добавлена: " + data + " status: " + status + " er:" + er);
        }
    });
}

function callServlet() {
    $.ajax({
        type: "GET",
        url: "/rest/schools/all",
        data: {},
        dataType: "json",
        success: function (data) {
            $("tr:has(td)").remove();
            console.log(data);

            $.each(data, function (index, school) {
                console.log(school);
     $("#added-schools").append($('<tr/>').append($('<td/>').html("<a href='"+school.school_id+"'>"+school.fioDirector+"</a>")))
                })
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
}


//
// $("tr:has(td)").remove();
//
// $.each(data, function (index, school) {
//
//     var td_categories = $("<td/>");
//     $.each(school.categories, function (i, tag) {
//         var span = $("<span class='label label-info' style='margin:4px;padding:4px' />");
//         span.text(tag);
//         td_categories.append(span);
//     });
//
//     var td_tags = $("<td/>");
//     $.each(school.tags, function (i, tag) {
//         var span = $("<span class='label' style='margin:4px;padding:4px' />");
//         span.text(tag);
//         td_tags.append(span);
//     });
//
//     $("#added-schools").append($('<tr/>')
//             .append($('<td/>').html("<a href='"+school.school_id+"'>"+school.fioDirector+"</a>"))
//         // .append(td_categories)
//         // .append(td_tags)
//     );
//
// });