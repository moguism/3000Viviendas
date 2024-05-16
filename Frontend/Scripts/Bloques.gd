extends Control

@onready var contenedor : GridContainer = $Contenedor

@onready var http_request : HTTPRequest = $HTTPRequest
const URL : String = "http://127.0.0.1:8080"
var pagina : String = str("/api/bloques/", Singleton.comunidad_id)

func _ready():
	print(pagina)
	http_request.request(URL + pagina)

func _on_http_request_request_completed(result, response_code, headers, body):
	if result == 0:
		var datos = JSON.parse_string(body.get_string_from_utf8())
		print(datos)

		for dato in datos:
			print("mongo")
			print(dato)
			
			var label_id : Button = Button.new()
			var label_comunidad_id : Label = Label.new()

			label_id.text = str(dato.id)
			label_comunidad_id.text = str(dato.nombre)

			contenedor.add_child(label_id)
			contenedor.add_child(label_comunidad_id)

	else:
		print("No se puede conectar")
