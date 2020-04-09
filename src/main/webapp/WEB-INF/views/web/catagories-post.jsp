<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
<title>${model.item.title}</title>
<style type="text/css">
.the-article-body {
    float: left;
    width: 100%;
    position: relative;
    font-family: 'Noto Serif',serif;
    color: #333;
    line-height: 1.618;
    font-size: 1.1em;
}
</style>


</head>

<body>
<div id="fb-root"></div>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v6.0&appId=2959170494151349&autoLogAppEvents=1"></script>
<script>
	window.fbAsyncInit = function() {
		FB.init({
			appId      : '2959170494151349',
			xfbml      : true,
			version    : 'v6.0'
		});
		FB.AppEvents.logPageView();
	};

	(function(d, s, id){
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id)) {return;}
		js = d.createElement(s); js.id = id;
		js.src = "https://connect.facebook.net/en_US/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
</script>
<center><h1>${model.item.title}</h1></center>
	<center><div>
		${model.item.shortDescription}
	</div></center>
	<!-- Page Content -->
	<div class="container">
	<div class="the-article-body">
	
		${model.item.content}
		<h5>Bình Luận</h5>
			<div class="fb-comments" data-href="https://springjwat.herokuapp.com/" data-width="" data-numposts="5"></div>
	</div>
	</div>
	<!-- /.container -->
<div
		class="fb-like"
		data-share="true"
		data-width="450"
		data-show-faces="true">
</div>

</body>

</html>