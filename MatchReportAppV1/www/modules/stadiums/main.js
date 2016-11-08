angular.module('stadium', [])

.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('tab.stadiums', {
            url: '/stadiums',
            views: {
                'tab-stadiums': {
                    templateUrl: 'modules/stadiums/views/tab-stadiums.html',
                    controller: 'StadiumCtrl'
                }
            }
        })
        .state('tab.stadium-detail', {
            url: '/stadiums/:stadiumId',
            views: {
                'tab-stadiums': {
                    templateUrl: 'modules/stadiums/views/stadium-detail.html',
                    controller: 'StadiumDetailCtrl'
                }
            }
        })
});