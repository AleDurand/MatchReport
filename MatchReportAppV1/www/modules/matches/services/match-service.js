'use strict';

angular.module('match').factory('Matches', function($http) {
    return {
        all: function() {
            return $http({ method: 'GET', url: 'http://localhost:8081/api/matches' });
        },
        read: function(id) {
            return $http({ method: 'GET', url: 'http://localhost:8081/api/matches/' + id });
        },
        delete: function(id) {
            return $http({ method: 'DELETE', url: 'http://localhost:8081/api/matches/' + id });
        }

    };
});
