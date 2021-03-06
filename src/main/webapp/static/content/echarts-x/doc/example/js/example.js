define(function (require) {
    var CodeMirror = require('static/content/echarts-x/doc/lib/codemirror/codemirror');
    var ec = require('static/content/echarts-x/doc/lib/echarts/echarts');
    require('static/content/echarts-x/doc/lib/echarts-x/echarts-x');
    require('static/content/echarts-x/doc/lib/echarts/chart/map');
    require('static/content/echarts-x/doc/lib/echarts/chart/bar');
    require('static/content/echarts-x/doc/lib/echarts-x/chart/map3d');
    require('static/content/echarts-x/doc/lib/codemirror/mode/javascript');

    var myChart = null;

    $('#editor textarea').val($('#code-source').text());
    // Init code mirror
    var editor = CodeMirror.fromTextArea(
        $('#editor textarea')[0],
        {
            lineNumbers: true,
            mode: 'javascript',
            tabSize: 4
        }
    );
    editor.setOption('theme', 'twilight');

    function runCode(code) {
        var func = new Function('myChart', 'require', code);
        func(myChart, require);
    }

    function update() {
        runCode(editor.doc.getValue());
    }

    function refresh() {
        if (myChart) {
            myChart.dispose();
        }
        myChart = ec.init(document.getElementById('chart'));
        runCode(editor.doc.getValue());
    }

    $('#open-editor').click(function () {
        $('#editor').show();
        // Force editor to show
        editor.refresh();
    });

    $('#editor-close').click(function () {
        $('#editor').hide();
    });

    $('#editor-refresh').click(refresh);
    $('#editor-update').click(update);

    setTimeout(function () {
        refresh();
    });
});