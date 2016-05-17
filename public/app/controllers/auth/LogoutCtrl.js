/**
 * Created by Shane on 5/17/2016.
 */

'use strict';

(function(angular) {
    angular
        .module('autograder')
        .controller('LogoutCtrl',
            ['$state', '$log', '$auth', logoutCtrl]);

    function logoutCtrl($state, $log, $auth) {
        $auth.logout().then(function(){
            $state.go('home');
        });
    }
})(angular);