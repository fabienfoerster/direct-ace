'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:OperateurCtrl
 * @description
 * # OperateurCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('OperateurCtrl', function ($scope,$http) {
    $scope.id = 0;
    $scope.playerId = "nadal";
    $scope.matchId = "rollandgarros2014qf2";
    $scope.data = {
      'score' : 0,
      'aces' : 0 ,
      'forehands' : 0,
      'backhands' : 0
    }

    $scope.incremente = function(eventName) {
      $scope.setValue(eventName,$scope.data[eventName]+1);
    };

    $scope.setValue = function(eventName,value) {
      $scope.id += 1;
      $scope.data[eventName] = value;
      var json = $scope.createJson(eventName);
      $scope.sendData(json);
    }

    $scope.createJson = function(eventName) {
      var json = {
        id : String($scope.id),
        playerID : $scope.playerId,
        matchID : $scope.matchId,
        event :  eventName ,
        date : String(new Date().getTime()),
        value : String($scope.data[eventName])
      } ;
      return json;
    };

    $scope.sendData = function(json) {
      $http.post('http://localhost:8080/api/data', json).
        success(function(data, status, headers, config) {
          console.log("Hourra");
        }).
        error(function(data, status, headers, config) {
          console.log("Erf");
        });
    }


  });
