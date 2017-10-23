/*------------------------------------------------------------------------------
* created by yzh 2003.5.12
* 模拟简单HashMap类
* 请大家引用时保留这段作者声明，此代码为开源代码；使用不受限制，欢迎大家采用本人所写JS动态拖动表格实现代码。
*  修改履历：
*  2002.02.10 version 1.0
*  2003.03.10 version 2.0
*  2004.05.10 version 3.0

*   模拟简单HashMap类，要求key是String，value可以是任何类型
*   方法列表：
*     1，HashMap() : 构造函数
*     2, put(key, value) : void
*     3, get(key) : Object
*     4, keySet() : Array
*     5, values() : Array
*     6, size() : int
*     7, clear() : void
*     8, isEmpty() : boolean
*     9, containsKey(key) : boolean
*    10, containsValue(value) : boolean //如果value是Object类型或者数组类型，存在bug
*    11, putAll(map) : void
*    12, remove(key) : void
*    用法：
*      var map = new JHashMap();   
*      map.put("one","一只小猪");
*      map.put("two","两只小猪");
*      map.put("three","三只小猪");
* 
*      print("[ toString() ] : " + map);
*      print("[ get() ] :  " + map.get("one"));
*      print("[ keySet() ] :  " + map.keySet());
*      print("[ values() ] :  " + map.values());
*      print("[ size() ] :  " + map.size());
*      print("[ isEmpty() ] :  " + map.isEmpty());
*      print("[ containsKey() ] :  " + map.containsKey("one"));
*      print("[ containsValue() ] :  " + map.containsValue("三只小猪"));
*  
*      //putAll
*      var mapTemp = new JHashMap();
*      mapTemp.put("four","四只小猪");
*      mapTemp.put("five","五只小猪");
*      map.putAll(mapTemp);
*      print("[ putAll() ] :  " + map);
*  
*      //remove
*      map.remove("two");
*      map.remove("one");
*      print("[ remove() ] :  " + map);
*  
*      //clear
*      map.clear()
*      print("[ clear() ] :  " + map.size());
*      //辅助方法
*      function print(msg)
*      {
*         document.write(msg + "");
*      }

*
-------------------------------------------------------------------------------*/
/*
 * HashMap version 3.0
 * HashMap构造函数
*/
function JHashMap()
{
    this.length = 0;
    this.prefix = "hashmap_prefix_20040716_";
}
/**
 * 向HashMap中添加键值对
 */
JHashMap.prototype.put = function (key, value)
{
    this[this.prefix + key] = value;
    this.length ++;
}
/**
 * 从HashMap中获取value值
 */
JHashMap.prototype.get = function(key)
{
    return typeof this[this.prefix + key] == "undefined"
            ? null : this[this.prefix + key];
}
/**
 * 从HashMap中获取所有key的集合，以数组形式返回
 */
JHashMap.prototype.keySet = function()
{
    var arrKeySet = new Array();
    var index = 0;
    for(var strKey in this)
    {
        if(strKey.substring(0,this.prefix.length) == this.prefix)
            arrKeySet[index ++] = strKey.substring(this.prefix.length);
    }
    return arrKeySet.length == 0 ? null : arrKeySet;
}
/**
 * 从HashMap中获取value的集合，以数组形式返回
 */
JHashMap.prototype.values = function()
{
    var arrValues = new Array();
    var index = 0;
    for(var strKey in this)
    {
        if(strKey.substring(0,this.prefix.length) == this.prefix)
            arrValues[index ++] = this[strKey];
    }
    return arrValues.length == 0 ? null : arrValues;
}
/**
 * 获取HashMap的value值数量
 */
JHashMap.prototype.size = function()
{
    return this.length;
}
/**
 * 删除指定的值
 */
JHashMap.prototype.remove = function(key)
{
    delete this[this.prefix + key];
    this.length --;
}
/**
 * 清空HashMap
 */
JHashMap.prototype.clear = function()
{
    for(var strKey in this)
    {
        if(strKey.substring(0,this.prefix.length) == this.prefix)
            delete this[strKey];  
    }
    this.length = 0;
}
/**
 * 判断HashMap是否为空
 */
JHashMap.prototype.isEmpty = function()
{
    return this.length == 0;
}
/**
 * 判断HashMap是否存在某个key
 */
JHashMap.prototype.containsKey = function(key)
{
    for(var strKey in this)
    {
       if(strKey == this.prefix + key)
          return true; 
    }
    return false;
}
/**
 * 判断HashMap是否存在某个value
 */
JHashMap.prototype.containsValue = function(value)
{
    for(var strKey in this)
    {
       if(this[strKey] == value)
          return true; 
    }
    return false;
}
/**
 * 把一个HashMap的值加入到另一个HashMap中，参数必须是HashMap
 */
JHashMap.prototype.putAll = function(map)
{
    if(map == null)
        return;
    if(map.constructor != JHashMap)
        return;
    var arrKey = map.keySet();
    var arrValue = map.values();
    for(var i in arrKey)
       this.put(arrKey[i],arrValue[i]);
}
//toString
JHashMap.prototype.toString = function()
{
    var str = "";
    for(var strKey in this)
    {
        if(strKey.substring(0,this.prefix.length) == this.prefix)
              str += strKey.substring(this.prefix.length)
                  + " : " + this[strKey] + "\r\n";
    }
    return str;
}