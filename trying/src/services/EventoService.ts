import type IEvento from "@/interfaces/IEvento";

export default class EventoService {

    baseUri: string = "http://127.0.0.1:8080/api/eventos"

    async listAllEventos(): Promise<Array<IEvento>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listEventoById(id: number): Promise<IEvento> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createEvento(nombre: string){
        const data:IEvento = {
            id: 0,
            nombre: nombre,
        }
        const rawResponse = await fetch(this.baseUri, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            mode: 'cors',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

    async deleteEvento(id: number){
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'DELETE'
        })
        const response = await rawResponse.json()
        return response
    }

    async updateEvento(id: number, nombre: string){
        const uri = `${this.baseUri}/${id}`
        const data:IEvento = {
            id: 0,
            nombre: nombre,
        }
        const rawResponse = await fetch(uri, {
            method: 'PUT',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

}