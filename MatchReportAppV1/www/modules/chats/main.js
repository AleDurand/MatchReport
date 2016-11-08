angular.module('chat', [])

.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('tab.chats', {
            url: '/chats',
            views: {
                'tab-chats': {
                    templateUrl: 'modules/chats/views/tab-chats.html',
                    controller: 'ChatCtrl'
                }
            }
        })
        .state('tab.chat-detail', {
            url: '/chats/:chatId',
            views: {
                'tab-chats': {
                    templateUrl: 'modules/chats/views/chat-detail.html',
                    controller: 'ChatDetailCtrl'
                }
            }
        })
});