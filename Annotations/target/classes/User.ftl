<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
<form action="${form.action}" method="${form.method}">
    <#list form.inputs as input>
        <div>
            <input type="${input.type}" name="${input.name}" placeholder="${input.placeholder}">
        </div>
    </#list>
    <input type="submit" value="Отправить">
</form>
</body>
</html>