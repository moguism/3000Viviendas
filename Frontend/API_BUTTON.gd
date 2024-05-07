extends Button

@onready var contenedor_datos : VBoxContainer = get_parent().get_node("Datos")

@onready var http_request : HTTPRequest = $HTTPRequest
const URL : String = "http://127.0.0.1:8080"
var pagina : String = "/api/comunidades"

func _on_pressed():
	http_request.request(URL + pagina)

func _on_http_request_request_completed(result, response_code, headers, body):
	if result == 0:
		var datos = JSON.parse_string(body.get_string_from_utf8())
		print(datos)

		contenedor_datos.get_node("Id").text = str("Id: ", datos[0].id)
		contenedor_datos.get_node("Nombre").text = str("Nombre: ", datos[0].nombre)
		contenedor_datos.get_node("Direccion").text = str("Direccion: ", datos[0].direccion)
	else:
		print("No se puede conectar")
