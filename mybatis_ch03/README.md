# 项目说明

> 此项目用于对mybatis的编写

## spring 注解
> `@RequestBody`：前台传入的参数必须为JSON格式的字符串，且后台只能是一个Map对象，用来接收前台参数

> `@PathVariable`：前台传入的参数的值直接写在请求后面，形如：`http://localhost:9999/tb2/selectOne/1` 其中`1`就是后台所需参数`id`值

> `@RequestParam`：前台按照指定名称传入对应的参数


## 前台通过Ajax方式向后台服务器发起请求时需注意以下几点：

> 请求格式：

```js
let obj = {a:111,b:222}

$.ajax({
url:'http:localhost:9999/tb2/selectOne/1',
type:'post',
dataType:'json', //对象
//data:JSON.stringify(obj), //向后台传入的参数
data:obj, //
contentType:'application/json', //
success:function(data){
console.log(data.id);
}
});
```

1. 参数`dataType`是对后台返回结果的数据设置，如：`json`，`xml`，`text`，`html`，`jsonp`，`script`，[参考链接 Data Types（数据类型）](https://www.jquery123.com/jQuery.ajax/)
2. 参数`contentType`等于设置请求的`Headers`中的`Content-Type`参数值
