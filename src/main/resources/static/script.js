
const labels = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre']

const graph = document.querySelector("#grafica");


var fecha = document.querySelectorAll("#fecha > span");
let contador = 0;
let contadorEnero = 0;
let contadorFebrero = 0;
let contadorMarzo = 0;
let contadorAbril = 0;
let contadorMayo = 0;
let contadorJunio = 0;
let contadorJulio = 0;
let contadorAgosto = 0;
let contadorSeptiembre = 0;
let contadorOctubre = 0;
let contadorNoviembre = 0;
let contadorDiciembre = 0;

fecha.forEach(function(element) {
	let fechas = [element.innerHTML];
	contador = contador + 1;

	const f = new String(fechas);
	let str = f;

	if ((str[5] == 0) && (str[6] == 1)) {
		contadorEnero = contadorEnero + 1;
	}
	if ((str[5] == 0) && (str[6] == 2)) {
		contadorFebrero = contadorFebrero + 1;
	}
	if ((str[5] == 0) && (str[6] == 3)) {
		contadorMarzo = contadorMarzo + 1;
	}
	if ((str[5] == 0) && (str[6] == 4)) {
		contadorAbril = contadorAbril + 1;
	}
	if ((str[5] == 0) && (str[6] == 5)) {
		contadorMayo = contadorMayo + 1;
	}
	if ((str[5] == 0) && (str[6] == 6)) {
		contadorJunio = contadorJunio + 1;
	}
	if ((str[5] == 0) && (str[6] == 7)) {
		contadorJulio = contadorJulio + 1;
	}
	if ((str[5] == 0) && (str[6] == 8)) {
		contadorAgosto = contadorAgosto + 1;
	}
	if ((str[5] == 0) && (str[6] == 9)) {
		contadorSeptiembre = contadorSeptiembre + 1;
	}
	if ((str[5] == 1) && (str[6] == 0)) {
		contadorOctubre = contadorOctubre + 1;;
	}
	if ((str[5] == 1) && (str[6] == 1)) {
		contadorNoviembre = contadorNoviembre + 1;
	}
	if ((str[5] == 1) && (str[6] == 2)) {
		contadorDiciembre = contadorDiciembre + 1;
	}
	

});
console.log(contador);
console.log(contadorEnero);
console.log(contadorFebrero);
console.log(contadorMarzo);
console.log(contadorAbril);
console.log(contadorMayo);
console.log(contadorJunio);
console.log(contadorJulio);
console.log(contadorAgosto);
console.log(contadorSeptiembre);
console.log(contadorOctubre);
console.log(contadorNoviembre);
console.log(contadorDiciembre);




const dataset1 = {
	label: "Solicitudes",
	data: [contadorEnero, contadorFebrero, contadorMarzo, contadorAbril, contadorMayo, contadorJunio, contadorJulio, contadorAgosto, contadorSeptiembre, contadorOctubre, contadorNoviembre, contadorDiciembre],
	            backgroundColor: 'rgba(161, 198, 247, 1)',
            borderColor: 'rgb(47, 128, 237)',
	fill: true,
	tension: 0.5
};
const data = {
	labels: labels,
	datasets: [dataset1]
};

const config = {
	type: 'line',
	data: data,
};

new Chart(graph, config);

		
