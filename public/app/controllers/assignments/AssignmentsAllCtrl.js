/**
 * Created by Shane on 5/17/2016.
 */

'use strict';

(function(angular){
    angular
        .module('autograder')
        .controller('AssignmentsAllCtrl',
            ['$http', '$scope', '$log', '$stateParams', assignmentsAllCtrl]);

    function assignmentsAllCtrl($http, $scope, $log, $stateParams) {
        
        $http.get('api/courses/' + $stateParams.courseId + '/assignments')
            .then(function(res) {
                $scope.course = res.data;
                $log.log(res);
            }, function(err) {
                $log.log(err);
            })
    }
})(angular);