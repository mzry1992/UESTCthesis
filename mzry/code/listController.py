cdoj.controller("ListController", [
  "$scope", "$rootScope", "$http",
  ($scope, $rootScope, $http) ->
    $scope.condition = 0
    $scope.list = []
    # 翻页导航
    $scope.pageInfo =
      countPerPage: 20
      currentPage: 1
      displayDistance: 2
      totalPages: 1
    # 列表的请求地址
    $scope.requestUrl = 0
    # 查询条件重置
    $scope.reset = ->
      _.each($scope.condition, (value, index) ->
        $scope.condition[index] = undefined
      )
      $scope.condition["currentPage"] = null
    # 列表刷新
    $scope.refresh = ->
      if $scope.requestUrl != 0
        condition = angular.copy($scope.condition)
        $http.post($scope.requestUrl, condition).then (response) =>
          $scope.list = response.data.list
          $scope.pageInfo = response.data.pageInfo
    # 在用户登陆/登出时刷新
    $rootScope.$watch("currentUser", ->
      $scope.refresh()
    , true)
    # 在查询条件发生变化时刷新
    $scope.$watch("condition", ->
      $scope.refresh()
    , true
    )
])