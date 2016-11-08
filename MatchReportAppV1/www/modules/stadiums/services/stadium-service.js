'use strict';

angular.module('stadium').factory('Stadiums', function($http) {
    return {
        all: function() {
            return $http({ method: 'GET', url: 'http://localhost:8081/api/stadiums' });
        },
        read: function(id) {
            return $http({ method: 'GET', url: 'http://localhost:8081/api/stadiums/' + id });
        },
        delete: function(id) {
            return $http({ method: 'DELETE', url: 'http://localhost:8081/api/stadiums/' + id });
        }

    };
});
