var omp=(function(){
	var intiModule;
	intiModule=function(blNameSpace){
		eval("omp."+blNameSpace+".intiModule($('.page'));");
	};
	return {intiModule:intiModule};
});