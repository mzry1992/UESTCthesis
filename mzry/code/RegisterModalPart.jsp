<div class="form-group">
  <label class="control-label col-sm-4 "
         for="userName">User Name</label>
  <div class="col-sm-8">
    <input type="text"
           ng-model="userRegisterDTO.userName"
           maxlength="24"
           id="userName"
           ng-required="true"
           ng-pattern="/^[a-zA-Z0-9_]{4,24}$/"
           class="form-control input-sm"/>
    <ui-validate-info value="fieldInfo" for="userName"></ui-validate-info>
  </div>
</div>