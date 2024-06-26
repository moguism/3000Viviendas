import type ICommunity from "@/interfaces/ICommunity";
import type ILocal from "@/interfaces/ILocal";
import type ITipoLocal from "@/interfaces/ITipoLocal";

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

    async createLocal(nombre: string, comunidad: ICommunity, tipo_local: ITipoLocal){
        const data:ILocal = {
            id: 0,
            nombre: nombre,
            comunidad: comunidad,
            tipoLocal: tipo_local,
            nombreTipo: tipo_local.nombre
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
        await fetch(uri, {
            method: 'DELETE'
        })
    }

    async updateLocal(id: number, nombre: string, comunidad: ICommunity, tipo_local: ITipoLocal){
        const uri = `${this.baseUri}/${id}`
        const data:ILocal = {
            id: 0,
            nombre: nombre,
            comunidad: comunidad,
            tipoLocal: tipo_local,
            nombreTipo: tipo_local.nombre
        }
        const rawResponse = await fetch(uri, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

}