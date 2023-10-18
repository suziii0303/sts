<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예린 restful 클라이언트</title>
</head>
<body>
<div id="disp"></div>
</hr>
<form>
    타이틀 <input type="text" name="title"><br>
    이름 <input type="text" name="sname"><br>
    내용 <textarea name="scont" ></textarea></br>
    <!-- button 태그는 form태그 안에 있음 기본 type이 submit이 됨-->
    <button type="button" onclick="fSearch()">조회</button>
    <button type="button" onclick="fModify()">수정</button>
    <button type="button" onclick="fDel()">삭제</button>
    <button type="button" onclick="fInsert()">입력</button>

</form>
<script>
    //입력
    function fInsert(){
        // 넘겨야 할 data
        let yerinVO = {
            title: myTitle.value,
            sname: myName.value,
            scont: myCont.value
        }
        // 아작스
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "/merong/rest/yerin", true); //
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.onreadystatechange = function(){
            if (xhr.readyState == 4 && xhr.status == 200){
                if(xhr.responseText){
                    alert("잘 입력 되었습니다");
                    getList(); // 리스트를 다시 뿌리기
                }else{
                    alert("입력되지 않았습니다.")
                }
            }
        }
        xhr.send(JSON.stringify(yerinVO)); // 문자열 (json형식의 문자열)
    }

    //타이틀로 조회
    function fSearch(){
            let xhr = new XMLHttpRequest();
            let schURL =`/merong/rest/yerin/\${myTitle.value}`
            xhr.open("get",schURL,true);
            xhr.onreadystatechange = function(){
                if(xhr.readyState == 4 && xhr.status == 200){
                    console.log("체크",xhr.responseText);
                    let yerinVO = xhr.responseText;
                    if(yerinVO){
                        yerinVO =JSON.parse(yerinVO); //json문자열을 json객체로
                        myName.value = yerinVO.sname;
                        myCont.value = yerinVO.scont;
                    }else{
                        alert("찾는 타이틀 없음");
                    }
                }
            }
            xhr.send(); 
        }


    function fDel(){
        let xhr = new XMLHttpRequest();
        let delURL =`/merong/rest/yerin/\${myTitle.value}`
        xhr.open("delete",delURL,true);
        xhr.onreadystatechange = function(){
            if(xhr.readyState == 4 && xhr.status == 200){
                console.log("체크",xhr.responseText);
                getList();//리스트를 다시 뿌리기
            }
        }
        xhr.send(); // 문자열 (json형식의 문자열)
    }

    function fModify(){
        // 넘겨야 할 data
        let yerinVO = {
            title:myTitle.value,
            sname:myName.value,
            scont:myCont.value

        }

        let xhr = new XMLHttpRequest();
        xhr.open("put","/merong/rest/yerin",true);
        xhr.setRequestHeader("Content-Type","application/json;charset=UTF-8");
        xhr.onreadystatechange = function(){
            if(xhr.readyState == 4 && xhr.status == 200){
                console.log("체크",xhr.responseText);
                getList();//리스트를 다시 뿌리기
            }
        }
        xhr.send(JSON.stringify(yerinVO)); // 문자열 (json형식의 문자열)
    }
    const myList = document.querySelector("#disp");
    const myTitle =document.querySelector("input[name=title]");
    const myName =document.querySelector("input[name=sname]");
    const myCont =document.querySelector("textarea[name=scont]");
    //TR 마우스 오버
    function fmover(pthis){
        pthis.style.backgroundColor ="black";
        pthis.style.color="yellow";
    }
    //TR 마우스 아웃
    function fout(pthis){
        pthis.style.backgroundColor ="white";
        pthis.style.color="black";
    }
    function fmclick(pthis){
        myTitle.value = pthis.children[0].innerHTML;
        myName.value = pthis.children[1].innerHTML;
        myCont.value = pthis.children[2].innerHTML;
}

    // 리스트 가져다 뿌리기
    const getList= ()=>{
        let xhr =new XMLHttpRequest();
        xhr.open("get","/merong/rest/yerins",true);
        xhr.onreadystatechange=() =>{
            if(xhr.readyState == 4&&xhr.status == 200){
                console.log("항상체크",xhr.responseText);//주고받는건 항상 문자열
                //json문자열을 json객체로 바꿔야 편하게 쓸 수 있음
                let rslt =JSON.parse(xhr.responseText);

                let tblStr ="<table border=1>";
                tblStr += "<tr><th>타이틀</th><th>이름</th><th>내용</th></tr>"
                for(let i=0; i<rslt.length; i++){
                    tblStr +="<tr onmouseover=fmover(this) onmouseout=fout(this) onclick=fmclick(this)>";
                    tblStr +=`<td>\${rslt[i].title}</td>`;
                    tblStr +=`<td>\${rslt[i].sname}</td>`;
                    tblStr +=`<td>\${rslt[i].scont}</td>`;
                    tblStr +="</tr>";
                }
                tblStr += "</table>";
                myList.innerHTML=tblStr;
                console.log(JSON.parse(xhr.responseText));

            }
        }
        xhr.send();

    }
    getList();
</script>

</body>
</html>