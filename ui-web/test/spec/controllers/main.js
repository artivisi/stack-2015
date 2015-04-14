'use strict';

describe('Controller: MainCtrl', function () {

  // load the controller's module
  beforeEach(module('uiWebApp'));

  var MainCtrl,
    scope, 
    $rootScope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, _$rootScope_) {
    $rootScope = _$rootScope_;
    scope = $rootScope.$new();

    MainCtrl = $controller('MainCtrl', {
      $scope: scope
    });
  }));

  it('should not set rootScope.sudahLogin', function () {
    expect($rootScope.currentUser).not.toBeDefined();
  });
});
