function getBlogDetail() {
    $.ajax({
        type: "get",
        url: "blog/getBlogDetail" + location.search,
        success: function (result) {
            if (result.code === "FAIL") {
                alert(result.errMsg);
                return;
            }
            if (result.code === "SUCCESS" && result.data != null) {
                $(".content .title").text(result.data.title);
                $(".content .date").text(result.data.createTime);
                $(".content .detail").text(result.data.content);
            }
        }
    });
}

function getAuthorInfo() {
    $.ajax({
        type: "get",
        url: "user/getAuthorInfo" + location.search,
        success: function (result) {
            if (result != null && result.code === "SUCCESS" && result.data != null) {
                $(".container .left .card h3").text(result.data.userName);
                $(".container .left .card a").attr("href", result.data.githubUrl)
            }
        }
    })
}