import type ITipoContrato from "@/interfaces/ITipoContrato";

export default class TipoContratoService {

    baseUri: string = "http://127.0.0.1:8080/api/tipos-contrato"

    async listAllTiposContrato(): Promise<Array<ITipoContrato>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listTipoContratoById(id: number): Promise<ITipoContrato> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createTipoContrato(name: string) {
        const data:ITipoContrato = {
            id: 0,
            nombre: name
        }
        const rawResponse = await fetch(this.baseUri, {
            method: 'POST',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

    async deleteTipoContrato(id: number){
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'DELETE'
        })
        const response = await rawResponse.json()
        return response
    }

    async updateTipoContrato(id: number, name: string) {
        const data:ITipoContrato = {
            id: id,
            nombre: name
        }
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri, {
            method: 'PUT',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

}