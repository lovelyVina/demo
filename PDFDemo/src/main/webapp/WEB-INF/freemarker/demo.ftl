<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8" />
		<title>测试</title>
	</head>
	<body>
		<fieldset>
			<legend>根据模板生成PDF文档</legend>
			<form action="${request.contextPath}/demo/ceratePdf.htm" method="post">
				<p>
					<label>姓名：</label><input type="text" name="name" placeholder="迈克-Mike" >
				</p>
				<p>
					<label>性别：</label><input type="text" name="gender" placeholder="男-Male">
				</p>
				<p>
					<input type="submit" value="根据模板生成PDF文档">
				</p>
			</form>
		</fieldset>
		
		
		<fieldset>
			<legend>根据模板利用AbstractPdfStamperView返回PDF文档</legend>
			<form action="${request.contextPath}/demo/exportPdfByView.htm" method="post">
				<p>
					<label>姓名：</label><input type="text" name="name" placeholder="迈克-Mike" >
				</p>
				<p>
					<label>性别：</label><input type="text" name="gender" placeholder="男-Male">
				</p>
				<p>
					<input type="submit" value="根据模板利用AbstractPdfStamperView返回PDF文档">
				</p>
			</form>
		</fieldset>
		
	</body>
</html>