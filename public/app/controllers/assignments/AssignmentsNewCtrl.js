/**
 * Created by Shane on 5/17/2016.
 */

'use strict';

(function(angular){
    angular
        .module('autograder')
        .controller('AssignmentsNewCtrl',
            ['$http', '$scope', '$log', '$stateParams', assignmentsNewCtrl]);

    function assignmentsNewCtrl($http, $scope, $log, $stateParams) {
        $scope.assignment = {};

        $scope.createAssignment = function(assignment) {

            $http.post('api/courses/' + $stateParams.courseId + '/assignments/new', assignment)
                .then(function(res) {
                    $log.log(res);
                    $state.go('course_show', {userId: $stateParams.courseId});
                }, function(err) {
                    $log.log(err);
                });
        }
    }

})(angular);