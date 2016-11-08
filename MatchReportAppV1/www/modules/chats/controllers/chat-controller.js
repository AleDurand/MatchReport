'use strict';

angular.module('chat').controller('ChatCtrl', function($scope, Chats) {
    $scope.chats = Chats.all();
    
    $scope.remove = function(chat) {
        Chats.remove(chat);
    };

});