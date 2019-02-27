$(document).ready(function () {
    $("body").html("<h2>Run crawler?</h2>" + "<button id=\"runCrawlerBtn\">Run</button>");
    $("#runCrawlerBtn").click(runCrawlerAjax);
});

function runCrawlerAjax() {
    $.ajax({
        method: "POST",
        dataType: "json",
        contentType: "application/json",
        url: "/crawler",
        beforeSend: displayLoading
    })
        .always(getCrawlerResult)
}

function getCrawlerResult(data) {
    if (data.status === 200) {
        $.ajax({
            method: "GET",
            dataType: "json",
            contentType: "application/json",
            url: "/crawler",
            beforeSend: displayLoading
        })
            .always(getCrawlerResultHandler);
    } else {
        errorHandler(data);
    }
}

function getCrawlerResultHandler(data, textStatus, responseData) {
    if (responseData.status === 200) {
        $("body").html("<table id=\"crawlerResult\" border=\"1\"></table>");

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
    } else {
        errorHandler(data);
    }
}

function displayLoading() {
    $("body").html("<h2>Loading...</h2>");
}

function errorHandler(data) {
    $("body").html("<h2>Something bad happened:</h2>" + data.responseJSON.message);
}