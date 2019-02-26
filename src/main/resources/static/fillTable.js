$(document).ready(function () {
    $.ajax({
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        url: window.location.protocol + "//" + window.location.host + "/crawler",
        success: getCrawlerResultHandler
    });
});

function getCrawlerResultHandler(data) {
    var tableHeader = "<tr>" +
        "<th>Title</th>" +
        "<th>Description</th>" +
        "<th>Publication Date</th>" +
        "<th>Extraction link</th>" +
        "<th>Main Image Link</th>" +
        "</tr>";

    var rows = "";
    data.forEach(function (row) {
        rows += "<tr>" +
            "<td>" + row.title + "</td>" +
            "<td>" + row.description + "</td>" +
            "<td>" + row.publicationDate + "</td>" +
            "<td>" + "<a href='" + row.extractionLink + "'>link</a>" + "</td>" +
            "<td>" + "<a href='" + row.mainImageLink + "'>link</a>" + "</td>" +
            "</tr>"
    });

    $("#crawlerResult").html(tableHeader + rows);
}