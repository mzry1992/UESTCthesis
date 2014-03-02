    $rootScope.$watch("hasLogin",
      ->
        if $rootScope.hasLogin
          view = $scope.views.userView
        else
          view = $scope.views.loginView
        # recompile content
        $element.html(view)
        $compile($element.contents())($scope)

        if $rootScope.hasLogin && $rootScope.currentUser.type == 1
          $rootScope.isAdmin = true
        else
          $rootScope.isAdmin = false
    )