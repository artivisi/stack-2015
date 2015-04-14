'use strict';

/**
 * @ngdoc overview
 * @name uiWebApp
 * @description
 * # uiWebApp
 *
 * Main module of the application.
 */
angular
  .module('uiWebApp', [
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'oauth'
  ])
  .config(function ($routeProvider, $httpProvider) {
      $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .when('/halo', {
        templateUrl: 'views/halo.html',
        controller: 'HaloCtrl'
      })
      .when('/access_token=:accessToken', {
        template: '',
        controller: function ($location, AccessToken) {
          var hash = $location.path().substr(1);
          AccessToken.setTokenFromString(hash);
          $location.path('/');
          $location.replace();
        }
      })
      .otherwise({
        redirectTo: '/'
      });

      $httpProvider.interceptors.push(function($q, $rootScope) {
        return {
         'request': function(config) {
             if($rootScope.token) {
              config.headers = config.headers || {};
              config.headers.Authorization = 'Bearer '+$rootScope.token.access_token;
             }
             return config;
          }
        };
      });
  });
