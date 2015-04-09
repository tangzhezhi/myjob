({
    appDir: './',
    baseUrl: 'js',
    dir: './dist',
    modules: [
        {
            name: 'app'
        }
    ],
    fileExclusionRegExp: /^(r|build)\.js$/,
    optimizeCss: 'standard',
    removeCombined: false,
    paths: {
        'css':'../lib/css',
        'css-builder':'../lib/css-builder',
        'normalize':'../lib/normalize',
        'jquery': '../lib/jquery',
        'bootstrap': '../lib/bootstrap',
        'jquery.toastmessage':'../lib/jquery.toastmessage',
        'jquery.md5':'../lib/jquery.md5',
        'jquery.form':'../lib/jquery.form',
        'common':'../lib/common',
        'bootstrapValidator':'../lib/formvalidation.bootstrap',
        'formValidation':'../lib/formValidation',
        'lightbox':'../lib/lightbox',
        'jquery.cookie':'../lib/jquery.cookie',
        app: '../app'
    },
    shim : {
        bootstrap : {
            deps : [
                'jquery'
            ]
        },
        'formValidation': {
            deps:[
                'jquery'
            ]
        },
        'bootstrapValidator':{
            deps:['jquery', 'formValidation']
        },
        'jquery.toastmessage':{
            deps:[
                'jquery'
            ]
        },
        'jquery.md5':['jquery'],
        'lightbox':{
            deps : [
                'jquery'
            ]
        }
    }
})