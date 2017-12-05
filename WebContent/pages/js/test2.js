

$(document).ready(function() {
var modelSwitchStateToggle=true;	
console.log(modelSwitchStateToggle);
test2();


var arr=parseInt("32")+1;
console.log(arr+"----");
var XX=$;
XX("#div1").html("x1212xxxxx");
///////////////////////////////////////////////////////////////////
  function Animal(){
	  this.name="animal";
	  this.showName=function(){
		//  alert(this.name);
	  }
  }
  
  function Cat(){
	  this.name="cat";
  }

  var animal=new Animal();
  var cat=new Cat();
  animal.showName();
  animal.showName.call(cat);
  
  
  
  
  window.color = 'red';
  document.color = 'yellow';
  document.name = '黄色';
  var s1 = {color: 'blue' ,name:'绿色'};
  function changeColor(){
      console.log(this.color+"--"+(this.name==undefined?"不存在":this.name));
  }
  changeColor.call();         //red (默认传递参数)
  changeColor.call(window);   //red
  changeColor.call(document); //yellow
  changeColor.call(this);     //yellow
  changeColor.call(s1);       //blue
  
  changeColor.apply();         //red (默认传递参数)
  changeColor.apply(window);   //red
  changeColor.apply(document); //yellow
  changeColor.apply(this);     //yellow
  changeColor.apply(s1);       //blue

  function Pet(words){
      this.words = words;
      this.speak = function () {
          console.log( this.words)
      }
  }
  function Dog(words){
      //Pet.call(this, words); //结果： Wang
     Pet.apply(this, arguments);  //Pet.apply(this, [words]);//结果： Wang
  }
  var dog = new Dog('Wang');
  dog.speak();

  
  function add(c,d){
      return this.a + this.b + c + d;
  }
  var s = {a:1, b:2};
  console.log(add.call(s,3,4)); // 1+2+3+4 = 10
  console.log(add.apply(s,[5,6])); // 1+2+5+6 = 14
  
  
  window.firstName = "Cynthia"; 
  window.lastName = "_xie";

  var myObject = {firstName:'my', lastName:'Object'};

  function getName(){
      console.log(this.firstName + this.lastName);
  }

  function getMessage(sex,age){
      console.log(this.firstName + this.lastName + " 性别: " + sex + " age: " + age );
  }

  getName.call(window); // Cynthia_xie
  getName.call(myObject); // myObject

  getName.apply(window); // Cynthia_xie
  getName.apply(myObject);// myObject

  getMessage.call(window,"女",21); //Cynthia_xie 性别: 女 age: 21
  getMessage.apply(window,["女",21]); // Cynthia_xie 性别: 女 age: 21

  getMessage.call(myObject,"未知",22); //myObject 性别: 未知 age: 22
  getMessage.apply(myObject,["未知",22]); // myObject 性别: 未知 age: 22
  
  
  for (let i=0;i<10;i++){
	  console.log(i);
  }
  console.log("===="+i);
  
  
//////////////////////////////////////////////////////






});

var image =new Image();
//alert(navigator.appName);
//alert(navigator.userAgent);
function test2(){
	modelSwitchStateToggle=false;
	console.log("test2="+modelSwitchStateToggle);
}

!(function a(a){
	  alert(a);
}(12121));
