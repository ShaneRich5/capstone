/**
 * Created by Shane on 5/16/2016.
 */

'use strict';

(function(angular){
    angular
        .module('autograder')
        .controller('HomeCtrl', ['$log', homeCtrl]);

    function homeCtrl($log) {
        $log.log('In home Ctrl')
    }
})(angular);