(function () {

    function resize() {
        $('#main').height($(window).height() - $('#header').height());
    }

    $(window).resize(resize);
    resize();

    var developMode = false;

    if (developMode) {
        require.config({
            paths: {
                "lib": 'static/content/echarts-x/doc/lib'
            },
            packages: [
                {
                    name: 'qtek',
                    location: 'static/content/echarts-x/src',
                    main: 'qtek'
                },
                {
                    name: 'echarts',
                    location: 'static/content/echarts-x/echarts/src',
                    main: 'echarts'
                },
                {
                    name: 'echarts-x',
                    location: 'static/content/echarts-x/src',
                    main: 'echarts-x'
                }
            ]
        });

        boot();
    }
    else {
        $(document).ready(function () {
            loadScript([
                'static/content/echarts-x/doc/lib/echarts-x/echarts-x.js',
                'static/content/echarts-x/doc/lib/echarts/echarts.js',
                'static/content/echarts-x/doc/lib/echarts/chart/map.js'
            ], function () {
                require.config({
                    paths: {
                        "lib": 'static/content/echarts-x/doc/lib',
                        'echarts-x': 'static/content/echarts-x/doc/lib/echarts-x',
                        'echarts': 'static/content/echarts-x/doc/lib/echarts'
                    }
                });
                boot();
            });
        });
    }

    function boot() {
        require(['static/content/echarts-x/doc/example/js/example']);
    }

    function loadScript(urlList, onload) {
        var count = urlList.length;;
        for (var i = 0; i < urlList.length; i++) {
            var script = document.createElement('script');
            script.async = true;

            script.src = urlList[i];
            script.onload = function () {
                count--;
                if (count === 0) {
                    onload && onload();
                }
            }

            document.getElementsByTagName('head')[0].appendChild(script);
        }
    }
})()