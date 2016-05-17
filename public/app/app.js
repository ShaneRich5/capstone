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
        $authProvider.loginUrl = '/api/auth/login';
        $authProvider.signupUrl = '/api/auth/register';
        
        $urlRouterProvider.otherwise('/home');

        $stateProvider
            
            .state('course_show', {
                url: '/courses/:courseId',
                templateUrl: templatePath('courses/show'),
                controller: 'CoursesShowCtrl'
            })
            .state('course_all', {
                url: '/courses',
                templateUrl: templatePath('courses/all'),
                controller: 'CoursesAllCtrl'
            })
            .state('user_show', {
                url: '/users/:userId',
                templateUrl: templatePath('users/show'),
                controller: 'UsersShowCtrl'
            })
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