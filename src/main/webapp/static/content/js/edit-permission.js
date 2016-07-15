
$(function () {
    if ($.browser.msie == undefined) {
        $('.group-title').css("marginLeft", "7px");
    }
});

/* 下拉框 */
function initDropDownList(selectId, dataUrl, systemId, permissionItemId, selectedValue) {
    var list = $("#" + selectId);
    list.empty().append('<option value="" style="color:#166A00;">正在初始化...</option>');
    $.postJson(dataUrl, { systemId: systemId, permissionItemId: permissionItemId, selectedValue: selectedValue }, function (data) {
        list.empty().append('<option value="">请选择</option>');
        var html = [];
        for (var p in data) {
            var option = data[p];
            var selected = (option.selected ? 'selected="selected"' : '');
            list.append('<option value="' + option.value + '"' + selected + '>' + option.text + '</option>');
        }
    }, function () {
        list.empty().append('<option value="" style="color:#ff0000;">初始化出错</option>');
    });
}

/* 树视图 */
function initTreeView(treeId, dataUrl, systemId, permissionItemId, checkedIds) {
    var setting = {
        key: {
            checked: "checked",
            childs: "childs",
            name: "name",
            title: "name"
        },
        check: {
            enable: true,
            chkStyle: "checkbox"
        },
        view: {
            expandSpeed: "",
            selectedMulti: false
        },
        callback: {
            onCheck: function (e, treeId, treeNode) {
                dofillTreeInput(treeId);
            }
        }
    };

    var tree = $("#" + treeId);
    tree.html('<div class="loading"><img src="' + loading_img + '" border="0" /> 正在初始化...</div>');
    $.postJson(dataUrl, { systemId: systemId, permissionItemId: permissionItemId, checkedIds: checkedIds }, function (data) {
        tree.empty();
        $.fn.zTree.init(tree, setting, data);
        $.fn.zTree.getZTreeObj(treeId).expandAll(true);
    }, function () {
        tree.html('<div class="loading"><font color="red">初始化出错</font></div>');
    });
}

function doCheckAllNodes(sender, treeId) {
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    if (zTree) {
        zTree.checkAllNodes(sender.checked);
        dofillTreeInput(treeId);
    }
}
function dofillTreeInput(treeId) {
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    if (zTree) {
        // 半选状态的不保存
        var nodes = $.map(zTree.getCheckedNodes(true), function (node) { return node.getCheckStatus().half ? null : node.id; });

        // 更新到 hidden
        $('#' + treeId.substring('tree-view-'.length)).val(nodes.join(','));
    }
}