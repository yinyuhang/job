var trTemplate = "<tr>\n" +
    "                    <th>${id}</th>\n" +
    "                    <th>${name}</th>\n" +
    "                    <th>${location}</th>\n" +
    "                    <th>${industry}</th>\n" +
    "                    <th><a onclick='showModalForUpdate(this)'>修改</a>&nbsp;&nbsp;<a onclick='remove(this)'>删除</a></th>\n" +
    "                </tr>"


function fill(data) {
    if (!data) {
        return ""
    }

    if (data["createUser"])
        data["createUser"] = data["createUser"]["name"]
    if (data["modifyUser"])
        data["modifyUser"] = data["modifyUser"]["name"]
    if (data["createDate"])
        data["createDate"] = data["createDate"].substr(0, 10)
    if (data["buyDate"])
        data["buyDate"] = data["buyDate"].substr(0, 10)

    var tr = trTemplate
    var id = "<input class='hide' value='" + data.id + "' readonly/>" + data.id.substr(0, 6);
    tr = tr.replace("${id}", id)
    for (var attr in data) {
        if (!data[attr]) {
            data[attr] = ""
        }
        tr = tr.replace("${" + attr + "}", data[attr])
    }
    return tr
}

function loadTable(goNext) {
    var uri = "/companys"
    var conditions = $("#search-form").serializeArray()
    var parameters = {}
    for (var index in conditions) {
        var condition = conditions[index]
        if (condition.value) {
            parameters[condition.name] = condition.value
        }
    }
    var pageIndex = $("#page-index").text();
    if (!pageIndex || !goNext) {
        pageIndex = 0
    }
    parameters["pageIndex"] = pageIndex
    parameters["pageSize"] = 10
    $.get(uri, parameters, function (result) {
        console.log("got form data" + result)
        var tBody = $("#data-table");
        tBody.empty()
        if (result.content) {
            for (var index in result.content) {
                var data = result.content[index]
                tBody.append(fill(data))
            }
            var pageIndex = result['number'];
            var totalPages = result['totalPages'];
            pageIndex++;
            $("#page-index").text(pageIndex)
            $("#total-page-num").text(totalPages)
            $('.customPagination').pagination({
                totalCount: totalPages,
                currPage: pageIndex,
                cb: function (current) {
                    console.log('回调执行了', current)
                }
            })
        }
    })
}

function remove(ele) {
    var id = $(ele).parents("tr").children("th").first().children("input").val()
    if (!id) {
        alert("未找到有效ID")
        return
    }
    $.ajax({
        type: "DELETE",
        url: "/company/" + id,
        data: {},
        success: function (result) {
            loadTable()
            alert("已删除")
        }
    })
}

function showModalForUpdate(ele) {
    var trs = $(ele).parents("tr").children("th");
    var id = trs.first().children("input").val()
    var name = trs[1].textContent
    var location = trs[2].textContent
    var industry = trs[3].textContent

    $("#updateModal input[name='id']").val(id)
    $("#updateModal input[name='name']").val(name)
    $("#updateModal input[name='location']").val(location)
    $("#updateModal input[name='industry']").val(industry)

    $("#updateModal").modal("show")
}

function add() {
    var datas = $("#update-form").serializeArray()
    var parameters = {}
    for (var index in datas) {
        var data = datas[index]
        if (data.value) {
            parameters[data.name] = data.value
        }
    }
    var method = "POST"
    if (parameters.id) {
        method = "PUT"
    }
    $.ajax({
        type: method,
        url: "/company",
        data: parameters,
        success: function (result) {
            loadTable()
            alert("新增成功")
            $("#update-form")[0].reset()
            $("#updateModal").modal("hide")
        }
    })
}

$(function () {
    // $(".btn-search").click(loadTable)
    $(".btn-add").click(function () {
        $("#updateModal").show()
    })
    $(".btn-clear").click(function () {
        $("#search-form")[0].reset()
    })
    $("#add").click(add)
})

