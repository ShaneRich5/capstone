/**
 * Created by Shane on 5/17/2016.
 */

'use strict';

(function(angular){
    angular
        .module('autograder')
        .controller('LoginCtrl',
            ['$state', '$auth', '$scope', '$log', loginCtrl]);

    function loginCtrl($state, $auth, $scope, $log) {
        
        if ($auth.isAuthenticated()) $state.go('home');

        $scope.attemptLogin = function(credentials) {
            if (credentials == null) {
                $scope.error = "Please fill out the form correctly";
                return;
            }

            $auth.login(credentials)
                .then(function(data) {
                    $state.go('home');
                }).catch(function(error) {
                    $log.log(error);
                });
        }
    }
})(angular);