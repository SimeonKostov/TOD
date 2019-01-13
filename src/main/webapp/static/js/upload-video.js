/**
 * VIDEO UPLOADING JAVASCRIPT
 */
$(document).ready(function(){
	setInterval(function(){
		if(_("file1").files[0] != null){
			$("#file-name").text(_("file1").files[0].name);
		}
	}, 500);
})

function _(el) {
  return document.getElementById(el);
}

function uploadFile() {
  var file = _("file1").files[0];
  var challengeId=document.getElementById("challenge-id").value;
  var notificationId=document.getElementById("notification-id").value;
  var formdata = new FormData();
  formdata.append("file1", file);
  var ajax = new XMLHttpRequest();
  ajax.upload.addEventListener("progress", progressHandler, false);
  ajax.addEventListener("load", completeHandler, false);
  ajax.addEventListener("error", errorHandler, false);
  ajax.addEventListener("abort", abortHandler, false);
  ajax.open("POST", "uploadVideo?notifierId=" + challengeId + "&notificationId=" + notificationId);
  ajax.send(formdata);
}

function progressHandler(event) {
  _("loaded_n_total").innerHTML = "Uploaded " + event.loaded/(1000000) + " MB of " + event.total/(1000000);
  var percent = (event.loaded / event.total) * 100;
  _("progressBar").value = Math.round(percent);
  _("status").innerHTML = Math.round(percent) + "% uploaded... please wait to redirect";
}

function completeHandler(event) {
  _("status").innerHTML = event.target.responseText;
  _("progressBar").value = 0;
  
  setTimeout(function(){
		window.location.href = "index";
	}, 1500);
}

function errorHandler(event) {
  _("status").innerHTML = "Upload Failed";
}

function abortHandler(event) {
  _("status").innerHTML = "Upload Aborted";
}

