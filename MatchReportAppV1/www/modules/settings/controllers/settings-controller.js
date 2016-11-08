'use strict';

angular.module('settings').controller('SettingsCtrl', function($scope, $translate) {
    $scope.settings = {
        enableFriends: true,
        language: 'es'
    };

    $scope.switchLanguage = function(key) {
        $translate.use(key);
    };

});