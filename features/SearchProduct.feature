Feature: Prueba de busqueda de productos
	
	Scenario Outline: Search
		Given Abrimos el navegador e ingresamos a EBAY
		When ingresamos los zapatos "<marca>" de tamanio "<tamanio>"
		Then imprimimos resultados
		And ordenar por precio ascendente
		And obtener "<cantidad>" productos
		And se debe cerrar el navegador
	Examples:
		|marca|tamanio|cantidad|
		|PUMA|10|5|
		|Nike|8.5|5|