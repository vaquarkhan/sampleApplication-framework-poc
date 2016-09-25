(function() {
    'use strict';

    angular
        .module('sampleApplicationApp')
        .controller('MutualFundNavDetailController', MutualFundNavDetailController);

    MutualFundNavDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'MutualFundNav'];

    function MutualFundNavDetailController($scope, $rootScope, $stateParams, previousState, entity, MutualFundNav) {
        var vm = this;

        vm.mutualFundNav = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sampleApplicationApp:mutualFundNavUpdate', function(event, result) {
            vm.mutualFundNav = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
