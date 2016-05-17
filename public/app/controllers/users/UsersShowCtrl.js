/**
 * Created by Shane on 5/17/2016.
 */

'use strict';

(function(angular) {
    angular
        .module('autograder')
        .controller('UsersShowCtrl',
            ['$log', '$scope', '$http', '$auth', '$stateParams ', usersShowCtrl]);

    function usersShowCtrl($log, $scope, $http, $auth, $stateParams) {

        $http.get('/api/users/' + $stateParams.userId)
            .then(function(response) {
                $scope.user = response.data;
                
                if ($stateParams.userId == $scope.user.id) $scope.user.current = true;
                
                $log(response);
            }, function (error) {
                $log.log(error);
            })
    }
})(angular);