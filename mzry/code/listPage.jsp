<div id="xxx-list"
     ng-controller="ListController"
     ng-init="condition={
        currentPage: null,
        keyword: undefined,
        orderFields: undefined,
        orderAsc: undefined
    };
    requestUrl='/xxxx/search'">
  <div class="row">
    <div class="col-md-12">
      <div ui-page-info
           page-info="pageInfo"
           condition="condition"
           id="page-info">
      </div>
      <div id="advance-search">
        <a href="#" id="advanced" data-toggle="dropdown"><i
            class="fa fa-caret-square-o-down"></i></a>
        <ul ui-dropdown-menu class="dropdown-menu cdoj-form-menu"
            role="menu"
            aria-labelledby="advance-menu">
          <li role="presentation" id="condition">
            <form class="form">
              列表查询条件表单
              <p class="pull-right">
                <button type="button" class="btn btn-danger btn-sm" ng-click="reset()">Reset
                </button>
              </p>
            </form>
          </li>
        </ul>
      </div>
    </div>
  </div>

  <div class="row">
    <div ng-repeat="xxx in list">
      列表主体
    </div>
  </div>
</div>