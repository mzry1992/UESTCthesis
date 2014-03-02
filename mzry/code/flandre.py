# 一个简单的Markdown编辑器
cdoj.directive("uiFlandre",
->
  restrict: "A"
  scope:
    content: "=ngModel"
    uploadUrl: "@"
  controller: [
    "$scope", "$element",
    ($scope, $element) ->
      $scope.mode = "edit"
      $scope.previewContent = ""
      $editor = $element.find(".flandre-editor")

      # 预览按钮事件绑定
      $scope.togglePreview = ->
        if $scope.mode == "edit"
          # preview
          $scope.previewContent = $scope.content
          $scope.mode = "preview"
        else
          # edit
          $scope.mode = "edit"

      # 图片上传
      pictureUploader = new qq.FineUploaderBasic(
        button: $element.find(".flandre-picture-uploader")[0]
        request:
          endpoint: $scope.uploadUrl
          inputName: "uploadFile"
        validation:
          allowedExtensions: ["jpeg", "jpg", "gif", "png"],
          sizeLimit: 10 * 1000 * 1000 # 10 MB
        multiple: false
        callbacks:
          onComplete: (id, fileName, data) ->
            if data.success == "true"
              value = "![title](#{data.uploadedFileUrl})"
              position = $editor.getCursorPosition()
              oldText = $editor.val()
              # 将代码插入到文章中
              oldText = oldText.insert(value, position)
              $scope.$apply(->
                $scope.content = oldText
              )
              # ![title](...)
              #   ^^^^^
              # 同时选中title部分
              $editor.setSelection(position + 2, position + 7)
            else
              # TODO
              console.log(data)
      )
  ]
  template: """
      <div class="panel panel-default">
        <div class="panel-heading flandre-heading">
          <div class="btn-toolbar" role="toolbar">
            <div class="btn-group">
              <button type="button" class="btn btn-default btn-sm"
                      ng-click="togglePreview()"
                      ng-class="{active: mode == 'preview'}">Preview</button>
            </div>
            <div class="btn-group flandre-tools">
              <span class="btn btn-default btn-sm"><i class="fa fa-smile-o"></i></span>
              <span class="btn btn-default btn-sm flandre-picture-uploader"><i class="fa fa-picture-o"></i></span>
            </div>
          </div>
        </div>
        <textarea class="tex2jax_ignore form-control flandre-editor"
                  msd-elastic
                  ng-class="{'flandre-show': mode == 'edit'}"
                  ng-model="content"></textarea>
        <div class="flandre-preview" ng-class="{'flandre-show': mode == 'preview'}">
          <div ui-markdown content="previewContent"></div>
        </div>
      </div>
      """
  replace: true
)