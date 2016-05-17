/**
 * Created by Shane on 5/17/2016.
 */

'use strict';

(function(angular){
    angular
        .module('autograder')
        .controller('SubmissionShowCtrl', ['$http', '$scope', '$log', submissionShowCtrl]);
    
    function submissionShowCtrl($http, $scope, $log) {
        var submissionId = $stateParams.submissionId;
        
        $http.get('api/submissions/' + submissionId)
            .then(function(res) {
               $scope.submission = res.data;
                $log.log(res);
            }, function(err) {
                $scope.error = err.data
                $log.log(err);
            });
    }
})(angular);