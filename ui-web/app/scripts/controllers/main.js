'use strict';

/**
 * @ngdoc function
 * @name uiWebApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the uiWebApp
 */
angular.module('uiWebApp')
  .controller('MainCtrl', function ($scope, $rootScope, AccessToken, Profile) {
    var setelahLogin = function(){
      $scope.currentUser = Profile.get();
      $scope.sudahLogin = true;
      console.log("Sudah Login : "+$scope.sudahLogin);
      $rootScope.token = AccessToken.get();
    }

    $scope.$on('oauth:authorized', function(event) {
      setelahLogin();
    });

    $scope.$on('oauth:login', function(event) {
      setelahLogin();
    });

    $scope.$on('oauth:logout', function(event) {
      $scope.currentUser = {};
      $scope.sudahLogin = false;
      console.log("Sudah Login : "+$scope.sudahLogin);
    });
  });
