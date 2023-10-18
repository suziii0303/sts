<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/gridstack.js/0.6.2/gridstack.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/gridstack.js/0.6.2/gridstack.min.js"></script>

<script type="text/javascript">
//HTML에서 그리드 스택 요소를 선택합니다.
var gridElement = document.getElementById('grid');

// Grid Stack을 초기화합니다.
var options = {
  // 여기에 옵션을 설정할 수 있습니다.
};

var grid = GridStack.init(options, gridElement);

grid.addWidget({ x: 0, y: 0, width: 2, height: 2, content: '내용' });

//다른 아이템 추가
grid.addWidget({ x: 2, y: 0, width: 1, height: 1, content: '다른 내용' });
</script>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="grid"></div>
</body>
</html>