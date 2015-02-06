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
    $scope.playerId = "nadal";
    $scope.matchId = "rollandgarros2014qf2";
    $scope.data = {
      'aces' : 0 ,
      'forehands' : 0,
      'backhands' : 0
    }

    $scope.incremente = function(eventName) {
      $scope.data[eventName] += 1 ;
      var json = $scope.createJson(eventName);
      $scope.sendData(json);
    }

    $scope.createJson = function(eventName) {
      var json = {
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
