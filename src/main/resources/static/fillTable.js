const BASE_URL = window.location.protocol + "//" + window.location.host;

$(document).ready(function () {
    $.ajax({
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        url: BASE_URL + "/crawler",
        success: getCrawlerResultHandler
    });
});

function getCrawlerResultHandler(data) {
    let tableHeader = "<tr>" +
        "<th>Title</th>" +
        "<th>Description</th>" +
        "<th>Publication Date</th>" +
        "<th>Extraction link</th>" +
        "<th>Main Image Link</th>" +
        "</tr>";

    let rows = "";
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