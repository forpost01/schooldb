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
          //  console.log(data);

            $.each(data, function (index, school) {
             //   console.log(school);
     $("#added-schools").append($('<tr/>')
         .append($('<td/>').html("<a href='rest/schools?id="+school.school_id+"'>"+school.school_id+"</a>"))
         .append($('<td/>').append($("<span class='label label-info' style='margin:4px;padding:4px' />")).append(school.fioDirector))
         .append($('<td/>').append($("<span class='label' style='margin:4px;padding:4px' />")).append(school.address))
         .append($('<td/>').append($("<span class='label label-info' style='margin:4px;padding:4px' />")).append(school.accountNumber))

     )
                })
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
}
