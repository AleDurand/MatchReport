'use strict';

angular.module('match').controller('MatchesCtrl', function($scope, Matches) {
    Matches.all()
    .success(function (response) {
        $scope.matches = response.content;
    })
    .error(function (response) {
        $scope.error = response;
    });

});