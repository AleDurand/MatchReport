angular.module('settings', [])

.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('tab.settings', {
            url: '/settings',
            views: {
                'tab-settings': {
                    templateUrl: 'modules/settings/views/tab-setting.html',
                    controller: 'SettingsCtrl'
                }
            }
        });
});