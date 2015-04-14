'use strict';

/**
 * @ngdoc function
 * @name uiWebApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the uiWebApp
 */
angular.module('uiWebApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
