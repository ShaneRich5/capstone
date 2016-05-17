/**
 * Created by Shane on 5/17/2016.
 */

'use strict';

(function(angular) {
    angular
        .module('autograder')
        .controller('RegisterCtrl',
            ['$state', '$scope', '$log', '$auth', registerCtrl]);

    function registerCtrl($state, $scope, $log, $auth) {

        if($auth.isAuthenticated()) $state.go('home');

        $scope.attemptRegister = function(credentials) {
            
            if (credentials == null) {
                $scope.error = "Enter form data";
                $state.go('home');
            }
            
            $auth.signup(credentials)
                .then(function(response) {
                    $state.go('login');
                })
                .catch(function(error) {
                    $log.log(error);
                    $scope.error = error.data;
                });
        };
    }
})(angular);