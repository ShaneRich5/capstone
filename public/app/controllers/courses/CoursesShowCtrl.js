/**
 * Created by Shane on 5/17/2016.
 */

'use strict';

(function(angular) {
    angular
        .module('autograder')
        .controller('CoursesShowCtrl',
            ['$scope', '$http', '$log', '$stateParams', coursesShowCtrl]);

    function coursesShowCtrl($scope, $http, $log, $stateParams) {
        var courseId = $stateParams.courseId;
        
        $http.get('api/courses/' + courseId)
            .then(function(res) {
                $scope.course = res.data;
                $log.log(res);
            }, function(error) {
                $log.log(error);
                $scope.error = error.data;
            })
    }
})(angular);