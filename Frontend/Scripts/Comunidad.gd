extends Control

@onready var center_container : CenterContainer = $CenterContainer
@onready var contenedor : GridContainer = $CenterContainer/GridContainer

@onready var http_request : HTTPRequest = $HTTPRequest
const URL : String = "http://127.0.0.1:8080/api/"
var pagina : String = str("comunidades/", Singleton.comunidad_id)

var prueba : bool = false

func _ready():
	print(URL + pagina)
	http_request.request(URL + pagina)

func _on_http_request_request_completed(result, response_code, headers, body):
	if prueba == true && response_code < 400:
		prueba = false
		pagina = str("comunidades/", Singleton.comunidad_id)
		http_request.request(URL + pagina)

	print("El código de respuesta fue: ", response_code)
	if result == 0:
		var datos = JSON.parse_string(body.get_string_from_utf8())
		print(datos)
		
		if response_code == 200:
			for dato in datos.bloques:
				print("Siguiente dato:")
				print(dato)

				var label_id : Button = Button.new()
				label_id.text = str(dato.id)

				contenedor.add_child(label_id)

			for ingresos in datos.ingresos: # TODO: Ponerlo por pantalla también
				print("Siguiente ingreso:")
				print(ingresos)
		elif response_code == 201:
			var label_id : Button = Button.new()
			label_id.text = str(datos.id)
			contenedor.add_child(label_id)
	else:
		print("No se puede conectar")


func _on_enviar_button_down():
	var data_to_send = {
		"comunidad_id": Singleton.comunidad_id,
	}
	var json = JSON.stringify(data_to_send)
	print(json)
	var headers = ["Content-Type: application/json"]
	pagina = "bloques"
	prueba = true
	http_request.request(URL + pagina, headers, HTTPClient.METHOD_POST, json)
