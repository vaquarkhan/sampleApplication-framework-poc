(function() {
    'use strict';

    angular
        .module('sampleApplicationApp')
        .controller('MutualFundNavDeleteController',MutualFundNavDeleteController);

    MutualFundNavDeleteController.$inject = ['$uibModalInstance', 'entity', 'MutualFundNav'];

    function MutualFundNavDeleteController($uibModalInstance, entity, MutualFundNav) {
        var vm = this;

        vm.mutualFundNav = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            MutualFundNav.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
