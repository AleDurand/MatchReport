angular.module('match', [])

.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('tab.matches', {
            url: '/matches',
            views: {
                'tab-matches': {
                    templateUrl: 'modules/matches/views/tab-matches.html',
                    controller: 'MatchesCtrl'
                }
            }
        });
});