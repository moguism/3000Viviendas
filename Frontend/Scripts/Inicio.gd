extends Control

@onready var contenedor : GridContainer = $Contenedor

@onready var http_request : HTTPRequest = $HTTPRequest
const URL : String = "http://127.0.0.1:8080"
var pagina : String = "/api/comunidades"

func _ready():
	http_request.request(URL + pagina)

func _on_http_request_request_completed(result, response_code, headers, body):
	if result == 0:
		var datos = JSON.parse_string(body.get_string_from_utf8())

		for dato in datos:
			print("mongo")
			print(dato)

			var label_id : Button = Button.new()
			var label_nombre : Label = Label.new()
			var label_direccion : Label = Label.new()

			label_id.text = str(dato.id)
			label_id.button_up.connect(cambiar_escena)

			label_nombre.text = str(dato.nombre)
			label_direccion.text = str(dato.direccion)

			contenedor.add_child(label_id)
			contenedor.add_child(label_nombre)
			contenedor.add_child(label_direccion)
			
	else:
		print("No se puede conectar")

func cambiar_escena() -> void:
	Singleton.comunidad_id = contenedor.get_node("Comunidad").text
	get_tree().change_scene_to_file("res://Bloques.tscn")
