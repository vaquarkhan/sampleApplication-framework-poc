(function() {
    'use strict';

    angular
        .module('sampleApplicationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('mutual-fund-nav', {
            parent: 'entity',
            url: '/mutual-fund-nav',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sampleApplicationApp.mutualFundNav.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/mutual-fund-nav/mutual-fund-navs.html',
                    controller: 'MutualFundNavController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('mutualFundNav');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('mutual-fund-nav-detail', {
            parent: 'entity',
            url: '/mutual-fund-nav/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sampleApplicationApp.mutualFundNav.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/mutual-fund-nav/mutual-fund-nav-detail.html',
                    controller: 'MutualFundNavDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('mutualFundNav');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'MutualFundNav', function($stateParams, MutualFundNav) {
                    return MutualFundNav.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'mutual-fund-nav',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('mutual-fund-nav-detail.edit', {
            parent: 'mutual-fund-nav-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/mutual-fund-nav/mutual-fund-nav-dialog.html',
                    controller: 'MutualFundNavDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['MutualFundNav', function(MutualFundNav) {
                            return MutualFundNav.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('mutual-fund-nav.new', {
            parent: 'mutual-fund-nav',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/mutual-fund-nav/mutual-fund-nav-dialog.html',
                    controller: 'MutualFundNavDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                schemeCode: null,
                                isinDivPayoutIsinGrowth: null,
                                status: null,
                                schemeName: null,
                                netAssetValue: null,
                                repurchasePrice: null,
                                salePrice: null,
                                date: null,
                                updatedDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('mutual-fund-nav', null, { reload: 'mutual-fund-nav' });
                }, function() {
                    $state.go('mutual-fund-nav');
                });
            }]
        })
        .state('mutual-fund-nav.edit', {
            parent: 'mutual-fund-nav',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/mutual-fund-nav/mutual-fund-nav-dialog.html',
                    controller: 'MutualFundNavDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['MutualFundNav', function(MutualFundNav) {
                            return MutualFundNav.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('mutual-fund-nav', null, { reload: 'mutual-fund-nav' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('mutual-fund-nav.delete', {
            parent: 'mutual-fund-nav',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/mutual-fund-nav/mutual-fund-nav-delete-dialog.html',
                    controller: 'MutualFundNavDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['MutualFundNav', function(MutualFundNav) {
                            return MutualFundNav.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('mutual-fund-nav', null, { reload: 'mutual-fund-nav' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
