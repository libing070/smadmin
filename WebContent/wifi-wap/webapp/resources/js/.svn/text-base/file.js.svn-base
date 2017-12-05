var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
var FILE_TYPE_ERROR = "FILE_TYPE_ERROR";
var FILE_SIZE_ERROR = "FILE_SIZE_ERROR";
var FILE_IS_OK = "FILE_IS_OK";
var EMPTY_FILE = "EMPTY_FILE";

function checkFile(target, id, maxSize) {
	var fileSize = 0;
	var filetypes = [".jpg", ".jpeg", ".png"];
	var filepath = target.value;
	var filemaxsize = maxSize / 1024;
	
	var result = FILE_IS_OK;
	if (filepath) {
		var isnext = false;
		var fileend = filepath.substring(filepath.lastIndexOf("."));
		if (filetypes && filetypes.length > 0) {
			for ( var i = 0; i < filetypes.length; i++) {
				if (filetypes[i] == fileend.toLowerCase()) {
					isnext = true;
					break;
				}
			}
		}
		if (!isnext) {
			result = FILE_TYPE_ERROR;
			target.value = "";
			return result;
		}
	} else {
		result = EMPTY_FILE;
		return result;
	}
	if (isIE && !target.files) {
		var filePath = target.value;
		var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
		if (!fileSystem.FileExists(filePath)) {
			result = EMPTY_FILE;
			return result;
		}
		var file = fileSystem.GetFile(filePath);
		fileSize = file.Size;
	} else {
		fileSize = target.files[0].size;
	}

	var size = fileSize / 1024;
	if (size > filemaxsize) {
		result = FILE_SIZE_ERROR;
		target.value = "";
		return result;
	}
	if (size <= 0) {
		result = EMPTY_FILE;
		target.value = "";
		return result;
	}
	return result;
}
