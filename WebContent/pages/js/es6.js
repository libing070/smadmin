fun=(x) => {
    if (x > 0) {
        return 1;
    }
    return 2;
}
console.log(fun(2));

const s = new Set();
const m=new Map();
m.set('a','111');
console.log(m.get('a')+"-----");
[2, 3, 5, 4, 5, 2, 2].forEach(x => s.add(x));
for (let i of s) {
  console.log("i="+i);
}

let con=100;
con=200;
console.log(con);

funDemo=(x=1,y=2,z=3)=>{
	x=5,y=2,z=1;
	return x+y+z;
}
console.log(funDemo());


foo1=(...args)=>{
	console.log(args);
}

// let a={1,2,3,4,5};
// foo1(a);
foo1(1,2,3,4,5,6,7,8);


getCar=(make,model,value)=>{
	return {
		make,
		model,
		value,
		['make'+make]:true,
		depreciate() {
		      this.value += 2500;
		    }
	}
}
let car=getCar('BMW','lee',4000);
console.log(car);
let oValue = 0o10;
console.log(oValue); // 8


function foo() {
	  return [1,2,3];
	}
	let arr = foo(); // [1,2,3]
	 
	let [a, b, c] = foo();
	console.log(a, b, c); // 1 2 3
	 
	function bar() {
	  return {
	    x: 4,
	    y: 5,
	    z: 6
	  };
	}
	let {x: x, y: y, z: z} = bar();
	console.log(x, y, z); // 4 5 6
	
	
	var user='libing';
	console.log(`hi ${user}!`);
	
	let nickNames=['AA','BB','CC'];
	nickNames.size=3;
	for(let nickName of nickNames){
		console.log(`${nickName}`);
	}
	for(let nickName in nickNames){
		console.log(nickName);
	}