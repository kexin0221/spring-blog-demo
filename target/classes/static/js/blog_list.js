function getBlogList() {
    $.ajax({
        type: "get",
        url: "/blog/getList",
        success: function (result) {
            // 针对后端数据进行简单校验
            if (result.code === "SUCCESS" && result.data != null && result.data.length > 0) {
                let finalHTML = ""
                for (let bookInfo of result.data) {
                    finalHTML += '<div class="blog">'
                    finalHTML += '<div class="title">'+ bookInfo.title +'</div>'
                    finalHTML += '<div class="date">'+ bookInfo.createTime +'</div>'
                    finalHTML += '<div class="desc">'+ bookInfo.content +'</div>'
                    finalHTML += '<a class="detail" href="blog_detail.html?blogId='+ bookInfo.id +'">查看全文&gt;&gt;</a>'
                    finalHTML += '</div>'
                }
                $(".container .right").html(finalHTML);
            }
        }
    });
}

