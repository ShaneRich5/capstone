/**
 * Created by Shane on 5/16/2016.
 */

'use strict';

(function(angular){
    angular
        .module('autograder', ['satellizer', 'ui.router'])
        .config(['$stateProvider', '$urlRouterProvider', '$authProvider', routesConfigFn]);

    function routesConfigFn($stateProvider, $urlRouterProvider, $authProvider) {

        function templatePath(path) {
            return '/assets/partials/' + path + '.html';
        }
        
        $authProvider.baseUrl = '/';
        $authProvider.loginUrl = '/auth/login';
        $authProvider.signupUrl = '/auth/register';
        
        $urlRouterProvider.otherwise('/home');

        $stateProvider
            .state('register', {
                url: '/register',
                templateUrl: templatePath('auth/register'),
                controller: 'RegisterCtrl'
            })
            .state('logout', {
                url: '/logout',
                templateUrl: 'Signing out...',
                controller: 'LogoutCtrl'
            })
            .state('login', {
                url: '/login',
                templateUrl: templatePath('auth/login'),
                controller: 'SessionsCtrl'
            })
            .state('home', {
                url: '/home',
                templateUrl: templatePath('pages/home'),
                controller: 'HomeCtrl'
            })
            .state('about', {
                url: '/about',
                templateUrl: templatePath('pages/about'),
                controller: 'AboutCtrl'
            })
            .state('contact', {
                url: '/contact',
                templateUrl: templatePath('pages/contact'),
                controller: 'ContactCtr;'
            });
    }
})(angular);