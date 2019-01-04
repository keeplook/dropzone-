/* js/fileAppDirectives */
function dropzone() {
    return function(scope, element, attrs) {
        var config = {
            url: 'http://localhost:8080/upload',
            maxFilesize: 100,
            paramName: "uploadfile",
            maxThumbnailFilesize: 10,
            parallelUploads: 3,/* 上传文件数量 */
            autoProcessQueue: false
        };
        var eventHandlers = {
            'addedfile': function(file) {
                scope.file = file;
                if (this.files[3]!=null) {/* 超过上传文件数量    覆盖第一个 */
                    this.removeFile(this.files[0]);
                }
                scope.$apply(function() {
                    scope.fileAdded = true;
                });
            },
            'success': function (file, response) {
            }
        };
        dropzone = new Dropzone(element[0], config);
        angular.forEach(eventHandlers, function(handler, event) {
            dropzone.on(event, handler);
        });
        scope.processDropzone = function() {
            dropzone.processQueue();
        };
        scope.resetDropzone = function() {
            dropzone.removeAllFiles();
        }
    }
}
angular.module('fileApp').directive('dropzone', dropzone);