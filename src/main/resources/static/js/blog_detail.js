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
                // $(".content .detail").text(result.data.content);
                editormd.markdownToHTML("detail", {
                    markdown: result.data.content
                })
            }
        }
    });
}
