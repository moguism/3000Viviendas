import type IDeuda from "@/interfaces/IDeuda";
import type ITipoDeuda from "@/interfaces/ITipoDeuda";

export default class TipoDeudaService {

    baseUri: string = "http://127.0.0.1:8080/api/tipos-deuda"

    async listAllTiposDeuda(): Promise<Array<ITipoDeuda>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listTipoDeudaById(id: number): Promise<ITipoDeuda> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createTipoDeuda(name: string, deudas : Array<IDeuda>) {
        const data:ITipoDeuda = {
            id: 0,
            nombre: name,
            deudas : deudas
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

    async deleteTipoDeuda(id: number){
        const uri = `${this.baseUri}/${id}`
        await fetch(uri, {
            method: 'DELETE'
        })
    }

    async updateTipoDeuda(id: number, name: string, deudas : Array<IDeuda>) {
        const data:ITipoDeuda = {
            id: id,
            nombre: name,
            deudas : deudas
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