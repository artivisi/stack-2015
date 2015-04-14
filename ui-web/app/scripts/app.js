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
    'ngSanitize'
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
      .otherwise({
        redirectTo: '/'
      });

      $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
  });
