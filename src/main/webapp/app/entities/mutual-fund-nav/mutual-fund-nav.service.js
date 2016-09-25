(function() {
    'use strict';
    angular
        .module('sampleApplicationApp')
        .factory('MutualFundNav', MutualFundNav);

    MutualFundNav.$inject = ['$resource'];

    function MutualFundNav ($resource) {
        var resourceUrl =  'api/mutual-fund-navs/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
