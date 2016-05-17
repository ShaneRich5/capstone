/**
 * Created by Shane on 5/17/2016.
 */

'use strict';

(function(angular){
    angular
        .module('autograder')
        .controller('AssignmentsShowCtrl', 
            ['$http', '$scope', '$log', '$stateParams', assignmentsShowCtrl]);
    
    function assignmentsShowCtrl($http, $scope, $log, $stateParams) {
        $http.get('api/courses/' + $stateParams.courseId + '/assignments/' + $stateParams.assignmentId)
            .then(function(res) {
                $log.log(res);
                $scope.assignment = res.data;
            }, function(err){
               $log.log(err); 
            });
    }
    
})(angular);