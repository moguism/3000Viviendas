extends Control

@onready var contenedor : GridContainer = $Contenedor

@onready var http_request : HTTPRequest = $HTTPRequest
const URL : String = "http://127.0.0.1:8080/api/comunidades/"
var pagina : String = str(Singleton.comunidad_id)

func _ready():
	print(URL+pagina)
	http_request.request(URL + pagina)

func _on_http_request_request_completed(result, response_code, headers, body):
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
			pass

	else:
		print("No se puede conectar")
