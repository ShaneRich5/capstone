/**
 * Created by Shane on 5/17/2016.
 */

'use strict';

(function(angular){
    angular
        .module('autograder')
        .controller('CoursesAllCtrl',
            ['$log', '$stateParams', '$scope', '$http', coursesAllCtrl]);
    
    function coursesAllCtrl($log, $stateParams, $scope, $http) {
        var userId = $stateParams.userId;
        var url = (userId == null) ? 'api/courses' : 'api/users/' + userId + '/courses'; 
        
        $http.get(url)
            .then(function(response) {
                $scope.courses = response.data;
                $log.log(response);
            }, function(response) {
                $log.log(response);
                $scope.error = response.data;
            });
    }
})(angular);