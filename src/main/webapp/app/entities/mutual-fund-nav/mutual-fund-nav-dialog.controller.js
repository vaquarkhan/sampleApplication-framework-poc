(function() {
    'use strict';

    angular
        .module('sampleApplicationApp')
        .controller('MutualFundNavDialogController', MutualFundNavDialogController);

    MutualFundNavDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'MutualFundNav'];

    function MutualFundNavDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, MutualFundNav) {
        var vm = this;

        vm.mutualFundNav = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.mutualFundNav.id !== null) {
                MutualFundNav.update(vm.mutualFundNav, onSaveSuccess, onSaveError);
            } else {
                MutualFundNav.save(vm.mutualFundNav, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sampleApplicationApp:mutualFundNavUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
