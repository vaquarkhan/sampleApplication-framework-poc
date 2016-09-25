(function() {
    'use strict';

    angular
        .module('sampleApplicationApp')
        .controller('MutualFundNavController', MutualFundNavController);

    MutualFundNavController.$inject = ['$scope', '$state', 'MutualFundNav'];

    function MutualFundNavController ($scope, $state, MutualFundNav) {
        var vm = this;
        
        vm.mutualFundNavs = [];

        loadAll();

        function loadAll() {
            MutualFundNav.query(function(result) {
                vm.mutualFundNavs = result;
            });
        }
    }
})();
