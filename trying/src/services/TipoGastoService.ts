import type IGasto from "@/interfaces/IGasto";
import type ITipoGasto from "@/interfaces/ITipoGasto";

export default class TipoGastoService {

    baseUri: string = "http://127.0.0.1:8080/api/tipos-gasto"

    async listAllTiposGasto(): Promise<Array<ITipoGasto>> {
        const rawResponse = await fetch(this.baseUri)
        const response = await rawResponse.json()
        return response
    }

    async listTipoGastoById(id: number): Promise<ITipoGasto> {
        const uri = `${this.baseUri}/${id}`
        const rawResponse = await fetch(uri)
        const response = await rawResponse.json()
        return response
    }

    async createTipoGasto(name: string, gastos: Array<IGasto>) {
        const data:ITipoGasto = {
            id: 0,
            nombre: name,
            gastos: gastos
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

    async deleteTipoGasto(id: number){
        const uri = `${this.baseUri}/${id}`
        await fetch(uri, {
            method: 'DELETE'
        })
    }

    async updateTipoGasto(id: number, name: string, gastos: Array<IGasto>) {
        const data:ITipoGasto = {
            id: id,
            nombre: name,
            gastos: gastos
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
