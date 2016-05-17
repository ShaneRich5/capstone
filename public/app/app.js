/**
 * Created by Shane on 5/16/2016.
 */

'use strict';

(function(angular){
    angular
        .module('autograder', ['ui-router'])
        .config(['$stateProvider', '$urlRouterProvider', '$httpProvider', routesConfigFn]);

    function routesConfigFn($stateProvider, $urlRouterProvider, $httpProvider) {

        function templatePath(path) {
            return 'public/partials/' + path + '.html';
        }

        $urlRouterProvider.otherwise('/');

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: templatePath('pages/home')
            })
            .state('about', {
                url: '/about',
                templateUrl: templatePath('pages/about')
            })
            .state('about', {
                url: '/contact',
                templateUrl: templatePath('pages/contact')
            });
    }
})(angular);