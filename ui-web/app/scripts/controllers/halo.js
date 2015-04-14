'use strict';

/**
 * @ngdoc function
 * @name uiWebApp.controller:HaloCtrl
 * @description
 * # HaloCtrl
 * Controller of the uiWebApp
 */
angular.module('uiWebApp')
  .controller('HaloCtrl', function ($scope, $http) {
    $scope.response = {};

    $scope.halo = function(){
        $http.get("/api/halo?nama="+$scope.nama)
        .success(function(data){
            $scope.response = data;
        })
        .error(function(data, status){
            alert("Error status : "+status+", data : "+data);
        });
    }
  });
