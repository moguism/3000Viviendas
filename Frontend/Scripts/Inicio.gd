extends Control

@onready var contenedor : GridContainer = $Contenedor
@onready var address_edit : TextEdit = $Address
@onready var name_edit : TextEdit = $Name

@onready var http_request : HTTPRequest = $HTTPRequest
const URL : String = "http://127.0.0.1:8080/api/"
var pagina : String = "comunidades"

func _ready():
	conexion()

func _on_http_request_request_completed(result, response_code, headers, body):
	print("El código de respuesta fue: ", response_code)
	if result == 0:
		var datos = JSON.parse_string(body.get_string_from_utf8())
		print(datos)

		if response_code == 200:
			for dato in datos:
				print("Siguiente dato:")
				print(dato)

				var label_id : Button = Button.new()
				var label_nombre : Label = Label.new()
				var label_direccion : Label = Label.new()

				label_id.text = str(dato.id)
				label_id.button_up.connect(
					func prueba():
						Singleton.comunidad_id = label_id.text
						get_tree().change_scene_to_file("res://Escenas/Comunidad.tscn")
				)

				label_nombre.text = str(dato.nombre)
				label_direccion.text = str(dato.direccion)

				contenedor.add_child(label_id)
				contenedor.add_child(label_nombre)
				contenedor.add_child(label_direccion)
		elif response_code == 201:
			var label_id : Button = Button.new()
			var label_nombre : Label = Label.new()
			var label_direccion : Label = Label.new()
			
			label_id.text = str(datos.id)
			label_id.button_up.connect(cambiar_escena)

			label_nombre.text = str(datos.nombre)
			label_direccion.text = str(datos.direccion)

			contenedor.add_child(label_id)
			contenedor.add_child(label_nombre)
			contenedor.add_child(label_direccion)

	else:
		print("No se puede conectar")
		
func conexion() -> void:
	http_request.request(URL + pagina)

func cambiar_escena() -> void:
	Singleton.comunidad_id = contenedor.get_node("Comunidad").text
	get_tree().change_scene_to_file("res://Escenas/Comunidad.tscn")

func _on_enviar_button_down():
	if name_edit.text != "" && address_edit.text != "":
		var data_to_send = {
			"nombre": name_edit.text,
			"direccion": address_edit.text
		}
		var json = JSON.stringify(data_to_send)
		var headers = ["Content-Type: application/json"]
		http_request.request(URL + pagina, headers, HTTPClient.METHOD_POST, json)
