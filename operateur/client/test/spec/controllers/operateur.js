'use strict';

describe('Controller: OperateurCtrl', function () {

  // load the controller's module
  beforeEach(module('clientApp'));

  var OperateurCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OperateurCtrl = $controller('OperateurCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
