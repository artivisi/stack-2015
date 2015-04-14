'use strict';

describe('Controller: HaloCtrl', function () {

  // load the controller's module
  beforeEach(module('uiWebApp'));

  var HaloCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    HaloCtrl = $controller('HaloCtrl', {
      $scope: scope
    });
  }));

  it('should have empty response object in scope', function () {
    expect(scope.response).toEqual({});
  });
});
