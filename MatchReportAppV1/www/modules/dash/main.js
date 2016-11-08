angular.module('dash', [])

.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('tab.dash', {
            url: '/dash',
            views: {
                'tab-dash': {
                    templateUrl: 'modules/dash/views/tab-dash.html',
                    controller: 'DashCtrl'
                }
            }
        })
});