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
        var course
    }
})(angular);