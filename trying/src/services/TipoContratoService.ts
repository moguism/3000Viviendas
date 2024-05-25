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
            headers: {
                'Content-Type': 'application/json'
            },
            mode: 'cors',
            body: JSON.stringify(data)
        })
        const response = await rawResponse.json()
        return response
    }

    async deleteTipoContrato(id: number){
        const uri = `${this.baseUri}/${id}`
        await fetch(uri, {
            method: 'DELETE'
        })
    }

    async updateTipoContrato(id: number, name: string) {
        const data:ITipoContrato = {
            id: id,
            nombre: name
        }
        const uri = `${this.baseUri}/${id}`
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