import type ILocal from "@/interfaces/ILocal";

export default class LocalService {

    baseUri: string = "http://127.0.0.1:8080/api/locales"

    async listAllLocales(): Promise<Array<ILocal>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listLocalById(id: number): Promise<ILocal> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createLocal(nombre: string, comunidad_id: number, tipo_local_id: number){
        const data:ILocal = {
            id: 0,
            nombre: nombre,
            comunidad_id: comunidad_id,
            tipo_local_id: tipo_local_id
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

    async deleteLocal(id: number){
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'DELETE'
        })
        const response = await rawResponse.json()
        return response
    }

    async updateLocal(id: number, nombre: string, comunidad_id: number, tipo_local_id: number){
        const uri = `${this.baseUri}/${id}`
        const data:ILocal = {
            id: 0,
            nombre: nombre,
            comunidad_id: comunidad_id,
            tipo_local_id: tipo_local_id
        }
        const rawResponse = await fetch(uri, {
            method: 'PUT',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

}