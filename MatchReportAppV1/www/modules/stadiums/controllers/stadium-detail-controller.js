'use strict';

angular.module('stadium').controller('StadiumDetailCtrl', function($scope, $stateParams, Stadiums) {
    Stadiums.read($stateParams.stadiumId)
    .success(function (response) {
        $scope.stadium = response;
    })
    .error(function (response) {
        $scope.error = response;
    });
});
