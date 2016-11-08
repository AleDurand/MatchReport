'use strict';

angular.module('stadium').controller('StadiumCtrl', function($scope, Stadiums) {
    Stadiums.all()
    .success(function (response) {
        $scope.stadiums = response.content;
    })
    .error(function (response) {
        $scope.error = response;
    });
    
    $scope.remove = function(chat) {
        Chats.remove(chat);
    };

});