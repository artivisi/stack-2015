'use strict';

/**
 * @ngdoc function
 * @name uiWebApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the uiWebApp
 */
angular.module('uiWebApp')
  .controller('MainCtrl', function ($scope, $rootScope, $http) {
    $scope.login = function(){
        authenticate($scope.credentials);
    }

    var authenticate = function(credentials) {
        alert("Username : "+credentials.username+", password : "+credentials.password);
        
        var headers = credentials ? {authorization : "Basic "
            + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('/api/user', {headers : headers})
        .success(function(data) {
          if (data) {
            $rootScope.authenticated = true;
            $rootScope.currentUser = data;
          } else {
            $rootScope.authenticated = false;
          }
        }).error(function(data, status) {
           alert("Username / Password salah");
          $rootScope.authenticated = false;
        });
      };
  });
